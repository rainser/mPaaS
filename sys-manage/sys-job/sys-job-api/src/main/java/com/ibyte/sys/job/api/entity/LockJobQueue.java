package com.ibyte.sys.job.api.entity;

import com.ibyte.common.core.data.field.IField;
import org.hibernate.validator.constraints.Length;

/**
 * 任务队列持久化Entity（带锁）
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface LockJobQueue extends JobQueueEntity, IField {
    /**
     * 读-锁ID
     *
     * @return
     */
    @Length(max = 200)
    default String getFdLockId() {
        return (String) getExtendProps().get("fdLockId");
    }

    /**
     * 写-锁ID
     *
     * @param fdLockId
     */
    default void setFdLockId(String fdLockId) {
        getExtendProps().put("fdLockId", fdLockId);
    }
}
