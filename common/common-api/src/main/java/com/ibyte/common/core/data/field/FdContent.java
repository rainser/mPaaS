package com.ibyte.common.core.data.field;
import com.ibyte.common.core.data.IData;
import com.ibyte.framework.meta.annotation.MetaProperty;

import javax.persistence.Column;
import javax.persistence.Lob;

/**
 * 文档内容字段
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface FdContent extends IData, IField {
    /**
     * 读-文档内容
     *
     * @return
     */
    @Column
    @Lob
    @MetaProperty(messageKey = "property.fdContent")
    default String getFdContent() {
        return (String) getExtendProps().get("fdContent");
    }

    /**
     * 写-文档内容
     *
     * @param fdContent
     */
    default void setFdContent(String fdContent) {
        getExtendProps().put("fdContent", fdContent);
    }
}
