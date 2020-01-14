package com.ibyte.sys.org.dto;

import com.ibyte.common.core.dto.IdNameProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@ToString(callSuper = true)
@ApiModel(value = "sysOrgGroupVo", description = "群组VO类")
public class SysOrgGroupVO extends  SysOrgElementVO{

    /**
     * 群组成员
     */
    @ApiModelProperty(value = "群组成员", dataType = "List")
    private List<IdNameProperty> fdElements;

    /**
     * 组织类别
     */
    @ApiModelProperty(value = "组织类别", dataType = "IdNameProperty")
    private IdNameProperty fdCategory;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者", dataType = "IdNameProperty")
    private IdNameProperty fdCreator;
}
