package com.xyp.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * 需求找出 s的最长字串长度
 *  输入 字符串 s "abcabcbb"
 *  输出 3
 */
public class DoublePointerDemo3 {

    public static void main(String[] args) {
        System.out.println(returnStr("pwwkew"));
    }

    /**
     * 双指针：
     *   左右指针滑动边界 条件
     *   通过不断重新滑动指针找到最长字串长度
     */
    public static int returnStr(String s) {
        char[] chars1 = s.toCharArray();
        // 定义双指针 （左右边界）
        int left = 0; int right = 0;
        // 最长字符串长度
        int strLength = 0;

        // 使用两个hash表来记录 目标字符串 和 指针中间字符串出现次数
        Map<Character, Integer> sMap = new HashMap<>();

        // 右指针一直向右滑动
        while (right < s.length()) {
            char s1 = chars1[right];
            // 存在则 + 1 不存在则新增
            sMap.merge(s1, 1, Integer::sum);
            // 当前记录集合包含当前指针key 且key的值不等于1 则表示找到字串 修改match 记录标记
            if (sMap.containsKey(s1) && sMap.get(s1) > 1) {
                // 取出最长字串
                if (right - left > strLength) {
                    strLength = right  - left - 1;
                }
                // 清空集合重新填充数据
                sMap.clear();
                // 左指针右移
                left++;
                // 右指针从新滑动
                right = left;
            }
            // 如果不包含 右指针滑动
            right++;
        }

        return strLength;
    }
}
