package com.ibyte.sys.job.support.schedule.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 定时任务详情
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
public class JobScheduleDetail {
    private String fdId;

    private Integer fdTenantId;

    private String fdAppServer;

    private String fdSubject;

    private String fdJobService;

    private String fdJobMethod;

    private String fdParameter;

    private Boolean fdNeedRemove;

    private String fdCallbackUrl;
}