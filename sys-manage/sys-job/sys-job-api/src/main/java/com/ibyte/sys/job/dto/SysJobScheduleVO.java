package com.ibyte.sys.job.dto;

import com.ibyte.common.core.data.field.FdSubject;
import com.ibyte.common.core.data.field.MechanismData;
import com.ibyte.common.core.dto.AbstractVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 定时任务 vo
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@ToString
public class SysJobScheduleVO extends AbstractVO
        implements FdSubject, MechanismData {
    private String fdLockId;

    private String fdMessageKey;

    private String fdCronExpression;

    private String fdAppServer;

    private String fdJobService;

    private String fdJobMethod;

    private String fdParameter;

    private Boolean fdEnabled;

    private Boolean fdSysJob;

    private Boolean fdRequired;

    private Long fdNextTime;

    private Integer fdRetry;

    private String fdCallbackUrl;

    @Override
    public String getMechanismName() {
        return "sys-job";
    }
}