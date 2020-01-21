package com.ibyte.sys.job.api.entity;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ibyte.common.core.entity.IEntity;

/**
 * 队列持久化Entity
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public interface JobQueueEntity extends IEntity {

}
