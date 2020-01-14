package com.ibyte.sys.org.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 简要表定时同步任务
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Slf4j
@Service
@Transactional(readOnly = true, rollbackFor = {})
public class SysOrgSynchroSummaryService {

    @Autowired
    protected EntityManager entityManager;

    private static volatile AtomicBoolean executed = new AtomicBoolean(false);

    /**
     * 定时同步组织简要表信息
     * <p>
     *      服务微服务部署处理分库现象/用户信息保持同步状态
     * </p>
     */
    @Transactional(rollbackFor = {})
    public void syncOrgSummary() {

    }
}
