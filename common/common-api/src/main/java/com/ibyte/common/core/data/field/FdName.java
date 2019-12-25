package com.ibyte.common.core.data.field;

import com.ibyte.common.core.data.IData;
import com.ibyte.framework.meta.annotation.MetaProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 名称字段
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface FdName extends IData, IField {
    /**
     * 读-名称
     *
     * @return
     */
    @Length(max = 200)
    @NotBlank
    @MetaProperty(messageKey = "property.fdName")
    default String getFdName() {
        return (String) getExtendProps().get("fdName");
    }

    /**
     * 写-名称
     *
     * @param fdName
     */
    default void setFdName(String fdName) {
        getExtendProps().put("fdName", fdName);
    }
}
