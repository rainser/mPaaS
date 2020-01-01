package com.ibyte.sys.org.constant;

import com.ibyte.common.core.constant.IEnum;
import lombok.AllArgsConstructor;

/**
 * 组织构架类别
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@AllArgsConstructor
public enum CategoryType implements IEnum<Integer> {
    /**
     * 机构/部门
     */
    ORG(1, "sys-org:enum.CategoryType.ORG"),
    /**
     * 群组
     */
    GROUP(2, "sys-org:enum.CategoryType.GROUP");

    private Integer value;

    private String messageKey;

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getMessageKey() {
        return messageKey;
    }

    public static class Converter extends IEnum.Converter<Integer, CategoryType> {
    }
}
