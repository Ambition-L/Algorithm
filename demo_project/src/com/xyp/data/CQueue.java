package com.xyp.data;

import java.util.LinkedList;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 */
class CQueue {
    LinkedList<Integer> q1,q2;
    public CQueue() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void appendTail(int value) {
        q1.add(value);
    }

    public int deleteHead() {
        if (!q2.isEmpty()) return q2.removeLast();
        if (q1.isEmpty()) return -1;
        while (!q1.isEmpty())
            q2.add(q1.removeLast());
        return q2.removeLast();
    }
}