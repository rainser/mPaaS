package com.ibyte.sys.org.core.entity.config;

import com.ibyte.common.core.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/** 
 * 组织架构开关
 * 
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1 */
@Getter
@Setter
@Entity
@Table
public class SysOrgConfig extends AbstractEntity {

    /**
     * 是否实时搜索
     */
    protected Boolean realTimeSearch;
    /**
     * 岗位名称关联部门名称
     */
    protected Boolean isRelation;
    /**
     * 是否保持组织架构和群组名唯一
     */
    protected Boolean keepGroupUnique;

    /**
     * 编号是否必填，默认非必填
     * <p>
     * 如开启必填后，可以按类型（机构、部门、人员、岗位）自定义编号，前端录入的时候按类型校验唯一性，高级数据导入时同样需校验唯一性
     */
    protected Boolean isNoRequired;
    /**
     * 是否开启登录名允许特殊字符
     */
    protected Boolean isLoginSpecialChar;
}