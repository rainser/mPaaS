package com.ibyte.sys.job.core.service;

import com.ibyte.sys.job.support.schedule.JobScheduleConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * 定时任务调度器
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Slf4j
public class SysJobScheduleDistributor
        implements ApplicationListener<ApplicationReadyEvent>, DisposableBean,
        JobScheduleConstant {

    /** 服务上下文 */
    private JobScheduleServiceContext serviceContext = new JobScheduleServiceContext();

    private SysJobScheduleService sysJobScheduleService;

    @Autowired
    public void setSysJobScheduleService(
            SysJobScheduleService sysJobScheduleService) {
        this.sysJobScheduleService = sysJobScheduleService;
        serviceContext.setSysJobScheduleApi(sysJobScheduleService);
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

    }
}
