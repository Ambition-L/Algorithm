package com.ambition.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 归并排序
 *   1、将原无序数组拆分成若干个长度为1的数组
 *   2、将若干个长度为1的数组两两进行比较合并
 *   3、最后合并成一个长度为1的有序数组
 * 快速排序 （归并排序的优化版本，优化了拆分） T(n) = nlogn
 *   1、选取一个数据，将比该数据大的放到该数据左边，将比该数据小的放到右边
 *   2、依次往下递归选取中间值 然后比较并放到该中间值的左右两边
 *   3、直到分成长度为1的数组之后 可变成一个有序的数组
 */
/**
 * 第一种方法
 *  重新定义三个数组 分别接收 小于主元素的值，等于主元素的值，大于主元素的值
 *  然后将三个数组递归排序后合并
 *  当数组长度小于1时合并完毕。
 */
/**
 * 第二种方法 ： 双指针
 *  维护两个指针 一个指针指向数组起始角标 一个指针指向数组结束角标 用临时变量保存数组第一个值 作为主元素
 *  移动右指针直到找到小于主元素的数值 或者已经检索完所有的左右指针区间内所有的数值
 *          然后将右指针的值赋给左指针位置 此时右指针位置空缺
 *  移动左指针直到找到 大于主元素的数值 或者已经检索完左右指针区间内所有的值
 *          然后将左指针的值赋给右指针位置 此时左指针位置空缺
 *  以此递归比较交换 将数组起始指针的 结束指针传入方法即可
 *  当传入的左指针大于等于右指针时 表示所有拆分的数组都以比较完毕 排序结束
 */
public class Sort3 {

     int[] list = new int[]{2, 1, 5, 35, 27, 6, 100, 11};

    public static void main(String[] args) {
//        Sort3 sort3 = new Sort3();
//        sort3.sort602(0, sort3.list.length - 1);
//
//        for (int arg : sort3.list) {
//            System.out.println(arg + ",");
//        }

        List<Integer> ints = sort601(Arrays.asList(2, 1, 5, 35, 27, 6,2,1, 100, 11));
        System.out.println(ints);
    }

    /**
     * @param left 原数组的起始坐标
     * @param right 原数组的结束坐标
     */
    public  void sort602(int left, int right) {
        // 起始大于或等于结束 代表区间数组小于等于1 比较结束
        if (left >= right)
            return;

        // 定义左右指针 和 主元素 （左指针取数组起始坐标，右指针取数组结束坐标，主元素取数组第一个值
        int l = left,r = right,key = list[left];

        // 比较指针内的所有数值并交换位置 直到左右指针重叠 （所有数值已经检索完）
        while (l < r) {
            // 右指针移动 直到找到 小于主元素的值 或者 区间内的值已经检索完
            while (l < r && list[r] >= key) {
                r--;
            }
            // 将找到的小于主元素的值 与左指针进行交换 此时右指针位置为空
            list[l] = list[r];

            // 交换完之后 移动左指针 直到检索到 大于主元素的值 或者 区间内的所有值已经检索完
            while (l < r && list[l] < key) {
                l++;
            }
            // 将左指针找到的小于主元素的值 与右指针进行交换 此时左指针的位置为空
            list[r] = list[l];
        }
        // 将主元素放到中间
        list[l] = key;

        // 分别排序两边数组
        // 将主元素左边数组 起始和结束 角标传入方法
        sort602(left,l - 1);
        // 将主元素右边数组 起始和结束 角标传入方法
        sort602(l + 1, right);
    }


    public static List<Integer> sort601(List<Integer> nums) {
        ArrayList<Integer> aa = new ArrayList<>();
        if (nums.size() <= 1)
            return nums;

        int key = nums.get(0);
        ArrayList<Integer> lList = new ArrayList<>();
        ArrayList<Integer> rList = new ArrayList<>();
        ArrayList<Integer> mList = new ArrayList<>();
        mList.add(key);
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < key) {
                lList.add(nums.get(i));
            }else if (nums.get(i) > key) {
                rList.add(nums.get(i));
            }else
                mList.add(nums.get(i));
        }

        aa.addAll(sort601(lList));
        aa.addAll(mList);
        aa.addAll(sort601(rList));
        return aa;
    }


    /**
     * 失败残次品
     * @param nums
     * @return
     */
    public static int[] sort6(int[] nums) {
        if (nums.length == 1)
            return nums;

        // 定义中间值
        int mid = nums[nums.length/2];
        nums[nums.length/2] = nums[nums.length - 1];
        nums[nums.length - 1] = mid;

        // 定义中间值 左右区间的指针
        int left = nums.length;

        for (int i = 0; i < nums.length; i++) {
            // 如果当前值 小于主元 则将当前值和 右区间的第一个值进行交换 形成新的左右区间
            if (nums[i] <= mid) {
                if (left == nums.length) {
                    left = 0;
                    int temp = nums[left];
                    nums[left] = nums[i];
                    nums[i] = temp;
                } else {
                    int temp = nums[left + 1];
                    nums[left + 1] = nums[i];
                    nums[i] = temp;
                    left++;
                }
            }
        }

        int[] leftNums = new int[left + 1];
        int[] rightNums = new int[nums.length - left];
        int[] ints2 = null;
        if (left >= nums.length - 1) {
            for (int i = 0; i < left; i++) {
                leftNums[i] = nums[i];
            }

            rightNums[0] = nums[nums.length - 1];

            int[] ints = sort6(leftNums);
            int[] ints1 = sort6(rightNums);

            // 合并数组
            ints2 = new int[ints.length + ints1.length];

            for (int i = 0,j = 0; i < ints2.length; i++) {
                if (i < ints.length) {
                    ints2[i] = ints[i];
                }else {
                    ints2[i] = ints1[j];
                    j++;
                }
            }
        } else {
            // 将主元值放到中间
            for /* 倒序将大于主元的数据依次向数组后方移动 */(int i = nums.length - 1;  i > left; i--) {
                nums[i] = nums[i - 1];
            }

            // 如果左边全部小于右边 将主元放入中间
            // 并分割数组
            if (nums.length > left + 1){
                nums[left + 1] = mid;
                for (int i = 0; i <= left; i++) {
                    leftNums[i] = nums[i];
                }
                for (int i = left + 2,j = 0; i < nums.length; i++, j++) {
                    rightNums[j] = nums[i];
                }

                int[] ints = sort6(leftNums);
                int[] ints1 = sort6(rightNums);

                // 合并数组
                ints2 = new int[ints.length + ints1.length];
                for (int i = 0,j = 0; i < ints2.length; i++) {
                    if (i < ints.length) {
                        ints2[i] = ints[i];
                    }else {
                        ints2[i] = ints1[j];
                        j++;
                    }
                }
            }
        }
        return ints2;
    }
}
