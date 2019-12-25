package com.ibyte.sys.attach.support.apiwrapper.impl;

import com.ibyte.sys.attach.api.upload.ISysAttachTempUploadApi;
import com.ibyte.sys.attach.dto.SysAttachUploadBytesVO;
import com.ibyte.sys.attach.dto.slice.SysAttachUploadSliceBytesVO;
import com.ibyte.sys.attach.support.apiwrapper.SysAttachTempApiWrapper;
import com.ibyte.sys.attach.support.apiwrapper.dto.SysAttachTempUploadVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.function.Function;

/**
 * 临时附件API包装接口逻辑实现
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Service
public class SysAttachTempApiWrapperImpl extends AbstractAttachApiWrapperImpl implements SysAttachTempApiWrapper {

    @Autowired(required = false)
    private ISysAttachTempUploadApi sysAttachTempUploadApi;

    @Override
    public String uploadTemp(SysAttachTempUploadVO tempUploadVO) throws IOException {
        // 定义调用分片上传API的函数
        Function<SysAttachUploadSliceBytesVO, String> sliceApiCallFunction =
                sliceBytesVO -> sysAttachTempUploadApi.sliceTemp(sliceBytesVO);
        // 定义调用普通上传API的函数
        Function<SysAttachUploadBytesVO, String> uploadApiCallFunction =
                uploadBytesVO -> sysAttachTempUploadApi.addTemp(uploadBytesVO);
        // 执行上传
        return this.doUpload(tempUploadVO, null, null, sliceApiCallFunction, uploadApiCallFunction);
    }
}
