package com.ambition.boot.work1;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class LanQiaoCup {
    static HashSet<String> set = new HashSet<>();
    int[] months = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
    public static void main(String[] args) {
        LanQiaoCup lanQiaoCup = new LanQiaoCup();
        lanQiaoCup.QA20_5();
    }

    /**
     * 斐波那契最大公约数
     */
    public void QA20_5() {
        BigInteger[] dp = new BigInteger[2020];
        dp[0] = new BigInteger("1");
        dp[1] = new BigInteger("1");
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i-1].add(dp[i-2]);
        }
        System.out.println(dp[519]);
        System.out.println(dp[2019]);

        System.out.println(gcb(dp[519],dp[2019]));
    }


    public BigInteger gcb(BigInteger a,BigInteger b) {
        return b.toString().equals("0")? a:gcb(b,a.mod(b));
    }

    public void QA21_m6() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String str = n +"";
        if (str.length() %  2 == 0) {
            int c = (10 - str.length()) / 2;
            String temp = "";
            for (int i = 0; i < c; i++) {
                temp += "=";
            }
            System.out.println(temp + str + temp);
        }else {
            int c = (10 - str.length()) / 2;
            String temp = "";
            for (int i = 0; i < c; i++) {
                temp += "=";
            }
            System.out.println("=" + temp + str + temp);
        }
    }

    /**\
     * 回路计数
     *      --21
     */
    public void QA21_7() {
        List<Integer>[] graph = new LinkedList[22];
        for (int i = 0; i < graph.length; i++) graph[i] = new LinkedList<>();
        for (int i = 1; i <= 21; i++) {
            for (int j = 1; j <=21 ; j++) {
                if (gcb(i,j) == 1) {
                    if (!graph[i].contains(j)) graph[i].add(j);
                    if (!graph[j].contains(i)) graph[j].add(i);
                }
            }
        }
        LinkedList<Integer> objects = new LinkedList<>();
        objects.add(1);
        QA21_7CB(graph,objects,1);
    }
    long aaa = 0;
    public void QA21_7CB(List<Integer>[] graph,LinkedList<Integer> ans,int node) {
        if (ans.size() == 21) {
            if (node == 1) aaa++;
            return;
        }
        List<Integer> list = graph[node];
        for (int i = 0; i < list.size(); i++) {
            if (ans.contains(list.get(i))) continue;
            ans.add(list.get(i));
            QA21_7CB(graph,ans,list.get(i));
            ans.removeLast();
        }
    }

    public static int gcd(int i,int num){
        int t = 0;
        while(num!=0){
            t = num;
            num = i%num;
            i = t;
        }
        return i;
    }

    /**
     * 扫雷
     *  -- 21模拟
     */
    public void QA21_m5() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[][] nums = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nums[i][j] = scan.nextInt();
            }
        }

        int[] dx = new int[]{0,0,1,1,-1,-1,-1,1};
        int[] dy = new int[]{1,-1,0,1,0,1,-1,-1};
        int[][] an = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int ans = nums[i][j];
                if (ans == 1) {
                    an[i][j] = 9;
                    continue;
                }
                for (int k = 0; k < 8; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (x >=0 && x < n && y >=0 && y < m) ans += nums[x][y];
                }
                an[i][j] = ans;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(an[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 灌溉
     *      21 模拟
     *      多源bfs
     */
    long qa21M4Ans = 0;
    public void QA21_m4() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int t = scan.nextInt();
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < t; i++) {
            queue.offer(new int[]{scan.nextInt(), scan.nextInt()});
        }
        int k = scan.nextInt();
        bfs21_m4(queue,n,m,k);
        System.out.println(qa21M4Ans);
        scan.close();
    }

    public void bfs21_m4(Queue<int[]> queue,int n,int m,int k) {
        int[] dirs = new int[]{0,1,0,-1,0};
        Set<String> set = new HashSet<>();
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                if (!set.add(curr[0] + "," + curr[1])) continue;
                for (int j = 0; j < 4; j++) {
                    int x = curr[0]+dirs[j];
                    int y = curr[1]+dirs[j+1];
                    if (x <= 0 || x > n || y <= 0 || y > m) continue;
                    list.add(new int[]{x,y});
                }
                qa21M4Ans++;
            }
            queue.addAll(list);
            list.clear();
        }
    }

    /**
     * 删除字符
     *  21模拟
     */
    public void QA21_m3() {
        Scanner scan = new Scanner(System.in);
        StringBuilder sb = new StringBuilder(scan.next());
        int t = scan.nextInt();

        for (int i = 1; i <= t; i++) {
            if (sb.charAt(0) > sb.charAt(1)) {
                sb.deleteCharAt(0);
            }else sb.deleteCharAt(1);
        }
        System.out.println(sb);
        scan.close();
    }


    long ans21 = 0;
    public void QA21_m2 () {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] grid = new int[n][n];
        dfsBack21_m2(grid,0);
        System.out.println(ans21);
    }

    public void dfsBack21_m2(int[][] gird,int x) {
        if (x >= gird.length) {
            ans21++;
            return;
        }
        for (int i = 0; i < gird.length; i++) {
            if (check21_m2(gird,x,i)) {
                gird[x][i] = 1;
                dfsBack21_m2(gird,x+1);
                gird[x][i] = 0;
            }
        }
    }

    public boolean check21_m2(int[][] grid, int x, int y) {
        // 检查行列
        for (int i = 0; i < grid.length; i++)
            if (grid[i][y] == 1) return false;
        for (int i = 0; i < grid.length; i++)
            if (grid[x][i] == 1) return false;
        // 检查斜角
        for (int i = x - 3,j = y - 3; i <= x+3 && j <= y+3 ; j++,i++) {
            if (i < 0 || j < 0 || i >= grid.length || j>=grid.length) continue;
            if (grid[i][j] == 1) return false;
        }

        for (int i = x + 3,j = y - 3; i >= x-3 &&  j <= y+3; j++,i--) {
            if (i < 0 || j < 0 || i >= grid.length || j>=grid.length) continue;
            if (grid[i][j] == 1) return false;
        }
        return true;
    }

    /**
     *  跳跃
     *      21模拟
     */
    int maxLength = Integer.MIN_VALUE;
    public void QA21_m1 () {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = scan.nextInt();
            }
        }
        int[] dx = new int[]{0,0,0,1,2,3,1,1,2};
        int[] dy = new int[]{1,2,3,0,0,0,1,2,1};
        dfs21_m1(grid,dx,dy,0,0,grid[0][0]);
        System.out.println(maxLength);
        scan.close();
    }

    public void dfs21_m1(int[][] grid, int[] dx, int[] dy,int x, int y,int totalMax) {
        if (x == grid.length - 1 && y == grid[x].length - 1) {
            maxLength = Integer.max(totalMax,maxLength);
        }

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[x].length) continue;
            dfs21_m1(grid,dx,dy,nx,ny, totalMax + grid[nx][ny]);
        }
    }


    /**
     * 小明的游戏1
     */
    public void QA_8() {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scan.nextInt();
            for (int j = 0; j < n; j++) {
                int curr = scan.nextInt();
            }
            if (n % 2 == 0) {
                System.out.println("YES");
            }else System.out.println("NO");
        }
        scan.close();
    }

    /**
     * 蓝桥骑士
     */
    public void QA_7() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scan.nextInt();
        }
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for (int i = 1; i < n; i++) {
            if (nums[i] > list.get(list.size() - 1)) list.add(nums[i]);
            else {
                if (list.contains(nums[i])) continue;
                int left = 0,right = list.size();
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (list.get(mid) > nums[i]) {
                        right = mid;
                    }else left = mid + 1;
                }
                list.set(left,nums[i]);
            }
        }
        System.out.println(list.size());
        System.out.println(list);
    }

    /**
     * 百亿富翁
     */
    public void QA_6() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] nums = new int[n+1];
        for (int i = 1; i <= n; i++) {
            nums[i] = scan.nextInt();
        }
        Stack<Integer> stack = new Stack<>();
        int[] leftNums = new int[n+1];
        int[] rightNums = new int[n+1];
        for (int i = 1; i <= n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) stack.pop();
            leftNums[i] = stack.isEmpty()? -1:stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = n; i > 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) stack.pop();
            rightNums[i] = stack.isEmpty()? -1:stack.peek();
            stack.push(i);
        }
        System.out.println(Arrays.toString(leftNums).replace("0","").replace(",","")
            .replace("[","").replace("]","").trim());
        System.out.println(Arrays.toString(rightNums).replace("0","").replace(",","")
                .replace("[","").replace("]","").trim());
