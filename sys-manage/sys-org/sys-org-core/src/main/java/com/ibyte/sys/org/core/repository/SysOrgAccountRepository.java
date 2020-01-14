package com.ibyte.sys.org.core.repository;

import com.ibyte.common.core.repository.IRepository;
import com.ibyte.sys.org.core.entity.SysOrgAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Repository
public interface SysOrgAccountRepository extends IRepository<SysOrgAccount> {

    /**
     * 根据用户名查询有效账号
     *
     * @param loginName
     * @param isAvailable
     * @param tenantId
     * @return
     */
    @Query("from SysOrgAccount where trim(lower(fdLoginName)) = ?1 and fdIsActivated = ?2 and fdTenantId = ?3")
    SysOrgAccount findByLoginName(String loginName, Boolean isAvailable, int tenantId);

    /**
     * 检查登录名是否唯一
     *
     * @param id
     * @param loginName
     * @param tenantId
     * @return
     */
    @Query("from SysOrgAccount where fdId <> ?1 and trim(lower(fdLoginName)) = ?2 and fdIsActivated = ?3 and fdTenantId = ?4")
    SysOrgAccount checkLoginNameUnique(String id, String loginName, Boolean fdIsActivated, int tenantId);

    /**
     * 根据默认人员查询账号，不过滤租户（用于同步）
     *
     * @param personId
     * @return
     */
    @Query("from SysOrgAccount where fdDefPerson.fdId = ?1")
    List<SysOrgAccount> getEntityByPersonWithSync(String personId);

}
