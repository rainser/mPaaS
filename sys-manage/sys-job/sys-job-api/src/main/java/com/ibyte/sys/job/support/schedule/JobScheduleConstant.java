package com.ibyte.sys.job.support.schedule;

import com.ibyte.common.util.DateUtil;

/**
 * 定时任务常量
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface JobScheduleConstant {

    /**
     * 单个节点任务并发数
     */
    int THREAD_SIZE = 10;

    /**
     * 数据库每次查询条数
     */
    int QUERY_SIZE = 1000;
    /**
     * 任务队列长度
     */
    int QUEUE_SIZE = 1000;

    /**
     * 分发器通知主题
     */
    String KEY_TOPIC = "JobScheduleDistributor:Topic";
    /**
     * 分发器独占锁
     */
    String KEY_DISLOCK = "JobScheduleDistributor:Lock";
    /**
     * 任务执行锁前缀
     */
    String KEY_EXELOCK = "JobScheduleExeLock:";
    /**
     * 任务队列前缀
     */
    String KEY_QUEUE = "JobScheduleQueue:";
    /**
     * 定时任务客户端执行器地址
     */
    String JOB_CLIENT_ACTUATOR_URL = "/api/sys-job/sysJobClientActuator";

    /**
     * 把当前时间+JOBEXE_SCOPE之前的任务都认为是本次需执行的任务
     */
    long JOBEXE_SCOPE = DateUtil.ONE_SECOND * 5;
    /**
     * 阻塞任务轮询时间
     */
    long TIME_JOBWAIT = DateUtil.ONE_SECOND * 5;
    /**
     * 任务执行有效期（最小值），小于该值认为不误点
     */
    long JOBEXE_EXPIRE_MIN = DateUtil.ONE_SECOND * 5;
    /**
     * 任务执行锁超时时间
     */
    long JOB_KEY_LOCK_TIME = DateUtil.ONE_MINUTE * 15;
    /**
     * 任务执行锁等待时间
     */
    long JOB_KEY_WAIT_TIME = 0;
    /**
     * 任务失效时间间隔
     */
    long LONGTIME_AFTER = DateUtil.ONE_YEAR * 1000;
    /**
     * 任务分发异常睡眠时间
     */
    long JOB_ERROR_TIME = DateUtil.ONE_MINUTE * 2;


    /**
     * 任务执行有效期（最大值），大于该值的重复任务需等待下一次
     */
    long JOBEXE_EXPIRE_MAX = DateUtil.ONE_MINUTE * 30;
    /**
     * 介于任务执行有效期之间的任务，若离下一次执行时间超过该时长则马上触发
     */
    long JOBEXE_EXPIRE_NEXT = DateUtil.ONE_HOUR * 6;
    /**
     * 任务执行有效期
     */
    long JOBEXE_RETRY = DateUtil.ONE_MINUTE * 30;
    /**
     * 进入队列后最大等待时长
     */
    long JOBQUEUE_EXPIRE = DateUtil.ONE_MINUTE * 30;
    /**
     * 最大时间误差
     */
    long TIME_ERROR = DateUtil.ONE_MINUTE * 10;
    /**
     * 正在执行任务刷新时间
     */
    long TIME_POLL_REFRESH = DateUtil.ONE_MINUTE;
    /**
     * 正在执行的任务超时时间
     */
    long TIME_FAIL = DateUtil.ONE_MINUTE * 5;
    /**
     * 消费监控线程间隔时间
     */
    long TIME_MONITOR = DateUtil.ONE_SECOND * 10;
    /**
     * 数据库查询间隔
     */
    long QUERY_INTERVAL = DateUtil.ONE_HOUR * 2;
}
