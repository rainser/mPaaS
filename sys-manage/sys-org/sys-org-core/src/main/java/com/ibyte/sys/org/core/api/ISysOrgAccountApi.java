package com.ibyte.sys.org.core.api;

import com.ibyte.common.core.api.IApi;
import com.ibyte.sys.org.dto.SysOrgAccountVO;

/**
 * 人员账号API接口
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface ISysOrgAccountApi extends IApi<SysOrgAccountVO> {

    /**
     * 根据登录名获取账号
     *
     * @param loginName
     * @return
     */
    SysOrgAccountVO getByLoginName(String loginName);

    /**
     * 检查登录名是否唯一
     *
     * @param id
     * @param loginName
     * @return
     */
    boolean checkLoginNameUnique(String id, String loginName, Boolean fdIsActivated);
}
