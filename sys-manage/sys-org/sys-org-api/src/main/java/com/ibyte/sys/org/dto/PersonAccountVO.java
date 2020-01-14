package com.ibyte.sys.org.dto;

import com.ibyte.common.core.dto.AbstractVO;
import com.ibyte.common.core.dto.IdNameProperty;
import com.ibyte.sys.org.constant.AccountType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 人员和账号的VO类
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@ToString(callSuper = true)
@ApiModel(value = "personAccountVO", description = "人员和账号的VO类")
public class PersonAccountVO extends AbstractVO {

// ============================= 账号信息 ============================

    /**
     * 账号ID
     */
    @ApiModelProperty(value = "账号ID", dataType = "String", readOnly = true)
    private String fdAccountId;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名", dataType = "String", required = true)
    private String fdLoginName;
    /**
     * 关键字
     */
    @ApiModelProperty(value = "关键字", dataType = "String")
    private String fdKeyword;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", dataType = "String")
    private String fdNickName;
    /**
     * 微信号
     */
    @ApiModelProperty(value = "微信号", dataType = "String")
    private String fdWechatNo;
    /**
     * 短号
     */
    @ApiModelProperty(value = "短号", dataType = "String")
    private String fdShortNo;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", dataType = "Integer", allowableValues = "1,2,3")
    private AccountType.GenderType fdGender;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", dataType = "String")
    private String fdMobileNo;
    /**
     * 默认语言
     */
    @ApiModelProperty(value = "默认语言", dataType = "String")
    private String fdDefaultLang;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码", dataType = "String", hidden = true)
    private String fdPassword;
    /**
     * 动态密码卡
     */
    @ApiModelProperty(value = "动态密码卡", dataType = "String", hidden = true)
    private String fdCardNo;
    /**
     * 双因子认证（启用，禁用，网段策略）
     */
    @ApiModelProperty(value = "双因子认证", dataType = "Integer", allowableValues = "1,2,3")
    private AccountType.DualFactorsType fdDoubleValidation;
    /**
     * 动态考期卡
     */
    @ApiModelProperty(value = "动态考期卡", dataType = "String")
    private String fdAttendanceCardNo;


    // ============================= 人员信息 ============================

    /**
     * 组织名称
     */
    @ApiModelProperty(value = "名称", dataType = "String", required = true)
    private String fdName;
    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号", dataType = "Integer")
    private Integer fdOrder;
    /**
     * 上级
     */
    @ApiModelProperty(value = "上级", dataType = "Object")
    private IdNameProperty fdParent;

    /**
     * 所属机构
     */
    @ApiModelProperty(value = "所属机构", dataType = "Object")
    private IdNameProperty fdParentOrg;
    /**
     * 是否有效
     */
    @ApiModelProperty(value = "是否有效", dataType = "Boolean")
    private Boolean fdIsAvailable;
    /**
     * 是否与业务相关
     */
    @ApiModelProperty(value = "是否与业务相关", dataType = "Boolean")
    private Boolean fdIsBusiness;
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号", dataType = "String")
    private String fdNo;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", dataType = "String")
    private String fdDescribe;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", dataType = "String")
    private String fdEmail;
    /**
     * 办公电话
     */
    @ApiModelProperty(value = "办公电话", dataType = "String")
    private String fdWorkPhone;
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
     * 置为无效时的岗位
     */
    @ApiModelProperty(value = "原始岗位", dataType = "List")
    private List<String> fdOriPosts;

    /**
     * 置为无效时的上级（当组织置为无效时，记录当时的上级元素）
     */
    @ApiModelProperty(value = "置为无效时的上级", dataType = "Object")
    private IdNameProperty fdOriParent;

    /**
     * 用户类型（普通用户、租户管理员、系统管理员、系统用户）
     */
    private AccountType.PersonType fdPersonType;

    // ============================= 通用信息 ============================

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", dataType = "Date", readOnly = true)
    private Date fdCreateTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", dataType = "Date", readOnly = true)
    private Date fdAlterTime;


}
