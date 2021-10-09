package com.ambition.classis;

/**
 * 2、神话传说：女娲娘娘挥动神绳造人，挥动阴绳造出女人，挥动阳绳造出男人。（学习通画出类图）
 * 1.抽象产品：人
 * 2.具体产品：
 * ①男人
 * ②女人
 * 3.抽象工厂：女娲神绳
 * 4.具体工厂：
 * ①阳绳
 * ②阴绳
 * 5.用户：玉皇大帝
 *
 * 工厂方法角色：
 *      抽象商品
 *      具体商品
 *      抽象工厂
 *      具体工厂
 */
interface People {
    void run();
    void eat();
}
class Man implements People{
    @Override
    public void run() {
        System.out.println("男人走路");
    }

    @Override
    public void eat() {
        System.out.println("男人吃饭");
    }
}
class Woman implements People{
    @Override
    public void run() {
        System.out.println("女人走路");
    }

    @Override
    public void eat() {
        System.out.println("女人吃饭");
    }
}
interface Nvwa {
    People ZhaoRen();
}
class YangShen implements Nvwa{

    @Override
    public People ZhaoRen() {
        return new Man();
    }
}
class YinShen implements Nvwa {

    @Override
    public People ZhaoRen() {
        return new Woman();
    }
}

public class Work4 {
    public static void main(String[] args) {
        Nvwa yangShen = new YangShen();
        People man = yangShen.ZhaoRen();
        man.run();
        man.eat();

        Nvwa yinShen = new YinShen();
        People woman = yinShen.ZhaoRen();
        woman.eat();
        woman.run();
    }
}
