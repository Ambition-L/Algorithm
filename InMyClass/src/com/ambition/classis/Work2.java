package com.ambition.classis;

/**
 * 2、世界上只有一个月亮，月亮的直径是3476.28km，无论在中国还是在美国，我们所看到的都是同一个月亮。
 *  使用单例模式实现无论我们在哪所看到的月亮是同一个月亮（懒汉单例模式）。
 */

// 饿汉式
class Moon2 {
    private static final Moon2 m = new Moon2();

    private Moon2(){}


    public static Moon2 getInstance() {
        return m;
    }
}

// 懒汉式
class Moon {
    private static Moon moon;

    private Moon (){
    }

    public static Moon getInstance() {
        if (moon == null) {
            synchronized (Moon.class) {
                if (moon == null) {
                    moon = new Moon();
                }
            }
        }
        return moon;
    }
}
public class Work2 {

    public static void main(String[] args) {
        Moon instance = Moon.getInstance();
        Moon instance2 = Moon.getInstance();
    }
}
