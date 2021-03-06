package com.ambition.boot.work1;

import java.util.*;

public class LC {
    public static void main(String[] args) {
    }
    public String mostCommonWord(String paragraph, String[] banned) {

        List<String> bList = new ArrayList<>();
        for (String curr:banned)bList.add(curr.toLowerCase());
        String[] strs = paragraph.toLowerCase()
                .replaceAll(","," ")
                .replaceAll("\\."," ")
                .replaceAll("!"," ")
                .replaceAll("\\?"," ")
                .replaceAll(";"," ")
                .replaceAll("'"," ")
                .split(" ");
        Map<String,Integer> maps = new HashMap<>();
        int c = 0;
        String ans = "";
        for (String curr : strs) {
            if (!curr.equals("") && !bList.contains(curr)) {
                maps.put(curr,maps.getOrDefault(curr,0)+1);
                if (maps.get(curr) > c) {
                    c = maps.get(curr);
                    ans = curr;
                }
            }
        }
        return ans.toLowerCase();
    }

    public int largestPalindrome(int n) {
        if (n == 1) return 9;
        long num = (long) Math.pow(10,n)-1;
        long cur = 1;
        a:for (long i = num; i > 0; i--) {
            cur = i;
            for (long x = cur; x > 0; x /= 10) {
                cur = cur * 10 + x % 10; // 翻转左半部分到其自身末尾，构造回文数 p
            }
            for (long j = num; j * j >= cur; j--) {
                if (cur % j == 0)  {
                    cur %= 1337;
                    break a;
                }
            }
        }
        return (int)cur;
    }

