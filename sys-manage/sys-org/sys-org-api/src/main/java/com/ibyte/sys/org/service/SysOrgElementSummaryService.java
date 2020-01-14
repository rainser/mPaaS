package com.ibyte.sys.org.service;

import com.ibyte.common.constant.ReadyEventOrder;
import com.ibyte.common.core.service.AbstractServiceImpl;
import com.ibyte.sys.org.api.ISysOrgElementSummaryApi;
import com.ibyte.sys.org.dto.SysOrgElementSummaryVO;
import com.ibyte.sys.org.entity.SysOrgElementSummary;
import com.ibyte.sys.org.repository.SysOrgElementSummaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组织架构简要表服务层
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Slf4j
@Service
@RestController
@RequestMapping("/api/sys-org/sysOrgElementSummary")
@Transactional(readOnly = true, rollbackFor = {})
@Order(ReadyEventOrder.FRAMEWORK + 1)
public class SysOrgElementSummaryService extends AbstractServiceImpl<SysOrgElementSummaryRepository, SysOrgElementSummary, SysOrgElementSummaryVO>
        implements ISysOrgElementSummaryApi, ApplicationListener<ApplicationReadyEvent> {


    @Override
    @Transactional(rollbackFor = {})
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

    }
}
