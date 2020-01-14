package com.ibyte.sys.org.core.entity;

import com.ibyte.common.core.data.field.*;
import com.ibyte.common.core.entity.AbstractEntity;
import com.ibyte.common.core.entity.TreeEntity;
import com.ibyte.framework.meta.MetaConstant;
import com.ibyte.framework.meta.annotation.MetaProperty;
import com.ibyte.sys.org.constant.CategoryType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 组织类别（可用于：机构/部门、群组）
 * <li>当用于机构/部门时，体现为多维组织</li>
 * <li>当用于群组时，体现为群组分类</li>
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@Entity
@Table
public class SysOrgCategory extends AbstractEntity implements TreeEntity<SysOrgCategory>, FdName, FdOrder, FdCreateTime, FdAlterTime, FdLastModifiedTime {

    /**
     * 组织类型：1，机构/部门；2,群组
     */
    @NotNull
    @Convert(converter = CategoryType.Converter.class)
    @MetaProperty(notNull = true)
    private CategoryType fdType;

    /**
     * 上级分类，重写TreeEntity的属性，目的是在list接口也返回父级数据
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fd_parent_id")
    @MetaProperty(showType = MetaConstant.ShowType.ALWAYS)
    private SysOrgCategory fdParent;

    @Override
    public SysOrgCategory getFdParent() {
        return fdParent;
    }

    @Override
    public void setFdParent(SysOrgCategory fdParent) {
        this.fdParent = fdParent;
    }


}
