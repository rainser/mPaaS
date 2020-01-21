package com.ibyte.sys.job.api.annotation;

import com.ibyte.framework.meta.annotation.Feature;
import com.ibyte.framework.plugin.annotation.LocalExtensionPoint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记该Entity与定时任务有关，删除Entity时同步删除相关定时任务
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Feature
@LocalExtensionPoint(label = "数据字典扩展", config = com.ibyte.sys.job.support.schedule.config.JobScheduledEntity.class)
public @interface JobScheduledEntity {

}
