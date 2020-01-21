package com.ibyte.sys.job.support.db;

import com.ibyte.sys.job.api.SubscribeConfig;
import com.ibyte.sys.job.api.entity.JobQueueEntity;
import com.ibyte.sys.job.spi.IJobQueueService;
import com.ibyte.sys.job.spi.JobQueueServiceExtension;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 基于数据库实现队列
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Component
@JobQueueServiceExtension(order = Integer.MAX_VALUE)
@Slf4j
public class DbJobQueueServiceImpl implements IJobQueueService, DisposableBean {

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void publish(List<JobQueueEntity> entities) {
        if (entities.isEmpty()) {
            return;
        }


    }

    @Override
    public void subscribe(SubscribeConfig config) {

    }

    @Override
    public void destroy() throws Exception {

    }
}
