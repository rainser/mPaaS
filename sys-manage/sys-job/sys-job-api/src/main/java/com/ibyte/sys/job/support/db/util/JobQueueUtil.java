package com.ibyte.sys.job.support.db.util;

import com.alibaba.fastjson.JSON;
import com.ibyte.framework.meta.Meta;
import com.ibyte.framework.meta.MetaApplication;
import com.ibyte.framework.support.ApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Slf4j
public class JobQueueUtil {

    private static final Map<String, String> JOB_APP_MAP = new ConcurrentHashMap<>();
    private static final Map<String, String> JOB_DB_MAP = new ConcurrentHashMap<>();
    private static String LOCAL_DB;

    /** 是否本地数据库 */
    public static boolean isLocalDb(String jobType) {
        getJobAppName(jobType);
        return LOCAL_DB.equals(JOB_DB_MAP.get(jobType));
    }

    /** 获取任务队列所属应用 */
    public static String getJobAppName(String jobType) {
        String appName = JOB_APP_MAP.get(jobType);
        if (appName == null) {
            String module = Meta.getEntity(jobType).getModule();
            appName = Meta.getModule(module).getAppName();
            List<MetaApplication> apps = Meta.getApplications();
            log.info("发送消息到MQ：调用getJobAppName方法，获取到 apps = {}", JSON.toJSONString(apps));
            JOB_DB_MAP.put(jobType, findDbId(apps, appName));
            if (LOCAL_DB == null) {
                LOCAL_DB = findDbId(apps,
                        ApplicationContextHolder.getApplicationName());
            }
            JOB_APP_MAP.put(jobType, appName);
        }
        return appName;
    }

    public static String findDbId(List<MetaApplication> apps, String appName) {
        for (MetaApplication app : apps) {
            if (app.getAppName().equals(appName)) {
                return app.getDbId();
            }
        }
        return null;
    }
}
