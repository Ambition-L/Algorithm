package com.ambition.boot.netsecurity;

import com.ambition.boot.netsecurity.controller.LoginAndRegistController;
import com.ambition.boot.netsecurity.dao.DAO;
import com.ambition.boot.netsecurity.pojo.User;
import com.ambition.boot.netsecurity.service.LoginRegistService;

import java.util.HashMap;
import java.util.Map;

/**
 * 集成测试
 */
public class AuthAndEncryption {
    Map<String,String> maps = new HashMap<>();
    private static String userNameA = "userA";
    private static String userNameB = "userB";
    private LoginAndRegistController controller;

    public AuthAndEncryption(LoginAndRegistController controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        // 初始化业务层
        LoginRegistService loginRegistService = new LoginRegistService(new DAO());
        // 初始化控制层
        LoginAndRegistController c = new LoginAndRegistController(loginRegistService);
        // 初始化测试类
        AuthAndEncryption authAndEncryption = new AuthAndEncryption(c);
        // 注册用户A、B
        Map<String, User> map = authAndEncryption.regist();
        // 发送方用户A生成加密信息
        String meg = "a发送的消息";
        String message = authAndEncryption.userA(meg,map.get(userNameA),map.get(userNameB));
        // 接收方用户B接受加密信息并解密处理
        authAndEncryption.userB(meg,message,map.get(userNameA),map.get(userNameB));
    }

    // 用户A、B注册账号生成公私钥。。
    public Map<String,User> regist() {
        System.out.println("--------用户A、用户B注册开始--------");
        // 1、用户A、B 注册账号
        String userA = controller.regist(userNameA, "123456", "123456");
        String userB = controller.regist(userNameB, "123456", "123456");
        // 2、用户A、B 对账号密码进行加密保存进缓存
        User user1 = DAO.map.get(userA);
        User user2 = DAO.map.get(userB);
        // 3、用户A、B 生成公私钥对
        System.out.println("用户A公钥: \n" + user1.getPubKey());
        System.out.println("用户A私钥: \n" + user1.getPriKey());;
        System.out.println("用户B公钥: \n" + user2.getPubKey());;
        System.out.println("用户B私钥: \n" + user2.getPriKey());
        System.out.println("--------用户A、用户B注册结束--------");
        System.out.println();
        return DAO.map;
    }

    // 发送方 userA
    public String userA(String message,User userA,User userB) {
        System.out.println("--------发送方用户A准备发送信息--------");
        // 1、对消息进行散列运算得到摘要
        String h = controller.checkMessage1(userNameA, userNameB, message);
        System.out.println("明文散列计算成功，散列值为：\n" + h);
        // 2、使用私钥对明文进行加密
        String encryption = controller.priEncryption(userNameA, userNameB, message);
        System.out.println("私钥加密成功,加密之后的密文为：\n" + encryption);
        String auth = "auth";
        maps.put(auth,encryption);
        // 3、将摘要和加密后的明文使用接收方userB的公钥进行加密
        String encryption1 = controller.encryption(userNameB, userNameA, h + ",,," + auth);
        System.out.println("公钥加密成功,加密之后的密文为：\n" + encryption1);
        // 4、发送给B
        System.out.println("--------发送方发送完毕--------");
        return encryption1;
    }

    // 接收方 userB
    public void userB(String message,String message2,User userA,User userB) {
        System.out.println("--------接收方用户B接受到用户A的消息--------");
        System.out.println();
        // 1、用自己的私钥先解密 得到用对方私钥加密的明文和摘要
        String decrypt = controller.decrypt(userNameB, userNameA, message2);
        System.out.println("私钥解密成功,解密之后的明文为：\n" + decrypt);
        String[] split = decrypt.split(",,,");
        // 摘要
        String str1 = split[0];
        // 私钥加密的明文
        String str2 = split[1];
        // 2、使用散列函数计算明文 与 发送方的比较
        String check = controller.checkMessage1(userNameA, userNameB, message);
        if (str1.equals(check)) {
            System.err.println("消息对比成功 消息完整");
        }else System.err.println("消息对比失败 消息不完整");

        // 3、用发送方的公钥进行消息认证
        if (maps.get(str2) == null) {
            System.err.println("用户认证失败");
            return;
        }
        String decrypt1 = controller.pulDecrypt(userNameA, userNameB, maps.get(str2));
        System.out.println("公钥解密成功,解密之后的明文为：\n" + decrypt);
        if (decrypt1.equals(message)){
            System.err.println("公钥认证成功 用户认证成功");
        }else System.err.println("用户认证失败");

        // 4、结束
        System.out.println();
        System.out.println("--------接收方用户B解密完毕--------");
    }


    public void authAndEncryption() {
        boolean flag = true;
    }
}
