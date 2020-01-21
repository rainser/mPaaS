package com.ibyte.sys.job.api;

import com.ibyte.common.util.ReflectUtil;
import com.ibyte.framework.plugin.annotation.BaseOnProperty;
import com.ibyte.sys.job.api.entity.JobQueueEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 订阅设置
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
public class SubscribeConfig {

    private String threadName = "sys-job";

    private int threadSize = 5;

    private String topic;

    private JobHandler<? extends JobQueueEntity> handler;

    private Class<? extends JobQueueEntity> jobType;

    public SubscribeConfig() {
    }

    public SubscribeConfig(JobHandler<? extends JobQueueEntity> handler) {
        super();
        setHandler(handler);
    }

    @SuppressWarnings("unchecked")
    @BaseOnProperty
    public void setHandler(JobHandler<? extends JobQueueEntity> handler) {
        this.handler = handler;
        this.jobType = (Class<? extends JobQueueEntity>) ReflectUtil
                .getActualClass(handler.getClass(), JobHandler.class, "E");
    }
}
