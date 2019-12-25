package com.ibyte.sys.attach.support.apiwrapper.impl;

import com.ibyte.sys.attach.constant.SysAttachConstant;
import com.ibyte.sys.attach.dto.SysAttachUploadBytesVO;
import com.ibyte.sys.attach.dto.slice.SysAttachUploadSliceBytesVO;
import com.ibyte.sys.attach.support.FileToBytesService;
import com.ibyte.sys.attach.support.SysAttachUploadChecker;
import com.ibyte.sys.attach.support.apiwrapper.dto.AbstractSysAttachUploadVO;
import com.ibyte.sys.attach.util.MD5Util;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

/**
 * 附件API包装接口基类
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public abstract class AbstractAttachApiWrapperImpl implements FileToBytesService {

    /**
     * 执行上传或分片上传
     *
     * @param uploadVO              附件上传对象
     * @param sliceApiCallFunction  分片上传API接口
     * @param uploadApiCallFunction 普通上传API接口
     * @return 上传成功后生成的附件ID
     */
    String doUpload(AbstractSysAttachUploadVO uploadVO, String entityId, String entityKey,
                    Function<SysAttachUploadSliceBytesVO, String> sliceApiCallFunction,
                    Function<SysAttachUploadBytesVO, String> uploadApiCallFunction) throws IOException {
        String fileFullName = uploadVO.getFile().getName();
        int dotIndex = fileFullName.lastIndexOf('.');
        // 解析文件名
        String fileName = dotIndex > -1 ? fileFullName.substring(0, dotIndex) : fileFullName;
        // 解析文件扩展名
        String fileExtName = dotIndex > -1 ? fileFullName.substring(dotIndex + 1) : null;

        SysAttachUploadChecker.checkUpload(uploadVO.getFdEntityName(), entityKey, uploadVO.getFile().length(),
                fileExtName);

        long forceSliceSize = SysAttachConstant.FORCE_SLICE_FILE_SIZE;

        if (uploadVO.getFile().length() > forceSliceSize) {
            SysAttachUploadSliceBytesVO sliceBytesVO = this.buildSliceVO(uploadVO, entityId, entityKey, forceSliceSize
                    , fileName, fileExtName);
            try (InputStream inputStream = new FileInputStream(uploadVO.getFile())) {
                for (int i = 0; i < sliceBytesVO.getFdTotalSlices(); i++) {
                    this.readSliceBytes(i, inputStream, sliceBytesVO);

                    String result = sliceApiCallFunction.apply(sliceBytesVO);
                    if (StringUtils.isEmpty(result)) {
                        continue;
                    }
                    return result;
                }
            } catch (IOException e) {
                throw e;
            }
        } else {
            SysAttachUploadBytesVO uploadBytesVO = this.buildUploadVO(uploadVO, entityId, entityKey, fileName, fileExtName);
            return uploadApiCallFunction.apply(uploadBytesVO);
        }

        return null;
    }

    /**
     * 将SysAttachUploadVO转换成上传用的SysAttachUploadBytesVO，以供ISysAttachMainUploadApi使用
     */
    private SysAttachUploadBytesVO buildUploadVO(AbstractSysAttachUploadVO uploadVO, String entityId,
                                                 String entityKey, String fileName, String fileExtName) throws IOException {
        File file = uploadVO.getFile();

        // 将文件输出为byte数组
        byte[] fdContent = this.writeFileToBytes(file);

        SysAttachUploadBytesVO attachAddVO = new SysAttachUploadBytesVO();
        attachAddVO.setFdCreatorId(uploadVO.getFdCreatorId());
        attachAddVO.setFdFileData(fdContent);
        attachAddVO.setFdFileSize(file.length());
        attachAddVO.setFdFileName(fileName);
        attachAddVO.setFdFileExtName(fileExtName);
        attachAddVO.setFdEntityName(uploadVO.getFdEntityName());
        attachAddVO.setFdAnonymous(uploadVO.getFdAnonymous());
        if (uploadVO.getOriginAttachId() != null) attachAddVO.setOriginAttachId(uploadVO.getOriginAttachId());
        if (entityId != null) attachAddVO.setFdEntityId(entityId);
        if (entityKey != null) attachAddVO.setFdEntityKey(entityKey);
        // 生成MD5特征值
        attachAddVO.setFdMd5(MD5Util.hex(fdContent));
        return attachAddVO;
    }


    /**
     * 将SysAttachUploadVO转换成分片上传用的SysAttachUploadSliceBytesVO，以供ISysAttachMainUploadApi使用
     */
    private SysAttachUploadSliceBytesVO buildSliceVO(AbstractSysAttachUploadVO uploadVO, String entityId,
                                                     String entityKey, long forceSliceSize, String fileName,
                                                     String fileExtName) {
        File file = uploadVO.getFile();
        long fileSize = file.length();
        long totalSlices = fileSize % forceSliceSize == 0 ? fileSize / forceSliceSize : fileSize / forceSliceSize + 1;
        long eachSize = fileSize % totalSlices == 0 ? fileSize / totalSlices : fileSize / totalSlices + 1;

        SysAttachUploadSliceBytesVO sliceBytesVO = new SysAttachUploadSliceBytesVO();
        sliceBytesVO.setFdMd5(MD5Util.hex(file));
        sliceBytesVO.setFdFileSize(fileSize);
        sliceBytesVO.setFdCreatorId(uploadVO.getFdCreatorId());
        sliceBytesVO.setFdFileName(fileName);
        sliceBytesVO.setFdFileExtName(fileExtName);
        sliceBytesVO.setFdTotalSlices((int) totalSlices);
        sliceBytesVO.setFdEachSliceSize(eachSize);
        sliceBytesVO.setFdOrderUpload(true);
        sliceBytesVO.setFdEntityName(uploadVO.getFdEntityName());
        sliceBytesVO.setFdAnonymous(uploadVO.getFdAnonymous());
        if (uploadVO.getOriginAttachId() != null) sliceBytesVO.setOriginAttachId(uploadVO.getOriginAttachId());
        if (entityId != null) sliceBytesVO.setFdEntityId(entityId);
        if (entityKey != null) sliceBytesVO.setFdEntityKey(entityKey);
        return sliceBytesVO;
    }

    /**
     * 读取每个分片的数据字节
     */
    private void readSliceBytes(int i, InputStream inputStream, SysAttachUploadSliceBytesVO sliceBytesVO) throws IOException {
        byte[] bytes;
        if (i == sliceBytesVO.getFdTotalSlices() - 1) {
            bytes = new byte[inputStream.available()];
        } else {
            bytes = new byte[sliceBytesVO.getFdEachSliceSize().intValue()];
        }
        inputStream.read(bytes);

        sliceBytesVO.setFdSliceIndex(i);
        sliceBytesVO.setFdFileData(bytes);
    }

}
