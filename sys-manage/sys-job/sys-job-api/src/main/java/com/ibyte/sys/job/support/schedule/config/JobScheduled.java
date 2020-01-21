package com.ibyte.sys.job.support.schedule.config;

import lombok.Getter;
import lombok.Setter;

/**
 * 定时任务配置
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
public class JobScheduled {
    private String messageKey;

    private String cron;

    private String lockId;
}

