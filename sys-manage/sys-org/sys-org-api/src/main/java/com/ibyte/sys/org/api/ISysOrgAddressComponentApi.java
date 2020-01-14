package com.ibyte.sys.org.api;

import com.ibyte.sys.org.dto.SysOrgAddressVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 地址本接口
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface ISysOrgAddressComponentApi {

    /**
     * 获取获取组织架构树
     *
     * @param vo
     * @return
     */
    @PostMapping("getOrgTree")
    List<SysOrgAddressVO> getOrgTree(@RequestBody SysOrgAddressVO  vo);
}
