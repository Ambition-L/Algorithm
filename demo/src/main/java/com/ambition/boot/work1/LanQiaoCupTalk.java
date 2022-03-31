package com.ambition.boot.work1;

import java.util.*;

public class LanQiaoCupTalk {

    int count = Integer.MAX_VALUE;
    public static void main(String[] args) {
        LanQiaoCupTalk lanQiaoCupTalk = new LanQiaoCupTalk();
        lanQiaoCupTalk.QA8();
//        System.out.println(lanQiaoCupTalk.count);
    }

    /**
     * 13届蓝桥杯模拟赛。 回溯
     * @param num
     * @param c
     */
    public void QA3 (int num,int c) {
        if (num == 1)  {
            count = Math.min(count,c);
            return;
        }
        if (num % 2 == 0) {
            c++;
            QA3(num/2,c);
            c--;
        }else {
            for (int i = 0; i < 2; i++) {
                c++;
                if (i == 1) {
                    num++;
                    QA3(num,c);
                    num--;
                }else {
                    num--;
                    QA3(num,c);
                    num++;
                }
                c--;
            }
        }
    }


    /**
     *   13届蓝桥杯模拟赛 第八题《插座》
     *      "模拟"
     */
    int[][] yuan;
    int[][] cs;
    public void QA8 () {
        Scanner scan = new Scanner(System.in);
        int r = scan.nextInt();
        int c = scan.nextInt();
        yuan = new int[r][c];
        for (int i = 0; i < r; i++) {
            String[] split = scan.next().split("");
            for (int j = 0; j < c; j++) {
                yuan[i][j] = Integer.parseInt(split[j]);
            }
        }
        int nr = scan.nextInt();
        int nc = scan.nextInt();
        if (nr > r || nc > c) {
            System.out.println("NO");
            return;
        }
        cs = new int[nr][nc];
        for (int i = 0; i < nr; i++) {
            String[] split = scan.next().split("");
            for (int j = 0; j < nc; j++) {
                cs[i][j] = Integer.parseInt(split[j]);
            }
        }

        // 模拟插座的每行每列 （行列小于等于插头）
        for (int i = 0; i + nr - 1 < r; i++) {
            for (int j = 0; j + nc - 1 < c; j++) {
                if (check(i,j)) {
                    System.out.println((i+1) + " " + (j+1));
                    return;
                }
            }
        }

        System.out.println("NO");
        scan.close();
    }
    public boolean check(int x,int y) {
        // 模拟插头 处理对应的1是否有1
        for (int i = 0; i < cs.length; i++) {
            for (int j = 0; j < cs[0].length; j++) {
                if (cs[i][j] == 1 && yuan[i+x][j+y] == 0) return false;
            }
        }
        return true;
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

    /**
     * 循环填数
     */
    public void QA4 () {
        int[][] nums = new int[30][30];
        int end = 30;
        int num = 1;
        int start = 0;
        a:while (true){
            for (int k = start; k < end - 1; k++) {
                if (nums[19][19] != 0) break a;
                nums[start][k] = num++;
            }
            for (int k = start; k < end - 1; k++) {
                nums[k][end - 1] = num++;
            }

            for (int k = end - 1; k >= start + 1; k--) {
                nums[end - 1][k] = num++;
            }

            for (int k = end - 1; k >= start +1; k--) {
                nums[k][start] = num++;
            }
            start++;
            end--;
        }
        System.out.println(nums[19][19]);
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
