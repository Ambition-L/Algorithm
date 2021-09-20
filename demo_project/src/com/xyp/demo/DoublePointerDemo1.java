package com.xyp.demo;

import java.util.HashMap;
import java.util.Map;

/**
 *  字符串 s "ADOBECODEBANC"
 *  字符串 t "ABC"
 *  需求找出 s包含t 最短字符串
 */
public class DoublePointerDemo1 {

    public static void main(String[] args) {
        System.out.println(returnStr("aa", "aa"));
    }

    /**
     * 双指针
     *      求解字符串s包含字符串t的最短串
     *      思路 左右指针滑动的条件
     *       1、右指针滑动的条件 --》left指针到right窗口之间没有满足s包含t字符串时 一直滑动
     *       2、左指针滑动条件  --》当left指针到right窗口之间有满足当前s包含t字符串时 一次缩小窗口范围找到最小字符串
     *      注意点：1、使用两个hash表来分别记录s字符串和t字符串出现的次数
     *             2、使用一个计数器来记录s字符串是否完全满足t字符串的条件
     *             3、计数器的变化时机
     *                  01、当目标t字符串包含了 s字符串中的一个没有重复的字母时 计数器 + 1 right继续向右划动 当match满足 match = t.length 时 标志着left指针的滑动
     *                  02、当left指针向右移动 t字符串不包含当前left指针的字母 或者 s字符串不包含当前字符串的所有字母时 计数器 -1 标志着right指针将继续右划
     *      《特别注意 双指针滑动的控制条件 何时左指针滑动 何时右指针滑动》
     *      时间复杂度 O(s*t)
     */
    public static String returnStr(String s, String t) {
        char[] chars1 = s.toCharArray();

        // 定义双指针 （左右边界）
        int left = 0; int right = 0;
        // 定义指针滑动条件 (锁定窗口条件 窗口缩小条件)
        int match = 0;
        // 定义最小字符串起始坐标和结束坐标
        int start = 0; int end = 0;
        // 定义字符串长度
        int strLength = Integer.MAX_VALUE;

        // 使用两个hash表来记录 目标字符串 和 指针中间字符串出现次数
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        // 将t放入map中
        char[] chars = t.toCharArray();
        for (char aChar : chars) {
            tMap.put(aChar,0);
        }

        for (char tt1 : chars1) tMap.put(tt1, tMap.getOrDefault(tt1, 0) + 1);

        // 1 指针滑动条件
        while(right < s.length()) {
            // 取出指针数据
            char s1 = chars1[right];
            if (tMap.containsKey(s1)) {
                // 放入窗口中 如果存在则+1 不存在则保存
                sMap.put(s1, sMap.getOrDefault(s1, 0) + 1);
                if(sMap.get(s1) == tMap.get(s1)) {
                    match++;
                }
            }
            // 滑动窗口
            right++;

            // 当记录数据长度满足目标字符串长度
            while (match == t.length()) {
                // 取出字符串 长度
                if (right - left < strLength) {
                    start = left;
                    end = right;
                    strLength = right - left;
                }

                // 取出左指针数据
                char leftC = chars1[left];
                // 当前窗口是否已然包括 当前指针数据
                if (tMap.containsKey(leftC)) {
                    // 如果包含 （使得当前字母记录书 - 1）
                    sMap.put(leftC,sMap.get(leftC) - 1);
                    // 如果不满足目标字符串记录数
                    if (sMap.get(leftC) < tMap.get(leftC))
                        // 右指针滑动
                        match--;
                }
                // 如果不包含当前数据 或者依然满足条件 则指针向右滑动
                left++;
            }
        }

        // 如果未找到 字符串 返回空串
        return strLength == Integer.MAX_VALUE? "":s.substring(start, end);
    }
}
