package com.ibyte.sys.job.support.schedule.dto;

import java.util.List;

/**
 * 定时任务批次
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public class JobScheduleBatch {

    private String fdLockId;

    private Long fdLockTime;

    private Long fdPollTime;

    private List<JobScheduleDetail> details;

}
