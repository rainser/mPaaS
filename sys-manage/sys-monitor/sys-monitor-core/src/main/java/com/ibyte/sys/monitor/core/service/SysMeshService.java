package com.ibyte.sys.monitor.core.service;

import com.ibyte.common.core.service.AbstractServiceImpl;
import com.ibyte.sys.monitor.api.ISysMeshApi;
import com.ibyte.sys.monitor.dto.SysMeshVO;
import com.ibyte.sys.monitor.core.entity.SysMesh;
import com.ibyte.sys.monitor.core.repository.ISysMeshRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;

/** 
 * Service Mesh
 * 
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1 */
@Service
@RestController
@RequestMapping("/api/sys-monitor/sysMesh")
@Transactional(readOnly = true,rollbackFor = {Throwable.class})
public class SysMeshService extends AbstractServiceImpl<ISysMeshRepository,SysMesh,SysMeshVO> implements ISysMeshApi {

}