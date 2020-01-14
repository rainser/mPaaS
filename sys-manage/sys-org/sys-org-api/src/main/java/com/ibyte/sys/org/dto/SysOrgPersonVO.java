package com.ibyte.sys.org.dto;

import com.ibyte.common.core.dto.IdNameProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.ibyte.sys.org.constant.AccountType.PersonType;

import java.util.List;

/**
 * 人员VO类
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Setter
@Getter
@ToString(callSuper = true)
@ApiModel(value = "sysOrgPersonVo", description = "人员VO类")
public class SysOrgPersonVO extends SysOrgElementVO{
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", dataType = "SysOrgAccountVO")
    private SysOrgAccountVO fdAccount;
    /**
     * 职级/职务
     */
    @ApiModelProperty(value = "职级/职务", dataType = "IdNameProperty")
    private IdNameProperty fdStaffingLevel;
    /**
     * 岗位列表
     */
    @ApiModelProperty(value = "岗位列表", dataType = "List")
    private List<IdNameProperty> fdPosts;

    /**
     * 原始岗位
     */
    @ApiModelProperty(value = "原始岗位", dataType = "List")
    private List<String> fdOriPosts;

    /**
     * 用户类型（普通用户、租户管理员、系统管理员、系统用户）
     */
    @ApiModelProperty(value = "用户类型", dataType = "Integer")
    private PersonType fdPersonType;
}
