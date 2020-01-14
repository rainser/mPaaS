package com.ibyte.sys.org.core.entity;

import com.ibyte.framework.meta.MetaConstant;
import com.ibyte.framework.meta.annotation.MetaProperty;
import com.ibyte.sys.org.constant.AccountType.PersonType;
import com.ibyte.sys.org.constant.SysOrgConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 人员
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@Entity
@DiscriminatorValue("8")
public class SysOrgPerson extends SysOrgElement {
    public SysOrgPerson() {
        this.setFdOrgType(SysOrgConstant.ORG_TYPE_PERSON);
    }
    /**
     * 账号
     * <p>
     * 为实现一人多部门，这里设计一个账号对应多个人员
     */
    @MetaProperty(showType = MetaConstant.ShowType.ALWAYS)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fd_account_id")
    private SysOrgAccount fdAccount;

    /**
     * 岗位列表（仅用于：人员）
     */
    @ManyToMany(mappedBy = "fdPersons", fetch = FetchType.LAZY)
    @MetaProperty(showType = MetaConstant.ShowType.ALWAYS)
    private List<SysOrgPost> fdPosts;

    /**
     * 置为无效时的岗位ID（当组织置为无效时，记录当时的岗位）
     */
    @ElementCollection
    @JoinTable(name = "sys_org_person_ori_post", joinColumns = @JoinColumn(name = "fd_person_id", referencedColumnName = "fdId"))
    @OrderColumn(name = "fd_order")
    @Column(name = "fd_post_id")
    private List<String> fdOriPosts;

    /**
     * 职级/职务（仅用于：人员）
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fd_staffing_level_id")
    private SysOrgStaffingLevel fdStaffingLevel;

    /**
     * 用户类型（普通用户、租户管理员、系统管理员、系统用户）
     */
    @Convert(converter = PersonType.Converter.class)
    private PersonType fdPersonType;


}
