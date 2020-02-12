package com.ibyte.sys.job.support.db;

import com.ibyte.common.constant.NamingConstant;
import com.ibyte.common.util.StringHelper;
import com.ibyte.sys.job.api.entity.TopicJobQueue;
import org.redisson.api.RBoundedBlockingQueue;
import org.redisson.api.RBucket;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;

/**
 * 依赖的服务上下文
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public class DbJobServiceContext implements DbJobQueueConstant {
    RedissonClient redisson;
    DbJobQueuePersistenceService persistenceService;

    /** 通知分发器的订阅主题 */
    RTopic getDistributorTopic(String jobType, String topic) {
        String key = StringHelper.join(KEY_TOPIC, topic, ":",
                NamingConstant.shortName(jobType));
        return redisson.getTopic(key, StringCodec.INSTANCE);
    }

    /** 任务队列 */
    RBoundedBlockingQueue<String> getJobQueue(String jobType, String topic) {
        String key = StringHelper.join(KEY_QUEUE, topic, ":",
                NamingConstant.shortName(jobType));
        RBoundedBlockingQueue<String> queue = redisson
                .getBoundedBlockingQueue(key);
        queue.trySetCapacity(QUEUE_SIZE);
        return queue;
    }

    /** 任务锁 */
    RBucket<String> getJobLock(String lockId, String topic) {
        String key = StringHelper.join(KEY_EXELOCK, topic, ":", lockId);
        return redisson.getBucket(key, StringCodec.INSTANCE);
    }

    /** 获取主题信息 */
    String getTopic(Object entity) {
        if (entity instanceof TopicJobQueue) {
            return ((TopicJobQueue) entity).getFdTopic();
        }
        return null;
    }
}
