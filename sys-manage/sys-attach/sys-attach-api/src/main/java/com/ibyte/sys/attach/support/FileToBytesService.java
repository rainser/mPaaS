package com.ibyte.sys.attach.support;

import com.ibyte.common.exception.KmssServiceException;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;

/**
 * 文件转字节数组接口
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface FileToBytesService {

    /**
     * 将文件写入到byte数组
     */
    default byte[] writeFileToBytes(File file) {
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            IOUtils.copy(bis, bos);
            bos.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            throw new KmssServiceException("errors.fileOperationException", e);
        }
    }
}
