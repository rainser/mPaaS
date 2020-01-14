package com.ibyte.sys.org.core.repository;

import com.ibyte.sys.org.core.entity.SysOrgGroup;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * 群组持久化接口
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Repository
@CacheConfig(cacheNames = "sysOrgGroup")
public interface SysOrgGroupRepository extends SysOrgElementRepository<SysOrgGroup> {

    /**
     * 获取群组关联关系
     *
     * @param tenantId
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query(value = "select fd_group_id, fd_element_id from sys_org_group_element left join sys_org_element on fd_element_id = fd_id where fd_org_type = 16 and fd_tenant_id = :tenantId", nativeQuery = true)
    List<String[]> loadManyToMany(@Param("tenantId") int tenantId);
}