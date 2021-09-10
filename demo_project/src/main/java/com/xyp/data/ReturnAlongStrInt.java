package com.xyp.data;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回一个字符串的最长 无重复子串的长度
 */
public class ReturnAlongStrInt {
    public static void main(String[] args) {
//        System.out.println(returnStrInt("sjdfsld"));
        System.out.println(longestPalindrome("cbbd"));
    }

    public static String longestPalindrome(String s) {
        if(s.length() == 1) return s;

        // 定义双指针
        int left = 0, right = left + 1;
        // 定义字串长度
        int strLength = 1;
        // 定义两个记录指针数据的集合
        int[] xl= new int[2];
        char[] chars = s.toCharArray();

        while (right < chars.length) {
            String rights = String.valueOf(chars[right]);
            String lefts = String.valueOf(chars[left]);
            if (lefts.equals(rights)) {
                if (right - left >= strLength){
                    strLength = right - left;
                    //
                    xl[0] = left;
                    xl[1] = right;
                }
            }
            right++;

            if (right == chars.length && left != chars.length - 1) {
                left++;
                right = left + 1;
            }
        }
        return s.substring(xl[0],xl[1] + 1);
    }

    public static int returnStrInt(String str) {

        // 定义双指针
        int left = 0, right = 0;
        // 定义字串长度
        int strLength = 0;
        // 定义两个记录指针数据的集合
        Map<String,Integer> window = new HashMap<>();
        char[] chars = str.toCharArray();

        while (right < str.length() ) {
            String rights = String.valueOf(chars[right]);

            if (window.containsKey(rights)) {
                if (right - left >= strLength){
                    strLength = right - left;
                }
                window.clear();
                left ++;
                right = left;
            }else {
                window.put(rights,1);
                right++;
            }
        }
        return strLength;
    }

}
