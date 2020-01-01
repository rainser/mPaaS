package com.ibyte.org.server;

import com.ibyte.org.api.ISysOrgElementApi;
import com.ibyte.org.entity.SysOrgElement;
import com.ibyte.org.repository.SysOrgElementRepository;
import com.ibyte.sys.org.constant.SysOrgConstant;
import com.ibyte.sys.org.dto.SysOrgElementVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


    @Override
    public void updateRelation() {

    }

    @Override
    public long queryCountByNoWithType(SysOrgElementVO vo) {
        return 0;
    }

    @Override
    public boolean existsById(String fdId) {
        return false;
    }

    @Override
    public void updateOrders(List<SysOrgElementVO> list) {

    }

    @Override
    public void checkUniqueName(SysOrgElementVO vo) {

    }

    @Override
    public void checkRequiredNo(SysOrgElementVO vo) {

    }
}
