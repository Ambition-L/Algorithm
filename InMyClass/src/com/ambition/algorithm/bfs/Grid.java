package com.ambition.algorithm.bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class BFS {
    public static void main(String[] args) {
        System.out.println(new BFS().bfs(3, 3, new int[][]{
//                {1, 0, 1, 1},
//                {1, 0, 1, 0},
//                {0, 1, 0, 1},
//                {0, 0, 1, 1}
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1}
        }));
    }

    public int bfs(int x, int y, int[][] nums) {
        // 创建队列 并将原点入队
        Queue<Grid> queue = new LinkedList<>();
        queue.offer(new Grid(x,y,nums[x - 1][y - 1])); // 角标从0开始 故x，y减一

        // 定义hash表并记录原点的访问状态 1代表在队列中 2代表出队 最后hash表的长度就是覆盖的格子数
        Map<String,Integer> map = new HashMap<>();
        map.put(x + "." + y, 1);

        //  如果队列不为空
        while (!queue.isEmpty()) {
            // 出队
            Grid grid = queue.poll();

            // 上下左右移动
            move(grid,nums,queue,1,0,map);
            move(grid,nums,queue,0,-1,map);
            move(grid,nums,queue,-1,0,map);
            move(grid,nums,queue,0,1,map);

            // 移动完成后改变当前坐标状态
            map.put(grid.getX() + "." + grid.getY(), 2);
        }
        return map.size();
    }

    public void move(Grid grid, int[][] nums, Queue<Grid> queue, int num,int num2, Map<String,Integer> map) {
        // 拼接当前位置存入hash表的key
        String str = ".";
        if (num == 0)
            str =  grid.getX() + num2 + str + grid.getY();

        if (num2 == 0) {
            str = grid.getX() + str + (grid.getY() + num);
        }

        // 如果移动后 行和列都不越界 并且移动后的值和移动前的值不相等 并且这个位置未被访问过
        if ((grid.getY() + num <= nums.length && grid.getY() + num > 0) // 因为角标-1的故角标长度 大于0
                && (grid.getX() + num2 <= nums[0].length && grid.getX() + num2 > 0)
                && grid.getVal() != nums[grid.getX() + num2 - 1][grid.getY() + num - 1]
                && map.get(str) == null) {

            // 判断是否在队列中
            boolean flag = false;
            for (Grid t : queue) {
                if (t.getX().equals(grid.getX() + num2) && t.getY().equals(grid.getY() + num)) {
                    flag = true;
                    break;
                }
            }

            // 如果不在 则入队
            if (!flag) {
                queue.offer(new Grid(grid.getX() + num2,
                        grid.getY() + num, nums[grid.getX() + num2 - 1][grid.getY() + num - 1]));
            }

            // 移动完成后改变当前坐标状态
            map.put(str, 1);
        }
    }
}

// 初始化数据位置类
public class Grid {
    private Integer x;
    private Integer y;
    private Integer val;

    public Grid(){}

    public Grid(Integer x,Integer y,Integer val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
}
