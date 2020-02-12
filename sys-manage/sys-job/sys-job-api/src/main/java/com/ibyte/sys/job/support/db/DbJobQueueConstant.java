package com.ibyte.sys.job.support.db;

/**
 * 任务队列常量
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface DbJobQueueConstant {

    /** 数据库每次查询条数 */
    static final int QUERY_SIZE = 1000;
    /** 任务队列长度 */
    static final int QUEUE_SIZE = 500;

    /** 分发器通知主题前缀 */
    static final String KEY_TOPIC = "JobQueueDistributor:";
    /** 分发器独占锁前缀 */
    static final String KEY_DISLOCK = "JobQueueDisLock:";
    /** 任务执行锁前缀 */
    static final String KEY_EXELOCK = "JobQueueExeLock:";
    /** 任务队列前缀 */
    static final String KEY_QUEUE = "JobQueue:";
}
