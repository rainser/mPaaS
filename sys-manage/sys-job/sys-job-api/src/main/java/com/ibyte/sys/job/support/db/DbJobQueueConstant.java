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
}
