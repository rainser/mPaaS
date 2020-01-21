package com.ibyte.sys.job.api;

import com.ibyte.sys.job.api.entity.JobQueueEntity;

/**
 * 任务处理器
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface JobHandler<E extends JobQueueEntity> {

    /**
     * 处理任务
     *
     * @param job
     * @throws Exception
     */
    void handleJob(E job) throws Exception;
}
