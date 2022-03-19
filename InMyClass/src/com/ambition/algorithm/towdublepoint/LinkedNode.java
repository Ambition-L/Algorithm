package com.ambition.algorithm.towdublepoint;

import java.util.List;

/**
 * 链表：
 *  存储空间不连续 每一个空间存储着当前节点值和下一个空间的节点
 *
 * 优点：
 *      插入，删除快
 *      查找慢
 */
public class LinkedNode {
    public int data;
    public LinkedNode next;

    public LinkedNode(){}

    public LinkedNode(int data){
        this.data = data;
    }



    /**
     * 遍历链表
     */
    public List<Integer> printfs(LinkedNode head, List<Integer> list) {
        list.add(head.data);
        if (head.next == null)
            return list;
        return printfs(head.next, list);
    }

    /**
     * 查找节点 数值
     *  返回节点
     */
    public LinkedNode searchNode(LinkedNode head, int num) {
        if (head == null)
            return null;

        if (head.data == num) {
            return head;
        }else
            return searchNode(head.next, num);
    }

    /**
     * 查找节点 数值
     *  返回节点
     */
    public LinkedNode search2 (LinkedNode head, int index) {
        int i = 1;
        LinkedNode next = head.next;
        while (next != null && i < index) {
            i++;
            next = next.next;
        }
        if (i == index)
            return next;

        return null;
    }

    /**
     * 插入数据
     * @param head 头节点
     * @param index 插入位置
     * @param num 插入的节点数据
     * @param i 计数器
     * @return
     */
    public boolean insert(LinkedNode head, int index, int num, int i) {
        // 如果为空 则没有找到该位置数据
        if (head == null)
            return false;

        // 如果找到该数据的前一位数据
        // 因为头节点不存储数据 所以要找的数据为index 而不是index - 1
        if (i == index) {
            // 创建新的节点
            LinkedNode newNode = new LinkedNode(num);
            // 将父节点的下一个节点作为当前节点
            newNode.next = head.next;
            // 将当前节点作为父节点的下一个节点
            head.next = newNode;
            return true;
        }else {
            // 位置加一
            i++;
            return insert(head.next, index, num, i);
        }
    }

    /**
     * 删除节点
     *  找到删除节点的上一个节点和下一个节点
     *      将上一个节点的next 指向删除节点的next
     * @param head 头节点
     * @param index 删除节点位置
     * @param i 计数器
     * @return
     */
    public boolean deleteNode(LinkedNode head,int index, int i) {
        // 找不到删除的节点 返回false
        if (head == null)
            return false;

        // 如果找到删除节点的上一个节点 并且找到节点
        if (index == i && head.next != null ) {
            // 如果删除的节点没有子节点 直接删出
            if (head.next.next == null) {
                head.next = null;
            }else
                // 如果有子节点 将删除节点的上一个节点的next 指向删除节点的next
                head.next = head.next.next;

            return true;
        }else {
            // 记录位置
            i++;
            // 递归查找
            return deleteNode(head.next, index, i);
        }
    }

    /**
     * 判断是否为环形链表
     * @param node
     * @return
     */
    public boolean isCycle(LinkedNode node) {
        if (node.next == null)
            return false;
        LinkedNode p = node,q = node;
        while (q != null && q.next != null) {
            p = p.next;
            q = q.next.next;
            if (p == q) {
                return true;
            }
        }
        return false;
    }

    /**
     * 求链表长度
     *  环形链表求长度：
     *      一个指针不动 一个一直走 两指针相等 代表走了一圈
     * @param node
     * @return 链表长度
     */
    public int linkedLength (LinkedNode node) {
        if (node.next == null) {
            return 0;
        }
        int count = 1;
        LinkedNode p = node,q = node;

        // 不是环形链表
        if (!isCycle(node)) {
            // 定义一个指针 count记录长度
            p = p.next;
            while (p != null) {
                p = p.next;
                count++;
            }
        }else /* 是环形链表 定义定义两个指针求长度  */{
            while (q.next != null) {
                p = p.next;
                q = q.next.next;
                if (p != q) {
                    count++;
                }else
                    break;
            }

        }
        return count;
    }

    /**
     * 获取链表倒数第几个数据
     * @param node 头节点
     * @param k 倒数第多少个数
     *          index  角标
     * @return
     */
    public LinkedNode getReciprocalNum(LinkedNode node,int k, String index) {
        if (node.next == null)
            return null;

        int count = 1;

        // 定义快慢指针
        LinkedNode p = node,q = node;

        // 快指针走k步
        while (k > 0 && p.next != null) {
            p = p.next;
            k--;
        }

        // 如果没走完k步 说明无此数据
        if (k != 0) {
            return null;
        }

        // 快慢指针一起走直到快指针走到末尾 那么慢指针就是要找的值
        while (p != null) {
            count++;
            p = p.next;
            q = q.next;
        }

        index = "" + count;

        return q;
    }

    public LinkedNode getNode (LinkedNode head, LinkedNode q) {
        if (head == null)
            return null;

        if (head == q) {
            if (head.next == null){
                head = null;
            }
            if (head != null){
                head.data = q.next.data;
                head.next = q.next.next;
            }
            return null;
        }else {
            getNode(head.next, q);
        }
        return null;
    }

    /**
     * 生成链表
     */
    public LinkedNode init(LinkedNode head, int nums) {
        if (head.next != null)
            return init(head.next,nums);
        else
            head.next = new LinkedNode(nums);
        return head;
    }
}
class User {

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5};
        LinkedNode linkedNode = new LinkedNode();
        for (int i = 0; i < ints.length; i++) {
            linkedNode.init(linkedNode, ints[i]);
        }

        LinkedNode p = linkedNode,q = linkedNode;

        int k = 1;
        // 快指针走k步
        while (k > 0 && p != null) {
            p = p.next;
            k--;
        }
        // 如果没走完k步 说明无此数据
        if (k != 0) {
            System.out.println(linkedNode);
        }

        int count = 0;
        // 快慢指针一起走直到快指针走到末尾 那么慢指针就是要找的值
        while (p != null) {
            count++;
            p = p.next;
            q = q.next;
        }



        //System.out.println(linkedNode.printfs(linkedNode, new ArrayList<>()));
        //System.out.println(linkedNode);
        //LinkedNode linkedNode1 = linkedNode.searchNode(linkedNode, 6);
        //System.out.println(linkedNode1.data);
        //System.out.println(linkedNode.search2(linkedNode, 6).data);
        System.out.println(linkedNode.insert(linkedNode, count, q.data, 1));
        //System.out.println(linkedNode.deleteNode(linkedNode, 6, 1));
//        String a = "";
//        System.out.println(linkedNode.getReciprocalNum(linkedNode, 2, a).data);
//        System.out.println(a);
//        System.out.println(linkedNode.isCycle(linkedNode));
        System.out.println(linkedNode.getNode(linkedNode,q));
        System.out.println();
    }
}
