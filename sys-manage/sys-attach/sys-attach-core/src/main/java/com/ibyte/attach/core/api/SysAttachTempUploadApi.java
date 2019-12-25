package com.ibyte.attach.core.api;

import com.ibyte.attach.core.api.operation.upload.SysAttachTempUploadOperationService;
import com.ibyte.sys.attach.api.upload.ISysAttachTempUploadApi;
import com.ibyte.sys.attach.dto.SysAttachUploadBytesVO;
import com.ibyte.sys.attach.dto.slice.SysAttachUploadSliceBytesVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 临时附件上传API
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Slf4j
@Service
@RestController
@RequestMapping("/api/sys-attach/temp/")
public class SysAttachTempUploadApi implements ISysAttachTempUploadApi {

    @Autowired
    private SysAttachTempUploadOperationService sysAttachTempUploadOperationService;

    @Override
    @Transactional
    public String addTemp(SysAttachUploadBytesVO uploadBytesVO) {
        return sysAttachTempUploadOperationService.uploadBytes(uploadBytesVO);
    }

    @Override
    public String sliceTemp(SysAttachUploadSliceBytesVO sliceBytesVO) {
        return null;
    }
}
