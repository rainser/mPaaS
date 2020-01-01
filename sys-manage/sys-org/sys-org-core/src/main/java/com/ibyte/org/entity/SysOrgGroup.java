package com.ibyte.org.entity;

import com.ibyte.framework.meta.MetaConstant;
import com.ibyte.framework.meta.annotation.MetaProperty;
import com.ibyte.sys.org.constant.SysOrgConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 群组
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@Entity
@DiscriminatorValue("16")
public class SysOrgGroup extends SysOrgElement {

    public SysOrgGroup() {
        this.setFdOrgType(SysOrgConstant.ORG_TYPE_GROUP);
    }

    /**
     * 成员列表（仅用于：群组）
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_org_group_element", joinColumns = @JoinColumn(name = "fd_group_id"), inverseJoinColumns = @JoinColumn(name = "fd_element_id"))
    private List<SysOrgElement> fdElements;

    /**
     * 组织类别
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fd_category_id")
    @MetaProperty(showType = MetaConstant.ShowType.ALWAYS)
    private SysOrgCategory fdCategory;
}
