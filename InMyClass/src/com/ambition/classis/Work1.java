package com.ambition.classis;

/**
 * 1、通过一个实例来演示简单工厂模式
 * 背景：果园种植了苹果、草莓、桃子，三种水果。我们可以根据水果名称选择购买不同的水果。
 * 目的：通过简单工厂模式实现3种水果的生产。
 */

interface GuoYuan {
    void show();
}

class Apple implements GuoYuan{

    @Override
    public void show() {
        System.out.println("苹果");
    }
}
class CaoMei implements GuoYuan{

    @Override
    public void show() {
        System.out.println("草莓");
    }
}
class TaoZi implements GuoYuan{

    @Override
    public void show() {
        System.out.println("桃子");
    }
}
class EasyFactor {
    public static GuoYuan getInstance(String type) {
        if ("apple".equals(type)) {
            return new Apple();
        }else if ("caomei".equals(type)) {
            return new CaoMei();
        }else
            return new TaoZi();
    }
}

public class Work1 {
    public static void main(String[] args) {
        GuoYuan apple = EasyFactor.getInstance("apple");
        apple.show();
        GuoYuan caomei = EasyFactor.getInstance("caomei");
        caomei.show();
        GuoYuan taozi = EasyFactor.getInstance("taozi");
        taozi.show();
    }
}
