package com.ibyte.sys.org.core.controller;

import com.ibyte.common.core.dto.IdVO;
import com.ibyte.common.core.dto.QueryRequest;
import com.ibyte.common.core.dto.QueryResult;
import com.ibyte.common.dto.Response;
import com.ibyte.common.exception.NoRecordException;
import com.ibyte.sys.org.core.api.ISysOrgGroupApi;
import com.ibyte.sys.org.dto.SysOrgGroupVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 群组-控制层
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@RestController
@RequestMapping("/data/sys-org/sysOrgGroup")
public class SysOrgGroupController extends AbstractElementCommonController<ISysOrgGroupApi, SysOrgGroupVO> {

    /**
     * 初始化接口
     * @param vo
     * @return
     */
    @PostMapping("init")
    @ApiOperation("初始化接口")
    public Response init(@RequestBody Optional<SysOrgGroupVO> vo) {
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
    public Response add(@RequestBody SysOrgGroupVO vo) {
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
    public Response update(@RequestBody SysOrgGroupVO vo) {
        getApi().update(vo);
        return Response.ok();
    }

    /**
     *
     * @param request
     * @return
     */
    @PostMapping("list")
    @ApiOperation("列表查询接口")
    public Response<QueryResult<SysOrgGroupVO>> list(@RequestBody QueryRequest request) {
        return Response.ok(getApi().findAll(request));
    }

}
