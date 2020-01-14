package com.ibyte.sys.org.core.entity;

import com.ibyte.common.core.data.field.*;
import com.ibyte.common.core.entity.AbstractEntity;
import com.ibyte.common.core.entity.TreeEntity;
import com.ibyte.framework.meta.MetaConstant;
import com.ibyte.framework.meta.annotation.MetaProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 组织架构元素（包含：机构、部门、岗位、群组、人员）
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@Entity
@Table(indexes = {@Index(columnList = "fdOriId")})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "fdOrgType", discriminatorType = DiscriminatorType.INTEGER)
public class SysOrgElement extends AbstractEntity implements TreeEntity<SysOrgElement>,FdName4Language, FdOrder, FdCreateTime, FdAlterTime, FdLastModifiedTime {

    /**
     * 原始ID（如果是同步过来的数据，记录原始ID）
     */
    @Length(max = 100)
    private String fdOriId;

    /**
     * 组织类型
     */
    @Column(insertable = false, updatable = false)
    @MetaProperty(messageKey = "sys-org:sysOrgElement.fdOrgType")
    private Integer fdOrgType;

    /**
     * 名称拼音
     */
    @MetaProperty(messageKey = "sys-org:sysOrgElement.fdNamePinyin")
    @Length(max = 200)
    private String fdNamePinyin;

    /**
     * 名称简拼
     */
    @MetaProperty(messageKey = "sys-org:sysOrgElement.fdNameSimplePinyin")
    @Length(max = 200)
    private String fdNameSimplePinyin;

    /**
     * 是否有效
     */
    @NotNull
    @MetaProperty(messageKey = "sys-org:sysOrgElement.fdIsAvailable")
    private Boolean fdIsAvailable;

    /**
     * 是否与业务相关
     */
    @NotNull
    @MetaProperty(messageKey = "sys-org:sysOrgElement.fdIsBusiness")
    private Boolean fdIsBusiness;

    /**
     * 编号
     */
    @MetaProperty(messageKey = "sys-org:sysOrgElement.fdNo")
    @Length(max = 200)
    private String fdNo;

    /**
     * 描述
     */
    @Length(max = 4000)
    @MetaProperty(messageKey = "sys-org:sysOrgElement.fdDescribe")
    private String fdDescribe;

    /**
     * 同步导入的信息
     */
    @MetaProperty(messageKey = "sys-org:sysOrgElement.fdImportInfo")
    @Length(max = 200)
    private String fdImportInfo;

    /**
     * 同步导入的供应商
     */
    @MetaProperty(messageKey = "sys-org:sysOrgElement.fdImportProvider")
    @Length(max = 200)
    private String fdImportProvider;

    /**
     * 置为无效时的上级（当组织置为无效时，记录当时的上级元素）
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fd_ori_parent_id")
    @MetaProperty(messageKey = "sys-org:sysOrgElement.fdOriParent")
    private SysOrgElement fdOriParent;

    /**
     * 所属机构
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fd_parent_org_id")
    @MetaProperty(showType = MetaConstant.ShowType.ALWAYS, messageKey = "sys-org:sysOrgElement.fdParentOrg")
    private SysOrgElement fdParentOrg;

    /**
     * 邮箱（可用于：机构、部门、岗位、群组、人员）
     */
    @MetaProperty(messageKey = "sys-org:sysOrgElement.fdEmail")
    @Length(max = 200)
    private String fdEmail;

    /**
     * 办公电话（可用于：机构、部门、岗位、人员）
     */
    @MetaProperty(messageKey = "sys-org:sysOrgElement.fdWorkPhone")
    @Length(max = 100)
    private String fdWorkPhone;

    /**
     * 当前领导（可用于：机构、部门、岗位）
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fd_this_leader_id")
    @MetaProperty(showType = MetaConstant.ShowType.ALWAYS, messageKey = "sys-org:sysOrgElement.fdThisLeader")
    private SysOrgElement fdThisLeader;

    /**
     * 人员数量（可用于：机构、部门、岗位）
     */
    @MetaProperty(messageKey = "sys-org:sysOrgElement.fdPersonsNumber")
    private Integer fdPersonsNumber;

    /**
     * 子类（不提供set方法）
     */
    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fdParent", fetch = FetchType.LAZY)
    @OrderBy(value = "fd_org_type desc,fd_order,fd_name")
    private List<SysOrgElement> fdChildren;

    /**
     * 群组列表
     */
    @ManyToMany(mappedBy = "fdElements", fetch = FetchType.LAZY)
    @MetaProperty(showType = MetaConstant.ShowType.ALWAYS, messageKey = "sys-org:table.sysOrgGroup")
    private List<SysOrgGroup> fdGroups;

    /**
     * 上级，重写TreeEntity的属性，目的是在list接口也返回父级数据
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fd_parent_id")
    @MetaProperty(showType = MetaConstant.ShowType.ALWAYS, messageKey = "property.fdParent")
    private SysOrgElement fdParent;

    @Override
    public SysOrgElement getFdParent() {
        return fdParent;
    }

    @Override
    public void setFdParent(SysOrgElement fdParent) {
        this.fdParent = fdParent;
    }

    /**
     * 层级ID
     */
    @MetaProperty(showType = MetaConstant.ShowType.ALWAYS, messageKey = "property.fdHierarchyId")
    @Length(max = 900)
    @Column(columnDefinition = "varchar(900)")
    private String fdHierarchyId;

    @Override
    public String getFdHierarchyId() {
        return fdHierarchyId;
    }

    @Override
    public void setFdHierarchyId(String fdHierarchyId) {
        this.fdHierarchyId = fdHierarchyId;
    }

    /**
     * 关键字
     */
    @MetaProperty(messageKey = "sys-org:sysOrgElement.fdKeyword")
    @Length(max = 200)
    private String fdKeyword;
}
