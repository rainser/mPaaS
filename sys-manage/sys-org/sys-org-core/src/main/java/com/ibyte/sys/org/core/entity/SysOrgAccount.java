package com.ibyte.sys.org.core.entity;

import com.ibyte.common.core.data.field.FdAlterTime;
import com.ibyte.common.core.data.field.FdCreateTime;
import com.ibyte.common.core.data.field.FdLastModifiedTime;
import com.ibyte.common.core.entity.AbstractEntity;
import com.ibyte.common.core.validation.AddGroup;
import com.ibyte.sys.org.constant.AccountType.GenderType;
import com.ibyte.sys.org.constant.AccountType.DualFactorsType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

/**
 * 人员账号
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@Entity
public class SysOrgAccount extends AbstractEntity implements FdCreateTime, FdAlterTime, FdLastModifiedTime {

    /**
     * 登录名
     */
    @NotBlank(groups = AddGroup.class)
    @Length(max = 200)
    private String fdLoginName;

    /**
     * 登录密码
     */
    @NotBlank(groups = AddGroup.class)
    @Length(max = 200)
    private String fdPassword;

    /**
     * 密码加密方式，如：MD5, AES
     */
    @Length(max = 20)
    private String fdEncryption;

    /**
     * 动态密码卡
     */
    @Length(max = 200)
    private String fdCardNo;

    /**
     * 昵称
     */
    @Length(max = 200)
    private String fdNickName;

    /**
     * 微信号
     */
    @Length(max = 200)
    private String fdWechatNo;

    /**
     * 短号
     */
    @Length(max = 20)
    private String fdShortNo;

    /**
     * 性别
     */
    @Convert(converter = GenderType.Converter.class)
    private GenderType fdGender;

    /**
     * 手机号
     */
    @Length(max = 50)
    private String fdMobileNo;

    /**
     * 默认语言
     */
    @Length(max = 20)
    private String fdDefaultLang;

    /**
     * 初始密码（对称加密）
     */
    @Length(max = 200)
    private String fdInitPassword;

    /**
     * 双因子认证（启用，禁用，网段策略）
     */
    @Convert(converter = DualFactorsType.Converter.class)
    private DualFactorsType fdDoubleValidation;

    /**
     * 动态考期卡
     */
    @Length(max = 200)
    private String fdAttendanceCardNo;

    /**
     * 是否激活（适用于三员管理）
     */
    private Boolean fdIsActivated;

    /**
     * 最后一次修改密码时间
     */
    private Timestamp fdLastModifiedPassword;

    /**
     * 锁定时间（当用户在同一时间段内超过N次登录失败时将会锁定一段时间）
     */
    private Timestamp fdLockTime;

    /**
     * 为实现一人多部门，这里设计一个账号对应多个人员
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "fdAccount", fetch = FetchType.LAZY)
    @OrderBy(value = "fd_order,fd_name")
    private List<SysOrgPerson> fdPersons;

    /**
     * 默认人员
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fd_def_person_id")
    private SysOrgPerson fdDefPerson;
}
