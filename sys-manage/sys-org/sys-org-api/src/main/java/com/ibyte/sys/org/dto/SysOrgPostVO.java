package com.ibyte.sys.org.dto;

import com.ibyte.common.core.dto.IdProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 岗位成员
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SysOrgPostVO extends SysOrgElementVO{

    /**
     * 岗位成员
     */
    @ApiModelProperty(value = "岗位成员", dataType = "List")
    private List<IdProperty> fdPersons;
}
