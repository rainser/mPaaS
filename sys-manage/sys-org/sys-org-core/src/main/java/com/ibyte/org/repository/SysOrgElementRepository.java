package com.ibyte.org.repository;

import com.ibyte.common.core.repository.IRepository;
import com.ibyte.org.entity.SysOrgElement;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 组织架构持久化仓库接口
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Repository
public interface SysOrgElementRepository<E extends SysOrgElement> extends IRepository<E> {

    /**
     * 更新机构和第一层级组织的层级关系
     *
     * @param tenantId
     * @return
     */
    @Modifying
    @Query("update SysOrgElement set fdParentOrg = null, fdHierarchyId = concat(concat('x', fdId), 'x'), fdTreeLevel = 1, fdLastModifiedTime = ?1 where fdIsAvailable = true and fdTenantId = ?2 and (fdOrgType = 1 or fdOrgType > 1 and fdOrgType < 16 and fdParent is null) and (fdParentOrg is not null or fdHierarchyId <> concat(concat('x', fdId), 'x') or fdHierarchyId is null)")
    int updateParentOrgAndHierarchyByFirstFloor(Date lastModifiedTime, int tenantId);
}
