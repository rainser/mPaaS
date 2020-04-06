package com.ibyte.sys.job.core.service;

import com.ibyte.common.core.service.AbstractServiceImpl;
import com.ibyte.common.core.util.TransactionUtil;
import com.ibyte.common.exception.KmssServiceException;
import com.ibyte.sys.job.api.annotation.JobScheduled;
import com.ibyte.sys.job.core.entity.SysJobLog;
import com.ibyte.sys.job.core.repository.SysJobLogRepository;
import com.ibyte.sys.job.dto.SysJobLogVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

/**
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Slf4j
@Service
@RestController
@RequestMapping("/api/sys-job/sysJobLog")
@Transactional(readOnly = true, rollbackFor = {})
public class SysJobLogService extends AbstractServiceImpl<SysJobLogRepository, SysJobLog, SysJobLogVO> {


    @Override
    public void add(SysJobLogVO vo) {
        TransactionStatus status = TransactionUtil.beginNewTransaction();
        try {
            super.add(vo);
            TransactionUtil.commit(status);
        } catch (Exception e) {
            TransactionUtil.rollback(status);
            throw new KmssServiceException("", e);
        }
    }

    /**
     * 定时清理30天以前的日志
     * 每日定时清理超过30天的日志
     */
    @JobScheduled(messageKey = "sys-job:job.sysJobLogService.cleanLogs", cron = "0 0 2 * * ?")
    @Transactional(rollbackFor = {})
    public void cleanLogs() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -32);
        repository.deleteByExpired(cal.getTime());
    }
}