    public boolean rotateString(String s, String goal) {
        if (s.equals(goal)) return true;
        if (s.length() != goal.length()) return false;
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(0);
            sb.deleteCharAt(0);
            sb.append(c);
            if (goal.equals(sb.toString())) return true;
        }
        return false;
    }


    List<List<String>> ans = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        LinkedList<StringBuilder> list = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(".");
        for (int i = 0; i < n; i++) list.add(new StringBuilder(sb));
        comeBacking(list,0);
        return ans;
    }

    public void comeBacking (LinkedList<StringBuilder> list,int row) {
        if (row >= list.size()) {
            List<String> ll = new ArrayList<>();
            for (StringBuilder s : list) ll.add(s.toString());
            ans.add(ll);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (check(list,row,i)) {

                list.set(row,list.get(row).replace(i,i+1,"Q"));
                comeBacking(list,row+1);
                list.set(row,list.get(row).replace(i,i+1,"."));
            }
        }
    }

    public boolean check(LinkedList<StringBuilder> list,int row,int col) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(row).charAt(i) == 'Q') return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).charAt(col) == 'Q') return false;
        }
        for (int i = 0,j = 0; i < list.size() && j < list.size(); j++,i++) {
            if (list.get(i).charAt(j) == 'Q') return false;
        }
        for (int i = list.size()-1,j = 0;
             i >= 0 && j < list.size(); j++,i--) {
            if (list.get(i).charAt(j) == 'Q') return false;
        }
        return true;
    }

    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        a:for (int i = left; i <= right; i++) {
            int num = Integer.bitCount(i);
            if (num < 2 ) continue;
            for (int j = 2; j * j <= num; j++) {
                if (num % j == 0) continue a;
            }
            ans++;
        }
        return ans;
    }

    public boolean canReorderDoubled(int[] arr) {
        Map<Integer,Integer> maps = new HashMap<>();
        int count = 0;
        for (int curr:arr) {
            if (curr == 0) {
                count++;
                continue;
            }
            maps.put(curr,maps.getOrDefault(curr,0)+1);
        }
        List<Integer> list = new ArrayList<>(maps.keySet());
        if (list.size() % 2 != 0 || count % 2 != 0) return false;
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i);
            Integer c1 = maps.get(num);
            if (num < 0) {
                Integer c2 = maps.get(num / 2);
                if (num % 2 != 0 || c2 == null || c1 != c2) return false;
                maps.remove(num / 2);
            }else {
                Integer c2 = maps.get(num * 2);
                if (c2 == null || c1 != c2) return false;
                maps.remove(num * 2);
            }
            maps.remove(num);
        }
        return true;
    }

    public int trailingZeroes(int n) {
        long c = n;
        for (long i = n-1; i > 0; i--) {
            c *=i;
        }
        String s = ""+c;
        long count = 0,a = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            if (a == 1) break;
            if (s.charAt(i) == '0' && a == 0) {
                count++;
            }else a++;
        }
        return (int)count;
    }

    public String intToRoman(int num) {
        String[] dirc = new String[1001];
        dirc[1] = "I";dirc[4] = "IV";dirc[5] = "V";dirc[9] = "IX";
        dirc[10] = "X";dirc[40] = "XL";dirc[50] = "L";dirc[90] = "XC";
        dirc[100] = "C";dirc[400] = "CD";dirc[500] = "D";dirc[900] = "CM";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num / 1000; i++) {
            sb.append("M");
        }
        num %= 1000;
        while (num != 0) {
            for (int i = num; i >= 1; i--) {
                if (dirc[i] == null) continue;
                if (num % i == 0) {
                    for (int j = 0; j < num/i; j++) sb.append(dirc[i]);
                    num = 0;
                    break;
                }else {
                    int l = num/i;
                    for (int j = 0; j < l; j++) sb.append(dirc[i]);
                    num %= i;
                    break;
                }
            }
        }
        return sb.toString();
    }

    /**
     * <网络最早空闲时间></>
     * @param edges
     * @param patience
     * @return
     */
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        // 构造图
        List<int[]>[] grid = new LinkedList[patience.length];
        for (int i = 0; i < grid.length; i++) grid[i] = new LinkedList<>();
        for (int[] curr : edges) {
            int from = curr[0];
            int to = curr[1];
            grid[from].add(new int[]{to,1});
            grid[to].add(new int[]{from,1});
        }

        // 初始化路径列表
        int[] paths = new int[patience.length];
        Arrays.fill(paths,Integer.MAX_VALUE);
        paths[0] = 0;

        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0,0});
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int from = point[0];
            int len = point[1];
            // 存储的已经是最短路径
            if (len > paths[from] ) continue;
            for (int[] curr : grid[from]) {
                int nlen = curr[1] + len;
                if (nlen < paths[curr[0]]) {
                    paths[curr[0]] = nlen;
                    queue.offer(new int[]{curr[0],nlen});
                }
            }
        }

        // 求最大网络传输花费时间
        int max = 0;
        for (int i = 1; i < paths.length; i++) {
            int path = paths[i] * 2;
            int c = patience[i];
            int time = path;
            if (c < path) {
                time += path % c==0? path-c:path - path%c;
            }
            max = Math.max(time,max);
        }

        return max + 1;
    }

    int n;
    char[] chars;
    Set<String> set = new HashSet<>();
    public String[] permutation(String S) {
        this.n = S.length();
        this.chars = S.toCharArray();
        dfs(new StringBuilder());
        return set.toArray(new String[set.size()]);
    }
    // 力扣 字符串无重复排列
    public void dfs(StringBuilder sb) {
        if (sb.length() == n) {
            set.add(sb.toString());
            return;
        }
        for (int i = 0; i < n; i++) {
            if (sb.toString().contains(chars[i] + "")) continue;
            sb.append(chars[i] + "");
            dfs(sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    class Trei {
        Trei[] children;
        boolean isEnd;
        public Trei() {
            children = new Trei[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trei node = this;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null) node.children[index] = new Trei();
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trei node = this;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null) return false;
                node = node.children[index];
            }

            return node != null && node.isEnd;
        }
    }

    // 字符串替换
    public String replaceWords(List<String> dictionary, String sentence) {
        Trei trei = new Trei();
        for (String curr:dictionary) {
            trei.insert(curr);
        }
        StringBuilder sb = new StringBuilder();

        String[] strArr = sentence.split(" ");
        for (String str: strArr) {
            for (int i = 1; i < str.length(); i++) {
                String curr = str.substring(0,i);
                if (trei.search(curr)) {
                    str = curr;
                    break;
                }
            }
            sb.append(str + " ");
        }
        return sb.toString().trim();
    }
    /**
     * 力扣
     */
    static class AllOne {
        public static void main(String[] args) {
            AllOne allOne = new AllOne();
            allOne.inc("a");
            allOne.inc("b");
            allOne.inc("c");
            allOne.inc("d");
            System.out.println(allOne.getMinKey());
            System.out.println();
        }

        Map<String,Integer> maps;
        Map<Integer,Set<String>> maxMap;
        Map<Integer,Set<String>> minMap;
        public AllOne() {
            maps = new HashMap<>();
            maxMap = new TreeMap<>((a,b) -> b - a);
            minMap = new TreeMap<>();
        }

        public void inc(String key) {
            maps.put(key,maps.getOrDefault(key,0)+1);
            Integer setKey = maps.get(key);
            Integer setPreKey = maps.get(key) - 1;
            Set<String> max = maxMap.get(setKey);
            if (max == null) {
                maxMap.putIfAbsent(setKey,new HashSet<>());
                maxMap.get(setKey).add(key);
                minMap.putIfAbsent(setKey,new HashSet<>());
                minMap.get(setKey).add(key);
            }else {
                maxMap.get(setKey).add(key);
                minMap.get(setKey).add(key);
            }
            if (maxMap.get(setPreKey) != null ) {
                maxMap.get(setPreKey).remove(key);
                // 如果当前key没有值
                if (maxMap.get(setPreKey).size() == 0) maxMap.remove(setPreKey);
                minMap.get(setPreKey).remove(key);
                if (minMap.get(setPreKey).size() == 0) minMap.remove(setPreKey);
            }
        }

        public void dec(String key) {
            if (maps.get(key) == 0) maps.remove(key);
            else {
                Integer setPreKey = maps.get(key);
                maps.put(key, setPreKey - 1);
                Integer setKey = maps.get(key);
                // 删除之前的key
                maxMap.get(setPreKey).remove(key);
                minMap.get(setPreKey).remove(key);
                // 如果当前key没有值
                if (maxMap.get(setPreKey).size() == 0) {
                    maxMap.remove(setPreKey);
                    minMap.remove(setPreKey);
                }
                // 如果减一之后的长度不为0 则添加
                if (setKey != 0) {
                    Set<String> maxSet = maxMap.get(setKey);
                    if (maxSet == null) {
                        maxMap.putIfAbsent(setKey,new HashSet<>());
                        maxMap.get(setKey).add(key);
                        minMap.putIfAbsent(setKey,new HashSet<>());
                        minMap.get(setKey).add(key);
                    }else {
                        minMap.get(setKey).add(key);
                        maxMap.get(setKey).add(key);
                    }
                }
            }
        }

        public String getMaxKey() {
            for (Integer curr: maxMap.keySet()) for (String s: maxMap.get(curr)) return s;
            return "";
        }

        public String getMinKey() {
            for (Integer curr: minMap.keySet()) for (String s: minMap.get(curr)) return s;
            return "";
        }
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String,Integer> maps = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            maps.put(list1[i],i);
        }

        int sum = 2001;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            Integer index = maps.get(list2[i]);
            if (index != null) {
                if (i + index < sum) {
                    sum = i + index;
                    list.clear();
                    list.add(list2[i]);
                }else if (i + index == sum) {
                    list.add(list2[i]);
                }
            }
        }

        return new String[list.size()];
    }

    // 各位相加
    public int addDigits(int num) {
        StringBuilder sb = new StringBuilder(num+"");
        while (sb.length() > 1) {
            long ans = 0;
            for (int i = 0; i < sb.length(); i++) {
                char s = sb.charAt(i);
                ans += (s-48);
            }
            sb.replace(0,sb.length(),ans+"");
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     * z字形
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        int n = s.length();
        char[] chars = s.toCharArray();
        char[][] nums = new char[numRows][n/numRows + n/numRows];
        for (int k = 0; k < numRows; k++) {
            Arrays.fill(nums[k],' ');
        }
        int i = 0,j = 0,index = 0;
        while (true) {
            while (i < numRows && index < chars.length) {
                nums[i][j] = chars[index];
                i++;
                index++;
            }
            if (i == numRows) i-=2;
            j++;
            while (i >=0 && j < nums[i].length &&  index < chars.length) {
                nums[i][j] = chars[index];
                j++;
                i--;
                index++;
            }
            j--;
            i+=2;
            if (index == chars.length) break;
        }
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < numRows; k++) {
            for (int l = 0; l < nums[k].length; l++) {
                if (nums[k][l] != ' ') sb.append(nums[k][l]);
            }
        }
        return sb.toString();
    }

    // 欧拉回
