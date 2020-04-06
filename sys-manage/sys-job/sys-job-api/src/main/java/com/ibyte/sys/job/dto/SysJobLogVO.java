package com.ibyte.sys.job.dto;

import com.ibyte.common.core.data.field.FdAlterTime;
import com.ibyte.common.core.data.field.FdContent;
import com.ibyte.common.core.data.field.FdCreateTime;
import com.ibyte.common.core.data.field.FdSubject;
import com.ibyte.common.core.dto.AbstractVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SysJobLogVO extends AbstractVO implements FdSubject, FdContent, FdCreateTime, FdAlterTime {

    /**
     * 定时任务ID
     */
    private String fdJobId;

    /**
     * 任务开始时间
     */
    private Date fdStartTime;

    /**
     * 任务结束时间
     */
    private Date fdEndTime;

    /**
     * 服务名
     */
    private String fdAppServer;

    /**
     * 服务类名
     */
    private String fdJobService;

    /**
     * 方法名
     */
    private String fdJobMethod;

    /**
     * 是否成功
     */
    private Boolean fdSuccess;

    /**
     * 任务历时（秒）
     */
    private Float fdTaskDuration;

}
