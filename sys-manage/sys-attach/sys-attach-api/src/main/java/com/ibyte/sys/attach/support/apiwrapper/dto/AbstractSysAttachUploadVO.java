package com.ibyte.sys.attach.support.apiwrapper.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * 附件上传对象基类
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
public class AbstractSysAttachUploadVO {

    /**
     * 原始附件ID
     * <P/>如果是导入附件，则需要传入此参数
     * <P/>普通上传不需要传入
     * <P/>非必传
     */
    protected String originAttachId;

    /**
     * 附件文件对象
     * <P/>必传
     */
    protected File file;

    /**
     * 文件上传人
     * <P/>可不传
     */
    protected String fdCreatorId;

    /**
     * 主文档业务实体class类名
     * <P/>可不传
     */
    protected String fdEntityName;

    /**
     * 附件是否可以匿名访问
     * <P/>非必传，不传表示不允许匿名访问
     */
    protected Boolean fdAnonymous = false;

}
