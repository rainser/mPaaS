package com.ibyte.sys.org.core.service;

import com.ibyte.sys.org.core.api.ISysOrgElementApi;
import com.ibyte.sys.org.core.entity.SysOrgElement;
import com.ibyte.sys.org.core.repository.SysOrgElementRepository;
import com.ibyte.sys.org.constant.SysOrgConstant;
import com.ibyte.sys.org.dto.SysOrgElementVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组织架构业务服务层
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Service
@RestController
@RequestMapping("/api/sys-org/sysOrgElement")
@Transactional(readOnly = true, rollbackFor = {})
public class SysOrgElementService extends AbstractElementCommonService<SysOrgElementRepository<SysOrgElement>, SysOrgElement, SysOrgElementVO>
        implements ISysOrgElementApi<SysOrgElementVO>, SysOrgConstant {

}
