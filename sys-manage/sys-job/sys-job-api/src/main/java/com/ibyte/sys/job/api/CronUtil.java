package com.ibyte.sys.job.api;

import org.apache.commons.lang3.StringUtils;
import org.redisson.executor.CronExpression;

import java.util.Calendar;
import java.util.Date;

/**
 * 定时任务Cron表达式工具
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public class CronUtil {

    /**
     * 获取下一个执行时间，若表达式非法则返回null
     */
    public static Date next(String expression, Date date) {
        try {
            if (StringUtils.isBlank(expression)) {
                return null;
            }
            return new CronExpression(expression).getNextValidTimeAfter(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取下一个执行时间，若表达式非法则抛出异常
     */
    public static Date nextOrException(String expression, Date date) {
        if (StringUtils.isBlank(expression)) {
            throw new IllegalArgumentException();
        }
        return new CronExpression(expression).getNextValidTimeAfter(date);
    }

    /**
     * 将日期转换成cron表达式
     */
    public static String getCronExpression(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return getCronExpression(c);
    }

    /**
     * 将日期转换成cron表达式
     */
    public static String getCronExpression(Calendar c) {
        StringBuilder rtn = new StringBuilder();
        rtn.append(c.get(Calendar.SECOND)).append(' ')
                .append(c.get(Calendar.MINUTE)).append(' ')
                .append(c.get(Calendar.HOUR_OF_DAY)).append(' ')
                .append(c.get(Calendar.DAY_OF_MONTH)).append(' ')
                .append(c.get(Calendar.MONTH) + 1).append(" ? ")
                .append(c.get(Calendar.YEAR));
        return rtn.toString();
    }
}
