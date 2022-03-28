package com.ambition.boot.work1;

import java.util.*;

public class LanQiaoCupTalk {

    public static void main(String[] args) {
        String s =  "0100110001010001";;

        int ans = 0;
        Set<String> set  = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                set.add(s.substring(i,j));
            }
        }
        System.out.println(set.size());
    }



    /**
     *
     */
    public void QA8 () {
        Scanner scan = new Scanner(System.in);
        int r = scan.nextInt();
        int c = scan.nextInt();
        int[][] yuan = new int[r][c];
        String[] yuan1 = new String[r];
        for (int i = 0; i < r; i++) {
            String next = scan.next();
            String[] split = next.split("");
            for (int j = 0; j < c; j++) {
                yuan[i][j] = Integer.parseInt(split[j]);
            }
            yuan1[i] = next;
        }
        int nr = scan.nextInt();
        int nc = scan.nextInt();
        if (nr > r || nc > c) {
            System.out.println("NO");
            return;
        }
        List<String> tStr = new ArrayList<>();
        Set<String> set = new HashSet<>();
        int t = -1,d = -1,l = -1,r1 = -1;
        for (int i = 0; i < nr; i++) {
            String next = scan.next();
            tStr.add(next);
            String[] split = next.split("");
            for (int j = 0; j < nc; j++) {
                if (Integer.parseInt(split[j]) == 1) {
                    if (t == -1) t = i;
                    if (l == -1) l = j;
                    d = i;
                    r1 = Math.max(r1,j);
                    set.add(i + "," + j);
                }
            }
        }
        int top = t,down = nr - d - 1,left = l,right = nc - r1 - 1;

        boolean[][] flag = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            a:for (int j = 0; j < c; j++) {
                if (yuan[i][j] == 0 || flag[i][j]) continue;
                Set<int[]> tSet = new HashSet<>();
                int[] ld = new int[]{Integer.MAX_VALUE,-1,-1};
                QA8DFS(yuan,i,j,tSet,flag,ld);
                if (tSet.size() < set.size()) continue;
                // 校验是否越界
                if (i - top < 0 || (c - ld[2] - 1) - right < 0 ||
                    ld[0] - left < 0 || (r - ld[1] - 1) - down < 0 ) continue;

                List<String> lll = new ArrayList<>();
                for (int k = i; k <= ld[1]; k++) {
                    lll.add(yuan1[k].substring(ld[0],ld[2]+1));
                }

                int ab = 0;
                int x = -1;
                for (int k = 0; k < lll.size(); k++) {
                    for (int m = 0; m < tStr.size(); m++) {
                        int c1 =k;
                        if (lll.get(c1).contains(tStr.get(m))) {
                            if (x == -1) x = k;
                            ab++;
                        }
                        c1++;
                    }

                }
                if (ab < tStr.size()) continue a;

                System.out.println(2 + " " + 4);
                return;
            }
        }
        System.out.println("NO");
        scan.close();
    }
    public void QA8DFS(int[][] nums, int x, int y, Set<int[]> set,boolean[][] flag,
                       int[] ld) {
        if (x < 0 || x >= nums.length || y < 0 || y >= nums[x].length
            || nums[x][y] == 0 || flag[x][y]) return;
        ld[0] = Math.min(ld[0],y);
        ld[1] = Math.max(ld[1],x);
        ld[2] = Math.max(ld[2],y);
        set.add(new int[]{x,y});
        flag[x][y] = true;
        QA8DFS(nums,x - 1,y,set,flag,ld);
        QA8DFS(nums,x + 1,y,set,flag,ld);
        QA8DFS(nums,x,y - 1,set,flag,ld);
        QA8DFS(nums,x ,y + 1,set,flag,ld);
    }

    public void QA9 () {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        int count = 0;
        int max = Math.max(c,Math.max(a,b));
        for (int i = 1; i <= max; i++) {
                if ((a % i == 0 && b % i == 0) || (b % i == 0 && c % i == 0) ||
                        a % i == 0 && c % i == 0) count++;
        }
        System.out.println(count);
        scan.close();
    }

    public void QA6() {
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        String replace = str.replace(",", "");
        System.out.printf("%.2f%n",Double.parseDouble(replace));
        scan.close();
    }


    public void AQ5 () {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int c = scan.nextInt();
        if (c % n == 0) System.out.println(c / n);
        else System.out.println(c/n+1);
        scan.close();
    }

    public void QA4 () {
        int[][] nums = new int[30][30];
        int end = 30;
        int num = 1;
        int start = 0;
        for (int i = 0,j = 0; i < nums.length && j < nums.length; i++) {
            for (int k = start; k < end - 1; k++) {
                nums[start][k] = num;
                num++;
            }
            for (int k = start; k < end - 1; k++) {
                nums[k][end - 1] = num;
                num++;
            }

            for (int k = end - 1; k >= start + 1; k--) {
                nums[end - 1][k] = num;
                num++;
            }

            for (int k = end - 1; k >= start +1; k--) {
                nums[k][start] = num;
                num++;
            }
            start++;
            end--;
        }
    }

    public void QA3 () {
        int num = 2021;
        int count = 0;
        while (num != 1) {
            if (num % 2 == 0) num /= 2;
            else num -= 1;
            count++;
        }
        System.out.println(count);
    }

    public void QA2() {
        int count = 0;
        for (int i = 1; i <= 2021; i++) {
            String s = i+"";
            if (s.contains("2")) {
                count++;
            }
        }
        System.out.println(count);
    }

    public void QA1 () {
        String s = "AAAAAAABABBAABABABAAAAAAA" +
                "ABBBBBABBAABBBBBABABBBBBA" +
                "ABAAABABBBABAABBBBABAAABA" +
                "ABAAABABBBBBAABAABABAAABA" +
                "ABAAABABBABABBABABABAAABA" +
                "ABBBBBABBBABAABBBBABBBBBA" +
                "AAAAAAABABABABABABAAAAAAA" +
                "BBBBBBBBABAABABBBBBBBBBBB" +
                "AABAABABBAAABBAAABABBBBBA" +
                "ABBABABBBABBAAAABBBBAAAAB" +
                "BBBBAAABABAABABAABBBAABBA" +
                "BBAABABABAAAABBBAABBAAAAA" +
                "ABABBBABAABAABABABABBBBBA" +
                "AAAABBBBBABBBBAAABBBABBAB" +
                "AABAABAAABAAABAABABABAAAA" +
                "ABBBBBBBBABABBBBABAABBABA" +
                "ABBBAAABAAABBBAAAAAAABAAB" +
                "BBBBBBBBABBAAABAABBBABBAB" +
                "AAAAAAABBAAABBBBABABAABBA" +
                "ABBBBBABBAABABAAABBBABBAA" +
                "ABAAABABABBBAAAAAAAAAABAA" +
                "ABAAABABABABBBABBAABBABAA" +
                "ABAAABABBABBABABAABAABAAA" +
                "ABBBBBABABBBBBABBAAAABAAA" +
                "AAAAAAABAABBBAABABABBABBA";

        String b = s.replace("B", "");
        System.out.println(b.length());
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == 'A') count++;
        }
        System.out.println(count);
    }
}
