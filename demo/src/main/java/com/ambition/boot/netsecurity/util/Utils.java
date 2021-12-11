package com.ambition.boot.netsecurity.util;

import com.ambition.boot.netsecurity.exception.EDException;
import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.UUID;

/**
 * 加密解密算法类
 */
public class Utils {
    /**
     * 生成随机字符串
     */
    public static String generateKeys(String name,String type) {
        return UUID.randomUUID().toString();
    }

    //计算md5的工具方法
    public static  String md5(String password){
        try {
            //确定md5加密算法
            MessageDigest md = MessageDigest.getInstance("md5");
            //通过md5计算摘要
            byte [] bytes = md.digest(password.getBytes("UTF-8"));
            //md5值转成可读字符串
            BASE64Encoder base64 = new BASE64Encoder();
            String str = base64.encode(bytes);
            return str;
        } catch (Exception e) {
            return new EDException().EncryptionException();
        }
    }

    /**
     * 随机生成密钥对
     * @throws NoSuchAlgorithmException
     */
    public static String[] genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 返回公钥和私钥
        return new String[]{publicKeyString,privateKeyString};
    }

    public static void main(String[] args) throws Exception {
        String[] strings = Utils.genKeyPair();
        String encrypt = Utils.encrypt("W+OE4eFL07k7VyiMnpQB8g==,,,ZzYQH59yL6LqkySu8INKAiBYyTowZQ9GeupI6XL/Zde/qYpOn0J7bUbRgOM/I4gHtDqpTT0cX1eAqj6SVzwq5vX3Oxh7TAUIvs69MygW5Mvs5u8QU2Sj0uzwJuI5F4Dw4AdP6nQOSvRObzvm6Z32T6/L944ZkI6v2ljYaIifa/8=", strings[0]);
        System.out.println(encrypt);
        System.out.println(Utils.decrypt(encrypt,strings[1]));
    }

    /**
     * 公钥加密
     * @param str
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String encrypt( String str, String publicKey) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * 私钥解密
     * @param str
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String decrypt(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }


    /**
     * 私钥加密
     *
     * @param privateKeyText
     * @param text
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String privateKeyText, String text) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(text.getBytes());
        return Base64.encodeBase64String(result);
    }

    /**
     * 公钥解密
     * @param publicKeyText
     * @param text
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String publicKeyText, String text) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(text));
        return new String(result);
    }

}
