package com.ibyte.sys.job.support.db;

import com.ibyte.common.core.util.EntityUtil;
import com.ibyte.framework.support.ApplicationContextHolder;
import com.ibyte.sys.job.api.entity.JobQueueEntity;
import com.ibyte.sys.job.support.db.util.JobQueueUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    }
}
