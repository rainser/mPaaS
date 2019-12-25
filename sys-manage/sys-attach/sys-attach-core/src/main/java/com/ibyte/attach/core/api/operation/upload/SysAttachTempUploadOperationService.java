package com.ibyte.attach.core.api.operation.upload;

import com.ibyte.attach.core.api.uploader.SysAttachEntireUploader;
import com.ibyte.attach.core.dto.SysAttachUploadResultVO;
import com.ibyte.attach.core.entity.SysAttachFile;
import com.ibyte.attach.core.entity.SysAttachMain;
import com.ibyte.attach.core.service.SysAttachFileService;
import com.ibyte.attach.core.service.SysAttachService;
import com.ibyte.common.constant.NamingConstant;
import com.ibyte.common.core.util.MimeTypeUtil;
import com.ibyte.common.exception.KmssServiceException;
import com.ibyte.common.util.IDGenerator;
import com.ibyte.common.util.TenantUtil;
import com.ibyte.framework.config.ApplicationConfigApi;
import com.ibyte.sys.attach.constant.AttachEffectTypeEnum;
import com.ibyte.sys.attach.constant.AttachStatusEnum;
import com.ibyte.sys.attach.constant.SysAttachConstant;
import com.ibyte.sys.attach.dto.SysAttachMediaSizeVO;
import com.ibyte.sys.attach.dto.SysAttachUploadBytesVO;
import com.ibyte.sys.attach.dto.base.SysAttachUploadBaseVO;
import com.ibyte.sys.attach.dto.config.SysAttachConfigVO;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.function.Function;

/**
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Service
public class SysAttachTempUploadOperationService extends AbstractSysAttachUploadOperationService {

    @Autowired
    private SysAttachEntireUploader sysAttachEntireUploader;

    @Autowired
    private SysAttachFileService sysAttachFileService;

    @Autowired
    private SysAttachService sysAttachService;

    @Autowired
    private ApplicationConfigApi applicationConfig;

    /**
     * API上传临时附件
     * @return 生成的临时附件ID
     */
    public String uploadBytes(SysAttachUploadBytesVO uploadBytesVO) {
        // 定义使用完整上传模式，上传API附件的函数
        Function<InputStream, SysAttachUploadResultVO> uploadFunction = inputStream -> sysAttachEntireUploader.upload(inputStream, uploadBytesVO.getFdEntityName());
        // 执行上传操作逻辑
        return this.recordTempUpload(uploadBytesVO, new ByteArrayInputStream(uploadBytesVO.getFdFileData()), uploadFunction);
    }

    /**
     * 上传临时附件，并保存临时附件记录
     * @param uploadFunction 需要具体实现的文件上传逻辑
     * @return 生成的临时附件ID
     */
    private String recordTempUpload(SysAttachUploadBaseVO uploadBaseVO, InputStream inputStream,
                                    Function<InputStream, SysAttachUploadResultVO> uploadFunction) {
        try {
            // 执行文件上传
            SysAttachUploadResultVO uploadResultVO = uploadFunction.apply(inputStream);
            if (uploadResultVO == null) {
                return null;
            }
            // 保存attachFileTemp
            SysAttachFile attachFile = this.saveAttachFile(uploadBaseVO, uploadResultVO);

            // 保存attachTemp
            SysAttachMain attachTemp = this.saveAttachTemp(uploadBaseVO, attachFile);
            return attachTemp.getFdId();
        } catch (Exception e) {
            throw new KmssServiceException("sys-attach:sys.attach.msg.error.SysAttachWriteFailed", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 保存attachFileTemp
     */
    private SysAttachFile saveAttachFile(SysAttachUploadBaseVO uploadBaseVO, SysAttachUploadResultVO uploadResultVO) {
        SysAttachFile attachFile = new SysAttachFile();
        attachFile.setFdId(uploadResultVO.getFileId());
        attachFile.setFdFileName(uploadBaseVO.getFdFileName());
        attachFile.setFdFileExtName(uploadBaseVO.getFdFileExtName());
        attachFile.setFdFileSize(uploadBaseVO.getFdFileSize());
        attachFile.setFdMd5(uploadBaseVO.getFdMd5());
        attachFile.setFdFilePath(uploadResultVO.getFilePath());
        attachFile.setFdTenantId(TenantUtil.getTenantId());
        attachFile.setFdCreatorId(uploadBaseVO.getFdCreatorId());
        attachFile.setFdStatus(AttachStatusEnum.VALID);
        attachFile.setFdEncryptMethod(sysAttachConfig.getCurrentEncryMethod());
        attachFile.setFdLocation(sysAttachStoreService.getDefaultStoreLocation());
        attachFile.setFdSysAttachCatalog(uploadResultVO.getSysAttachCatalog());
        attachFile.setFdSysAttachModuleLocation(uploadResultVO.getSysAttachModuleLocation());
        SysAttachMediaSizeVO mediaSizeVO = this.getMediaSize(uploadBaseVO.getFdFileExtName(), uploadResultVO.getFullPath());
        if (mediaSizeVO != null) {
            attachFile.setFdWidth(mediaSizeVO.getWidth());
            attachFile.setFdHeight(mediaSizeVO.getHeight());
        }
        sysAttachFileService.add(attachFile);
        return attachFile;
    }



    /**
     * 保存attachTemp
     */
    private SysAttachMain saveAttachTemp(SysAttachUploadBaseVO uploadBaseVO, SysAttachFile attachFile) {
        // 获取临时文件过期时间配置
        SysAttachConfigVO configVO = applicationConfig.get(SysAttachConfigVO.class);
        Integer overdueSeconds = configVO != null ? configVO.getTempOverdueSeconds() : SysAttachConstant.ATTACH_TEMP_OVERDUE_SECONDS_DEFAULT;
        long overdueMillis = overdueSeconds * 1000L;

        // 保存临时的attach
        String attachId = IDGenerator.generateID();
        SysAttachMain attachTemp = new SysAttachMain();
        attachTemp.setFdId(attachId);
        attachTemp.setFdSysAttachFile(attachFile);
        attachTemp.setFdFileName(uploadBaseVO.getFdFileName());
        attachTemp.setFdFileExtName(uploadBaseVO.getFdFileExtName());
        attachTemp.setFdFileSize(uploadBaseVO.getFdFileSize());
        attachTemp.setFdEntityName(uploadBaseVO.getFdEntityName());
        attachTemp.setFdTenantId(TenantUtil.getTenantId());
        attachTemp.setFdCreatorId(uploadBaseVO.getFdCreatorId());
        attachTemp.setFdStatus(AttachStatusEnum.VALID);
        attachTemp.setFdEffectType(AttachEffectTypeEnum.TEMPORARY);
        attachTemp.setFdMimeType(MimeTypeUtil.getMimeType(attachFile.getFdFileName() + NamingConstant.DOT + attachFile.getFdFileExtName()));
        attachTemp.setFdExpirationTime(new Timestamp(System.currentTimeMillis() + overdueMillis));
        attachTemp.setFdExtendInfo(uploadBaseVO.getFdExtendInfo());
        attachTemp.setFdWidth(attachFile.getFdWidth());
        attachTemp.setFdHeight(attachFile.getFdHeight());
        attachTemp.setFdAnonymous(uploadBaseVO.getFdAnonymous());
        sysAttachService.add(attachTemp);
        return attachTemp;
    }


}
