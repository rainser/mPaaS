package com.ibyte.sys.org.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 组织架构同步接口
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">lishangzhi</a>
 * @since 1.0.1
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OrgSyncPoint {
    /**
     * 系统标识
     */
    String sysCode();

    /**
     * 系统名称
     */
    String sysName() default "";

    /**
     * 系统名称 国际化key
     */
    String sysMessageKey() default "";

    /**
     * 系统描述
     */
    String sysDesc() default "";

    /**
     * 系统访问前缀
     */
    String prefixUrl();
}