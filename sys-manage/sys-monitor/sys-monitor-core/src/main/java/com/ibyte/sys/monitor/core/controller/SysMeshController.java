package com.ibyte.sys.monitor.core.controller;

import com.ibyte.common.core.controller.AbstractController;
import com.ibyte.common.core.controller.CrudController;
import com.ibyte.sys.monitor.api.ISysMeshApi;
import com.ibyte.sys.monitor.dto.SysMeshVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service Mesh
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1 */
@RestController
@RequestMapping("/data/sys-monitor/sysMesh")
public class SysMeshController extends AbstractController<ISysMeshApi, SysMeshVO> implements CrudController<ISysMeshApi, SysMeshVO> {

}