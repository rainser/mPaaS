package com.ibyte.sys.org.core.service.config;

import com.ibyte.common.util.TenantUtil;
import com.ibyte.framework.config.ApplicationConfigApi;
import com.ibyte.sys.org.core.entity.config.SysOrgConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 
 * 组织架构开关
 * 
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1 */
@Slf4j
@Service
@Transactional(readOnly = true, rollbackFor = {})
public class SysOrgConfigService {

    @Autowired
    private ApplicationConfigApi applicationConfigApi;

    /**
     * 获取配置
     *
     * @return
     */
    public SysOrgConfig getSysOrgConfig() {
        SysOrgConfig config = applicationConfigApi.get(SysOrgConfig.class, TenantUtil.getTenantId());
        if (config == null) {
            config = new SysOrgConfig();
        }
        return config;
    }

    /**
     * 保存配置
     *
     * @param config
     */
    @Transactional(rollbackFor = {})
    public void setSysOrgConfig(SysOrgConfig config) {
        applicationConfigApi.save(config, TenantUtil.getTenantId());
    }

}