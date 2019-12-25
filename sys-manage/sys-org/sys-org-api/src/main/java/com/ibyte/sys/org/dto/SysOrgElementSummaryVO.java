package com.ibyte.sys.org.dto;

import com.ibyte.common.core.dto.AbstractVO;
import com.ibyte.common.core.dto.IdNameProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * 组织架构简要表DTO类
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Setter
@Getter
@ToString(callSuper = true)
@ApiModel(value = "sysOrgElementSummaryVo", description = "组织架构简要表DTO类")
public class SysOrgElementSummaryVO extends AbstractVO {
    /**
     * 原始ID（如果是同步过来的数据，记录原始ID）
     */
    @ApiModelProperty(value = "原始ID", dataType = "String")
    private String fdOriId;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID", dataType = "Integer", hidden = true)
    private Integer fdTanentId;

    /**
     * 组织类型
     */
    @ApiModelProperty(value = "组织类型", dataType = "Integer")
    private Integer fdOrgType;

    /**
     * 层级ID
     */
    @ApiModelProperty(value = "层级ID", dataType = "String", readOnly = true)
    private String fdHierarchyId;

    /**
     * 上级
     */
    @ApiModelProperty(value = "上级", dataType = "IdNameProperty")
    private IdNameProperty fdParent;

    /**
     * 上级机构
     */
    @ApiModelProperty(value = "上级机构", dataType = "IdNameProperty")
    private IdNameProperty fdParentOrg;

    /**
     * 是否有效
     */
    @ApiModelProperty(value = "是否有效", dataType = "Boolean")
    private Boolean fdIsAvailable;

    /**
     * 邮箱（可用于：机构、部门、岗位、群组、人员）
     */
    @ApiModelProperty(value = "邮箱", dataType = "String")
    private String fdEmail;

    /**
     * 摘要表最后修改时间
     */
    @ApiModelProperty(value = "摘要表最后修改时间", dataType = "Timestamp")
    private Timestamp fdLastModifiedTime;

    /**
     * 组织名称
     */
    @ApiModelProperty(value = "组织名称", dataType = "String")
    private String fdName;

    /**
     * 登录名（用于：人员）
     */
    @ApiModelProperty(value = "登录名", dataType = "String")
    private String fdLoginName;
}

