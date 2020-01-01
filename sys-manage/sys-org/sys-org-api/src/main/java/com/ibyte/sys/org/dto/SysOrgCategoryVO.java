package com.ibyte.sys.org.dto;

import com.ibyte.common.core.dto.AbstractVO;
import com.ibyte.common.core.dto.IdNameProperty;
import com.ibyte.sys.org.constant.CategoryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 组织类别VO类
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Setter
@Getter
@ToString(callSuper = true)
@ApiModel(value = "sysOrgCategoryVo", description = "组织类别VO类")
public class SysOrgCategoryVO extends AbstractVO {
    /**
     * 类别名称
     */
    @ApiModelProperty(value = "类别名称", dataType = "String")
    private String fdName;
    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号", dataType = "Integer")
    private Integer fdOrder;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", dataType = "Date")
    private Date fdCreateTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", dataType = "Date")
    private Date fdAlterTime;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型", dataType = "CategoryType", allowableValues = "1,2")
    private CategoryType fdType;
    /**
     * 上级
     */
    @ApiModelProperty(value = "上级", dataType = "IdNameProperty")
    private IdNameProperty fdParent;
    /**
     * 层级ID
     */
    @ApiModelProperty(value = "层级ID", dataType = "String", readOnly = true)
    private String fdHierarchyId;
    /**
     * 层级数
     */
    @ApiModelProperty(value = "层级数", dataType = "Integer", readOnly = true)
    private Integer fdTreeLevel;
}
