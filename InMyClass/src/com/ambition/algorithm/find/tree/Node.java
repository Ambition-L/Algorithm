package com.ambition.algorithm.find.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 二叉搜索树的 增删改查
 */
public class Node {
    private int val;
    private Node left;
    private Node right;

    public static void main(String[] args) {
        int[] nums = new int[]{2,14,5,19,3,8,6,17,15};
        Node root = new Node(9);
        // 插入
        for (int i = 0; i < nums.length; i++) {
            root.insert(root, nums[i]);
        }

        // 遍历
//        root.printf(root);
//        System.out.println(root.list);

        // 查找
//        root.search(root, root, 8).forEach( (x,y) -> {
//            if (x.equals("flag")) {
//                System.out.println(y);
//            }
//            if (x.equals("node")) {
//                Node y1 = (Node) y;
//                System.out.println("node" + y1.val);
//            }
//            if (x.equals("parent")) {
//                Node y1 = (Node) y;
//                System.out.println(y1.val);
//            }
//        });

        // 寻找前驱
//        root.getFistNode(root,3,new Node(-1)).forEach((x,y) -> {
//            if (x.equals("flag")) {
//                System.out.println(y);
//            }
//            if (x.equals("node")) {
//                Node y1 = (Node) y;
//                System.out.println("node" + y1.val);
//            }
//        });

        // 寻找后继
//        root.getLastNode(root,3,new Node(-1)).forEach((x,y) -> {
//            if (x.equals("flag")) {
//                System.out.println(y);
//            }
//            if (x.equals("node")) {
//                Node y1 = (Node) y;
//                System.out.println("node" + y1.val);
//            }
//        });

        // 删除
        root.delete(root, 5);

        root.printf(root);
        System.out.println(root.list);
    }

    public Node() {}

    public Node(int val) {
        this.val = val;
    }

    /**
     * 遍历二叉搜索树
     * @param root 根节点
     * @return
     */
    List<Integer> list = new ArrayList<>();
    public void printf(Node root) {
        if (root == null)
            return;
        list.add(root.val);
        printf(root.left);
        printf(root.right);
    }

    /**
     * 插入数据 （构建二叉树）
     * @param node 当前节点
     * @param data 插入数据
     */
    public void insert(Node node,int data) {
        // 如果当前节点为空 则将该数据作为当前节点
        if (node != null) {
            // 如果插入值小于当前节点 进入当前节点的左子树
            if (data < node.val) {
                // 如果当前节点的左节点刚好为空 则将该数据作为当前节点的左子节点插入
                if (node.left == null) {
                    node.left = new Node(data);
                }else // 如果该节点的左子节点不为空则向下查找比对
                    insert(node.left, data);
            }else if (data > node.val) { // 如果大于当前节点 进入右子树 进行插入操作
                if (node.right == null) {
                    node.right = new Node(data);
                }else
                    insert(node.right, data);
            }
        }else
            node = new Node(data);
    }

    /**
     * 查找
     * @param node 当前节点
     * @param parent 父节点
     * @param data 查找数据
     * @return 是否找到 当前节点 当前节点的父节点
     */
    public Map<String,Object> search(Node node, Node parent, int data) {
        Map<String,Object> map = new HashMap<>();

        // 如果没找到
        if (node == null) {
            map.put("flag", -1);
            map.put("node", node);
            map.put("parent", parent);
            return map;
        }

        // 如果找到了
        if (data == node.val) {
            map.put("flag", 1);
            map.put("node", node);
            map.put("parent", parent);
            return map;
        }

        // 如果寻找的值比当前节点小 进入当前节点的左子树进行查找
        if (data < node.val) {
            return search(node.left, node,data);
        }

        // 如果寻找的值比当前节点大 进入当前节点的右子树进行查找
        return search(node.right, node, data);
    }

