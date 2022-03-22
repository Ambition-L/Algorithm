package com.ambition.boot.work1;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class LanQiaoCup {
    static HashSet<String> set = new HashSet<>();
    public static void main(String[] args) {
      new LanQiaoCup().QA14();
    }

    /**
     *  贪心的自助餐
     */
    public void QA14() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        double C = scan.nextInt();
        Double[][] nums = new Double[n][3];
        double sumC = 0,value = 0;
        for (int i = 0; i < n; i++) {
            double v = scan.nextDouble();
            double c = scan.nextDouble();
            nums[i][0] = v;
            nums[i][1] = c;
            nums[i][2] = v/c;
            sumC +=c;
            value += v;
        }
        if (sumC <= C) {
            System.out.printf("%.3f%n",value);
            return;
        }
        // 按价值排序
        Arrays.sort(nums,(a,b) -> b[2].compareTo(a[2]));

        value = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((C - nums[i][1]) >= 0 ) {
                C -= nums[i][1];
                value+= nums[i][0];
            }else {
               while (C>0) {
                   value+=nums[i][2];
                   C--;
               }
               break;
            }
        }
        System.out.printf("%.3f%n",value);
        scan.close();
    }

    /**
     * <小b的宿舍></>
     *  "贪心" --只要计算出最大区间走廊使用量 / 2 即可让所有同学都搬完行李。
     */
    public void QA13 () {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            int p = scan.nextInt();
            Integer[] counts = new Integer[101];
            Arrays.fill(counts,0);
            for (int j = 0; j < p; j++) {
                int from = scan.nextInt();
                int to = scan.nextInt();
                if (from % 2 == 0) from /= 2;
                else from /=2 + 1;
                if (to % 2 == 0) to/=2;
                else to/=2+1;
                if (from > to) {
                    int temp = to;
                    to = from;
                    from = temp;
                }
                for (int k = from; k <= to; k++) {
                    counts[k]++;
                }
            }
            Arrays.sort(counts,(a,b) -> b-a);
            System.out.println(counts[0]%2 == 0? counts[0] / 2 * 10:(counts[0] / 2 + 1) * 10);
        }
        scan.close();
    }

    /**
     * <<找零问题>>
     */
    public void QA12 () {
        Scanner scan = new Scanner(System.in);
        int totalN = scan.nextInt();
        int h = 0;
        if (totalN >= 100) {
            h = totalN / 100;
            totalN %= 100;
        }
        int w = 0;
        if (totalN >= 50) {
            w = totalN / 50;
            totalN %= 50;
        }
        int e = 0;
        if (totalN >= 20) {
            e = totalN / 20;
            totalN %= 20;
        }
        int ww = 0;
        if (totalN >= 5) {
            ww = totalN / 5;
            totalN %= 5;
        }
        System.out.println("100:"+h);
        System.out.println("50:"+w);
        System.out.println("20:"+e);
        System.out.println("5:"+ww);
        System.out.println("1:"+totalN);
        scan.close();
    }

    /**
     * <<排座椅>> "贪心" "双hash" "排序"
     */
    public void QA11 () {
        Scanner scan = new Scanner(System.in);
        int rows = scan.nextInt();
        int cols = scan.nextInt();
        int countR = scan.nextInt();
        int countC = scan.nextInt();
        int d = scan.nextInt();
        Map<Integer,Integer> rmap = new HashMap<>();
        Map<Integer,Integer> cmap = new HashMap<>();
        for (int i = 0; i < d; i++) {
            int d1r = scan.nextInt();
            int d1c = scan.nextInt();
            int d2r = scan.nextInt();
            int d2c = scan.nextInt();

            if (d1r == d2r) {
                int min = Math.min(d1c, d2c);
                cmap.put(min,cmap.getOrDefault(min,0)+1);
            }else
            if (d1c == d2c) {
                int min = Math.min(d1r, d2r);
                rmap.put(min,rmap.getOrDefault(min,0)+1);
            }
        }
        List<Map.Entry<Integer, Integer>> rList = new ArrayList<>(rmap.entrySet());
        Collections.sort(rList, (o1,o2) -> o2.getValue() - o1.getValue());
        List<Integer> ansRList = new ArrayList<>();
        if (rmap.size() < countR) {
            for (Map.Entry<Integer, Integer> curr : rList) {
                ansRList.add(curr.getKey());
            }
        }else {
            for (Map.Entry<Integer, Integer> curr : rList) {
                if (countR == 0)break;
                ansRList.add(curr.getKey());
                countR--;
            }
        }
        List<Map.Entry<Integer, Integer>> cList = new ArrayList<>(cmap.entrySet());
        Collections.sort(cList, (o1,o2) -> o2.getValue() - o1.getValue());
        List<Integer> ansCList = new ArrayList<>();
        if (cmap.size() < countC) {
            for (Map.Entry<Integer, Integer> curr : cList) {
                ansCList.add(curr.getKey());
            }
        }else {
            for (Map.Entry<Integer, Integer> curr : cList) {
                if (countC == 0)break;
                ansCList.add(curr.getKey());
                countC--;
            }
        }

        Collections.sort(ansCList);
        Collections.sort(ansRList);
        for (int i = 0; i < ansRList.size(); i++) {
            if (i != ansRList.size() - 1) {
                System.out.print(ansRList.get(i) + " ");
            }else System.out.println(ansRList.get(i));
        }
        for (int i = 0; i < ansCList.size(); i++) {
            if (i != ansCList.size() - 1) {
                System.out.print(ansCList.get(i) + " ");
            }else System.out.println(ansCList.get(i));
        }
        scan.close();
    }

    /**
     *  《谈判》 "合并最小二叉树" --》优先队列
     */
    public void QA10 () {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        PriorityQueue<Long> queue = new PriorityQueue<>(Long::compareTo);
        for (int i = 0; i < n; i++) {
            queue.offer(scan.nextLong());
        }

        long sum = 0;
        while (queue.size() > 1) {
            Long num1 = queue.poll();
            Long num2 = queue.poll();
            sum += (num1 + num2);
            queue.offer(num1 + num2);
        }
        System.out.println(sum);
        scan.close();
    }

    /**
     *  《最大化股票交易利润》 单调栈
     */
    public void QA9 () {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Deque<Integer> stack = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int curr = scan.nextInt();
            int m1 = Integer.MIN_VALUE;
            while (!stack.isEmpty() && stack.getLast() > curr){
                Integer pop = stack.poll();
                m1 = Math.max(m1, curr - pop);
            }

            max = stack.isEmpty()? Math.max(max,m1):Math.max(max,curr-stack.getFirst());
            stack.offer(curr);
        }
        System.out.println(max);
        scan.close();
    }

    /**
     * <<m次方根>> 二分
     */
    public void QA8 () {
        Scanner scan = new Scanner(System.in);
        double n = scan.nextInt();
        int m = scan.nextInt();

        double left = 1,right = 100000;
        double mid = 0;
        while (left + (1e-8) < right) {
            mid = left + (right - left) / 2;
            double count = mid;
            for (int i = 0; i < m - 1; i++) {
                count *= mid;
                if (count > n) break;
            }
            if (count > n) {
                right = mid;
            }else if(count < n){
                left = mid;
            }else {
                System.out.printf("%.7f%n",mid);
                return;
            }
        }
        System.out.printf("%.7f%n",left);
        scan.close();
    }

    /**
     * 二分 《分巧克力》
     */
    public void QA7 () {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            int h = scan.nextInt();
            int w = scan.nextInt();
            nums[i][0] = h;
            nums[i][1] = w;
        }

        int left = 1,right = 100000;
        int mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            int count = 0;
            for (int[] curr : nums) {
                int h  = curr[0] / mid;
                int w  = curr[1] / mid;
                count+= h * w;
                if (count >= m) break;
            }
            if (count >= m) {
                left = mid + 1;
            }else {
                right = mid-1;
            }
        }
        System.out.println(left);
        scan.close();
    }

    /**
     * 差分数组
     */
    public void QA6() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[] nums = new int[n+1];
        for (int i = 0; i < m; i++) {
            int l = scan.nextInt();
            int r = scan.nextInt();
            int money = scan.nextInt();
            nums[l+1]+=money;
            nums[r+2] -= money;
        }
        int[] as = new int[n+1];
        for (int i = 1; i < n+1; i++) {
            as[i] = nums[i] + as[i-1];
        }
        int sum = 0;
        for (int i = 0; i < as.length; i++) {
            sum += as[i];
        }
        System.out.println(sum);
        scan.close();
    }

    /**
     * 前缀和
     */
    public void QA5 () {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[] nums = new int[n+1];
        for (int i = 1; i < n + 1; i++) {
            nums[i] = scan.nextInt() + nums[i-1];
        }
        for (int i = 0; i < m; i++) {
            int l = scan.nextInt();
            int r = scan.nextInt();
            System.out.println(nums[r] - nums[l-1]);
        }
        scan.close();
    }

    /**
     * 蓝桥杯 公平抽签
     */
    public void QA4Main(){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String[] str = new String[n];
        for (int i = 0; i < n; i++) {
            str[i] = scan.next();
        }
        List<ArrayList<String>> list = new ArrayList<>();
        new LanQiaoCup().QA4(new LinkedList<>(),str,list);
        for (ArrayList<String> strings : list) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
        scan.close();
    }
    public void QA4(LinkedList<String> ans, String[] str,
                    List<ArrayList<String>> list) {
        if (ans.size() == str.length) {
            list.add(new ArrayList<String>(ans));
            return;
        }
        for (int i = 0; i < str.length; i++) {
            if (ans.contains(str[i])) continue;
            ans.addLast(str[i]);
            QA4(ans,str,list);
            ans.removeLast();
        }
    }

    /**
     * <金币> 2015 省赛
     */
    public void QA3() {
        Scanner scan = new Scanner(System.in);
        int days = scan.nextInt();
        int ans = 0;
        int day = 1;
        while (days > 0) {
            if (days - day >= 0) {
                ans += day * day;
                days -= day;
            }else {
                int d = day - days;
                ans += day * (day - d);
                days = 0;
            }
            day++;
        }
        System.out.println(ans);
        scan.close();
    }

    /**
     * <鲁卡斯队列> 2012 省赛
     */
    public void QA2 (){
        String target = "0.618034";
        double[] dp = new double[10000];
        dp[0] = 1.0;dp[1]=3.0;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        for (int i = 1; i < dp.length; i++) {
            String format = String.format("%.6f", dp[i-1] / dp[i]);
            if (format.equals(target)) {
                System.out.println((int) dp[i-1] + "/" + (int) dp[i]);
                return;
            }
        }

    }

    /**
     * 蓝桥杯 2020国赛 《答疑》
     */
    public void QA() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] nums = new int[n][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                int num = scan.nextInt();
                nums[i][j] = num;
            }
        }
        // 排序
        Arrays.sort(nums, (a,b) -> ((a[0]+a[1]+a[2]) - (b[0]+b[1]+b[2])));
        // 计算最小时间
        long ans = 0;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                sum+= nums[i][j];
                if (j == 1) ans+=sum;
            }
        }
        System.out.println(ans);
        scan.close();
    }

    /**
     * 42点问题
     */
    public void Question(){
        Scanner scan = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            String s = scan.next();
            if (s.equals("K")) {
                list.add(13);
            }else if (s.equals("Q")) {
                list.add(12);
            }else if (s.equals("J")) {
                list.add(11);
            }else if (s.equals("A")) {
                list.add(1);
            }else {
                list.add(s.charAt(0) - '0');
            }
        }

        List<Integer> ansList = new ArrayList<>();
        ansList.add(list.get(0)+list.get(1));
        ansList.add(list.get(0)-list.get(1));
        ansList.add(list.get(0)*list.get(1));
        ansList.add(list.get(0)/list.get(1));
        List<Integer> ansList1 = new ArrayList<>();
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < ansList.size(); j++) {
                ansList1.add(ansList.get(j) + list.get(i));
                ansList1.add(ansList.get(j) - list.get(i));
                ansList1.add(ansList.get(j) * list.get(i));
                ansList1.add(ansList.get(j) / list.get(i));
            }
            ansList.clear();
            ansList.addAll(ansList1);
            ansList1.clear();
        }
        for (Integer curr : ansList) {
            if (curr == 42) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
        scan.close();
    }

    /**
     * 杨辉三角
     */
    public void getThree(){
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        int n = scan.nextInt();
        int[][] dp = new int[n][n];
        dp[0][0] = scan.nextInt();
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int curr = scan.nextInt();
                if (j == 0) {
                    dp[i][j] = dp[i-1][j] + curr;
                }else if (j == i){
                    dp[i][j] = dp[i-1][j-1] + curr;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j-1],dp[i-1][j]) + curr;
                }
            }
        }
        int max = 0;
        if (n % 2 != 0) {
            max = dp[n-1][n/2];
        }else max = Math.max(dp[n-1][n/2],dp[n-1][n/2-1]);
        System.out.println(max);
        scan.close();
    }

    public void getKuaiDi(){
        Scanner scan = new Scanner(System.in);
        Map<String,List<String>> maps = new HashMap<>();
        //在此输入您的代码...
        int n = scan.nextInt();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String curr = scan.next();
            String curr1 = scan.next();
            maps.putIfAbsent(curr1,new ArrayList<>());
            maps.get(curr1).add(curr);
            if (!list.contains(curr1))list.add(curr1);
        }

        for (String city:list) {
            List<String> ans = maps.get(city);
            System.out.println(city+ " " + ans.size());
            for (String curr:ans) System.out.println(curr);
        }

        scan.close();
    }

    public void getSort(){
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        int n = scan.nextInt();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = scan.nextInt();
        }
        Arrays.sort(ans);
        for (int curr:ans) System.out.print(curr + " ");
        System.out.println();
        for (int i = n-1; i >=0; i--) System.out.print(ans[i] + " ");

        scan.close();
    }

    public void getNameStack() {
        Scanner scan = new Scanner(System.in);
        Deque<String> deque = new ArrayDeque<>();
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            String next = scan.next();
            if (next.equals("in")) {
                deque.push(scan.next());
            }else {
                String p = scan.next();
                while (!deque.isEmpty() && !deque.pop().equals(p));
            }
        }
        System.out.println(deque.isEmpty()? "Empty":deque.poll());
    }

    public void getNameQueue(){
        Scanner scan = new Scanner(System.in);
        LinkedList<String> listV = new LinkedList<>();
        LinkedList<String> listN = new LinkedList<>();
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            String next = scan.next();
            if (next.equals("IN")) {
                String next2 = scan.next();
                String next3 = scan.next();
                if (next3.equals("V")) {
                    listV.add(next2);
                }else listN.add(next2);
            }else {
                String next2 = scan.next();
                if (next2.equals("V") && listV.size() > 0) {
                    listV.removeLast();
                }else if (next2.equals("N") && listN.size() > 0){
                    listN.removeLast();
                }
            }
        }
        for (String s : listV) {
            System.out.println(s);
        }
        for (String s : listN) {
            System.out.println(s);
        }
        scan.close();
    }


    // 循环链表
    public void getNode2() {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        // 构建循环链表
        Node parent = new Node(0);
        Node p = parent;
        for (int i = 1; i < 11; i++) {
            p.next = new Node(i);
            p = p.next;
        }
        p.next = parent.next;

        int n = scan.nextInt();
        // 标记起点

        for (int i = 0; i < n; i++) {
            int curr = scan.nextInt();
            Node p1 = parent.next;
            while (p1 != null) {
                if (p1.next != null && p1.next.val == curr) {
                    p1.next = p1.next.next;
                    break;
                }
                p1 = p1.next;
            }
            Node t = new Node(curr);
            t.next = parent.next;
            parent.next = t;

            Node q = parent.next;

            int n1 = 10;
            while (n1 > 0) {
                if (n1 != 1) {
                    System.out.print(q.val + " ");
                }else {
                    System.out.println(q.val);
                    break;
                }
                n1--;
                q = q.next;
            }
        }
        scan.close();
    }

    public void getForNode() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Node parent = new Node(0);
        Node p = parent;
        for (int i = 1; i <= n; i++) {
            p.next = new Node(i);
            p = p.next;
        }
        p.next = parent.next;

        int k = scan.nextInt();
        int m = scan.nextInt();

        int index = 1;
        Node q = parent;
        while (k > 0) {
            q = q.next;
            k--;
        }
        while (q.next != null) {
            index++;
            if (index == m) {
                index = 1;
                System.out.println(q.next.val);
                n--;
                q.next = q.next.next;
                if (n == 0) break;
            }
            q = q.next;
        }

        scan.close();
    }

    /**
     * 单链表
     */
    public void getNode(){
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        // 构建链表
        Node parent = new Node(0);
        Node p = parent;
        for (int i = 1; i < 11; i++) {
            p.next = new Node(i);
            p = p.next;
        }
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            int curr = scan.nextInt();
            Node p1 = parent.next;
            while (p1 != null) {
                if (p1.next != null && p1.next.val == curr) {
                    p1.next = p1.next.next;
                    break;
                }
                p1 = p1.next;
            }
            Node t = new Node(curr);
            t.next = parent.next;
            parent.next = t;

            Node q = parent.next;
            while (q != null) {
                if (q.next != null) {
                    System.out.print(q.val + " ");
                }else System.out.println(q.val);
                q = q.next;
            }
        }
        scan.close();
    }
    static class Node {
        int val;
        Node next;
        public Node() {}
        public Node(int val) {this.val = val;}
    }

    public int getCount(String str) {
        int left = 0,right = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') left++;
            else right++;
        }
        return left + right + Math.abs(left-right);
    }

    public void dfs(StringBuilder sb,int n,char[] chars) {
        if (sb.length() == n) {
            // 检查括号是否合法
            if (check(sb.toString())) set.add(sb.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            dfs(sb,n,chars);
            sb.deleteCharAt(sb.length()-1);
        }
    }
    // 检查字符串是否满足条件
    public boolean check(String str) {
        if ("".equals(str) ||  str.length() % 2 != 0) return false;
        // 右括号开头 左括号结尾
        if (str.charAt(0) == ')' || str.charAt(str.length()-1) == '(') return false;
        int left = 0,right = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') left++;
            else right++;
            // 右括号大于左括号
            if (right > left) return false;
        }
        return left == right;
    }



    /**
     * 2021省赛
     *      双向排序 （做不出来）
     */
    public void towSort() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] nums = new int[m][2];
        for (int i = 0; i < m; i++) {
            nums[i][0] = scanner.nextInt();
            nums[i][1] = scanner.nextInt();
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        for (int[] curr : nums) {
            int to = curr[1];
            if (curr[0] == 0){
                List<Integer> list1 =  list.subList(0,to);
                List<Integer> list2 =  list.subList(to,list.size());
                list1.sort((a,b) -> b - a);
                list = new ArrayList<>();
                list.addAll(list1);
                list.addAll(list2);
            }else {
                List<Integer> list1 =  list.subList(0,to-1);
                List<Integer> list2 =  list.subList(to-1,list.size());
                list1.sort((a,b) -> a - b);
                list = new ArrayList<>();
                list.addAll(list1);
                list.addAll(list2);
            }
        }

        System.out.println(list.toString().replace("[","").replace("]","")
                            .replace(",",""));
        scanner.close();
    }

    /**
     * 2021 省赛
     *  杨辉三角形
     *  ac 数据量 <= 20000
     */
    public void getNumIndex() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n == 1) {
            System.out.println(1);
            return;
        }
        long[][] dp = new long[40][10000];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            dp[i][i] = 1;
        }
        int index = 3;
        for (int i = 2; i < dp.length; i++) {
            index++;
            for (int j = 1; j < i; j++) {
                index++;
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                if (dp[i][j] == n) {
                    System.out.println(index);
//                    return;
                }
            }
            index++;
        }
        scanner.close();
    }
    /**
     *  2021 省赛
     *      砝码称重 dp
     */
    public void getFaMaCZ(){
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        Map<Integer,Integer> maps = new HashMap<>();
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            int curr = scan.nextInt();
            maps.put(curr,maps.getOrDefault(curr,0)+1);
        }
        // 砝码种类 A[i]
        int[] A = new int[maps.size()];
        // 砝码种类数量B[i]
        int[] B = new int[maps.size()];
        int index = 0;
        for (int key:maps.keySet()) {
            A[index] = key;
            B[index] = maps.get(key);
            index++;
        }

        int total = 0;
        // 计算可以称出的最大重量
        for (int i = 0; i < A.length; i++) {
            total+= A[i]*B[i];
        }

        // dp
        boolean[] dp = new boolean[total+1];
        dp[0] = true;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j <= B[i]; j++) {
                for (int k = total; k >= A[i]; k--) {
                    if (dp[k - A[i]])  dp[k] = true;
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i]) sum++;
        }

        System.out.println(sum);
        scan.close();
    }
    /**
     * 2021省赛
     *  时间显示
     */
    public void getDatetime(long mills) {
        // 存储毫秒
        Timestamp timestamp = new Timestamp(mills-28800000);
        // 初始化需要输出的字符串时间格式
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        // 格式化 时间戳转时间字符串
        String format = dateFormat.format(timestamp);
        System.out.println(format);
    }

    /**
     * 2021省赛 《路径》
     *   迪杰斯特拉最短路径
     */
    public void getPath() {
        int n = 2021;
        List<int[]>[] gird = new LinkedList[n+1];
        for (int i = 1; i <= n; i++) {
            gird[i] = new LinkedList<>();
        }
        // 构建图
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    if (Math.abs(i - j) <= 21) {
                        // 求最小公倍数
                        int min = Math.max(i,j);
                        while (min % i != 0 || min % j != 0) min+=Math.max(i,j);
                        gird[i].add(new int[]{j,min});
                    }
                }
            }
        }
        // 保存路径
        int[] path = new int[n+1];
        // 初始化最大值
        Arrays.fill(path,Integer.MAX_VALUE);
        // 优先队列 优选选最路径最小的
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)-> a[1] - b[1]);
        queue.offer(new int[]{1,0});
        // 初始化远点路径
        path[1] = 0;
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            // 如果遍历到最后一个节点
            if (curr[0] == 2021) break;
            // path路径比当前路径小 不再参与松弛
            if (path[curr[0]] < curr[1]) continue;
            // 取出相邻节点
            for (int[] next : gird[curr[0]]) {
                int n1 = next[0];
                // 当前节点到下一个节点的距离
                int n2 = next[1] + curr[1];
                // 如果最短路径大于该路径
                if (path[n1] > n2) {
                    // 更新路径
                    path[n1] = n2;
                    // 入队
                    queue.offer(new int[]{n1,n2});
                }
            }
        }
        System.out.println(path[2021]);
    }
}