//    int counts = 0;
//    boolean[] flag;
//    public int maximumRequests(int n, int[][] requests) {
//        int ans = 0;
//        // 2.对这些点进行图构造
//        List<int[]>[] grid = new LinkedList[n];
//        for (int i = 0; i < n; i++) grid[i] = new LinkedList<>();
//        for (int i = 0; i < requests.length; i++) {
//            int[] curr = requests[i];
//            int from = curr[0];
//            int to = curr[1];
//            if (from != to) {
//                grid[from].add(new int[]{to,i});
//            }else ans++; // 自交换
//        }
//
//
//        // 3.从每个顶点出发进行回溯寻找最大交换次数
//        for (int i = 0; i < n; i++) {
//            int[] curr = requests[i];
//            if (grid[curr[0]].size() == 0 || curr[0] == curr[1]) continue;
//            for (int[] ss: grid[curr[0]]) {
//                flag = new boolean[requests.length];
//                getRequestCount1(grid,0,ss,curr[0]);
//            }
//        }
//
//        return counts+=ans;
//    }
//    public void getRequestCount1(List<int[]>[] grid,int count,int[] node,int node1) {
//        if (node[0] == node1)  {
//            counts = Math.max(counts,count);
//            return;
//        }
//        List<int[]> list = grid[node1];
//
//        for (int i = 0; i < list.size(); i++) {
//
//            int[] ints = list.get(i);
//
//            if (flag[ints[1]]) continue;
//            flag[node[1]] = true;
//            getRequestCount1(grid,++count,node,ints[0]);
//            flag[node[1]] = false;
//            --count;
//        }
//
//    }


