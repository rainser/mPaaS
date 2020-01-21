package com.ibyte.sys.job.api.entity;

import com.ibyte.common.core.data.field.IField;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 带子主题的任务队列
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Table(indexes = @Index(columnList = "fdTopic, fdId"))
public interface TopicJobQueue extends JobQueueEntity, IField {
    /**
     * 读-Topic
     *
     * @return
     */
    @Length(max = 200)
    default String getFdTopic() {
        return (String) getExtendProps().get("fdTopic");
    }

    /**
     * 写-Topic
     *
     * @param fdTopic
     */
    default void setFdTopic(String fdTopic) {
        getExtendProps().put("fdTopic", fdTopic);
    }
}