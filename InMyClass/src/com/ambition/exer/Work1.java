package com.ambition.exer;

import java.util.*;

/**
 * 给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 *
 * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 */
public class Work1 {

    public static void main(String[] args) {
        System.out.println(new Work1().reorderedPowerOf2(268341));
    }

    Set<Boolean> set = new HashSet<>();
    List<LinkedList<Character>> s = new ArrayList<>();

    public boolean reorderedPowerOf2(int n) {
        if (isTrue(n))
            return true;

        StringBuilder sb = new StringBuilder(n+"");
        dsf(sb,0,new LinkedList<>());

        if (set.contains(true))
            return true;
            return false;
    }

    public void dsf (StringBuilder str, int index,LinkedList<Character> list) {
        // 结束条件
        if (str.length() == list.size()) {
            set.add(isTrue(list));
            s.add(new LinkedList<>(list));
            return;
        }

        // 选择路径
        for (int i = 0; i < str.length(); i++) {
            //if (list.contains(str.charAt(i))) continue;
            // 选择
            list.add(str.charAt(i));
            // 回溯
            dsf(str,i,list);
            // 撤回
            list.removeLast();
        }
    }

    public boolean isTrue(Integer integer) {
        return integer > 0 && (integer & (integer - 1)) == 0;
    }

    public boolean isTrue(List<Character> list) {
        if (list.get(0) == '0')
            return false;

        String str = "";
        for (Character character : list) {
            str += character;
        }

        int integer = Integer.parseInt(str);
        return (integer & (integer - 1)) == 0;
    }
}
