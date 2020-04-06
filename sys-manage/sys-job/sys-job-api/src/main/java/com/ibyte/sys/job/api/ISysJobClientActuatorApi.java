package com.ibyte.sys.job.api;

import com.ibyte.common.core.api.IApi;
import com.ibyte.sys.job.dto.SysJobReturnVO;
import com.ibyte.sys.job.dto.SysJobScheduleVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 调度器执行接口
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface ISysJobClientActuatorApi extends IApi<SysJobReturnVO> {

    /**
     * 执行方法
     * @param sysJobScheduleVO
     * @return
     */
    @PostMapping("execute")
    SysJobReturnVO execute(@RequestBody SysJobScheduleVO sysJobScheduleVO);

}