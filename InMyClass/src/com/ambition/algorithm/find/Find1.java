package com.ambition.algorithm.find;

/**
 * 1、暴力查找
 * 2、二分查找 (大前提 需要在数组有序的情况下)
 *   维护三个指针 start、mid、end 分别是起始指针 数组"中" 指针 和 结束指针
 *   将查找值与中间指针值进行比较
 *      如果比中间指针值大 则移动左指针到 mid + 1
 *      如果比中间指针值小 则移动右指针到 mid - 1
 *      如果等于中间指针值则返回
 *   如果三个指针重叠依旧没有找到 则代表无此数据
 */
public class Find1 {
    public static void main(String[] args) {
        System.out.println(find2(new int[]{ 1,2,3,4, 5, 6, 7, 8, 9}, 9));
    }


    // 2、二分查找
    public static int find2 (int[] nums, int key) {
        // 定义左指针 右指针
        int left = 0, right = nums.length - 1;

        // 计算中指针
        int mid = (right + left) / 2;

        // 当左指针小于右指针时 进行二分查找
        while (left <= right) {
            if (key > nums[mid]) {
                left = mid + 1;
                mid = (right + left) / 2;
            }else if (key < nums[mid]) {
                right = mid - 1;
                mid = (right + left) / 2;
            }else
                return mid;
        }

        return -1;
    }

    // 1、暴力查找
    public static int find1 (int[] nums,int key) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key)
                return i;
        }
        return -1;
    }
}
