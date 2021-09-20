package com.xyp.data;


import java.util.LinkedList;

/**
 * 倒叙遍历链表
 *   方法一：递归将链表数据押入栈中，然后利用栈先入后出的特点，将数据放入数组中
 *   方法二：先确定链表长度，然后倒叙放入链表数据。
 *
 */
public class PrintListNode {
    private Integer a = 1;
    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(2);
        l1.next = l2;
        l2.next = l3;

        int[] ints = reversePrint(l1);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
//        LinkedList<Integer> stack = test(l1, new LinkedList<>());
//        int[] arr = new int[stack.size()];
//        for (int i = arr.length - 1; i >= 0; i--) {
//            arr[i] = stack.get(i);
//        }

    }

    /**
     * 方法二
     * @param head
     * @return
     */
    public static int[] reversePrint (ListNode head) {
        ListNode head1 = head;
        int len = 0;

        while (head != null) {
            len++;
            head = head.next;
        }

        int[] arr = new int[len];
        len = len - 1;
        while (head1 != null) {
            arr[len--] = head1.val;
            head1 = head1.next;
        }

        return arr;
    }


    /**
     * 方法一
     * @param head
     * @param stack
     * @return
     */
    public static LinkedList<Integer> test(ListNode head, LinkedList<Integer> stack) {
        if (head == null) {
            return stack;
        }else {
            stack.add(head.val);
            test(head.next, stack);
        }
        return stack;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
 }

