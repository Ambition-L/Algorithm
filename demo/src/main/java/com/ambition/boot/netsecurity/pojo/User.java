package com.ambition.boot.netsecurity.pojo;

public class User {
    private String username;
    private String password;
    private String pubKey;//用户公钥
    private String priKey;//用户私钥
    private String enKey;// 用户加密散列

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public String getPriKey() {
        return priKey;
    }

    public void setPriKey(String priKey) {
        this.priKey = priKey;
    }

    public String getEnKey() {
        return enKey;
    }

    public void setEnKey(String enKey) {
        this.enKey = enKey;
    }

    public User(String username, String password, String pubKey, String priKey, String enKey) {
        this.username = username;
        this.password = password;
        this.pubKey = pubKey;
        this.priKey = priKey;
        this.enKey = enKey;
    }

    public User() {
    }


}
