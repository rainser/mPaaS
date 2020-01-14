package com.ibyte.sys.org.constant;

import com.ibyte.common.core.constant.IEnum;
import lombok.AllArgsConstructor;

/**
 * 账户常量
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface AccountType {
    /**
     * 性别枚举
     */
    @AllArgsConstructor
    enum GenderType implements IEnum<String> {
        /**
         * 男性
         */
        MALE("M", "sys-org:enum.GenderType.MALE"),
        /**
         * 女性
         */
        FEMALE("F", "sys-org:enum.GenderType.FEMALE"),
        /**
         * 未知
         */
        UNKNOWN("UN", "sys-org:enum.GenderType.UNKNOWN");

        private String value;

        private String messageKey;

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String getMessageKey() {
            return messageKey;
        }

        public static class Converter extends IEnum.Converter<String, GenderType> {
        }
    }

    /**
     * 双因子认证
     */
    @AllArgsConstructor
    enum DualFactorsType implements IEnum<Integer> {
        /**
         * 启用
         */
        ENABLED(1, "sys-org:enum.DualFactorsType.ENABLED"),
        /**
         * 禁用
         */
        DISABLED(2, "sys-org:enum.DualFactorsType.DISABLED"),
        /**
         * 网段策略
         */
        NETWORK(3, "sys-org:enum.DualFactorsType.NETWORK");

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

        public static class Converter extends IEnum.Converter<Integer, DualFactorsType> {
        }
    }

    /**
     * 用户类型
     */
    @AllArgsConstructor
    enum PersonType implements IEnum<Integer> {
        /**
         * 普通用户
         */
        GENERAL(1, "sys-org:enum.PersonType.GENERAL"),
        /**
         * 租户管理员
         */
        TENANT_ADMIN(2, "sys-org:enum.PersonType.TENANT_ADMIN"),
        /**
         * 系统管理员
         */
        SYS_ADMIN(4, "sys-org:enum.PersonType.SYS_ADMIN"),
        /**
         * 系统用户
         */
        SYS_USER(8, "sys-org:enum.PersonType.SYS_USER");

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

        public static class Converter extends IEnum.Converter<Integer, PersonType> {
        }
    }

}
