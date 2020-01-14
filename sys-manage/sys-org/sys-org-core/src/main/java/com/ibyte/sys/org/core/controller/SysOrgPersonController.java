package com.ibyte.sys.org.core.controller;

import com.ibyte.common.core.dto.IdVO;
import com.ibyte.common.core.dto.IdsDTO;
import com.ibyte.common.core.dto.QueryRequest;
import com.ibyte.common.core.dto.QueryResult;
import com.ibyte.common.dto.Response;
import com.ibyte.common.exception.NoRecordException;
import com.ibyte.sys.org.core.api.ISysOrgPersonApi;
import com.ibyte.sys.org.core.service.SysOrgPersonService;
import com.ibyte.sys.org.dto.IdsParentVO;
import com.ibyte.sys.org.dto.PersonAccountVO;
import com.ibyte.sys.org.dto.SysOrgPersonVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * 人员控制层
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@RestController
@RequestMapping("/data/sys-org/sysOrgPerson")
public class SysOrgPersonController extends AbstractElementCommonController<ISysOrgPersonApi, SysOrgPersonVO> {

    @Autowired
    private SysOrgPersonService sysOrgPersonService;

    /**
     * 快捷增加人员与账号
     *
     * @param vo
     * @return
     */
    @PostMapping("addPersonAccount")
    @ApiOperation("快捷增加人员与账号")
    public Response addPersonAccount(@RequestBody PersonAccountVO vo) {
        sysOrgPersonService.addPersonAccount(vo);
        return Response.ok();
    }

    /**
     * 快捷修改人员与账号
     *
     * @param vo
     * @return
     */
    @PostMapping("updatePersonAccount")
    @ApiOperation("快捷修改人员与账号")
    public Response updatePersonAccount(@RequestBody PersonAccountVO vo) {
        sysOrgPersonService.updatePersonAccount(vo);
        return Response.ok();
    }

    /**
     * 置为无效接口
     *
     * @param id
     * @return
     */
    @PostMapping("invalidated")
    @ApiOperation("置为无效接口")
    @Override
    public Response invalidated(@RequestBody IdVO id) {
        return super.invalidated(id);
    }


    /**
     * 批量置为无效接口
     *
     * @param ids
     * @return
     */
    @PostMapping("invalidatedAll")
    @ApiOperation("批量置为无效接口")
    @Override
    public Response invalidatedAll(@RequestBody IdsDTO ids) {
        return super.invalidatedAll(ids);
    }


    /**
     * 批量修改上级接口
     *
     * @param ids
     * @return
     */
    @PostMapping("updateParent")
    @ApiOperation("批量修改上级接口")
    @Override
    public Response updateParent(@RequestBody IdsParentVO ids) {
        return super.updateParent(ids);
    }

    /**
     * 批量修改排序号接口
     *
     * @param list
     * @return
     */
    @PostMapping("updateOrders")
    @ApiOperation("批量修改排序号接口")
    @Override
    public Response updateOrders(@RequestBody List<SysOrgPersonVO> list) {
        return super.updateOrders(list);
    }

    /**
     * 初始化接口
     *
     * @param vo
     * @return
     */
    @PostMapping("init")
    @ApiOperation("初始化接口")
    public Response init(@RequestBody Optional<SysOrgPersonVO> vo) {
        return Response.ok(getApi().init(vo));
    }

    /**
     * 查看接口
     *
     * @param vo
     * @return
     */
    @PostMapping("get")
    @ApiOperation("查看接口")
    public Response get(@RequestBody IdVO vo) {
        Optional result = getApi().loadById(vo);
        if (!result.isPresent()) {
            throw new NoRecordException();
        }
        return Response.ok(result.get());
    }

    /**
     * 列表查询接口
     *
     * @param request
     * @return
     */
    @PostMapping("list")
    @ApiOperation("列表查询接口")
    public Response<QueryResult<SysOrgPersonVO>> list(@RequestBody QueryRequest request) {
        return Response.ok(getApi().findAll(request));
    }

}
