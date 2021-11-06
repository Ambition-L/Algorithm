package com.ambition.exer;

import com.ambition.algorithm.bfs.Grid;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class RainWater{

    Integer min = Integer.MAX_VALUE;
    public static void main(String[] args) {
    }


    public boolean isTure(int x, int y, int data, int[][] nums,int num,int num2) {
        if ((y + num < nums[0].length  && y + num >= 0)
                && (x + num2 < nums.length && x + num2 >= 0)) {
            if (data > nums[x + num2][y + num]) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int bfs(int[][] nums) {

        // 创建队列 将中间区域放入队列中
        Queue<Grid> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                if (i == 0 || i == nums.length - 1 || j == 0 || j == nums[i].length - 1) {
                    if (nums[i][j] < min) {
                        int x = 0;
                        // 判断聆节点是否都大于这个最小围栏
                        if (isTure(i,j,nums[i][j],nums,1,0)) {
                            x++;
                        }if (isTure(i,j,nums[i][j],nums,0,1)) {
                            x++;
                        }if (isTure(i,j,nums[i][j],nums,-1,0)) {
                            x++;
                        }if (isTure(i,j,nums[i][j],nums,0,-1)) {
                            x++;
                        }
                        if (x != 0) min = nums[i][j];
                    }
                }else {
                    queue.offer(new Grid(i,j,nums[i][j]));
                }
            }
        }

        if (min == Integer.MAX_VALUE)
            min = nums[0][0];

        int count = 0;

        //  如果队列不为空
        while (!queue.isEmpty()) {
            // 出队
            Grid grid = queue.poll();

            if (grid.getVal() > min
                    && grid.getVal() < nums[grid.getX() - 1][grid.getY()]
                    && grid.getVal() < nums[grid.getX() + 1][grid.getY()]
                    && grid.getVal() < nums[grid.getX()][grid.getY() + 1]
                    && grid.getVal() < nums[grid.getX()][grid.getY() - 1]) {
                // 求出当前位置周围最小值
                int[] cc = new int[]{nums[grid.getX() - 1][grid.getY()], nums[grid.getX() + 1][grid.getY()], nums[grid.getX()][grid.getY() + 1], nums[grid.getX()][grid.getY() - 1]};
                Arrays.sort(cc);
                count += cc[0] - grid.getVal();
                continue;
            }

            // 判断周围格子高度是否满足当前格子接雨水
            if (!move(grid,nums,1,0)) continue;
            if (!move(grid,nums,0,-1)) continue;
            if (!move(grid,nums,-1,0)) continue;
            if (!move(grid,nums,0,1)) continue;

            // 如果当前值的满足小于上下左右或者 小于围栏值 并且当前值大于围栏值 则要判断他本身的上下左右是否大于他本身 并且上下左右大于围栏值

            if (grid.getVal() > min)
                continue;
            count += min - grid.getVal();
        }
        return count;
    }

    public boolean move(Grid grid, int[][] nums, int num, int num2) {
        int data1 = nums[grid.getX() + num2][grid.getY() + num];
        // 如果移动后 行和列都不越界 并且移动后的值大于等于移动前的值
        if ((grid.getY() + num < nums[0].length  && grid.getY() + num >= 0)
                && (grid.getX() + num2 < nums.length && grid.getX() + num2 >= 0)
                && (grid.getVal() <= nums[grid.getX() + num2][grid.getY() + num] || min >= nums[grid.getX() + num2][grid.getY() + num])) {
            return true;
        }
        return false;
    }
}

public class Work2 {


}