//    int counts = 0;
//    boolean flag1 = false;
//    boolean[] flag;
//    boolean[] flag2;
//    public int maximumRequests(int n, int[][] requests) {
//        int ans = 0;
//        // 1.统计每个点的出度和入度
//        Map<Integer,int[]> maps = new HashMap<>();
//        for (int[] curr:requests) {
//            if (curr[0] == curr[1]) continue;
//            if (maps.get(curr[0]) == null) maps.put(curr[0],new int[2]);
//            if (maps.get(curr[1]) == null) maps.put(curr[1],new int[2]);
//            maps.get(curr[0])[1]++;
//            maps.get(curr[1])[0]++;
//        }
//
//        Set<Integer> ll = new HashSet<>();
//        // 2.对这些点进行图构造
//        List<Integer>[] grid = new LinkedList[n];
//        for (int i = 0; i < n; i++) grid[i] = new LinkedList<>();
//        for (int i = 0; i < requests.length; i++) {
//            int[] curr = requests[i];
//            int to = curr[1];
//            if (curr[0] != to)
//                grid[curr[0]].add(to);
//            else {
//                ans++;
//                ll.add(to);
//            }
//        }
//
//        boolean flag1 = false;
//        Set<Integer> keys = maps.keySet();
//        for (int curr:keys){
//            int[] cr = maps.get(curr);
//            if (cr[1] == 0 || cr[0] == 0) continue;
//            // 3.获取所有有关系的点
//            flag = new boolean[n];
//            List<Integer> list =  bfs(grid,curr);
//            int count = 0;
//            for (int cc:list) {
//                if (!getRequestCount(grid,-1,cc)) {
//                    continue;
//                }
//                int[] ccr = maps.get(cc);
//                count+=Math.min(ccr[0],ccr[1]);
//            }
//            counts = Math.max(count,counts);
//        }
//        return counts+=ans ;
//    }
//    public boolean getRequestCount(List<Integer>[] grid,int node1,int node2) {
//        List<Integer> list = grid[node1== -1? node2:node1];
//        for (int i = 0; i < list.size(); i++) {
//            if (grid[list.get(i)].contains(node2)) {
//                return true;
//            }else return getRequestCount(grid,list.get(i),node2);
//        }
//        return false;
//    }
//
//    public List<Integer> bfs(List<Integer>[] grid,int node) {
//        List<Integer> list = new ArrayList<>();
//        Queue<Integer> queue = new LinkedList<>();
//        queue.offer(node);
//        while (!queue.isEmpty()) {
//            int curr = queue.poll();
//            if (flag[curr]) continue;
//            list.add(curr);
//            for (int n:grid[curr]) {
//                queue.offer(n);
//            }
//            flag[curr] = true;
//        }
//        return list;
//    }


}