    /**
     * 查找某个数的前驱节点
     *      前驱节点： 比这个数小的所有数中最大的一个
     *      所以：
     *          当目标节点存在左子树时 前驱节点一定在左子树的右子树最后一个右子树上
     *          当目标节点不存在左子树 前驱节点一定在根节点到目标节点路路径上
     * @param node 当前节点
     * @param data 寻找前驱节点的值
     * @param maxn 最大前驱节点
     * @return 是否找到 和 前驱节点
     */
    public Map<String, Object> getFistNode(Node node, int data, Node maxn) {
        Map<String, Object> map = new HashMap<>();

        // 如果当前节点为空 未找到
        if (node == null) {
            map.put("flag", -1);
            map.put("node", maxn);
            return map;
        }

        // 如果找到目标节点 进入左子树
        if (data == node.val) {
            map.put("flag", 1);
            // 如果左子树不为空
            if (node.left != null) {
                // 进入左子树 寻找左子树的右子树中的最后一个值
                Node temp = node.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                map.put("node", temp);
            }else
                map.put("node", maxn);
                return map;
        }

        // 如果小于当前节点 进入左子树
        if (data < node.val) {
            return getFistNode(node.left, data, maxn);
        }

        // 如果大于当前节点 进入右子树 并把当前节点作为前驱节点
        return getFistNode(node.right, data, node);
    }

    /**
     * 查找后继节点：
     *      后继节点： 比这个节点大的所有数中最小的一个数
     *      所以：
     *          当这个节点存在右子树 那么它的后继节点一定在右子树的左子树的最后一个左子树中
     *          当这个节点不存在右子树 那么它的后继节点一定在根节点到这个节点的路径上
     *
     * @param node 当前节点
     * @param data 寻找后继节点的数
     * @param minx 记录路径最小节点
     * @return 是否查找到 和 后继节点
     */
    public Map<String, Object> getLastNode(Node node, int data, Node minx) {
        Map<String, Object> map = new HashMap<>();

        // 未找到目标值节点 返回路径上最小节点
        if (node == null) {
            map.put("flag", -1);
            map.put("node", minx);
            return map;
        }

        // 找到了目标值节点
        if (data == node.val) {
            map.put("flag", 1);
            // 该节点存在右子树 进入该节点的右子树
            if (node.right != null) {
                // 进入右子树的左子树寻找后继节点
                Node temp = node.right;
                while (temp.left != null) {
                    // 更新
                    temp = temp.left;
                }
                map.put("node",temp);
            }else
                map.put("node",minx);

            return map;
        }

        // 目标值比节点小 进入左子树 并将该节点作为最小节点
        if (data < node.val)
            return getLastNode(node.left, data, node);

        // 目标值比节点大 进入右子树
        return getLastNode(node.right, data, minx);
    }

    /**
     * 删除节点
     *      如果当前节点子节点数小于2 则直接删除当前节点 将当前节点的右子节点指向当前节点
     *      如果当前节点的子节点数大于2 则找到当前节点的后继节点
     *                              将后继节点的右子节点指向后继节点的父节点的左子节点
     *                              将节点修改为后继节点 将后继节点删除
     * @param root 根节点
     * @param data 删除的数
     */
    public Node delete(Node root, int data) {
        if (root == null) {
            System.out.println("需要删除的数据不存在");
            return root;
        }

        // 比当前节点小 进入左子树
        if (data < root.val) {
            root.left = delete(root.left, data);
            return root;
        }else if /* 比当前节点大 进入右子树 */(data > root.val) {
            root.right = delete(root.right, data);
            return root;
        }else { // 找到要删除的数
            // 如果当前节点不存在左子树
            if (root.left == null) {
                Node rightNode = root.right;
                root.right = null;
                return rightNode;
            }else if /* 如果当前节点不存在右子树 */(root.right == null) {
                Node leftNode = root.left;
                root.left = null;
                return leftNode;
            }else /* 如果当前节点存在左右子树 */{
                // 进入右子树找到 后继节点
                Node rightNode = root.right;
                // 如果当前右子树没有左子树
                if (rightNode.left == null) {
                    // 将当前节点的右节点 设置为当前节点的值
                    root.val = rightNode.val;
                    // 将当前节点的的右节点的右子树 设置为当前节点的右子树
                    root.right = rightNode.right;
                }else {
                    // 如果当前右子树存在左子树 则进入该左子树
                    Node next = rightNode.left;
                    while (next.left != null) {
                        // 更新后继节点
                        next = next.left;
                    }
                    // 将当前节点值 修改为后继节点值
                    root.val = next.val;
                    // 将后继节点删除 并返回当前节点的右子树
                    root.right = del(root.right);
                }
                // 返回当前节点
                return root;
            }
        }
    }

    /**
     * 删除节点
     * @param root
     */
    public Node del(Node root) {
        if (root.left == null) {
            return root.right;
        }
        root.left = del(root.left);
        return root;
    }
}
