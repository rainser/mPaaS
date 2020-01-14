package com.ibyte.sys.org.dto;

import com.ibyte.common.core.dto.AbstractVO;
import com.ibyte.common.core.dto.IdNameProperty;
import com.ibyte.sys.org.constant.AccountType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.ibyte.sys.org.constant.AccountType.GenderType;

/**
 * 人员账号VO类
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Setter
@Getter
@ToString(callSuper = true)
@ApiModel(value = "sysOrgAccountVo", description = "人员账号VO类")
public class SysOrgAccountVO extends AbstractVO {
    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名", dataType = "String", required = true)
    private String fdLoginName;
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
    private GenderType fdGender;
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
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", dataType = "Date", readOnly = true)
    private Date fdCreateTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", dataType = "Date", readOnly = true)
    private Date fdAlterTime;
    /**
     * 为实现一人多部门，这里设计一个账号对应多个人员
     */
    @ApiModelProperty(value = "人员", dataType = "List")
    private List<IdNameProperty> fdPersons;
    /**
     * 默认人员
     */
    @ApiModelProperty(value = "默认人员", dataType = "SysOrgPersonVO")
    private IdNameProperty fdDefPerson;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码", dataType = "String", hidden = true)
    private String fdPassword;
    /**
     * 密码加密方式，如：MD5, AES
     */
    @ApiModelProperty(value = "密码加密方式", dataType = "String", hidden = true)
    private String fdEncryption;
    /**
     * 动态密码卡
     */
    @ApiModelProperty(value = "动态密码卡", dataType = "String", hidden = true)
    private String fdCardNo;
    /**
     * 初始密码（对称加密）
     */
    @ApiModelProperty(value = "初始密码", dataType = "String", hidden = true)
    private String fdInitPassword;
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
    /**
     * 是否激活（适用于三员管理）
     */
    @ApiModelProperty(value = "是否激活", dataType = "Boolean")
    private Boolean fdIsActivated;
    /**
     * 最后一次修改密码时间
     */
    @ApiModelProperty(value = "最后一次修改密码时间", dataType = "Timestamp", readOnly = true)
    private Timestamp fdLastModifiedPassword;
    /**
     * 锁定时间（当用户在同一时间段内超过N次登录失败时将会锁定一段时间）
     */
    @ApiModelProperty(value = "锁定时间", dataType = "Timestamp", readOnly = true)
    private Timestamp fdLockTime;
}