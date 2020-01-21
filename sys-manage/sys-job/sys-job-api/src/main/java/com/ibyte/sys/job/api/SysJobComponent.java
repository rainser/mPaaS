package com.ibyte.sys.job.api;

import com.ibyte.sys.job.api.entity.JobQueueEntity;

import java.util.List;

/**
 * 任务服务，必须在插件工厂初始化完后才能使用
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface SysJobComponent {

    /**
     * 发布消息，由@JobSubscribe的handler进行消费
     *
     * @param entity
     */
    void publish(JobQueueEntity entity);

    /**
     * 发布消息，由@JobSubscribe的handler进行消费
     *
     * @param entities
     */
    void publish(List<JobQueueEntity> entities);

    /**
     * 手动订阅
     *
     * @param config
     */
    void subscribe(SubscribeConfig config);

}
