package com.ibyte.sys.job.support;

import com.ibyte.common.core.util.TransactionUtil;
import com.ibyte.framework.plugin.Plugin;
import com.ibyte.sys.job.api.SubscribeConfig;
import com.ibyte.sys.job.api.SysJobComponent;
import com.ibyte.sys.job.api.entity.JobQueueEntity;
import com.ibyte.sys.job.spi.IJobQueueService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务服务，必须在插件工厂初始化完后才能使用
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public class SysJobComponentImpl implements ApplicationListener<ApplicationReadyEvent>,
        SysJobComponent {

    private IJobQueueService jobQueueService;


    @Override
    public void publish(JobQueueEntity entity) {
        List<JobQueueEntity> entities = new ArrayList<>();
        entities.add(entity);
        publish(entities);
    }

    @Override
    public void publish(List<JobQueueEntity> entities) {
        CommitJob result = TransactionUtil.getAfterCommit(CommitJob.class);
        if (result == null) {
            TransactionUtil.afterCommit(new CommitJob(entities));
        } else {
            result.entities.addAll(entities);
        }
    }

    @Override
    public void subscribe(SubscribeConfig config) {
        jobQueueService.subscribe(config);
    }

    /**
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<SubscribeConfig> configs = Plugin
                .getProviders(SubscribeConfig.class);
        for (SubscribeConfig config : configs) {
            subscribe(config);
        }
    }

    @AllArgsConstructor
    private class CommitJob implements Runnable {
        private List<JobQueueEntity> entities;

        @Override
        public void run() {
            jobQueueService.publish(entities);
            entities.clear();
        }
    }
}
