package com.ibyte.sys.org.core.service;

import com.ibyte.common.core.mapper.EntityToVoMapper;
import com.ibyte.common.core.service.AbstractServiceImpl;
import com.ibyte.common.util.TenantUtil;
import com.ibyte.sys.org.core.api.ISysOrgAccountApi;
import com.ibyte.sys.org.core.entity.SysOrgAccount;
import com.ibyte.sys.org.core.repository.SysOrgAccountRepository;
import com.ibyte.sys.org.dto.SysOrgAccountVO;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 人员账号服务
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Service
@RestController
@RequestMapping("/api/sys-org/sysOrgAccount")
@Transactional(readOnly = true, rollbackFor = {})
public class SysOrgAccountService extends AbstractServiceImpl<SysOrgAccountRepository, SysOrgAccount, SysOrgAccountVO> implements ISysOrgAccountApi {
    /**
     * 根据登录名获取账号
     *
     * @param loginName
     * @return
     */
    @Override
    public SysOrgAccountVO getByLoginName(String loginName) {
        SysOrgAccount account = getEntityByLoginName(loginName);
        SysOrgAccountVO vo = new SysOrgAccountVO();
        EntityToVoMapper.getInstance().entityToVo(account, vo, false);
        return vo;
    }

    /**
     * 根据登录名获取账号
     *
     * @param loginName
     * @return
     */
    public SysOrgAccount getEntityByLoginName(String loginName) {
        return repository.findByLoginName(loginName, true, TenantUtil.getTenantId());
    }

    /**
     * 检查登录名是否唯一
     *
     * @param fdId
     * @param fdLoginName
     * @param fdIsActivated
     * @return
     */
    @Override
    public boolean checkLoginNameUnique(String fdId, String fdLoginName, Boolean fdIsActivated) {
        if (fdIsActivated == null) {
            fdIsActivated = Boolean.TRUE;
        }
        if (BooleanUtils.isTrue(fdIsActivated)) {
            return repository.checkLoginNameUnique(fdId, fdLoginName, fdIsActivated, TenantUtil.getTenantId()) == null;
        } else {
            // 无效人员的登录名可能会有多个重复的，所以无效人员不作检查
            return true;
        }
    }
}
