package com.ibyte.sys.job.spi;

import com.ibyte.framework.plugin.annotation.LocalExtensionPoint;
import com.ibyte.sys.job.support.SysJobComponentImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 任务队列服务实现扩展点
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@LocalExtensionPoint(label = "任务队列服务", baseOn = IJobQueueService.class, manager = SysJobComponentImpl.class)
public @interface JobQueueServiceExtension {
    int order() default 0;
}
