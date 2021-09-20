package com.xyp.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  字符串 s "cbaebabacd"
 *  字符串 t "abc"
 *  输出 [0,6]
 *  需求找出 s包含t 的所有异位字符串的字串 起始索引
 */
public class DoublePointerDemo2 {

    public static void main(String[] args) {
        System.out.println(returnStr("cbaebabacd", "abc"));
    }

    /**
     * 双指针
     */
    public static List<Integer> returnStr(String s, String t) {
        char[] chars1 = s.toCharArray();

        // 定义双指针 （左右边界）
        int left = 0; int right = 0;
        // 定义指针滑动条件 (锁定窗口条件 窗口缩小条件)
        int match = 0;

        // 使用两个hash表来记录 目标字符串 和 指针中间字符串出现次数
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        // 存放起始索引
        List<Integer> addStartIndex = new ArrayList<>();

        // 将t放入map中
        char[] chars = t.toCharArray();
        for (char aChar : chars) {
            tMap.put(aChar,0);
        }

        // 将right指针依次向右滑动 直至指针滑动到字符串末尾
        while (right < s.length()) {
            // 获取当前右指针数据
            char s1 = chars1[right];
            // 将数据放入窗口中 不存在则新增 存在则值+1
            sMap.merge(s1, 1, Integer::sum);

            // 目标字符串是否包含当前串
            if (tMap.containsKey(s1)) {
                // 记录
                tMap.put(s1,1);
                // 目标字符串是否和当前指针数据相等 (如果大于等于则代表 已经存在)
                if (sMap.get(s1) == 1) {
                    match++;
                }
            }

            // 滑动窗口
            right++;

            // 当记录数据长度满足目标字符串长度
            while (match == chars.length) {
                // 当窗口之间满足 字符串长度时
                if (right - left == t.length()) {
                    addStartIndex.add(left);
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
        return addStartIndex;
    }
}
