package com.ambition.boot.netsecurity.service;

import com.ambition.boot.netsecurity.dao.DAO;
import com.ambition.boot.netsecurity.exception.EDException;
import com.ambition.boot.netsecurity.pojo.User;
import com.ambition.boot.netsecurity.result.ResultConstants;
import com.ambition.boot.netsecurity.util.Utils;

import java.util.UUID;


public class LoginRegistService {
    public DAO dao;

    public LoginRegistService(DAO dao) {
        this.dao = dao;
    }

    /**
     * 注册
     */
    public String regist(String username, String password1, String password2) {
        // 校验用户名是否已经存在
        if (DAO.map.get(username) != null) return "用户名已存在";
        // 使用RSA生成公私钥对
        String[] strings = null;
        try {
            strings = Utils.genKeyPair();
        }catch (Exception e) {
            return new EDException().generateException();
        }
        // 取出公钥
        String pubKey = strings[0];
        // 取出私钥
        String priKey = strings[1];
        // 生成用户密码加密散列
        String uuid = UUID.randomUUID().toString();
        //加密
        String ePassword = Utils.md5(password1);
        // 保存用户
        try {
            DAO.map.put(username,new User(username,ePassword+uuid,pubKey,priKey,uuid));
            return username;
        }catch (Exception e) {
            return new EDException().RegistException();
        }
    }

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    public String login(String username, String password) {
        User user = DAO.map.get(username);
        if (user == null) return "用户名不存在";
        // 使用用户输入密码加密 然后对比存储密码 相同则登陆成功 否则登陆失败
        if ((Utils.md5(password)+user.getEnKey()).equals(user.getPassword())) return ResultConstants.LOGIN_SUCCESS;
        return new EDException().LoginException();
    }

    public String encryption(String userA, String userB, String message) {
        User user1 = DAO.map.get(userA);
        User user2 = DAO.map.get(userB);
        if (user1 == null || user2 == null) return new EDException().EncryptionException();
        // 取出用户a的公钥
        String pubKey = user1.getPubKey();
        try {
            // 加密并返回信息
            return Utils.encrypt(message,pubKey);
        }catch (Exception e) {
            return new EDException().EncryptionException();
        }
    }

    public String decrypt(String userA, String userB, String message) {
        User user1 = DAO.map.get(userA);
        User user2 = DAO.map.get(userB);
        if (user1 == null || user2 == null) return new EDException().DecryptException();
        // 取出用户a的私钥
        String priKey = user1.getPriKey();
        try {
            // 解密并返回信息
            return Utils.decrypt(message,priKey);
        }catch (Exception e) {
            return new EDException().DecryptException();
        }
    }

    public String priEncryption(String userA, String userB, String message) {
        User user1 = DAO.map.get(userA);
        User user2 = DAO.map.get(userB);
        if (user1 == null || user2 == null) return new EDException().EncryptionException();
        // 取出用户a的私钥
        String priKey = user1.getPriKey();
        try {
            // 加密并返回信息
            return Utils.encryptByPrivateKey(priKey,message);
        }catch (Exception e) {
            return new EDException().EncryptionException();
        }
    }

    public String pulDecrypt(String userA, String userB, String message) {
        User user1 = DAO.map.get(userA);
        User user2 = DAO.map.get(userB);
        if (user1 == null || user2 == null) return new EDException().DecryptException();
        // 取出用户a的公钥
        String pubKey = user1.getPubKey();
        try {
            // 解密并返回信息
            return Utils.decryptByPublicKey(pubKey,message);
        }catch (Exception e) {
            return new EDException().DecryptException();
        }
    }

    // 返回加密信息
    public String checkMessage1(String userA, String userB, String message) {
        int hashCode = message.hashCode();
        return Utils.md5(hashCode+"");
    }

    public String checkMessage2(String userA, String userB, String message) {
        return Utils.md5(message.hashCode() +"");
    }
}
