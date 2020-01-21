package com.ibyte.common.core.data.field;

import com.ibyte.common.core.data.IData;
import com.ibyte.framework.meta.annotation.MetaProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 标题字段
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface FdSubject extends IData, IField {
    /**
     * 读-标题
     *
     * @return
     */
    @Length(max = 200)
    @NotBlank
    @MetaProperty(messageKey = "property.fdSubject")
    default String getFdSubject() {
        return (String) getExtendProps().get("fdSubject");
    }

    /**
     * 写-标题
     *
     * @param fdSubject
     */
    default void setFdSubject(String fdSubject) {
        getExtendProps().put("fdSubject", fdSubject);
    }
}