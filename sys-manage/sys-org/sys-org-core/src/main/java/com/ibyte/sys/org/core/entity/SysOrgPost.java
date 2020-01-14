package com.ibyte.sys.org.core.entity;

import com.ibyte.framework.meta.MetaConstant;
import com.ibyte.framework.meta.annotation.MetaProperty;
import com.ibyte.sys.org.constant.SysOrgConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 岗位
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@Entity
@DiscriminatorValue("4")
public class SysOrgPost extends SysOrgElement {
    public SysOrgPost() {
        this.setFdOrgType(SysOrgConstant.ORG_TYPE_POST);
    }

    /**
     * 人员列表（仅用于：岗位）
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_org_post_person", joinColumns = @JoinColumn(name = "fd_post_id"), inverseJoinColumns = @JoinColumn(name = "fd_person_id"))
    @MetaProperty(showType = MetaConstant.ShowType.ALWAYS)
    private List<SysOrgPerson> fdPersons;

}
