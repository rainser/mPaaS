package com.ibyte.sys.job.core.entity;

import com.ibyte.common.core.data.field.MechanismData;
import com.ibyte.common.core.entity.AbstractEntity;
import com.ibyte.framework.meta.annotation.MetaProperty;
import com.ibyte.sys.job.api.entity.LockJobQueue;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@Entity
@Table(indexes = { @Index(columnList = "fdNextTime") })
public class SysJobSchedule extends AbstractEntity
        implements LockJobQueue, MechanismData {
    @Length(max = 200)
    private String fdSubject;

    @Length(max = 100)
    private String fdMessageKey;

    @Length(max = 100)
    private String fdCronExpression;

    @Length(max = 100)
    private String fdAppServer;

    @Length(max = 200)
    private String fdJobService;

    @Length(max = 50)
    private String fdJobMethod;

    @Length(max = 2000)
    private String fdParameter;

    private Boolean fdEnabled = Boolean.TRUE;

    @MetaProperty(readOnly = true)
    private Boolean fdSysJob = Boolean.FALSE;

    private Boolean fdRequired = Boolean.FALSE;

    @MetaProperty(readOnly = true)
    private Long fdNextTime;

    private Integer fdRetry = 0;

    @Length(max = 200)
    private String fdCallbackUrl;

    @Override
    public String getMechanismName() {
        return "sys-job";
    }
}