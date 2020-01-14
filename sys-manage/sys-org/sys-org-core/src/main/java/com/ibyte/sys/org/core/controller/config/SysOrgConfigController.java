package com.ibyte.sys.org.core.controller.config;

import com.ibyte.common.dto.Response;
import com.ibyte.sys.org.core.entity.config.SysOrgConfig;
import com.ibyte.sys.org.core.service.config.SysOrgConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * 组织架构开关
 * 
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1 */
@RestController
@RequestMapping("/data/sys-org/sysOrgConfig")
public class SysOrgConfigController {

    @Autowired
    private SysOrgConfigService sysOrgConfigService;

    /**
     * 更新配置
     *
     * @param config
     * @return
     */
    @PostMapping("update")
    public Response update(@RequestBody SysOrgConfig config) {
        sysOrgConfigService.setSysOrgConfig(config);
        return Response.ok();
    }

    /**
     * 获取配置
     *
     * @return
     */
    @PostMapping("get")
    public Response get() {
        return Response.ok(sysOrgConfigService.getSysOrgConfig());
    }

}