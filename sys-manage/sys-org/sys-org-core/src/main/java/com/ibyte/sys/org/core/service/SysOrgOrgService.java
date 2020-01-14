package com.ibyte.sys.org.core.service;

import com.ibyte.common.core.dto.IdVO;
import com.ibyte.common.exception.KmssServiceException;
import com.ibyte.common.exception.NoRecordException;
import com.ibyte.common.i18n.ResourceUtil;
import com.ibyte.common.util.TenantUtil;
import com.ibyte.sys.org.core.api.ISysOrgOrgApi;
import com.ibyte.sys.org.core.entity.SysOrgOrg;
import com.ibyte.sys.org.core.repository.SysOrgOrgRepository;
import com.ibyte.sys.org.dto.SysOrgOrgVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 机构服务层
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Service
@RestController
@RequestMapping("/api/sys-org/sysOrgOrg")
@Transactional(readOnly = true, rollbackFor = {})
public class SysOrgOrgService extends AbstractElementCommonService<SysOrgOrgRepository, SysOrgOrg, SysOrgOrgVO> implements ISysOrgOrgApi {


    /**
     * 置为无效
     *
     * @param element
     */
    @Transactional(rollbackFor = {})
    @Override
    public void invalidated(SysOrgOrg element) {
        // 有子类不能置为无效
        if (element.getFdChildren() != null && !element.getFdChildren().isEmpty()) {
            throw new KmssServiceException("sys-org:error.sysOrgElement.invalidated.existChildren");
        }

        super.invalidated(element);
    }

    /**
     * 将机构转化为部门
     * <p>
     *     注意：部门下的子级不能有机构，所有需要判断当前机构是否有子机构。
     * </p>
     * @param id
     */
    @Override
    public void updateOrgToDept(IdVO id) {
        Optional<SysOrgOrg> optional = findById(id.getFdId());
        if (!optional.isPresent()) {
            throw new NoRecordException();
        }
        SysOrgOrg org = optional.get();
        // 判断该机构是否包含子架构
        if (!repository.findSubOrg(org, TenantUtil.getTenantId()).isEmpty()) {
            throw new KmssServiceException("sys-org:error.sysOrgElement.updateOrgToDept",
                    ResourceUtil.replaceArgs(ResourceUtil.getString("sys-org:error.sysOrgElement.updateOrgToDept"), org.getFdName()));
        }
        //更新当前机构的上级机构
        if (org.getFdParent() != null) {
            if (org.getFdParent() != null) {
                // 如果有上级，那么上级一定是机构，需要更新到部门的上级机构中
                repository.updateOrgToDept(org.getFdId(), org.getFdParent().getFdId(), TenantUtil.getTenantId());
            } else {
                repository.updateOrgToDept(org.getFdId(), null, TenantUtil.getTenantId());
            }
        }
        // 更新所有子级的上级机构
        repository.updateAllParentOrg(org.getFdParent(), org.getFdHierarchyId() + "%", TenantUtil.getTenantId());
        // 更新组织架构层级关系
        super.updateRelation();

    }
}
