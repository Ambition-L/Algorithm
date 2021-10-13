package com.ambition.algorithm.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 05 归并排序 （将无序数组分成若干个长度为1的小数组 随后将若干个数组比较合并 最后合并成一个有序数组 --> 分治思想）
 * 1、拆
 * 2、比较
 * 3、合并
 *  Java解题思路
 *      使用ArrayList<Integer[]> 链表存储递归拆分的若干个长度为1的数组
 *      将ArrayList<Integer[]> 转换成List<ArrayList<Integer>> 方便添加数据操作
 *      使用链表存储两两比较后的数组形成新链表 ArrayList<Integer>
 *      使用链表存储两两比较之后的若干个数组 形成新的List<ArrayList<Integer>>
 */
public class Sort2 {
    static ArrayList<Integer[]> list = new ArrayList<>();

    public static void main(String[] args) {
        sort4(new Integer[]{2, 1, 5, 35, 27, 6, 100, 11});
        // 将数组转换成 链表--》方便后期创建合并数组时不用填写数组长度
        List<ArrayList<Integer>> collect
                = list.stream()
                // 过滤空值
                .filter(t -> Objects.nonNull(t))
                // 集合转换
                .map(ts -> {
                    ArrayList<Integer> news = new ArrayList<>();
                    news.add(ts[0]);
                    return news;
                })
                .collect(Collectors.toList());

        System.out.println(sort4_02(collect).get(0));
    }

    /**
     * 2 ->3 比较-合并-比较-合并... 直到合并为一个数组停止合并
     * @param collect
     * @return
     */
    public static List<ArrayList<Integer>> sort4_02(List<ArrayList<Integer>> collect) {
        // 初始化一个存储合并完的数组的链表
        List<ArrayList<Integer>> newss = new ArrayList<>();
        /* 当数组长度为1时说明已经合并完毕 故返回已经排序好的数组 */
        if (collect.size() == 1)
            return collect;

        for (int i = 0; i < collect.size(); i += 2) {
            // 初始化两两数组比较完合并链表
            ArrayList<Integer> news = new ArrayList<>();
            // 依次取出拆分数组中前后数组
            ArrayList<Integer> integers = collect.get(i);
            ArrayList<Integer> integers1 = collect.get(i + 1);
            // 定义前后数组 指针，
            int left = 0, right = 0;
            // 2 比较：当两组数组数据都没有比较完成式
            while (left != integers.size() && right != integers1.size()) {
                // 3：合并 滑动指针将两数组数据依次做比较
                if /* 左边小于右边数组值 */(integers.get(left) < integers1.get(right)) {
                    // 将左边数据添加入有序的新数组
                    news.add(integers.get(left));
                    // 将左边数组指针指向下一个数据
                    left++;
                } else /* 当右边数组数据小于左边数据时 */{
                    // 添加右边数据进新的有序数组
                    news.add(integers1.get(right));
                    // 右指针指向下一个数据
                    right++;
                }
            }

            // 如果是左边数组全部比较完成 故将右边数组数据继续添加入新数组中
            if (left == integers.size()) {
                // 遍历右数组
                for (int j = right; j < integers1.size(); j++) {
                    // 添加数据
                    news.add(integers1.get(j));
                }
            } else /* 反之相同 */{
                for (int j = left; j < integers.size(); j++) {
                    news.add(integers.get(j));
                }
            }
            // 将合并完的数组放入合并数组存放链表
            newss.add(news);
        }
        // 返回递归比较合并
        return sort4_02(newss);
    }

    // 1 拆：将当前数组拆分成若干个长度为1的数组返回
    public static void sort4(Integer[] nums) {
        if /* 当数组长度为1时返回拆分数组 */(nums.length == 1)
            list.add(nums);
        else {
            // 计算中间值角标
            int mid = nums.length / 2;
            // 根据中间值角标将当前数组拆分成两个数组 分别放入数据
            /* 左边数组 */Integer[] left = new Integer[mid];
            for (int i = 0; i < mid; i++) {
                left[i] = nums[i];
            }
            /* 右边数组 */Integer[] right = new Integer[mid];
            int j = 0;
            for (int i = mid; i < nums.length; i++) {
                right[j] = nums[i];
                j++;
            }
            // 递归拆分左右数组
            sort4(left);
            sort4(right);
        }

    }
}
