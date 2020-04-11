package com.ibyte.sys.job.support.schedule;

import lombok.Getter;
import lombok.Setter;
import org.redisson.api.RedissonClient;

/**
 * 依赖服务的上下文
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Setter
@Getter
public class JobScheduleServiceContext implements JobScheduleConstant {

    private ISysJobScheduleApi sysJobScheduleApi;

    private RedissonClient redisson;


}
