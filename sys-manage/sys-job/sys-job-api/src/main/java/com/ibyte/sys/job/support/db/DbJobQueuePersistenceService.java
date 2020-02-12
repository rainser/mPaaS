package com.ibyte.sys.job.support.db;

import com.ibyte.common.core.dto.MechanismDTO;
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

    /**
     * 根据ID查找
     *
     * @param dto
     * @return
     */
    @PostMapping("findById")
    JobQueueEntity findById(@RequestBody MechanismDTO dto);

    /**
     * 删除任务
     *
     * @param dto
     */
    @PostMapping("delete")
    void delete(@RequestBody MechanismDTO dto);


    /**
     * 加载待处理任务ID列表
     *
     * @param query
     * @return
     */
    @PostMapping("loadJobIds")
    List<String> loadJobIds(@RequestBody JobQueueIdQuery query);

    /**
     * 加载待处理任务ID列表
     *
     * @param query
     * @return
     */
    @PostMapping("loadJobLockIds")
    public List<String> loadJobLockIds(@RequestBody JobQueueIdQuery query);


}
