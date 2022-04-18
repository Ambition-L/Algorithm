package com.ambition.boot.bigdata;

import java.util.*;

public class KMeans {
    // 路径
    boolean[][] vis;
    // 集合簇
    List<Map<String,List<int[]>>> ans = new ArrayList<>();
    public static void main(String[] args) {
        KMeans means = new KMeans();
        means.center(new int[][]{
                {3,1}, {2,2}, {5,3}, {8,3}, {3,4},
                {10,4}, {8,6}, {10,7}, {14,7}, {9,8},
                {12,8},{15,8} ,{14,9},{10,10}, {11,13},
                {3,14}, {10,14}, {12,15}, {14,15},
        },3,3);
        System.out.println(means.ans);
    }
//    {2,1},
//    {5,1},
//    {1,2},
//    {2,2},
//    {3,2},
//    {4,2},
//    {5,2},
//    {6,2},
//    {1,3},
//    {2,3},
//    {5,3},
//    {2,4},

    /**
     * 核心方法
     * @param points 数据集
     * @param minPts 判定为核心对象的最小对象包含值
     * @param r 范围r
     */
    public void center(int[][] points,int minPts,int r) {
        vis = new boolean[100][100];
        Map<String,int[]> flag = new HashMap<>();
        for (int[] point : points) {
            if (vis[point[0]][point[1]]) continue;
            List<int[]> N = calculate(point, r, points);
            // 当前对象为核心对象
            if (N.size() >= minPts) {
                List<int[]> C = new ArrayList<>();
                C.add(point);
                vis[point[0]][point[1]] = true;
                Map<String,List<int[]>> maps = new HashMap<>();
                Queue<int[]> queue = new LinkedList<>();
                queue.addAll(N);
                N.clear();
                while (!queue.isEmpty()) {
                    int[] n = queue.poll();
                    int x = n[0];
                    int y = n[1];
                    if (vis[x][y]) continue;
                    // 计算该对象是否为核心对象
                    List<int[]> calculate = calculate(n, r, points);
                    if (calculate.size() >= minPts) {
                        // 如果是添加到集合N中
                        queue.addAll(calculate);
                    }
                    C.add(n);
                    // 已有簇 删除key
                    flag.remove(x + "," + y);
                    vis[x][y] = true;
                }

                maps.put(point[0] + "," + point[1],C);
                ans.add(maps);
            }else {
                // 将不能形成簇的点 先添加入集合中
                flag.put(point[0] + "," + point[1],point);
            }
        }

        // 将最后都没有任何簇的点(噪声)分为一个簇
        Map<String,List<int[]>> zs = new HashMap<>();
        if (flag.size() != 0) {
            zs.putIfAbsent("p",new ArrayList<>(flag.values()));
        }

        ans.add(zs);
    }

    /**
     * 计算当前点指定半径内包含的点
     * @param point 当前点
     * @param r 半径
     * @param points 点集合
     * @return
     */
    public List<int[]> calculate(int[] point,int r,int[][] points) {
        List<int[]> list = new ArrayList<>();
        for (int[] curr : points) {
            int x = curr[0];
            int y = curr[1];
//            if (point[0] == x && point[1] == y) continue;
            int nx = Math.abs((x - point[0]));
            int ny = Math.abs((y - point[1]));
            if (Math.sqrt(nx * nx + ny * ny) <= r) {
                list.add(curr);
            }
        }
        return list;
    }
}
