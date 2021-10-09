package com.ambition.classis;

/**
 * 一款游戏中，具有多种植物角色角色可供用户选择，豌豆射手、坚果墙、向日葵。。。
 */
// 抽象植物
interface ZhiWu{
    void show();
}
// 具体植物
class XRK implements ZhiWu{

    @Override
    public void show() {
        System.out.println("向日葵 生产阳光");
    }
}

class JG implements ZhiWu{

    @Override
    public void show() {
        System.out.println("坚果 抵御攻击");
    }
}

class SheShou implements ZhiWu {

    @Override
    public void show() {
        System.out.println("豌豆射手 攻击");
    }
}

// 工厂
class Factor {
    public static ZhiWu getInstance (String type) {
        if ("xrk".equals(type)) {
            return new XRK();
        }else if ("jg".equals(type)) {
            return new JG();
        }else
            return new SheShou();
    }
}

public class Work3 {
    public static void main(String[] args) {
        GuoYuan xrk = EasyFactor.getInstance("xrk");
        xrk.show();
        GuoYuan jg = EasyFactor.getInstance("jg");
        jg.show();
        GuoYuan sheshou = EasyFactor.getInstance("sheshou");
        sheshou.show();
    }
}
