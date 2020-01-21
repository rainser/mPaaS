package com.ibyte.sys.job.support.db;

import com.ibyte.sys.job.api.entity.JobQueueEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 任务队列持久化服务
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface DbJobQueuePersistenceService {

    /**
     * 批量保存
     *
     * @param entities
     */
    @PostMapping("saveAll")
    void saveAll(@RequestBody List<JobQueueEntity> entities);
}
