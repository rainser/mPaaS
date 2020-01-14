package com.ibyte.sys.org.core.repository;

import com.ibyte.sys.org.core.entity.SysOrgPost;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 岗位持久化接口
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Repository
public interface SysOrgPostRepository extends SysOrgElementRepository<SysOrgPost> {

    /**
     * 根据人员删除关系
     *
     * @param personId
     */
    @Modifying
    @Query(value = "delete from sys_org_post_person where fd_person_id = ?1", nativeQuery = true)
    void deleteByPerson(String personId);

    /**
     * 增加岗位关系
     *
     * @param postId
     * @param personId
     */
    @Modifying
    @Query(value = "insert into sys_org_post_person (fd_post_id, fd_person_id) values (?1, ?2)", nativeQuery = true)
    void addByPerson(String postId, String personId);

}
