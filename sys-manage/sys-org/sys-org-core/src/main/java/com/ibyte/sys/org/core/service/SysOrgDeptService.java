package com.ibyte.sys.org.core.service;

import com.ibyte.sys.org.core.api.ISysOrgDeptApi;
import com.ibyte.sys.org.core.entity.SysOrgDept;
import com.ibyte.sys.org.core.repository.SysOrgDeptRepository;
import com.ibyte.sys.org.dto.SysOrgDeptVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门服务层
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Service
@RestController
@RequestMapping("/api/sys-org/sysOrgDept")
@Transactional(readOnly = true, rollbackFor = {})
public class SysOrgDeptService extends AbstractElementCommonService<SysOrgDeptRepository, SysOrgDept, SysOrgDeptVO> implements ISysOrgDeptApi {
}
