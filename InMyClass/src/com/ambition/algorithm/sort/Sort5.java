package com.ambition.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 桶排序
 *  1、计算数组中最大值 最小值 确认桶的大小
 *  2、调整元素放入桶中的位置 （顺序放置）桶中存放元素的个数，角标才是元素数值大小
 *  3、输出桶元素 （桶中元素的个数 * 角标）为当前输出的元素的个数
 */
public class Sort5 {
    public static void main(String[] args) {
        sort8(new Integer[]{12,5,14,2,2,16,72,5,7,12,10});
    }

    public static void sort8 (Integer[] nums) {
        // 计算数组中最大值 最小值
        int minv = Collections.min(Arrays.asList(nums));
        int maxv = Collections.max(Arrays.asList(nums));

        List<Integer> result = new ArrayList<>();

        // 确认桶的大小
        int tlength = maxv - minv + 1;

        // 创建桶
        List<Integer> count = new ArrayList<>(tlength);

        // 初始化桶数据
        for (int i = 0; i < tlength; i++) {
            count.add(0);
        }

        // 统计数据
        for (int i = 0; i < nums.length; i++) {
            // 调整角标 统计元素
            count.set(nums[i] - minv,count.get(nums[i] - minv) + 1);
        }

        // 输出元素到新的链表
        for (int i = 0; i < count.size(); i++) {
            if (count.get(i) == 0)
                continue;

            // 因为桶中存储的是 数据的个数 故需要循环输出数据
            for (int j = 0; j < count.get(i); j++) {
                result.add(minv + i);
            }
        }

        System.out.println(result);
    }
}
