package com.ibyte.sys.org.core.service;

import com.ibyte.sys.org.core.api.ISysOrgGroupApi;
import com.ibyte.sys.org.core.entity.SysOrgGroup;
import com.ibyte.sys.org.core.repository.SysOrgGroupRepository;
import com.ibyte.sys.org.dto.SysOrgGroupVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 群组服务层
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Slf4j
@Service
@RestController
@RequestMapping("/api/sys-org/sysOrgGroup")
@Transactional(readOnly = true, rollbackFor = {})
public class SysOrgGroupService extends AbstractElementCommonService<SysOrgGroupRepository, SysOrgGroup, SysOrgGroupVO> implements ISysOrgGroupApi {

    @Override
    public void update(SysOrgGroup entity) {
        super.update(entity);
    }

    @Override
    public void delete(SysOrgGroup entity) {
        super.delete(entity);
    }

}
