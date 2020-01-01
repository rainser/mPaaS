package com.ibyte.org.server;
import com.ibyte.common.core.dto.IdVO;
import com.ibyte.common.core.dto.IdsDTO;
import com.ibyte.common.core.service.AbstractServiceImpl;
import com.ibyte.common.exception.NoRecordException;
import com.ibyte.common.util.TenantUtil;
import com.ibyte.org.api.ISysOrgElementApi;
import com.ibyte.org.entity.SysOrgElement;
import com.ibyte.org.repository.SysOrgElementRepository;
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

/**
 * 抽象组织架构服务层，提供各种组织架构公共方法
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Slf4j
@Transactional(readOnly = true, rollbackFor = {})
public abstract class AbstractElementCommonService<R extends SysOrgElementRepository<E>, E extends SysOrgElement, V extends SysOrgElementVO>
        extends AbstractServiceImpl<R, E, V> implements ISysOrgElementApi<V> {

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


    @Transactional(rollbackFor = {})
    public void invalidated(E element) {
        element.setFdIsAvailable(Boolean.FALSE);
        // 发布置为无效事件
        applicationContext.publishEvent(new SysOrgElementInvalidatedEvent(element));
        update(element);
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





    }
}
