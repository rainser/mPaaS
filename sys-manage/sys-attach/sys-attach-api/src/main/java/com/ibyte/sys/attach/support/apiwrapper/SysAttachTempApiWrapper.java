package com.ibyte.sys.attach.support.apiwrapper;

import com.ibyte.sys.attach.support.apiwrapper.dto.SysAttachTempUploadVO;

import java.io.IOException;

/**
 * 上传临时附件信息
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface SysAttachTempApiWrapper {

    /**
     * 上传临时附件
     * @return 上传后得到的附件ID
     */
    String uploadTemp(SysAttachTempUploadVO tempUploadVO) throws IOException;
}
