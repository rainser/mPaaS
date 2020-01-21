package com.ibyte.sys.job.spi;

import com.ibyte.sys.job.api.SubscribeConfig;
import com.ibyte.sys.job.api.entity.JobQueueEntity;

import java.util.List;

/**
 * 队列服务
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface IJobQueueService {

    /**
     * 是否生效
     *
     * @return
     */
    boolean isEnabled();

    /**
     * 发布消息
     *
     * @param entities
     */
    void publish(List<JobQueueEntity> entities);

    /**
     * 订阅消息
     *
     * @param config
     */
    void subscribe(SubscribeConfig config);
}
