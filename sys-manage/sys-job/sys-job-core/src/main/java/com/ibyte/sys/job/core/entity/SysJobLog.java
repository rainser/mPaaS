package com.ibyte.sys.job.core.entity;

import com.ibyte.common.core.data.field.FdAlterTime;
import com.ibyte.common.core.data.field.FdContent;
import com.ibyte.common.core.data.field.FdCreateTime;
import com.ibyte.common.core.entity.AbstractEntity;
import com.ibyte.common.util.IDGenerator;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 定时任务
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@Entity
@Table
public class SysJobLog extends AbstractEntity implements FdContent, FdCreateTime, FdAlterTime {

    /**
     * 定时任务ID
     */
    @Length(max = IDGenerator.LEN)
    private String fdJobId;

    /**
     * 任务标题
     */
    @Length(max = 200)
    private String fdSubject;

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
    @Length(max = 100)
    private String fdAppServer;

    /**
     * 服务类名
     */
    @Length(max = 200)
    private String fdJobService;

    /**
     * 方法名
     */
    @Length(max = 50)
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
