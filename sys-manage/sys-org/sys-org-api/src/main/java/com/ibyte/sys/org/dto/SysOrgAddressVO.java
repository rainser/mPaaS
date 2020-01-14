package com.ibyte.sys.org.dto;

import com.ibyte.common.core.dto.AbstractVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 地址本 vo
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SysOrgAddressVO extends AbstractVO {

    // ================入参，由前端传入的参数信息=================

    /**
     * 上级ID
     */
    private String parent;
    /**
     * 组织类型
     */
    private Integer orgType;
    /**
     * 可能包含的值是：myDept, myOrg, 具体的某个fdId
     * myDept：包含当前用户的所属部门，或当前用户所在岗位的所属部门
     * myOrg：包含当前用户的所属机构，或当前用户所在岗位的所属机构
     * 具体的fdId：当前fdId的实际对象
     */
    private String deptLimit;
    /**
     * 是否管理端页面
     */
    private Boolean sysPage;

    // ====================地址本搜索参数========================
    /**
     * 多个ID
     */
    private List<String> fdIds;
    /**
     * 搜索范围
     */
    private String range;
    /**
     * 部门名称
     */
    private String fdDeptName;
    /**
     * 是否返回层级ID
     */
    private Boolean returnHierarchyId;
    /**
     * 模糊搜索关键字
     */
    private String key;
    /**
     * 是否精确搜索
     */
    private Boolean accurate;

    // ==============树节点属性（新UI待确定是否保留）=============

    /**
     * 节点值（取fdId）
     */
    private String value;
    /**
     * 节点名称（取fdName）
     */
    private String text;
    /**
     * 描述信息（取部门层级名称）
     */
    private String title;
    /**
     * 节点类型（取fdOrgType）
     */
    private Integer nodeType;
    /**
     * 是否可用
     */
    private Boolean isAvailable;
    /**
     * 请求路径
     */
    private String href;

    // ==============公共组织属性==============

    /**
     * 原始ID（如果是同步过来的数据，记录原始ID）
     */
    private String fdOriId;
    /**
     * 名称
     */
    private String fdName;
    /**
     * 组织类型
     */
    private Integer fdOrgType;
    /**
     * 描述信息
     */
    private String fdInfo;
    /**
     * 是否禁用
     */
    private Boolean fdIsAvailable;
    /**
     * 层级ID
     */
    private String fdHierarchyId;
    /**
     * 完整的层级ID（包含所有上级机构）
     */
    private String fdHierarchyIdComplete;
    /**
     * 上级名称
     */
    private String fdParentName;

    // ==============人员属性==============

    /**
     * 头像地址
     */
    private String fdImg;
    /**
     * 智能应用组件选择组织架构人员使用此参数
     */
    private String fdUid;
    /**
     * 职级名称
     */
    private String fdStaffingLevel;

    // === 针对无效的组织，避免出现重名无法区分，需要返回一些额外的信息（如编号，登录名） ===

    /**
     * 编号
     */
    private String fdNo;
    /**
     * 登录名
     */
    private String fdLoginName;

}
