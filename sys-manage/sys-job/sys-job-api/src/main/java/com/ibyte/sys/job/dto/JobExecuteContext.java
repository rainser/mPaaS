package com.ibyte.sys.job.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 定时任务运行上下文
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
public class JobExecuteContext {
    private String fdParameter;
}

