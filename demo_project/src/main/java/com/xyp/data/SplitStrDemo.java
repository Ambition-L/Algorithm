package com.xyp.data;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 * 长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 */
public class SplitStrDemo {
    // 字符串长度 小于等于8的情况
    public static ArrayList lessThan8(ArrayList<String> strs, String str){
        String trimStr = str.trim();
        int strLenth = str.trim().length();
        if (str == null || trimStr.equals("")) {
            return strs;
        }
        if (strLenth == 8) {
            strs.add(str);
        }else {
            for (int i = 0; i < 8 - strLenth; i++) {
                trimStr = trimStr + "0";
            }
            strs.add(trimStr);
        }
        return strs;
    }

    // 字符串长度 大于等于8的情况
    public static ArrayList  moreThan8(ArrayList<String> strs, String str) {
        if (str.trim().length() <= 8) {
            return lessThan8(strs, str);
        }else {
            strs.add(str.substring(0, 8));
            return moreThan8(strs, str.substring(8));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            ArrayList arrayList = moreThan8(new ArrayList<>(), scanner.next());
            for (int i = 0; i < arrayList.size(); i++) {
                System.out.println(arrayList.get(i));
            }
        }
    }
}
