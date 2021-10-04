package com.ambition.find.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建二叉树的两种方式 以及遍历二叉树的三种方式
 *  第一种方式：
 *      先初始化节点数组（将数组中的每个数初始化为一个树节点）
 *      再利用二叉树的特性（当前节点的左子节点为 i*2 右子节点为i*2+1） 为各个节点添加左右子节点
 *    注意点：
 *      根节点的位置 和 遍历时边界
 *
 *  第二种方式：
 *      先初始化根节点
 *      再根据插入的书为根节点添加左右子节点
 *          当该父节点不为空：
 *              则将输入节点和该父节点进行比较如果比父节点小
 *                  则放入父节点左边
 *                      如果该父节点的左子节点刚好为空 则直接添加
 *                      否则根据该左子节点依次往下递归寻找插入
 *              如果比父节点大则放入父节点右边
 *                      如果该父节点的右子节点刚好为空 则直接添加
 *                      否则根据该右子节点递归往下查找并添加
 *         当该父节点为空：
 *              则将该节点作为当前父节点
 *
 *  三种遍历方式
 *      1、先序遍历
 *      2、中序遍历
 *      3、后序遍历
 *      （前中后针对的都是中间节点 比如先序 中间节先输出 然后左右 后序：左右节点先输出 最后输出中间节点）
 */

/**
 * 节点类
 */
public class TreeNode {
    // 当前值
    int val;
    // 左孩子
    TreeNode leftSon;
    // 右孩子
    TreeNode rightSon;

    public TreeNode(int val) {
        this.val = val;
    }

    /**
     * 构建二叉树方式二 存储地址
     * @param treeNode
     * @param data
     */
    public void insert(TreeNode treeNode, int data) {
        // 如果当前节点不为空
        if (treeNode != null) {
            // 如果小于当前父节点
            if (data < treeNode.val) {
                // 则放在父节点的左边
                if (treeNode.leftSon == null) { // 如果当前父节点的左子节点不为空 则直接添加
                    treeNode.leftSon = new TreeNode(data);
                }else /* 如果不为空 则递归查找*/{
                    insert(treeNode.leftSon,data);
                }
            // 如果大于当前父节点
            }else if (data > treeNode.val) {
                // 则放在父节点的右边
                if (treeNode.rightSon == null) { // 如果当前父节点的右子节点为空 则直接添加
                    treeNode.rightSon = new TreeNode(data);
                } else /* 如果不为空 则递归查找*/ {
                    insert(treeNode.rightSon,data);
                }
            }
        }else {
            // 如果当前节点为空 将当前值赋值给当前节点
            treeNode = new TreeNode(data);
        }
    }

    public void priintf(TreeNode treeNode) {
        if (treeNode == null)
            return;
        priintf(treeNode.leftSon);
        priintf(treeNode.rightSon);
        System.out.println(treeNode.val);
    }
}

class Test {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10};
        TreeNode treeNode = new TreeNode(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            treeNode.insert(treeNode,nums[i]);
        }

        treeNode.priintf(treeNode);

        /*Test test = new Test();
        List<TreeNode> treeNodes = test.init(nums);
        test.printfs(treeNodes,1);*/
    }

    /**
     * 初始化树的方式1 存储值
     * @param nums 注意根节点的位置 和 遍历时边界
     * @return
     */
    public List<TreeNode> init(int[] nums) {
        // 初始化存储存储节点链表
        List<TreeNode> nodeList = new ArrayList<>();
        nodeList.add(null);

        // 将节点添加节点链表
        for (int i = 0; i < nums.length; i++) {
            nodeList.add(new TreeNode(nums[i]));
        }

        // 定义当前节点指针 从1开始
        int index = 1;

        // 为节点添加孩子节点
        for (int i = 0; i < nodeList.size(); i++) {
            // 如果当前节点为null 则不存在孩子节点 跳出循环
            if (nodeList.get(i) == null)
                continue;

            // 如果存在左子节点 并且左子节点不为空
            if (index * 2 < nums.length && nodeList.get(index * 2) != null) {
                nodeList.get(i).leftSon = nodeList.get(index * 2);
            }

            // 如果存在右子节点 并且右子节点不为空
            if (index * 2 + 1 < nums.length && nodeList.get(index * 2 + 1) != null) {
                nodeList.get(i).rightSon = nodeList.get(index * 2 + 1);
            }

            // 指针指向下一个节点
            index++;
        }
        return nodeList;
    }

    public void printfs(List<TreeNode> nodes, int index) {
        if (index >= nodes.size() || nodes.get(index) == null)
            return;

        System.out.println(nodes.get(index).val);
        printfs(nodes, index * 2);
        printfs(nodes, index * 2 + 1);
    }
}
