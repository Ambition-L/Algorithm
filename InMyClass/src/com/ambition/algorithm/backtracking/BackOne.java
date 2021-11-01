package com.ambition.algorithm.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *  回溯算法：
 *      用于穷举while 和 for无法穷举的算法
 *      例如 排列组合 等等
 *  算法公式：
 *      1、准备好可行路径，和存储路径的容器
 *      2、回溯结束条件 一般是 路径长度==容器长度
 *      3、选择路径
 *      4、选择之前要做的事情 例如过滤不需要的元素 等等
 *      5、选择
 *      6、回溯
 *      7、选择之后要做的事情 不如撤回操作等
 */
public class BackOne {
    static List<LinkedList<Integer>> linkedLists = new ArrayList<>();

    public static void main(String[] args) {
        new BackOne().back1(new int[]{1,2,3,4},new LinkedList<>());
        System.out.println(linkedLists);
    }

    /**
     * 给出一组无重复数据的数组 输出该数组数据的所有组合。
     * @param nums 可选择路径
     * @param list  存储路径容器
     */
    public void back1 (int[] nums, LinkedList<Integer> list) {
        // 结束条件 当决策树到底时。
        if (nums.length == list.size()) {
            // 找到答案
            linkedLists.add(new LinkedList<>(list));
            return;
        }

        // 选择路径
        for (int i = 0; i < nums.length; i++) {
            // 过滤掉集合中存在的数据
            if (list.contains(nums[i])) continue;

            // 选择数据
            list.add(nums[i]);

            // 回溯
            back1(nums, list);

            // 撤回选择
            list.removeLast();
        }
    }
}
