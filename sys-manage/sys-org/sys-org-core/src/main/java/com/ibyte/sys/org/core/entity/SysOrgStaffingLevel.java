package com.ibyte.sys.org.core.entity;

import com.ibyte.common.core.data.field.*;
import com.ibyte.common.core.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 职级（职务）
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@Entity
public class SysOrgStaffingLevel extends AbstractEntity implements FdName, FdOrder, FdCreateTime, FdAlterTime, FdLastModifiedTime {
    /**
     * 描述
     */
    @Length(max = 400)
    private String fdDescription;
    /**
     * 等级
     */
    @NotNull
    private Integer fdLevel;
    /**
     * 是否默认
     */
    private Boolean fdIsDefault;

    /**
     * 人员列表
     */
    @OneToMany(mappedBy = "fdStaffingLevel", fetch = FetchType.LAZY)
    private List<SysOrgPerson> fdPersons;
}