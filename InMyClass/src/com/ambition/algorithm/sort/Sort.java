package com.ambition.algorithm.sort;

/**
 * 01 插入排序 (无序区数据 依次和有序去区数据比较 如果符合需求 比较一次交换依次)
 *   1、获取当前位置
 *   2、比较
 *   3、交换位置
 *   涉及到两个比较区域 有序区 无序区
 *
 *   01 -1 插入排序优化版本 （折中比较插入--无序区数据每次挑选有序区中间位置数据进行比较）
 *      01 初始化有序区数组 顺便将无序区数组第一个数据放入有序区数组 （减少了一次比较次数 并且不用判空）
 *      02 确定有序区数组左右边界和中间值角标
 *      03 当左边界小于或者等于右边界时 判断当前值与中间值的关系
 *             03-1 当前值大于中间值 左边界缩小 重新计算中间值角标
 *             03-2 当前值小于中间值 右边界缩小 重新计算中间值角标
 *             03-3 当前值等于中间值 中间值=中间值角标-1
 *             04-4 最后中间值 就是最接近当前值的数据
 *      04 如果当前值大于中间值 则将该数据放入中间值后面
 *      05 如果当前值小于等于中间值 则将该数据放入中间值前面
 */
/**
 * 02 选择排序 (将无序区数据依次与最小值比较 直到找到最符合需求的数据再进行交换 )
 *  1、定义最小值
 *  2、与最小值比较
 *  3、交换
 *  涉及到两个比较区域
 */
/**
 * 03 冒泡排序 （从第一个数据依次往后比较 符合需求的话 比较依次交换一次）
 *  1、前一个数据和后一个数据比较
 *  2、符合需求
 *  3、交换位置
 *  一个比较区域 -- 当前数组
 */
/**
 * 04 希尔排序 （插入排序的加强版--将数组中的数据每隔d个数据 两两进行比较 最后将较大的数据先放到了最后 可以减少后续排序的比较次数）
 *  1、确定d的长度
 *  2、最佳长度 5，2，1
 *  3、比较并交换位置
 */
import java.util.ArrayList;

public class Sort {
    static ArrayList<Integer[]> list = new ArrayList<>();

    public static void main(String[] args) {


        int[] sort = sort4(new int[]{35, 27,6,2, 1, 5,  100,11},2);
        int[] sort1 = sort4(sort,1);
        int[] sort2 = sort4(sort1,1);
        for (int i : sort) {
            System.out.print(i + ",");
        }

        //System.out.println(sorts(new int[]{2, 1, 5, 35, 27,6 , 100}));
    }

    // 01 插入排序
    public static int[] sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] < nums[j]) {
                    int tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                }
            }
        }
        return nums;
    }

    // 01-1 插入排序 改进方法 折中比较
    public static ArrayList<Integer> sorts(int[] nums) {
        // 初始化有序区链表
        ArrayList<Integer> list = new ArrayList();
        list.add(nums[0]);
        // 遍历无序区
        for (int i = 1; i < nums.length; i++) {
            // 定义有序区链表左右边界
            int left = 0,right = list.size() - 1;
            // 选取中间值角标
            int mid = (left + right)/2;
                // 如果左边界大于右边界时停止查找
                while (left <= right) {
                    // 如果当前数据大于中间值
                    if (nums[i] < list.get(mid)) {
                        // 右边界缩小
                        right = mid - 1;
                        // 重新选取中间值角标
                        mid = (left + right)/2;
                    // 如果当前值大于中间值
                    }else if (nums[i] > list.get(mid)) {
                        // 左边界缩小
                        left = mid + 1;
                        // 重新选取中间值角标
                        mid = (left + right)/2;
                    }else {
                        // 如果相等 则将该值插入中间值前面防止角标溢出
                        mid = mid - 1;
                    }
                }

            // 如果中间值 大于等于当前值 则插入中间值前面
            if (list.get(mid) >= nums[i]) {
                // 添加虚拟元素 腾出插入数据的空间
                list.add(0);
                // 将当前值和当前值以后的值角标依次往后移动一位
                for (int j = list.size() - 1; j > mid - 1 && j >0; j--) {
                    list.set(j,list.get(j - 1));
                 }
                // 插入数据
                list.set(mid,nums[i]);
            }else /* 如果中间值 小于当前值 则插入中间值前面  */{
                list.add(0);
                // 将当前值以后的值角标依次往后移动一位
                for (int j = list.size() - 1; j > mid && j > 0; j--) {
                    list.set(j,list.get(j - 1));
                }
                // 插入数据
                list.set(mid + 1,nums[i]);
            }

        }
        return list;
    }

    // 02 选择排序
    public static int[] sort2(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int min = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            int tem = nums[i];
            nums[i] = nums[min];
            nums[min] = tem;
        }
        return nums;
    }

    // 03 冒泡排序
    public static int[] sort3 (int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        return nums;
    }

    // 04 希尔排序
    public static int[] sort4 (int[] nums, int d) {
        int dlen = nums.length / (d + 1);
        for (int i = 0; i < dlen; i++) {
            if (nums[i] > nums[i + d + 1]) {
                int temp = nums[i];
                nums[i] = nums[i + d + 1];
                nums[i + d + 1] = temp;
            }
        }
        return nums;
    }
}
