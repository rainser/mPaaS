package com.ibyte.sys.org.core.service;

import com.ibyte.sys.org.core.api.ISysOrgPostApi;
import com.ibyte.sys.org.core.entity.SysOrgPost;
import com.ibyte.sys.org.core.repository.SysOrgPostRepository;
import com.ibyte.sys.org.dto.SysOrgPostVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 岗位服务层
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Service
@RestController
@RequestMapping("/api/sys-org/sysOrgPost")
@Transactional(readOnly = true, rollbackFor = {})
public class SysOrgPostService extends AbstractElementCommonService<SysOrgPostRepository, SysOrgPost, SysOrgPostVO> implements ISysOrgPostApi {
}
