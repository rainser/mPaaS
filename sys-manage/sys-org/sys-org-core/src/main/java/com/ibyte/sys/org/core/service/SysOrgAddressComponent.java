package com.ibyte.sys.org.core.service;

import com.ibyte.sys.org.core.service.address.SysOrgAddressTreeService;
import com.ibyte.sys.org.api.ISysOrgAddressComponentApi;
import com.ibyte.sys.org.constant.SysOrgConstant;
import com.ibyte.sys.org.dto.SysOrgAddressVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 地址本接口
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Slf4j
@RestController
@RequestMapping("/api/sys-org/sysOrgAddress")
@Transactional(readOnly = true, rollbackFor = {})
public class SysOrgAddressComponent implements ISysOrgAddressComponentApi, SysOrgConstant {

    @Autowired
    private SysOrgAddressTreeService sysOrgAddressTreeService;

    /**
     * 获取获取组织架构树
     *
     * @param vo
     * @return
     */
    @Override
    public List<SysOrgAddressVO> getOrgTree(SysOrgAddressVO vo) {
        return null;
    }
}
