package com.ibyte.sys.job.core.service;

import com.ibyte.sys.job.support.schedule.JobScheduleConstant;

/**
 * 定时任务分发线程
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public class SysJobScheduleThread implements Runnable, JobScheduleConstant {

    //    public SysJobScheduleThread(
//            SysJobScheduleDistributor sysJobScheduleDistributor,
//            JobScheduleServiceContext serviceContext) {
//        super();
//        this.sysJobScheduleDistributor = sysJobScheduleDistributor;
//        this.serviceContext = serviceContext;
//    }

    private SysJobScheduleDistributor sysJobScheduleDistributor;

    private JobScheduleServiceContext serviceContext;

    @Override
    public void run() {

    }
}
