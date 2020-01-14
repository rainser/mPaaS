package com.ibyte.sys.org.core.controller;

import com.alibaba.fastjson.JSONObject;
import com.ibyte.common.core.controller.AbstractController;
import com.ibyte.common.core.dto.IdVO;
import com.ibyte.common.core.dto.IdsDTO;
import com.ibyte.common.dto.Response;
import com.ibyte.framework.meta.MetaEntity;
import com.ibyte.sys.org.core.api.ISysOrgElementApi;
import com.ibyte.sys.org.core.entity.config.SysOrgConfig;
import com.ibyte.sys.org.core.service.config.SysOrgConfigService;
import com.ibyte.sys.org.dto.IdsParentVO;
import com.ibyte.sys.org.dto.SysOrgElementVO;
import com.ibyte.sys.org.dto.SysOrgOrgVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 组织架构抽象公共控制层
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Slf4j
public abstract class AbstractElementCommonController<A extends ISysOrgElementApi<V>, V extends SysOrgElementVO> extends AbstractController<A, V> {

    @Autowired
    SysOrgConfigService sysOrgConfigService;


    /**
     * 置为无效
     *
     * @param id
     * @return
     */
    @PostMapping("invalidated")
    @ApiOperation("置为无效接口")
    public Response invalidated(@RequestBody IdVO id) {
        getApi().invalidated(id);
        return Response.ok();
    }

    /**
     * 批量置为无效
     *
     * @param ids
     * @return
     */
    @PostMapping("invalidatedAll")
    @ApiOperation("批量置为无效接口")
    public Response invalidatedAll(@RequestBody IdsDTO ids) {
        getApi().invalidatedAll(ids);
        return Response.ok();
    }

    /**
     * 批量修改上级
     *
     * @param ids
     * @return
     */
    @PostMapping("updateParent")
    @ApiOperation("批量修改上级接口")
    public Response updateParent(@RequestBody IdsParentVO ids) {
        getApi().updateParent(ids);
        return Response.ok();
    }

    /**
     * 批量修改排序号
     *
     * @param list
     * @return
     */
    @PostMapping("updateOrders")
    @ApiOperation("批量修改排序号接口")
    public Response updateOrders(@RequestBody List<V> list){
        getApi().updateOrders(list);
        return Response.ok();
    }

    /**
     * 读取数据字典
     *
     * @return
     */

    @PostMapping("meta")
    public Response meta() {
        MetaEntity metaEntity = MetaEntity.localEntity(getEntityName());
        JSONObject json = (JSONObject) JSONObject.toJSON(metaEntity);
        JSONObject properties = json.getJSONObject("properties");
        // 判断是否开启编号必填，如果是必填，需要修改编号校验必填
        SysOrgConfig config = sysOrgConfigService.getSysOrgConfig();
        if (BooleanUtils.isTrue(config.getIsNoRequired())) {
            JSONObject fdNo = properties.getJSONObject("fdNo");
            fdNo.put("notNull", true);
        }
        if (BooleanUtils.isTrue(config.getKeepGroupUnique())) {
            JSONObject fdName = properties.getJSONObject("fdName");
            fdName.put("unique", true);
        }
        return Response.ok(json);
    }
}
