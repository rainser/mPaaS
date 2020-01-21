package com.ibyte.sys.job.api.annotation;

import com.ibyte.framework.plugin.annotation.LocalExtensionPoint;
import com.ibyte.sys.job.api.JobHandler;
import com.ibyte.sys.job.api.SubscribeConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 任务订阅<br>
 * 1、任务订阅实现类需标记JobSubscribe注解，并实现JobHandler接口。<br>
 * 2、任务详情，请采用Entity的方式定义（实现接口：JobQueueEntity），带锁的任务请在Entity实现LockJobQueue接口。<br>
 * 3、Entity可以不定义Repository，当无MQ的服务时，系统采用数据库的方式持久化Entity，否则交给MQ持久化。<br>
 * 4、采用SysJobCoreService.publish方法，发布一个任务后，JobHandler将接收到该任务。<br>
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@LocalExtensionPoint(label = "任务订阅", baseOn = JobHandler.class, config = SubscribeConfig.class)
public @interface JobQueueSubscribe {
    /**
     * 任务队列名称
     */
    String messageKey();

    /**
     * 线程名称前缀
     */
    String threadName() default "sys-job";

    /**
     * 默认线程数
     */
    int threadSize() default 5;

    /**
     * 订阅子主题
     */
    String topic() default "";
}