//        for (int i = 1; i <= n; i++) {
//            System.out.print(leftNums[i]  + " ");
//        }
//        System.out.println();
//        for (int i = 1; i <= n; i++) {
//            System.out.print(rightNums[i] + " ");
//        }
        scan.close();
    }

    /**
     * 走迷宫
     */
    int x2,y2;
    int len = Integer.MAX_VALUE;
    boolean qa5Flag = false;
    public void QA_5() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = scan.nextInt();
            }
        }

        int x1 = scan.nextInt();
        int y1 = scan.nextInt();
        x2 = scan.nextInt() - 1;
        y2 = scan.nextInt() - 1;

        boolean[][] flag = new boolean[n][m];
        dfsQA_5(grid,x1 - 1,y1 - 1,0,flag);

        System.out.println(!qa5Flag? -1:len);
        scan.close();
    }

    public void dfsQA_5(int[][] grid,int x,int y,int path,boolean[][] flag) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length
                    || flag[x][y] || grid[x][y] == 0 || (x == x2 && y ==y2)) {
            if (x == x2 && y == y2) {
                qa5Flag = true;
                len = Math.min(len,path);
            }
            return;
        }
        flag[x][y] = true;
        path++;
        dfsQA_5(grid,x - 1,y,path,flag);
        dfsQA_5(grid,x + 1,y,path,flag);
        dfsQA_5(grid,x,y - 1,path,flag);
        dfsQA_5(grid,x,y + 1,path,flag);
        path--;
        flag[x][y] = false;
    }

    /**
     * 解立方根 (二分 不要怀疑解题的方式)
     */
    public void QA_4 () {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        double[] ans1 = new double[n];
        for (int i = 0; i < n; i++) {
            double curr = scan.nextDouble();
            if (curr == 0 || curr == 1) {
                ans1[i] = curr;
                continue;
            }
            double right = Math.sqrt(curr);
            double left = Math.sqrt(right);
            double ans = -1;
            while (left < right) {
                double mid = left + ( right - left ) / 2;
                if ((mid * mid * mid) == curr) {
                    ans = mid;
                    break;
                }else if ((mid * mid * mid) > curr) {
                    right = mid - 0.0001;
                }else {
                    left = mid + 0.0001;
                    ans = left;
                }
            }
            ans1[i] = ans;
        }
        for (int i = 0; i < n; i++) System.out.printf("%.3f%n",ans1[i]);
        scan.close();
    }


    /**
     * 蓝桥侦探
     */
    public void QA_3() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[] nums = new int[n*2+1];
        for (int i = 1; i <= n * 2; i++) {
            nums[i] = scan.nextInt();
        }

        int res = 0 , pos = 0;
        for (int i = 1; i < nums.length; i++) {
            int x,y;
        }
    }

    class UF {
        int[] nums;
        public UF(int n) {
            nums = new int[n];
        }

        public int find(int x) {
            while (nums[x] != x) {
                x = nums[x];
            }
            return x;
        }
        public void union(int x,int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx != ry) nums[x] = y;
        }
    }

    /**
     * 绝世武功
     *
     */
    public void QA_2() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int c = scan.nextInt();
        long ans = 0;
        for (int i = 0; i < c; i++) {
            long l = scan.nextInt();
            long r = scan.nextInt();
            long s = scan.nextInt();
            long e = scan.nextInt();
            ans += (s + e) * (r - l + 1) / 2;
        }
        System.out.println(ans);
        scan.close();
    }

    /**
     * 小明的彩灯
     */
    public void QA_1() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int q = scan.nextInt();
        // 原数组
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scan.nextInt();
        }
        // 记录操作数组
        long[] ans = new long[n];
        for (int i = 0; i < q; i++) {
            int l = scan.nextInt();
            int r = scan.nextInt();
            long x = scan.nextInt();
            ans[l-1] += x;
            if (r != nums.length)ans[r] -= x;
        }
        // 操作数组前缀和
        System.out.print(Math.max(ans[0] + nums[0],0) + " ");
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i] + ans[i-1];
            System.out.print(Math.max(ans[i] + nums[i],0) + " ");
        }
        scan.close();
    }

    /**
     * 跳跃的小明
     */
    public void QATG_2() {
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        int t = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        if (t < 2 ) {
            System.out.println(0);
            return;
        }

    }

    /**
     * 打包
     */
    public void QATG_1() {
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] nums = new int[1001];
        long total = 0;
        for (int i = 0; i < n ; i++) {
            int curr = sc.nextInt();
            nums[curr]++;
            total += curr;
        }

