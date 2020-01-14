package com.ibyte.sys.org.core.service;

import com.ibyte.common.core.dto.IdVO;
import com.ibyte.common.core.dto.IdsDTO;
import com.ibyte.common.core.service.AbstractServiceImpl;
import com.ibyte.common.exception.NoRecordException;
import com.ibyte.common.util.StringHelper;
import com.ibyte.common.util.TenantUtil;
import com.ibyte.sys.org.core.api.ISysOrgElementApi;
import com.ibyte.sys.org.core.entity.SysOrgElement;
import com.ibyte.sys.org.core.repository.SysOrgElementRepository;
import com.ibyte.sys.org.constant.SysOrgConstant;
import com.ibyte.sys.org.dto.IdsParentVO;
import com.ibyte.sys.org.dto.SysOrgElementVO;
import com.ibyte.sys.org.event.SysOrgElementInvalidatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.ibyte.common.core.entity.TreeEntity.HIERARCHY_ID_SPLIT;

/**
 * 抽象组织架构服务层，提供各种组织架构公共方法
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Slf4j
public abstract class AbstractElementCommonService<R extends SysOrgElementRepository<E>, E extends SysOrgElement, V extends SysOrgElementVO>
        extends AbstractServiceImpl<R, E, V> implements ISysOrgElementApi<V>, SysOrgConstant {

    @Autowired
    protected SysOrgElementService sysOrgElementService;

    /**
     * 置为无效
     *
     * @param id
     */
    @Transactional(rollbackFor = {})
    @Override
    public void invalidated(IdVO id) {
        Optional<E> optional = findById(id.getFdId());
        if (optional.isPresent()) {
            invalidated(optional.get());
        } else {
            throw new NoRecordException();
        }
    }

    /**
     * 批量置为无效
     *
     * @param ids
     */
    @Transactional(rollbackFor = {})
    @Override
    public void invalidatedAll(IdsDTO ids) {
        // 循环处理每一个组织元素
        for (String id : ids.getFdIds()) {
            invalidated(IdVO.of(id));
        }
    }

    @Transactional(rollbackFor = {})
    public void invalidated(E element) {
        element.setFdIsAvailable(Boolean.FALSE);
        // 发布置为无效事件
        applicationContext.publishEvent(new SysOrgElementInvalidatedEvent(element));
        update(element);
    }

    /**
     * 批量修改上级
     *
     * @param ids
     */
    @Transactional(rollbackFor = {})
    @Override
    public void updateParent(IdsParentVO ids) {
        // 查询上级(由于上级可以是部门或机构，因此这里使用Element来查询)
        String fdParentId = ids.getFdParentId();
        Optional<SysOrgElement> fdParent = sysOrgElementService.findById(fdParentId);
        if (!fdParent.isPresent()) {
            throw new NoRecordException();
        }

        // 批量修改上级
        List<String> fdIds = ids.getFdIds();
        for (String id : fdIds) {
            // 查询要修改的组织元素
            Optional<E> element = findById(id);
            if (element.isPresent()) {
                E elem = element.get();
                // 设置新的上级
                elem.setFdParent(fdParent.get());
                // 更新数据
                update(elem);
            } else {
                throw new NoRecordException();
            }
        }
    }

    /**
     * 判断记录是否存在
     *
     * @param fdId
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public boolean existsById(String fdId) {
        return repository.existsById(fdId);
    }

    /**
     * 批量修改排序号
     *
     * @param list
     */
    @Transactional(rollbackFor = {})
    @Override
    public void updateOrders(List<V> list) {
        if (list != null && !list.isEmpty()) {
            for (V v : list) {
                Optional<E> optional = findById(v.getFdId());
                optional.ifPresent((entity) -> {
                    entity.setFdOrder(v.getFdOrder());
                    update(entity);
                });
            }
        }
    }

    /**
     * 更新组织架构层级关系（适用于修改组织架构的层级关系）
     */
    @Transactional(rollbackFor = {})
    @Override
    public void updateRelation() {
        if (log.isInfoEnabled()) {
            log.info("更新组织架构层级关系");
        }
        int selectCount = 0;
        int updateCount = 0;

        // 更新机构和第一层级
        if (log.isInfoEnabled()) {
            log.info("更新机构和第一层级");
        }
        repository.updateParentOrgAndHierarchyByFirstFloor(new Date(), TenantUtil.getTenantId());

        // 更新其它层级
        List<String> waitList = new ArrayList();
        if (log.isInfoEnabled()) {
            log.info("更新其它层级");
        }
        while (true) {
            List<Object[]> result = repository.findElementRelation(TenantUtil.getTenantId());
            selectCount++;
            // 没有需要更新的记录，退出循环
            if (result.isEmpty()) {
                break;
            }
            // 将所有的id记录到待更新的列表中
            for (Object[] data : result) {
                if (!waitList.contains(data[0])) {
                    waitList.add((String) data[0]);
                }
            }
            for (Object[] data : result) {
                // 若父也需要更新，则不更新子
                if (!waitList.contains(data[1])) {
                    waitList.remove(data[0]);
                    String parentOrgId = (String) data[3];
                    if (((Integer) data[4]).intValue() == ORG_TYPE_ORG) {
                        parentOrgId = (String) data[1];
                    }
                    updateCount++;
                    // 拼接新的层级ID
                    String hierarchyId = StringHelper.join(data[2], data[0], HIERARCHY_ID_SPLIT);
                    // 新的层级数 = 父类层级 + 1
                    int treeLevel = 1;
                    if (data[5] != null) {
                        Object temp = data[5];
                        if (temp != null) {
                            try {
                                treeLevel += Integer.valueOf(temp.toString());
                            } catch (Exception e) {
                                log.error("获取treeLevel失败：", e);
                            }
                        }
                    }
                    if (log.isInfoEnabled()) {
                        log.info(StringHelper.join("正在更新：parentOrgId=", parentOrgId, ",hierarchyId=", hierarchyId, ",treeLevel=", treeLevel, ",id=", data[0]));
                    }
                    repository.updateParentOrgAndHierarchyById(parentOrgId, hierarchyId, treeLevel, new Date(), (String) data[0], TenantUtil.getTenantId());
                }
            }
        }
        // 补偿更新
        // 查询层级ID为空的数据
        List<E> list = repository.findByHierarchyIsNull();
        if (list != null && !list.isEmpty()) {
            for (E elem : list) {
                List<String> hierarchyId = getHierarchyId(elem);
                StringBuilder sb = new StringBuilder();
                for (String id : hierarchyId) {
                    sb.append(HIERARCHY_ID_SPLIT).append(id);
                }
                sb.append(HIERARCHY_ID_SPLIT);
                elem.setFdHierarchyId(sb.toString());
                repository.save(elem);
            }
        }
        if (log.isInfoEnabled()) {
            log.info(StringHelper.join("更新组织架构关系成功，执行了 ", selectCount, " 次查询和 ", updateCount, " 次更新。"));
        }
    }

    /**
     * 获取上级机构Id
     *
     * @param elem
     * @return
     */
    public List<String> getHierarchyId(E elem){
        List<String> ids = new ArrayList<>();
        E parent = (E) elem.getFdParent();
        if (parent != null && parent.getFdOrgType().intValue() != ORG_TYPE_ORG) {
            ids.addAll(getHierarchyId(parent));
        }
        ids.add(elem.getFdId());
        return ids;
    }

}
