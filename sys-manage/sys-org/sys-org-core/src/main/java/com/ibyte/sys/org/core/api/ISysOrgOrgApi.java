package com.ibyte.sys.org.core.api;

import com.ibyte.common.core.dto.IdVO;
import com.ibyte.sys.org.dto.SysOrgOrgVO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 机构API接口
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface ISysOrgOrgApi extends ISysOrgElementApi<SysOrgOrgVO>{

    /**
     * 将机构转换为部门
     * <p>
     * 注意：部门下的子级不能有机构，所有需要判断当前机构是否有子机构。
     *
     * @param id
     */
    void updateOrgToDept(@RequestBody IdVO id);
}