//        int left =

        sc.close();
    }

    /**
     * 积木大赛
     */
    public void QA13_4() {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int ans=0,last=0;
        for(int i=0;i<n;++i) {
            int a=sc.nextInt();
            if(a>last) ans+=(a-last);
            last=a;
        }
        System.out.println(ans);
    }

    /**
     * 含 2 天数
     */
    public void QA18_G2() {
        int ans = 0;
        for (int i = 1900; i <= 9999; i++) {
            String y = i + "";
            if (y.contains("2")) {
                if (isR(i)) ans += 366;
                else ans+= 365;
                continue;
            }
            for (int j = 1; j <= 12 ; j++) {
                if (j == 2) {
                    if (isR(i)) ans += 29;
                    else ans+=28;
                }else if (j == 12) ans += 31;
                else ans += 12;
            }
        }
        System.out.println(ans);
    }
    public boolean isR(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) return true;
        return false;
    }

    /**
     * 最大乘积
     */
    public void QA18_G1() {
        Set<String> set = new HashSet<>();
        dfs_18_G1(set,new StringBuilder());
        long max = 0;
        for (String curr : set) {
            for (int i = 0; i < curr.length(); i++) {
                a:for (int j = i+1; j < curr.length(); j++) {
                    long a = Long.parseLong(curr.substring(i,j));
                    long b = Long.parseLong(curr.substring(j));
                    String str = a * b + "";
                    if (str.contains("0") || str.length() > 9) continue ;
                    for (int k = 1; k <= 9; k++) {
                        if (!str.contains(k+"")) continue a;
                    }
                    max = Math.max(max,a * b);
                }
            }
        }
        System.out.println(max);
    }
    public void dfs_18_G1(Set<String> set,StringBuilder sb) {
        if (sb.length() == 9) {
            set.add(sb.toString());
            return;
        }
        for (int i = 1; i <= 9 ; i++) {
            if (sb.toString().contains(i+"")) continue;
            sb.append(i);
            dfs_18_G1(set,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    /**
     * 数的幂次
     */
    public void QA_n1(){
        Scanner scan = new Scanner(System.in);
        int c = scan.nextInt();
        while (c --> 0) {

        }
        for (int i = 0; i < c; i++) {
            long n = scan.nextInt();
            int m = scan.nextInt();
            int p = scan.nextInt();
            long ans = 1;
            while(m>0) {
                //这样判断k的二进制最后一位是否是1
                if((m&1)==1) ans=ans*n%p;
                m>>=1;
                n=n*n%p;
            }
            System.out.println(ans%p);
        }
        scan.close();
    }

    /**
     * 全球变暖
     *
     */
    boolean f = false;
    public void QA18_4() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String[][] nums = new String[n][n];
        for (int i = 0; i < n; i++) {
            String next = scan.next();
            for (int j = 0; j < n; j++) {
                String s = next.charAt(j) + "";
                nums[i][j] = s;
            }
        }
        int ans = 0;
        boolean[][] flag = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (flag[i][j] || nums[i][j].equals(".")) continue;
                dfs_18(nums,flag,i,j);
                if (!f)ans++;
                f = false;
            }
        }
        System.out.println(ans);
        scan.close();
    }

    public void dfs_18(String[][] nums,boolean[][] flag,int x,int y) {
        if (x-1 >= 0 && x+1 < nums.length && y - 1>=0 && y+1 < nums.length
                && nums[x-1][y].equals("#") && nums[x+1][y].equals("#")
            && nums[x][y-1].equals("#") && nums[x][y+1].equals("#")) {
            f = true;
            return;
        }
        if (flag[x][y] || nums[x][y].equals(".")) {
            return;
        }
        flag[x][y] = true;
        dfs_18(nums,flag,x-1,y);
        dfs_18(nums,flag,x+1,y);
        dfs_18(nums,flag,x,y-1);
        dfs_18(nums,flag,x,y+1);
    }


    /**
     * 买不到的数目
     *  对于互质的两个数 他们不能组成的最大数为
     *      a * b - a - b
     */
    public void QA_A() {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        System.out.println(a * b - a -b);
    }

    /**
     * 等差数列
     */
    public void QA19_2() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = scan.nextInt();
        Arrays.sort(nums);
        int c = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            c = Math.min(nums[i] - nums[i-1],c);
        }
        if (c == 0) {
            System.out.println(n);
            return;
        }

        int ans = 0;
        for (int i = nums[0]; i <= nums[n-1]; i+=c) {
            ans++;
        }
        System.out.println(ans);
    }

    /**
     * 四平方和
     */
    public void de(){

    }

    /**
     * 金额差错
     * 6
     * 5
     * 3
     * 2
     * 4
     * 3
     * 1
     */
    int count = 0;
    public void QA11_1() {
        Scanner scanner = new Scanner(System.in);
        int money = scanner.nextInt();
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = scanner.nextInt();
        Arrays.sort(nums);
        dfs1(nums,money,0,0);
        System.out.println(count);
    }
    public void dfs1(int[] nums,int money,int total,int index) {
        if (total >= money) {
            if (money == total)
                count++;
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if(i>index&&nums[i]==nums[i-1]) continue;
            total += nums[i];
            dfs1(nums,money,total,i+1);
            total -= nums[i];
        }
    }

    /**
     * 时间加法
     */
    public void QA21_6() {
        Scanner scanner = new Scanner(System.in);
        long date = scanner.nextInt();
        long time = scanner.nextInt();
        long min = scanner.nextInt();
        long addD =  min/60;
        long addt =  min % 60;
        if (time + addt >= 60) {
            time = (time + addt) % 60;
            date++;
            date = (date + addD) % 24;
        }else {
            time = (time + addt) % 60;
            date = (date + addD) % 24;
        }
        System.out.println(date);
        System.out.println(time);
    }

    /**
     * 分巧克力
     */
    public void QA17_2() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int[][] nums = new int[n][2];

        for (int i = 0; i < n; i++) {
            nums[i][0]= scan.nextInt();
            nums[i][1]= scan.nextInt();
        }

        int left = 1,right = 100000;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            int count = 0;
            for (int i = 0; i < n; i++) {
                count+=(nums[i][0]/mid) * (nums[i][1]/mid);
            }
            if (count <= k) {
                right = mid - 1;
            }else left = mid;
        }
        System.out.println(left);
    }

    /**
     * 时间显示
     */
    public void QA21_5() {
        Scanner scan = new Scanner(System.in);
        long time = scan.nextLong();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String format = dateFormat.format(new Date(time));
        System.out.println(format);
        scan.close();
    }

    /**
     * 字串分值
     */
    public void QA20_4(){
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        long ans = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            for (int j = i+1; j <= str.length(); j++) {
                int[] count = new int[26];
                for (char c:str.substring(i,j).toCharArray()) {
                    count[c-'a']++;
                }
                for (int k = 0; k < count.length; k++) {
                    if (count[k] == 1) ans++;
                }
            }
        }
        System.out.println(ans + 1);
        scan.close();
    }

    /**
     * k 倍区间
     */
    public void QA17_1() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        long[] prefix = new long[n];
        prefix[0] = scan.nextInt();
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i-1] + scan.nextInt();
        }
        Map<Long,Integer> maps = new HashMap<>();

        int ans = 0;
        maps.put(0L,1);
        for (int i = 0; i < n; i++) {
            ans += maps.getOrDefault(prefix[i] % k ,0);
            maps.put(prefix[i] % k,maps.getOrDefault(prefix[i] % k ,0)+1);
        }
        System.out.println(ans);
    }


    public void QANONE() {
        for (int i = 1; true; i++) {
            int ans = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) ans++;
            }
            if (ans == 100) {
                System.out.println(i);
                return;
            }
        }
    }

    /**
     * 算式问题
     * 173 + 286 = 459
     * 295 + 173 = 468
     * 173 + 295 = 468
     * 183 + 492 = 675
     */
    public void QA12_3(){
        int ans = 0;
        for (int i = 123; i < 999; i++) {
            for (int j = 123; j < 999; j++) {
                int[] tong = new int[10];
                char[] chars = (i + "").toCharArray();
                if (!iss(tong,chars)) {
                    continue;
                }
                char[] char2 = (j + "").toCharArray();
                if (!iss(tong,char2)) {
                    continue;
                }
                if (i + j > 999) continue;
                char[] char3 = (i + j + "").toCharArray();
                if (!iss(tong,char3)) {
                    continue;
                }
                ans++;
            }
        }
        System.out.println(ans);
    }
    public boolean iss(int[] t,char[] cs) {
        for (int k = 0; k < cs.length; k++) {
            int index = cs[k]-'0';
            if (index == 0 || t[index] != 0) return false;
            t[index]++;
        }
        return true;
    }

    /**
     * 数列求值
     */
    public void QA19_1() {
        int p1 = 1;
        int p2 = 1;
        int p3 = 3;
        int ans = 0;
        for (int i = 5; i <= 20190324; i++) {
            ans = p1 + p2 + p3;
            ans %= 10000;
            p1 = p2;
            p2 = p3;
            p3 = ans;
        }
        System.out.println(p3);
    }

    /**
     * 棋盘放麦子
     */
    public void QA12_2() {
        BigInteger x1 = new BigInteger("1");
        BigInteger x2 = new BigInteger("2");
        BigInteger ans = new BigInteger("1");
        for (int i = 2; i <= 64; i++) {
            x1 = x1.multiply(x2);
            ans = ans.add(x1);
        }
        System.out.println(ans);
    }

    /**
     * 猜生日
     */
    public void QA12_1() {
        for (int i = 1900;i<2013; i++) {
            for (int k = 1; k <= getMonthDays(i, 6); k++) {
                String ts = "" +  i + "06" + (k >= 10? k:"0"+k);
                int th = Integer.parseInt(ts);
                if (th % 2012 == 0 && th % 3 == 0 && th % 12 ==0){
                    System.out.println(th);
//                    return;
                }
            }

        }
    }

    /**
     *  星期一
     */
    public void QA18_3() {
        long days = 0;
        for (int i = 1901; i <= 2000 ; i++) {
            for (int j = 0; j < 12; j++) {
                days += getMonthDays(i,j);
            }
        }

        Calendar cs = Calendar.getInstance();
        cs.set(2000,11,31);
        int week = cs.get(Calendar.DAY_OF_WEEK);
        int ans = 0;

        System.out.println((days - 6)%7);
        System.out.println((days - 6)/7);
    }

    /**
     * 获取某年某月有多少天
     */

    public int getMonthDays(int year,int month){
        if (month != 2) return months[month];
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return months[month]+1;
        }else {
            return months[month];
        }
    }

    /**
     * 跑步锻炼
     *  星期一 月初一跑2km，其余1km
     *  从   2000 年 1 月 1 日周六（含）到
     *      2020 年 10 月 1 日周四（含）
     */
    public void QA20_3() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000,0,1);
        int ans = 0;
        while (true){
            if (calendar.get(Calendar.DAY_OF_WEEK) == 2 ||
                calendar.get(Calendar.DATE) == 1) ans+=2;
            else ans+=1;

            if (calendar.get(Calendar.YEAR) == 2020 &&
                calendar.get(Calendar.MONTH) == 9
                    && calendar.get(Calendar.DATE) == 1)  break;

            calendar.add(Calendar.DATE,1);
        }
        System.out.println(ans);
    }

    /**
     * 分数
     */
    public void QA18_1() {
        int[] fenmus = new int[20];
        int[] fenzis = new int[20];
        Arrays.fill(fenzis,1);
        fenmus[0] = 1;
        for (int i = 1,j = 1; i < 20; i++) {
            fenmus[i] = j *=2;
        }
        long fenmu =  fenmus[19];
        long fenzi = 0;
        for (int i = 0; i < fenzis.length; i++) {
            fenzi += fenmu / fenmus[i];
        }
        long gcb = gcb(fenzi,fenmu);
        System.out.println((fenzi / gcb) + "/" + (fenmu/gcb));
    }
    public long gcb(long a,long b) {
        for (long i = b ; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) return i;
        }
        return 0;
    }

    /**
     * 凑算式
     */
    int answer = 0;
    LinkedList<Integer> list = new LinkedList<>();
    public void QA16_3() {
        if (list.size() == 9){
//            int a = list.get(0);
//            int a1 = list.get(1);
//            int a2 = list.get(2);
//            int x = Integer.parseInt(list.get(3) + "" + list.get(4) + list.get(5));
//            int y = Integer.parseInt(list.get(6) + "" + list.get(7) + list.get(8));
//            if ((a1 * y + a2 * x) % (y * a2) == 0 &&
//                    a + (a1 * y + a2 * x) / (y * a2) == 10) {
//                answer++;
//            }

            int c1 = list.get(0) + list.get(1) + list.get(2) + list.get(3);
            int c2 = list.get(3) + list.get(4) + list.get(5) + list.get(6);
            int c3 = list.get(0) + list.get(6) + list.get(7) + list.get(8);
            if (c1 == c2 && c1 == c3) {
                answer++;
            }
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (i == 0 || list.contains(i)) continue;
            list.add(i);
            QA16_3();
            list.removeLast();
        }
    }

    /**
     * 生日蜡烛
     */
    public void QA16_2() {
        a:for (int i = 1; true; i++) {
            int count = 0;
            for (int j = i; true; j++) {
                count += j;
                if (count == 236) {
                    System.out.println(i);
                    break a;
                }else if (count > 236) break;
            }
        }
    }

    /**
     * 煤球数目
     */
    public void QA16_1() {
        int[] dp = new int[101];
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = i + dp[i-1];
        }
        System.out.println(dp[100]);
    }

    /**
     * 《牌型种数》
     */
    int[] pk;
    int ansans = 0;
    public void QA15_4() {
        pk = new int[52];
        int p = 0;
        for (int i = 1; i <= 13; i++) {
            pk[p++]  = i;
            pk[p++]  = i;
            pk[p++]  = i;
            pk[p++]  = i;
        }
        dfs(0,new LinkedList<>());
    }
    public void dfs(int start,LinkedList<Integer> ans) {
        if (ans.size() == 13) {
            ansans++;
            return;
        }
        for (int i = start; i < pk.length; i++) {
            if (i > start && pk[i]==pk[i-1]) continue;
            ans.add(i);
            dfs(i + 1,ans);
            ans.removeLast();
        }
    }

    /**
     * <饮料换购></>
     */
    public void QA15_3() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int p = n;
        while (p >= 3) {
            int s = p % 3;
            int t = p / 3;
            n += t;
            p = s + t;
        }
        System.out.println(n);
        scan.close();
    }

    /**
     * 《加法变乘法》
     */
    public void QA15_2() {
        for (int i = 1; i < 49; i++) {
            for (int j = i + 2; j <= 46; j++) {
                int count = i * (i+1) + j * (j + 1);
                for (int k = 1; k < 50; k++) {
                    if (k != i && k != i +1 && k != j && k != j + 1) count+=k;
                }
                if (count==2015) System.out.println(i);
            }
        }
    }

    public void QA15_1() {
        Map<Long,Long> maps = new HashMap<>();
        for (long i = 1; i < 100000; i++) {
            String[] ss = ((Long)(i * i * i) + "").split("");
            long c = 0;
            for (int j = 0; j < ss.length; j++) {
                c += Long.parseLong(ss[j]);
            }
            if (c == i) maps.put(i,c);
        }
        System.out.println(maps.size());
    }

    public void QA14_1() {
        String str = "abcdefghijklmnopqrs";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 106; i++) {
            sb.append(str);
        }
        while (sb.length() > 1) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < sb.length(); i++) {
                if (i % 2 == 0) list.add(i);
            }
            int i = 0;
            for (Integer integer : list) {
                sb.deleteCharAt(integer - i);
                i++;
            }
        }
        System.out.println(sb.toString());
    }

    public void QA13_3() {
        int[][] dp = new int[4][5];
        Arrays.fill(dp[0],1);
        for (int i = 0; i < 4; i++) dp[i][0] = 1;
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 5; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j-1];
            }
        }
        System.out.println(dp[3][4]);
    }

    /**
     * 假设 a b c d e 代表 1 ~ 9 不同的 5 个数字（注意是各不相同的数字，且不含 00 ）
     * 能满足形如： ab * cde = adb * ce 这样的算式一共有多少种呢？
     */
    public void QA13_2() {
        int ans = 0;
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10 ; j++) {
                if (j == i) continue;
                for (int k = 1; k < 10; k++) {
                    if (k == i || k == j) continue;
                    for (int l = 1; l < 10 ; l++) {
                        if ( l == k || l == j || l == i) continue;
                        for (int m = 1; m < 10; m++) {
                            if (m == i || m == j || m == k || m == l) continue;
                            int left = Integer.parseInt(i + "" + j) * Integer.parseInt(k + "" + l + m);
                            int right = Integer.parseInt(i + "" + l + j) * Integer.parseInt(k + "" + m);
                            if (left == right) ans++;
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }
    public void QA13_1() {
        Calendar instance = Calendar.getInstance();
        for (int i = 2099; true; i+=100) {
            instance.set(Calendar.YEAR,i);
            instance.set(Calendar.MONTH,11);
            instance.set(Calendar.DATE,31);
            if (instance.get(Calendar.DAY_OF_WEEK) == 1) {
                System.out.println(i);
                break;
            }
        }
    }

    public void QA20_2(){
        int count = 0;
        for (int i = 1; i < 2021; i++) {
            String s  = ""+i;
            if (s.contains("2")) {
                count++;
            }
        }
        System.out.println(count);
    }
    public void QA20_1(){
        int count = 0;
        a:for (int i = 4; i <= 2020; i++) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    count++;
                    continue a;
                }
            }
        }
        System.out.println(count);
    }


    /**
     * <数字位数></>
     */
    public void QA21_4(){
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 2020 ; i++) {
            sb.append(i);
        }
        System.out.println(sb.length());
    }

    /**
     * 《相乘》
     */
    public void QA21_3(){
        long t = 2021 * 1000000007L;
        long l = 999999999;
        long r = 1000000007L;
        for (long j = 10000; j <= 1000000007L; j++) {
            long c = 2021 * j;
            if (c % r == l) {

                System.out.println(j);
                System.out.println(c);
            }
        }
        System.out.println(36000000244L%r);
    }

    /**
     * 空间
     */
    public void QA21_2(){
        /**
         * 32位长度为int类型
         * 每个int长度为4个字节
         *
         * 1024 字节等于1kb
         * 1024kb 等于 1MB
         */
    }

    /**
     * 《卡片》
     */
    public void QA21_1() {
        int[] nums = new int[10];
        Arrays.fill(nums,2021);
        int count = 0;
        a:for (int i = 1; true; i++) {
            String s = i + "";
            for (int k = 0; k < s.length(); k++) {
                int i1 = Integer.parseInt(s.charAt(k) + "");
                if (nums[i1] == 0) break a;
                nums[i1]--;
            }
            count++;
        }
        System.out.println(count);
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
//        Timestamp timestamp = new Timestamp(mills-28800000);
        // 初始化需要输出的字符串时间格式
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        // 格式化 时间戳转时间字符串
//        String format = dateFormat.format(timestamp);
//        System.out.println(format);
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
