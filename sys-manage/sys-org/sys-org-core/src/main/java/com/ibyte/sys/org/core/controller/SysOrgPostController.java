package com.ibyte.sys.org.core.controller;

import com.ibyte.common.core.dto.IdVO;
import com.ibyte.common.core.dto.IdsDTO;
import com.ibyte.common.core.dto.QueryRequest;
import com.ibyte.common.core.dto.QueryResult;
import com.ibyte.common.dto.Response;
import com.ibyte.common.exception.NoRecordException;
import com.ibyte.sys.org.core.api.ISysOrgPostApi;
import com.ibyte.sys.org.dto.IdsParentVO;
import com.ibyte.sys.org.dto.SysOrgPostVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * 岗位控制层
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@RestController
@RequestMapping("/data/sys-org/sysOrgPost")
public class SysOrgPostController extends AbstractElementCommonController<ISysOrgPostApi, SysOrgPostVO> {


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
    public Response updateOrders(@RequestBody List<SysOrgPostVO> list) {
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
    public Response init(@RequestBody Optional<SysOrgPostVO> vo) {
        return Response.ok(getApi().init(vo));
    }


    /**
     * 新增接口
     *
     * @param vo
     * @return
     */
    @PostMapping("add")
    @ApiOperation("新增接口")
    public Response add(@RequestBody SysOrgPostVO vo) {
        getApi().add(vo);
        return Response.ok();
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
     * 更新接口
     *
     * @param vo
     * @return
     */
    @PostMapping("update")
    @ApiOperation("更新接口")
    public Response update(@RequestBody SysOrgPostVO vo) {
        getApi().update(vo);
        return Response.ok();
    }

    /**
     * 列表查询接口
     *
     * @param request
     * @return
     */
    @PostMapping("list")
    @ApiOperation("列表查询接口")
    public Response<QueryResult<SysOrgPostVO>> list(@RequestBody QueryRequest request) {
        return Response.ok(getApi().findAll(request));
    }
}
