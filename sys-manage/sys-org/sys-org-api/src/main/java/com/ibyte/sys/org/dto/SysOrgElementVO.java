package com.ibyte.sys.org.dto;

import com.ibyte.common.core.dto.AbstractVO;
import com.ibyte.common.core.dto.IdNameProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 组织架构VO类
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Setter
@Getter
@ToString(callSuper = true)
@ApiModel(value = "sysOrgElementVo", description = "组织架构VO类")
public class SysOrgElementVO extends AbstractVO {
    /**
     * 原始ID（如果是同步过来的数据，记录原始ID）
     */
    @ApiModelProperty(value = "原始ID", dataType = "String")
    private String fdOriId;

    /**
     * 组织名称
     */
    @ApiModelProperty(value = "名称", dataType = "String", required = true)
    private String fdName;
    /**
     * 名称拼音
     */
    @ApiModelProperty(value = "名称拼音", dataType = "String", readOnly = true)
    private String fdNamePinyin;
    /**
     * 名称简拼
     */
    @ApiModelProperty(value = "名称简拼", dataType = "String", readOnly = true)
    private String fdNameSimplePinyin;
    /**
     * 组织类型
     */
    @ApiModelProperty(value = "类型", dataType = "Integer", allowableValues = "1,2,4,8,16")
    private Integer fdOrgType;
    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号", dataType = "Integer")
    private Integer fdOrder;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", dataType = "Date", readOnly = true)
    private Date fdCreateTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", dataType = "Date", readOnly = true)
    private Date fdAlterTime;
    /**
     * 最后修改时间
     */
    @ApiModelProperty(value = "最后修改时间", dataType = "Timestamp", readOnly = true)
    private Timestamp fdLastModifiedTime;
    /**
     * 上级
     */
    @ApiModelProperty(value = "上级", dataType = "Object")
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
    /**
     * 是否有效
     */
    @ApiModelProperty(value = "是否有效", dataType = "Boolean")
    private Boolean fdIsAvailable;
    /**
     * 是否与业务相关
     */
    @ApiModelProperty(value = "是否与业务相关", dataType = "Boolean")
    private Boolean fdIsBusiness;
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号", dataType = "String")
    private String fdNo;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", dataType = "String")
    private String fdDescribe;
    /**
     * 同步导入的信息
     */
    @ApiModelProperty(value = "同步导入的信息", dataType = "String")
    private String fdImportInfo;
    /**
     * 同步导入的供应商
     */
    @ApiModelProperty(value = "同步导入的供应商", dataType = "String")
    private String fdImportProvider;
    /**
     * 置为无效时的上级（当组织置为无效时，记录当时的上级元素）
     */
    @ApiModelProperty(value = "置为无效时的上级", dataType = "Object")
    private IdNameProperty fdOriParent;
    /**
     * 所属机构
     */
    @ApiModelProperty(value = "所属机构", dataType = "Object")
    private IdNameProperty fdParentOrg;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", dataType = "String")
    private String fdEmail;
    /**
     * 办公电话
     */
    @ApiModelProperty(value = "办公电话", dataType = "String")
    private String fdWorkPhone;

    /**
     * 当前领导
     */
    @ApiModelProperty(value = "当前领导", dataType = "Object")
    private IdNameProperty fdThisLeader;
    /**
     * 人员数量(适用于：机构、部门、岗位、群组)
     */
    @ApiModelProperty(value = "人员数量", dataType = "Integer", readOnly = true)
    private Integer fdPersonsNumber;

    /**
     * 群组列表
     */
    @ApiModelProperty(value = "群组列表", dataType = "List")
    private List<IdNameProperty> fdGroups;

    /**
     * 关键字
     */
    @ApiModelProperty(value = "关键字", dataType = "String")
    private String fdKeyword;
}
