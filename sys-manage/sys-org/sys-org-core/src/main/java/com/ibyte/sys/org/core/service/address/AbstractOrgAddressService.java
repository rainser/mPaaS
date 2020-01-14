package com.ibyte.sys.org.core.service.address;

import com.ibyte.sys.org.core.service.SysOrgElementService;
import com.ibyte.sys.org.constant.SysOrgConstant;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * 地址本抽象服务
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public class AbstractOrgAddressService implements SysOrgConstant {

    @Autowired
    protected EntityManager entityManager;

    @Autowired
    protected SysOrgElementService sysOrgElementService;
}
