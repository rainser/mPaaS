package com.ibyte.sys.job.support.db;

import com.ibyte.common.core.constant.QueryConstant;
import com.ibyte.common.core.dto.MechanismDTO;
import com.ibyte.common.core.dto.QueryRequest;
import com.ibyte.common.core.entity.IEntity;
import com.ibyte.common.core.query.PageQueryTemplate;
import com.ibyte.common.core.util.EntityUtil;
import com.ibyte.common.util.ReflectUtil;
import com.ibyte.common.util.StringHelper;
import com.ibyte.framework.meta.Meta;
import com.ibyte.framework.support.ApplicationContextHolder;
import com.ibyte.sys.job.api.entity.JobQueueEntity;
import com.ibyte.sys.job.support.db.util.JobQueueUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 任务队列持久化服务
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Service
@RestController
@RequestMapping(DbJobQueuePersistenceServiceImpl.APIPATH)
@Transactional(readOnly = true, rollbackFor = {})
@Slf4j
public class DbJobQueuePersistenceServiceImpl implements DbJobQueuePersistenceService {
    /** API路径 */
    static final String APIPATH = "/api/sys-job/sysJobQueuePersistenceService";
    /** 远程调用重试时长 */
    static final long REMOTE_RETRY_TIME = 60000;

    @Autowired
    private EntityManager entityManager;

    // ========== 生产者专用，若不同数据库，则采用远程调用方式分发 ==========
    /**
     * 批量保存
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {})
    public void saveAll(List<JobQueueEntity> entities) {
        Map<String, List<JobQueueEntity>> remotes = new HashMap<>();
        // 本地保存
        for (JobQueueEntity entity : entities) {
            String jobType = EntityUtil.getEntityClassName(entity);
            if (JobQueueUtil.isLocalDb(jobType)) {
                entityManager.persist(entity);
            } else {
                String appName = JobQueueUtil.getJobAppName(jobType);
                List<JobQueueEntity> list = remotes.get(appName);
                if (list == null) {
                    list = new ArrayList<>();
                    remotes.put(appName, list);
                }
                list.add(entity);
            }
        }
        // 远程保存
        if (remotes.isEmpty()) {
            return;
        }
        for (Map.Entry<String, List<JobQueueEntity>> entry : remotes.entrySet()) {
            ApplicationContextHolder.getRemoteApi(entry.getKey(), APIPATH,
                    DbJobQueuePersistenceService.class)
                    .saveAll(entry.getValue());
        }
    }

    // ========== 分发者专用，本地调用 ==========
    @Override
    public List<String> loadJobIds(JobQueueIdQuery query) {
        if (JobQueueUtil.isLocalDb(query.getJobType().getName())) {
            return createQuery(query.getJobType(), String.class,
                    new String[] { "fdId" }, query.getTopic(),
                    query.getFromTime());
        } else {
            return remoteCall(query.getJobType().getName(), (service) -> {
                return service.loadJobIds(query);
            });
        }
    }

    @Override
    public List<String> loadJobLockIds(JobQueueIdQuery query) {
        if (JobQueueUtil.isLocalDb(query.getJobType().getName())) {
            List<Object[]> list = createQuery(query.getJobType(),
                    Object[].class, new String[] { "fdId", "fdLockId" },
                    query.getTopic(), query.getFromTime());
            List<String> result = new ArrayList<>(list.size());
            for (Object[] item : list) {
                if (item[1] == null || item[1].equals(item[0])) {
                    result.add((String) item[0]);
                } else {
                    result.add(StringHelper.join(item[0], ";", item[1]));
                }
            }
            return result;
        } else {
            return remoteCall(query.getJobType().getName(), (service) -> {
                return service.loadJobLockIds(query);
            });
        }
    }

    /**
     * 加载待处理任务ID列表
     */
    private <E extends IEntity, V> List<V> createQuery(Class<E> entityClass,
                                                       Class<V> viewClass, String[] fields, String topic, long fromTime) {
        QueryRequest request = new QueryRequest();
        request.addColumn(fields);
        if (StringUtils.isNotBlank(topic)) {
            request.addCondition("fdTopic", topic);
        }
        if (fromTime > 0) {
            request.addCondition("fdId", QueryConstant.Operator.gt,
                    Long.toUnsignedString(fromTime, 32));
        }
        request.addSort("fdId", QueryConstant.Direction.ASC);
        request.setCount(false);
        request.setPageSize(DbJobQueueConstant.QUERY_SIZE);
        PageQueryTemplate<E, V> template = new PageQueryTemplate<>(
                entityManager, entityClass, viewClass);
        template.setFilterTenant(false);
        return template.findAll(request).getContent();
    }

    // ========== 执行者专用，若不同数据库，则采用远程调用方式分发 ==========
    /**
     * 根据ID查找
     */
    @Override
    public JobQueueEntity findById(MechanismDTO dto) {
        if (JobQueueUtil.isLocalDb(dto.getFdEntityName())) {
            return (JobQueueEntity) entityManager.find(
                    ReflectUtil.classForName(dto.getFdEntityName()),
                    dto.getFdEntityId());
        } else {
            return getRemote(dto.getFdEntityName()).findById(dto);
        }
    }

    /**
     * 删除任务
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {})
    public void delete(MechanismDTO dto) {
        if (JobQueueUtil.isLocalDb(dto.getFdEntityName())) {
            StringBuilder hql = new StringBuilder();
            hql.append("delete from ")
                    .append(dto.getFdEntityName())
                    .append(" where fdId=:id");
            Query query = entityManager.createQuery(hql.toString());
            query.setParameter("id", dto.getFdEntityId());
            query.executeUpdate();
        } else {
            getRemote(dto.getFdEntityName()).delete(dto);
        }
    }

    /**
     * 取远程服务
     */
    private DbJobQueuePersistenceService getRemote(String jobType) {
        String appName = JobQueueUtil.getJobAppName(jobType);
        return ApplicationContextHolder.getRemoteApi(appName, APIPATH,
                DbJobQueuePersistenceService.class);
    }

    /**
     * 远程调用
     */
    private <R> R remoteCall(String jobType,
                             Function<DbJobQueuePersistenceService, R> func) {
        while (true) {
            try {
                return func.apply(getRemote(jobType));
            } catch (Exception e) {
                String appName = JobQueueUtil.getJobAppName(jobType);
                if (Meta.isApplicationAlive(appName)) {
                    log.warn("远程调用错误，1分钟后重试！", e);
                } else {
                    log.debug("远程调用错误，1分钟后重试！", e);
                }
                try {
                    Thread.sleep(REMOTE_RETRY_TIME);
                } catch (InterruptedException e1) {
                }
            }
        }
    }
}

