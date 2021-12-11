package com.ambition.boot.netsecurity.exception;

/**
 * 异常类
 */
public class EDException {

    public String UnKnow() {
        return new RuntimeException("未知错误").getMessage();
    }

    public String EncryptionException() {
        return new RuntimeException("加密失败").getMessage();
    }

    public String DecryptException() {
        return new RuntimeException("解密失败").getMessage();
    }

    public String AuthException() {
        return new RuntimeException("认证失败").getMessage();
    }

    public String LoginException() {
        return new RuntimeException("登陆失败").getMessage();
    }

    public String RegistException() {
        return new RuntimeException("注册失败").getMessage();
    }

    public String generateException() {
        return new RuntimeException("生成公私钥对失败").getMessage();
    }
}
