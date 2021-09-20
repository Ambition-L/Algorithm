package com.xyp.data;

public class Test1 {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("aacabdkacaa"));
    }

    public static boolean isPas(char[] strs, int left , int right) {
        while (left <= right) {
            if (strs[left] != strs[right]) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
//        if(strs[left] != strs[right] || left > right + 1) {
//            return false;
//        }else if (strs[left] == strs[right] && left >= right){
//            return true;
//        }else {
//            left++;
//            right--;
//            return isPas(strs, left, right);
//        }
    }

    public static String longestPalindrome(String s) {
        if(s.length() == 1) return s;

        // 定义双指针
        int left = 0, right = s.length() - 1;
        // 定义字串长度
        int strLength = 1;
        // 定义两个记录指针数据的集合
        int[] xl= new int[2];
        char[] chars = s.toCharArray();

        while (right > left) {
            String rights = String.valueOf(chars[right]);
            String lefts = String.valueOf(chars[left]);
            if (lefts.equals(rights)) {
                // 判断是否是回文字串 递归
                char[] chars1 = s.substring(left,right + 1).toCharArray();
                boolean isFs = isPas(chars1, 0 , chars1.length - 1);

                if (isFs && right - left >= strLength){
                    strLength = right - left;
                    xl[0] = left;
                    xl[1] = right;
                    left ++;
                    right = s.length() - 1;
                }else right--;
            }else {
                right--;
            }

            if (right == left && left != chars.length - 1) {
                left++;
                right = s.length() - 1;
            }
        }
        return s.substring(xl[0],xl[1] + 1);
    }
}
