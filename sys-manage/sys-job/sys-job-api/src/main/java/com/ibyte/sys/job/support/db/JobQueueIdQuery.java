package com.ibyte.sys.job.support.db;

import com.ibyte.common.core.entity.IEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 任务Id请求
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobQueueIdQuery {
    private Class<? extends IEntity> jobType;
    private String topic;
    private long fromTime;
}
