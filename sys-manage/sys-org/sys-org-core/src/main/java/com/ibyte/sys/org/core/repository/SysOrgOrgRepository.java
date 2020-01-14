package com.ibyte.sys.org.core.repository;

import com.ibyte.sys.org.core.entity.SysOrgOrg;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 机构持久化仓库接口
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Repository
public interface SysOrgOrgRepository extends SysOrgElementRepository<SysOrgOrg>{

    /**
     * 查询子机构
     *
     * @param parent
     * @param tenantId
     * @return
     */
    @Query("from SysOrgOrg where fdOrgType = 1 and fdParent = :parent and fdTenantId = :tenantId")
    List<SysOrgOrg> findSubOrg(@Param("parent") SysOrgOrg parent, @Param("tenantId") int tenantId);


    /**
     * 机构转换为部门，需要修改一些信息
     *
     * @param fdId
     * @param parentId
     * @param tenantId
     */
    @Modifying
    @Query(value = "update sys_org_element set fd_org_type = 2, fd_parent_org_id = :parentId where fd_id = :fdId and fd_tenant_id = :tenantId", nativeQuery = true)
    void updateOrgToDept(@Param("fdId") String fdId, @Param("parentId") String parentId, @Param("tenantId") int tenantId);
}
