package com.ibyte.sys.org.core.controller;

import com.ibyte.common.core.controller.AbstractController;
import com.ibyte.sys.org.core.api.ISysOrgElementApi;
import com.ibyte.sys.org.dto.SysOrgElementVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组织权限管理
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@RestController
@RequestMapping("/data/sys-org/sysOrgElement")
public class SysOrgElementController extends AbstractController<ISysOrgElementApi<SysOrgElementVO>, SysOrgElementVO> {

}
