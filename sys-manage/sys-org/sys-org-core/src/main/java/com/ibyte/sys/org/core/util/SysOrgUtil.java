package com.ibyte.sys.org.core.util;

import com.ibyte.common.util.StringHelper;
import com.ibyte.sys.org.core.entity.SysOrgElement;
import com.ibyte.sys.org.constant.SysOrgConstant;
import com.ibyte.sys.org.entity.SysOrgElementSummary;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * 组织架构工具
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Slf4j
public class SysOrgUtil implements SysOrgConstant {


    /**
     * 获取用户可见的顶级组织数据（会过滤上下级组织类型的数据，在地址本中保留上下级组织的层级关系）
     *
     * @param person
     * @return
     * @throws Exception
     */
    public Set<String> getPersonRootVisibleOrgIds(SysOrgElementSummary person) {
        return null;
    }


    public Set<String> getPersonVisibleOrgIds(SysOrgElementSummary person) {

        return null;
    }

    public static String buildHierarchyIdIncludeOrg(SysOrgElement element) {
        String hierarchyIdIncludeOrg = element.getFdHierarchyId();
        if (element.getFdOrgType().equals(ORG_TYPE_ORG)) {
            SysOrgElement parent = element.getFdParent();
            while (parent != null) {
                hierarchyIdIncludeOrg = StringHelper.join("x", parent.getFdId(), hierarchyIdIncludeOrg);
                parent = parent.getFdParent();

            }
        } else {
            SysOrgElement org = element.getFdParentOrg();
            if (org != null) {
                org = org.getFdParent();
                while (org != null) {
                    hierarchyIdIncludeOrg = StringHelper.join("x", org.getFdId(), hierarchyIdIncludeOrg);
                    org = org.getFdParent();

                }
            }
        }
        return hierarchyIdIncludeOrg;
    }

}
