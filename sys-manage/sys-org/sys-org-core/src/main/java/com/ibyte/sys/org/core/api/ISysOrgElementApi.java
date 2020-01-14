package com.ibyte.sys.org.core.api;

import com.ibyte.common.core.api.IApi;
import com.ibyte.common.core.dto.IdVO;
import com.ibyte.common.core.dto.IdsDTO;
import com.ibyte.sys.org.dto.IdsParentVO;
import com.ibyte.sys.org.dto.SysOrgElementVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 组织架构公共API
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface ISysOrgElementApi<V extends SysOrgElementVO> extends IApi<V> {

    /**
     * 置为无效
     *
     * @param id
     * @return
     */
    void invalidated(@RequestBody IdVO id);

    /**
     * 批量置为无效
     *
     * @param ids
     * @return
     */
    void invalidatedAll(@RequestBody IdsDTO ids);

    /**
     * 批量修改上级
     *
     * @param ids
     */
    void updateParent(@RequestBody IdsParentVO ids);

    /**
     * 更新组织架构层级关系（适用于修改组织架构的层级关系）
     */
    void updateRelation();


    /**
     * 判断记录是否存在
     *
     * @param fdId
     * @return
     */
    boolean existsById(@RequestBody String fdId);

    /**
     * 批量修改排序号
     *
     * @param list
     */
    void updateOrders(@RequestBody List<V> list);
}
