package com.ambition;

import java.util.*;
import java.util.stream.Collectors;


public class Test {


    static class Main {
        public static void main(String[] args) {}


    }

    static  class Test3 {
        //N皇后问题
        public static void main(String[] args) {
            int[][] nums = new int[][]{
                    {0,0,0,0},
                    {0,0,0,0},
                    {0,0,0,0},
                    {0,0,0,0}
            };

            //System.out.println(new Test3().isTrue(nums,1,2));
           // new Test3().dfs(nums,0,0);
            boolean[][] ss = new boolean[2][2];
            boolean a = true;
            HashSet<String> set = new HashSet<>();
            String s = "1s3 PSt";
            String s1 = s.toLowerCase();
            System.out.println(s1);
            //System.out.println(a);
        }


        // 回溯
        public void dfs(int[][] nums,int x,int y) {
            if (!isTrue(nums, x,y)) {
                return;
            }

            for (int i = x; i < nums.length; i++) {
                for (int j = y; j < nums.length; j++) {
                    nums[i][j] = 1;
                    dfs(nums,x,j);
                }
                dfs(nums,i+1,0);
            }
        }


        // 校验n皇后 的指定位置是否可以放置棋子
        public boolean isTrue(int[][] nums,int x,int y) {
            // 行列
            for (int i = 0,j = 0; i < nums.length; i++,j++){
                if (nums[x][i] == 1 || nums[j][y] == 1) return false;
            }

            // 对角
            int x1 = x - 1,y1 = y - 1;
            while (x1 >= 0 && x1 < nums[0].length && y1 >= 0 && y1 < nums[0].length){
                if (nums[x1][y1] == 1) return false;
                x1--;y1--;
            }

            x1 = x - 1;
            y1 = y + 1;
            while (x1 >= 0 && x1 < nums[0].length && y1 >= 0 && y1 < nums[0].length){
                if (nums[x1][y1] == 1) return false;
                x1--;y1++;
            }

            x1 = x + 1;
            y1 = y - 1;
            while (x1 >= 0 && x1 < nums[0].length && y1 >= 0 && y1 < nums[0].length){
                if (nums[x1][y1] == 1) return false;
                x1++;y1--;
            }

            x1 = x + 1;
            y1 = y + 1;
            while (x1 >= 0 && x1 < nums[0].length && y1 >= 0 && y1 < nums[0].length){
                if (nums[x1][y1] == 1) return false;
                x1++;y1++;
            }
            return true;
        }
    }


