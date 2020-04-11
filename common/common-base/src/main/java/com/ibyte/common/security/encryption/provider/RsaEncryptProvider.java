package com.ibyte.common.security.encryption.provider;

import com.ibyte.common.security.encryption.IEncrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.codec.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA加解密处理组件
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @Date: 2019-10-10
 */
@Slf4j
public class RsaEncryptProvider implements IEncrypt {

    public static final String RSA = "RSA";

    public static final String CHARSET_NAME = "UTF-8";
    /**
     * RSA私钥
     */
    private byte [] privateCodeByte;

    /**
     * RSA公钥
     */
    private byte [] publicCodeByte;

    /**
     * RSA公钥加密
     *
     * @param encryptStr 字符串
     * @return base64 编码后的密文
     */
    @Override
    public String encrypt(String encryptStr) {
        String outStr = null;
        try {
            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(publicCodeByte));
            //RSA加密
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            outStr = Base64.getEncoder().encodeToString(cipher.doFinal(encryptStr.getBytes(CHARSET_NAME)));
        } catch (InvalidKeySpecException e) {
           log.error("无效的密钥规范",e);
        } catch (NoSuchAlgorithmException e) {
            log.error("无效的算法",e);
        } catch (NoSuchPaddingException e) {
            log.error("无效的算法",e);
        } catch (InvalidKeyException e) {
            log.error("无效的密钥",e);
        } catch (IllegalBlockSizeException e) {
            log.error("非法块大小",e);
        } catch (BadPaddingException e) {
            log.error("错误填充异常",e);
        } catch (UnsupportedEncodingException e) {
            log.error("不支持的编码异常",e);
        }
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param decryptStr Base64 编码的加密字符串
     * @return 明文
     */
    @Override
    public String decrypt(String decryptStr){
        String outStr = null;
        try {
            //64位解码加密后的字符串
            byte[] inputByte = Base64.getDecoder().decode(decryptStr.getBytes(CHARSET_NAME));
            //私钥
            RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(RSA).generatePrivate(new PKCS8EncodedKeySpec(privateCodeByte));
            //RSA解密
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            outStr = new String(cipher.doFinal(inputByte));
        } catch (Exception e) {
           log.error("RSA解密失败",e);
        }
        return outStr;
    }


    /**
     * 随机生成密钥对
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Hex.encode(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Hex.encode((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        //0表示公钥
        System.out.println("公钥 16进制:"+publicKeyString);
        //1表示私钥
        System.out.println("私钥 16进制："+privateKeyString);
    }



    public RsaEncryptProvider(byte [] privateCodeByte, byte [] publicCodeByte) {
        this.privateCodeByte = privateCodeByte;
        this.publicCodeByte = publicCodeByte;
    }



    public static void main(String[] args) throws Exception {
        AesEncryptProvider aesEncryptProvider = new AesEncryptProvider("mPaaS_Code");
        System.out.println(aesEncryptProvider.encrypt("mPaaS-Nacosx"));
    }
}
