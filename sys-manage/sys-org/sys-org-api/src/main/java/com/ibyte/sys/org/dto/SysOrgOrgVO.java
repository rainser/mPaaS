package com.ibyte.sys.org.dto;

import com.ibyte.common.core.dto.IdNameProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 机构Vo类
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public class SysOrgOrgVO extends SysOrgElementVO{

    /**
     * 上级领导
     */
    @ApiModelProperty(value = "上级领导", dataType = "IdNameProperty")
    private IdNameProperty fdSuperLeader;
    /**
     * 管理员
     */
    @ApiModelProperty(value = "管理员", dataType = "List")
    private List<IdNameProperty> fdAdmins;
    /**
     * 组织类别
     */
    @ApiModelProperty(value = "组织类别", dataType = "IdNameProperty")
    private IdNameProperty fdCategory;
}
