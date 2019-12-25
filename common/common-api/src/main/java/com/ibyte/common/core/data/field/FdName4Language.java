package com.ibyte.common.core.data.field;

import com.ibyte.framework.meta.annotation.MetaProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 字段名称-支持多语言
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface FdName4Language extends FdName {

    /**
     * 读-名称
     *
     * @return
     */
    @Override
    @Length(max = 200)
    @NotBlank
    @MetaProperty(langSupport = true, messageKey = "property.fdName")
    default String getFdName() {
        return (String) getExtendProps().get("fdName");
    }

}