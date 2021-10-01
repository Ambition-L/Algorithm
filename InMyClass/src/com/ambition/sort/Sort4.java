package com.ambition.sort;

/**
 * 堆排序
 *   1、将数组初始化为一颗二叉树
 *   2、将二叉树初始化为大顶堆（子节点都小于父节点）或者小顶堆（子节点都大于父节点）
 *      2.1 找到二叉树中最小的一个节点元素
 *      2.2 如果这个节点是非叶子节点
 *          2.2.1 将该节点的子节点进行比较 然后将该节点与两个子节点中较大的节点进行比较 如果该节点小于较大的子节点则该节点和子节点交换位置
 *          2.2.2 然后依次将该节点的 的子节点的两个子节点比较之后和该节点进行比较 然后交换位置
 *          2.2.3 直到子节点全是叶子节点为止 然后将该节点的兄弟节点与他的子节点 依次重复 2.2.1，2.2.2步骤
 *          2.2.4 子节点处理完成之后将该节点和兄弟节点进行比较 并找出最大的和父节点进行比较 如果最大的比父节点大 则与父节点交换位置 然后该节点再次重复2.2.1，2.2.2步骤
 *          2.2.5 依次往上比较交换 然后再依次往下比较交换 直到达到树的根节点 那么顶堆初始化完成
 *    3、将顶堆根节点的值与叶子节点最小的值或者最大的值进行交换为止
 *    4、移除这个叶子节点。 然后从顶堆开始 继续将该树恢复成大顶堆 或者小顶堆
 *    4、重复3 步骤 直到该树只剩一个值 那么移出去的数值就是有序的
 */
public class Sort4 {

    static int[] list = new int[]{2, 1, 5, 35, 27, 6, 100, 11};

    public static void main(String[] args) {
        init();
        heapSort();
        for (int arg : list) {
            System.out.print(arg + ",");
        }
    }

    /**
     * 向下调整的函数
     * @param start 堆顶点的角标
     * @param end 堆末尾的角标
     */
    public static void sort7(int start, int end) {
        // 定义父节点和左叶子节点
        int father = start, son = father * 2;

        // 当当前的数据还有子节点时
        while (son <= end) {
            // 把子节点中 较小的节点放在父节点的右边
            if (son + 1 <= end && list[son + 1] > list[son]) {
                // 跳过左子节点
                son ++;
            }
            // 如果父节点小于子节点
            if (list[father] < list[son]) {
                int temp = list[father];
                list[father] = list[son];
                list[son] = temp;
                // 重新定义父节点
                father = son;
                // 重新定义子节点
                son = father * 2;
            }else
                return;
        }
    }

    /**
     * 初始化大顶堆
     */
    public static void init () {
        int[] nums = new int[list.length + 1];
        for (int i = 1,j = 0; i < nums.length; i++,j++) {
            nums[i] = list[j];
        }

        list = nums;

        // 从最底层的非叶子节点开始 初始化顶堆
        for (int i = (list.length- 1 ) / 2; i > 0 ; i--) {
            sort7(i, list.length - 1);
        }
    }

    /**
     * 堆排序函数
     */
    public static void heapSort () {
        // 将初始化好的堆 从最后一个元素开始 依次放在数组的最后 然后再继续初始化顶堆 以此往返 直到剩下最顶堆的数 排序完毕
        for (int i = list.length - 1; i > 0 ; i--) {
            int temp = list[1];
            list[1] = list[i];
            list[i] = temp;
            sort7(1, i - 1);
        }
    }
}
