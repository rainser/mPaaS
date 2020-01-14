package com.ibyte.sys.org.core.entity;

import com.ibyte.framework.meta.MetaConstant;
import com.ibyte.framework.meta.annotation.MetaProperty;
import com.ibyte.sys.org.constant.SysOrgConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 部门
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@Entity
@DiscriminatorValue("2")
public class SysOrgDept extends SysOrgElement {
    public SysOrgDept() {
        this.setFdOrgType(SysOrgConstant.ORG_TYPE_DEPT);
    }

    /**
     * 上级领导（可用于：机构、部门）
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fd_super_leader_id")
    @MetaProperty(showType = MetaConstant.ShowType.ALWAYS)
    private SysOrgElement fdSuperLeader;

    /**
     * 管理员（可用于：机构、部门）
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_org_element_admin", joinColumns = @JoinColumn(name = "fd_element_id"), inverseJoinColumns = @JoinColumn(name = "fd_admin_id"))
    private List<SysOrgElement> fdAdmins;

    /**
     * 组织类别（可用于：机构、部门）
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fd_category_id")
    @MetaProperty(showType = MetaConstant.ShowType.ALWAYS)
    private SysOrgCategory fdCategory;
}
