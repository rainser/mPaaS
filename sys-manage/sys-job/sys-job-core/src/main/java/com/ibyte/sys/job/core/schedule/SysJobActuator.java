package com.ibyte.sys.job.core.schedule;

import com.ibyte.common.util.IDGenerator;
import com.ibyte.common.util.StringHelper;
import com.ibyte.framework.support.ApplicationContextHolder;
import com.ibyte.framework.support.persistent.DesignElementApi;
import com.ibyte.sys.job.api.ISysJobClientActuatorApi;
import com.ibyte.sys.job.core.entity.SysJobLog;
import com.ibyte.sys.job.core.entity.SysJobSchedule;
import com.ibyte.sys.job.core.service.SysJobLogService;
import com.ibyte.sys.job.dto.SysJobReturnVO;
import com.ibyte.sys.job.dto.SysJobScheduleVO;
import com.ibyte.sys.job.support.schedule.JobScheduleConstant;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.ibyte.sys.job.support.db.DbJobQueueConstant.KEY_EXELOCK;
import static com.ibyte.sys.job.support.schedule.JobScheduleConstant.*;

/**
 * 定时任务执行
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @time 2020-04-05 9:30
 */
@Slf4j
public class SysJobActuator implements Runnable {

    private DesignElementApi designElementApi;

    private SysJobLogService sysJobLogService;

    private SysJobSchedule jobSchedule;

    private RedissonClient redisson;


    @Override
    public void run() {
        Long begin = null;
        Long end = null;
        RLock rlock = null;
        ISysJobClientActuatorApi remoteApi;
        SysJobReturnVO returnVO = new SysJobReturnVO();
        SysJobLog jobLog = null;

        log.debug("job:{},method:{},time:{} 需要调用！{}", jobSchedule.getFdId(), jobSchedule.getFdJobMethod(), jobSchedule.getFdNextTime(), Thread.currentThread().getId());
        //检查是否存在此任务执行
        String lockId = StringUtil.isBlank(jobSchedule.getFdLockId())
                ? jobSchedule.getFdId() : jobSchedule.getFdLockId();
        String key = StringHelper.join(KEY_EXELOCK, ":",
                lockId);
        rlock = redisson.getLock(key);
        //1、争抢任务锁
        if (!tryLock(rlock, key)) {
            return;
        }
        //2、计算执行时间
        if (!computeWaitTime()) {
            return;
        }
        log.debug("job:{},method:{} 已调用！{}", jobSchedule.getFdId(), jobSchedule.getFdJobMethod(), Thread.currentThread().getId());
        //3、 TODO 到点刷新下次执行时间、无下次执行时间禁用
        try {
            //4、记录日志、开始调用
            begin = System.currentTimeMillis();
            jobLog = createJobLog(jobSchedule);
            sysJobLogService.add(jobLog);
            //4.1调用具体服务接口
            remoteApi = ApplicationContextHolder.getApi(JobScheduleConstant.JOB_CLIENT_ACTUATOR_URL);
            //remoteApi = ApplicationContextHolder.getRemoteApi(jobSchedule.getFdAppServer().toUpperCase(), JobScheduleConstant.JOB_CLIENT_ACTUATOR_URL, ISysJobClientActuatorApi.class);
            if (remoteApi != null) {
                SysJobScheduleVO sysJobScheduleVO = new SysJobScheduleVO();
                BeanUtils.copyProperties(jobSchedule, sysJobScheduleVO);
                returnVO = remoteApi.execute(sysJobScheduleVO);
            } else {
                returnVO = new SysJobReturnVO();
                returnVO.setContent("服务不存在");
            }
            end = System.currentTimeMillis();
        } catch (Exception e) {
            end = System.currentTimeMillis();
            returnVO.setContent(ExceptionUtils.getStackTrace(e));
            log.error("执行定时任务异常：{}", jobSchedule.getFdId(), e);
        } finally {
            //记录日志信息
            //解锁
            try {
                rlock.unlock();
            } catch (Exception e) {
                log.error("redisson unlock fail ", e);
            }

            if (jobLog != null) {
                //5、 更新任务日志信息
                jobLog.setFdEndTime(new Date(end));
                jobLog.setFdTaskDuration((end - begin) / 1000F);
                updateLogResult(jobLog, returnVO);
            }
        }

    }

    /**
     * 尝试获取锁
     * @param rlock
     * @param key
     * @return true 获取成功  false 获取失败
     */
    private boolean tryLock(RLock rlock, String key) {
        try {
            Boolean lock = rlock.tryLock(JOB_KEY_WAIT_TIME, JOB_KEY_LOCK_TIME, TimeUnit.MILLISECONDS);
            if (!lock) {
                log.warn("job:{},lock:{} 被占用！", jobSchedule.getFdId(), key);
                return false;
            }
        } catch (InterruptedException e) {
            log.error("获取锁异常，{}", jobSchedule.getFdId(), e);
            return false;
        }
        return true;
    }


    /**
     * 计算是否符合执行时间差
     * 1、不符合，直接跳过不执行
     * 2、符合、计算等待时间，线程睡眠等待执行
     * @return true 执行  false 跳过
     */
    private boolean computeWaitTime() {
        long tmp = jobSchedule.getFdNextTime() - System.currentTimeMillis();
        if (tmp > JOBEXE_EXPIRE_MIN) {
            log.info("job:{},sleep time too lang:{} ms,{}", jobSchedule.getFdId(), tmp, jobSchedule.getFdNextTime());
            return false;
        }
        if (tmp > 0) {
            log.debug("job:{},sleep time:{} ms", jobSchedule.getFdId(), tmp);
            try {
                Thread.sleep(tmp);
            } catch (Exception e) {
            }
        }
        return true;
    }

    private void updateLogResult(SysJobLog jobLog, SysJobReturnVO returnVO) {
        if (jobLog != null && returnVO != null) {
            jobLog.setFdSuccess(returnVO.getSuccess());
            jobLog.setFdContent(returnVO.getContent());
            sysJobLogService.update(jobLog);
        }
    }

    /**
     * 增加错误日志
     *
     * @param jobSchedule
     */
    private SysJobLog createJobLog(SysJobSchedule jobSchedule) {
        SysJobLog logVO = new SysJobLog();
        logVO.setFdId(IDGenerator.generateID());
        logVO.setFdJobId(jobSchedule.getFdId());
        logVO.setFdAppServer(jobSchedule.getFdAppServer());
        logVO.setFdSubject(jobSchedule.getFdSubject());
        logVO.setFdStartTime(new Date());
        logVO.setFdJobService(jobSchedule.getFdJobService());
        logVO.setFdJobMethod(jobSchedule.getFdJobMethod());
        return logVO;
    }
}
