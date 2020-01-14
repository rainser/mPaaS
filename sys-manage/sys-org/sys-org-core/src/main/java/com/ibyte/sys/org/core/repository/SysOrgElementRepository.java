package com.ibyte.sys.org.core.repository;

import com.ibyte.common.core.repository.IRepository;
import com.ibyte.sys.org.core.entity.SysOrgElement;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.Date;
import java.util.List;

/**
 * 组织架构持久化仓库接口
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Repository
public interface SysOrgElementRepository<E extends SysOrgElement> extends IRepository<E> {


    /**
     * 根据层级ID更新上级机构（用于部门转机构（或机构转部门）时更新原有子级）
     *
     * @param parentOrg
     * @param hierarchyId
     * @param tenantId
     * @return
     */
    @Modifying
    @Query("update SysOrgElement set fdParentOrg = ?1 where fdHierarchyId like ?2 and fdTenantId = ?3")
    int updateAllParentOrg(SysOrgElement parentOrg, String hierarchyId, int tenantId);

    /**
     * 更新机构和第一层级组织的层级关系
     *
     * @param tenantId
     * @return
     */
    @Modifying
    @Query("update SysOrgElement set fdParentOrg = null, fdHierarchyId = concat(concat('x', fdId), 'x'), fdTreeLevel = 1, fdLastModifiedTime = ?1 where fdIsAvailable = true and fdTenantId = ?2 and (fdOrgType = 1 or fdOrgType > 1 and fdOrgType < 16 and fdParent is null) and (fdParentOrg is not null or fdHierarchyId <> concat(concat('x', fdId), 'x') or fdHierarchyId is null)")
    int updateParentOrgAndHierarchyByFirstFloor(Date lastModifiedTime, int tenantId);

    /**
     * 获取组织架构层级关系（用于更新组织架构层级关系）
     *
     * @param tenantId
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select s.fdId, p.fdId, p.fdHierarchyId, p.fdParentOrg.fdId, p.fdOrgType, p.fdTreeLevel from SysOrgElement s inner join s.fdParent p where s.fdOrgType > 1 and s.fdOrgType < 16 and s.fdIsAvailable = true and s.fdTenantId = :tenantId and (p.fdOrgType = 1 and (s.fdParentOrg is null or s.fdParentOrg <> p) or p.fdOrgType > 1 and (s.fdParentOrg is null and p.fdParentOrg is not null or s.fdParentOrg is not null and p.fdParentOrg is null or s.fdParentOrg <> p.fdParentOrg) or s.fdHierarchyId <> concat(concat(p.fdHierarchyId, s.fdId), 'x'))")
    List<Object[]> findElementRelation(@Param("tenantId") int tenantId);


    /**
     * 根据ID更新上级机构和层级ID
     *
     * @param parentOrgId
     * @param hierarchyId
     * @param treeLevel
     * @param id
     * @param tenantId
     * @return
     */
    @Modifying
    @Query("update SysOrgElement set fdParentOrg.fdId = ?1, fdHierarchyId = ?2, fdTreeLevel = ?3, fdLastModifiedTime = ?4 where fdId = ?5 and fdTenantId = ?6")
    int updateParentOrgAndHierarchyById(String parentOrgId, String hierarchyId, int treeLevel, Date lastModifiedTime, String id, int tenantId);

    /**
     * 查询层级ID为空的数据
     *
     * @return
     */
    @Query("from SysOrgElement where fdHierarchyId is null")
    List<E> findByHierarchyIsNull();


    /**
     * 根据组织类型查询编码数量（用于判断同类别的编码是否唯一）
     *
     * @param fdNo
     * @param orgType
     * @param tenantId
     * @return
     */
    @Query("select count(fdId) from SysOrgElement where fdOrgType = ?1 and fdNo = ?2 and fdTenantId = ?3")
    long queryCountByNoWithType(int orgType, String fdNo, int tenantId);
}
