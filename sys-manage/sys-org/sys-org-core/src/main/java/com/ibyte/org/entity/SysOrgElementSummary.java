package com.ibyte.org.entity;

import com.ibyte.common.core.data.field.FdLastModifiedTime;
import com.ibyte.common.core.data.field.FdName4Language;
import com.ibyte.common.core.entity.AbstractEntity;
import com.ibyte.common.core.entity.TreeEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * 组织架构简要表（用于与业务模块关联）
 * <p>
 * 地址本使用组织架构表（sys_org_element）
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@Entity
@Table(indexes = {@Index(columnList = "fdLoginName"), @Index(columnList = "fdOriId")})
public class SysOrgElementSummary extends AbstractEntity implements TreeEntity<SysOrgElementSummary>, FdName4Language, FdLastModifiedTime {

    /**
     * 原始ID（如果是同步过来的数据，记录原始ID）
     */
    @Length(max = 100)
    private String fdOriId;

    /**
     * 组织类型
     */
    private Integer fdOrgType;

    /**
     * 上级机构
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fd_parent_org_id")
    private SysOrgElementSummary fdParentOrg;

    /**
     * 是否有效
     */
    private Boolean fdIsAvailable;

    /**
     * 邮箱（可用于：机构、部门、岗位、群组、人员）
     */
    @Length(max = 255)
    private String fdEmail;

    /**
     * 登录名（用于：人员）
     */
    @Length(max = 255)
    private String fdLoginName;

}