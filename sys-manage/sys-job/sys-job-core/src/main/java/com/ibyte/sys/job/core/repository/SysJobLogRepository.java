package com.ibyte.sys.job.core.repository;

import com.ibyte.common.core.repository.IRepository;
import com.ibyte.sys.job.core.entity.SysJobLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Repository
public interface SysJobLogRepository extends IRepository<SysJobLog> {

    /**
     * 清理过期的日志
     *
     * @param expired
     */
    @Modifying
    @Query("delete from SysJobLog where fdCreateTime < ?1")
    void deleteByExpired(Date expired);

}