    public List<String> removeInvalidParentheses(String s) {
        // 先统计需要删除的左括号数和右括号数
        int leftExtra = 0, rightExtra = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftExtra++;
            } else if (c == ')') {
                if (leftExtra == 0) {
                    rightExtra++;
                } else {
                    leftExtra--;
                }
            }
        }

        // 如果字符串本身就是合法的，就不用删除了
        if (leftExtra == 0 && rightExtra == 0) {
            return Arrays.asList(s);
        }

        Set<String> ans = new HashSet<>();

        // 回溯
        dfs(ans, s, 0, new StringBuilder(), leftExtra, rightExtra);

        return new ArrayList<>(ans);
    }

    // "()())()" leftExtra = 0；rightExtra = 1；
    private void dfs(Set<String> ans, String s, int i, StringBuilder sb, int leftExtra, int rightExtra) {
        if (i == s.length()) {
            if (leftExtra == 0 && rightExtra == 0 && isValid(sb)) {
                ans.add(sb.toString());
            }
            return;
        }

        char c = s.charAt(i);
        if (c == '(') {
            // 要这个左括号
            sb.append(c);
            dfs(ans, s, i + 1, sb, leftExtra, rightExtra);
            sb.deleteCharAt(sb.length() - 1);

            // 不要这个左括号
            if (leftExtra > 0) {
                dfs(ans, s, i + 1, sb, leftExtra - 1, rightExtra);
            }
        } else if (c == ')') {
            // 要这个右括号
            sb.append(c);
            dfs(ans, s, i + 1, sb, leftExtra, rightExtra);
            sb.deleteCharAt(sb.length() - 1);

            // 不要这个右括号
            if (rightExtra > 0) {
                dfs(ans, s, i + 1, sb, leftExtra, rightExtra - 1);
            }
        } else {
            sb.append(c);
            dfs(ans, s, i + 1, sb, leftExtra, rightExtra);
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    private boolean isValid(StringBuilder sb) {
        // 判断括号是否合法
        int left = 0;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left == 0) {
                    return false;
                } else {
                    left--;
                }
            }
        }
        return left == 0;
    }


    static class Solution {
        static Set<Integer> set = new HashSet<>();
        static {
            for (int i = 1; i < (int)1e9+10; i *= 2) set.add(i);
        }
        int m;
        int[] cnts = new int[10];
        public boolean reorderedPowerOf2(int n) {
            while (n != 0) {
                cnts[n % 10]++;
                n /= 10;
                m++;
            }
            return dfs(0, 0);
        }
        boolean dfs(int u, int cur) {
            if (u == m) return set.contains(cur);
            for (int i = 0; i < 10; i++) {
                if (cnts[i] != 0) {
                    cnts[i]--;
                    if ((i != 0 || cur != 0) && dfs(u + 1, cur * 10 + i)) return true;
                    cnts[i]++;
                }
            }
            return false;
        }

        public static void main(String[] args) {
            System.out.println(new Solution().reorderedPowerOf2(1234));
        }
    }

    public static void main(String[] args) {
        //new Test().removeInvalidParentheses("(a)())()");
        String str = "1"; // 2134 2314 2341 1324 1342 3124  1243 4123 1423 1243
//        123 312 231 321 132 213
        StringBuilder sb = new StringBuilder(str);


        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
            sb.insert(0,c);
            if (isTrue(Integer.parseInt(sb.toString()))) {
                System.out.println(1);
            }
        }

        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.insert(Math.max(sb.length() - 1, 0),c);
            if (isTrue(Integer.parseInt(sb.toString()))) {
                System.out.println(2);
            }
        }
    }

    public static boolean isTrue(int n) {
        return n > 0 && (n & (n-1)) == 0;
    }


    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // 用最少的钱 购买到所必须要的物品
        // 并且不能超过所需物品
        if (
                price.size() > 6 || needs.size() > 6 ||
                        price.size() != needs.size()) {
            return 0;
        }

        // 过滤套餐中 不符合购买需求的套餐
        if (special != null && special.size() != 0) {
            special = special.stream().map(t -> {
                boolean flag = true;
                for (int i = 0; i < t.size() - 1; i++) {
                    if (t.get(i) > needs.get(i)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return t;
                }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toList());
        }

        // 原始套餐
        int minShoppingOffers = 0;
        for (int i = 0; i < needs.size(); i++) {
            minShoppingOffers += needs.get(i) * price.get(i);
        }

        // 遍历新套餐 挑选适合的价格
        //List<Integer> list = new ArrayList<>();

        if (special == null || special.size() == 0) {
            return minShoppingOffers;
        }

        // 1、套餐自身组合
        Map<Integer,List<Integer>> map = new HashMap<>();
        special.forEach(t -> {
            List<Integer> t1 = t;
            t1.remove(t1.size() - 1);
            map.put(Collections.min(t1),t);
        });

        // 2、套餐交叉组合

        return 1;
    }

    Map<List<Integer>, Integer> memo = new HashMap<List<Integer>, Integer>();

    // 记忆化搜索计算满足购物清单所需花费的最低价格
    public int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> curNeeds, List<List<Integer>> filterSpecial, int n) {
        if (!memo.containsKey(curNeeds)) {
            int minPrice = 0;
            for (int i = 0; i < n; ++i) {
                minPrice += curNeeds.get(i) * price.get(i); // 不购买任何大礼包，原价购买购物清单中的所有物品
            }
            for (List<Integer> curSpecial : filterSpecial) {
                int specialPrice = curSpecial.get(n);
                List<Integer> nxtNeeds = new ArrayList<Integer>();
                for (int i = 0; i < n; ++i) {
                    if (curSpecial.get(i) > curNeeds.get(i)) { // 不能购买超出购物清单指定数量的物品
                        break;
                    }
                    nxtNeeds.add(curNeeds.get(i) - curSpecial.get(i));
                }
                if (nxtNeeds.size() == n) { // 大礼包可以购买
                    minPrice = Math.min(minPrice, dfs(price, special, nxtNeeds, filterSpecial, n) + specialPrice);
                }
            }
            memo.put(curNeeds, minPrice);
        }
        return memo.get(curNeeds);
    }
}
