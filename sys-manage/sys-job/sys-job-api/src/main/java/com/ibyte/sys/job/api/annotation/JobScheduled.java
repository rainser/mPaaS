package com.ibyte.sys.job.api.annotation;

import com.ibyte.framework.plugin.annotation.GlobalExtensionPoint;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定时任务
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@GlobalExtensionPoint(label = "定时任务", config = com.ibyte.sys.job.support.schedule.config.JobScheduled.class, listener = "com.ibyte.sys.job.core.service.SysJobScheduleService", scanMemberFor = {
        Service.class,
        Component.class })
public @interface JobScheduled {
    /**
     * 名称
     */
    String messageKey();

    /**
     * 触发时间
     */
    String cron();

    /**
     * 锁ID，相同锁ID的两个定时任务不会同时触发
     */
    String lockId() default "";
}
