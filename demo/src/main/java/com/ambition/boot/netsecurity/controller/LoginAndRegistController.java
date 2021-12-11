package com.ambition.boot.netsecurity.controller;

import com.ambition.boot.netsecurity.service.LoginRegistService;

/**
 * 登陆验证
 *      验证用户名密码
 *      使用md5和hamc解密对比校验
 * 注册角色
 *      1、生成角色
 *      2、生成公私钥
 *      3、保存公私钥
 *      4、使用md5和hmac对密码加密
 *      5、保存角色
 */
public class LoginAndRegistController {
    private LoginRegistService loginRegistService;

    public LoginAndRegistController(LoginRegistService loginRegistService) {
        this.loginRegistService = loginRegistService;
    }

    /**
     * 登陆
     * @param username 用户名
     * @param password 密码
     * @return 登陆成功或者失败
     */
    public String login(String username,String password) {
        if ("".equals(username) || username == null || password == null) return "用户名或者密码不能为空";

        return loginRegistService.login(username,password);
    }

    /**
     * 注册
     * @param username 用户名
     * @param password1 密码1
     * @param password2 密码2
     * @return 注册成功或者失败
     */
    public String regist(String username, String password1,String password2) {
        if ("".equals(username) || username == null || password1 == null || password2 == null) return "用户名或者密码不能为空";
        if (!password1.equals(password2)) return "输入密码与确认密码不相同！！！";
        return loginRegistService.regist(username,password1,password2);
    }

    /**
     * 公钥加密
     *
     * 用户b用用户a的公钥加密发送信息给用户a
     * @param userA 用户A
     * @param userB 用户B
     * @param message 加密的信息
     * @return 返回使用用户a公钥加密的信息
     */
    public String encryption(String userA,String userB,String message) {
        if (userA == null || userB == null) return "用户名不能为空";
        return loginRegistService.encryption(userA,userB,message);
    }

    /**
     * 私钥解密
     *
     * 用户a用用户a的私钥解密用户b用用户a加密的信息
     * @param userA 用户A
     * @param userB 用户B
     * @param message 加密的信息
     * @return 返回使用用户a私钥解密的信息
     */
    public String decrypt(String userA,String userB,String message) {
        if (userA == null || userB == null) return "用户名不能为空";
        return loginRegistService.decrypt(userA,userB,message);
    }

    /**
     * 私钥加密
     *
     * 用户a用用户a的私钥加密发送的信息给用户b
     * @param userA 用户A
     * @param userB 用户B
     * @param message 加密的信息
     * @return 返回使用用户a私钥加密的信息
     */
    public String priEncryption(String userA,String userB,String message) {
        if (userA == null || userB == null) return "用户名不能为空";
        return loginRegistService.priEncryption(userA,userB,message);
    }


    /**
     * 公钥认证
     *
     * 用户b用用户a的公钥认证用户a发送的信息
     * @param userA 用户A
     * @param userB 用户B
     * @param message 加密的信息
     * @return 返回使用用户a公钥解密的信息
     */
    public String pulDecrypt(String userA,String userB,String message) {
        if (userA == null || userB == null) return "用户名不能为空";
        return loginRegistService.pulDecrypt(userA,userB,message);
    }

    /**
     * 消息完整性验证 a发送消息给b
     *
     * 用户b用用户a的公钥认证用户a发送的信息
     * @param userA 用户A
     * @param userB 用户B
     * @param message 加密的信息
     * @return 返回使用用户a公钥解密的信息
     */
    public String checkMessage1(String userA,String userB,String message) {
        if (userA == null || userB == null) return "用户名不能为空";
        return loginRegistService.checkMessage1(userA,userB,message);
    }

    /**
     * 消息完整性验证 b验证消息是否完整
     *
     * 用户b用用户a的公钥认证用户a发送的信息
     * @param userA 用户A
     * @param userB 用户B
     * @param message 加密的信息
     * @return 返回使用用户a公钥解密的信息
     */
    public String checkMessage2(String userA,String userB,String message) {
        if (userA == null || userB == null) return "用户名不能为空";
        return loginRegistService.checkMessage2(userA,userB,message);
    }
}
