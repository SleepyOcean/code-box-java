package com.sleepyocean.exercise.complicate.search;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 树的遍历和查找
 *
 * @author gehoubao
 * @create
 **/
public class TreeSearch {
    public static void main(String[] args) {
        test02();
    }

    /**
     * 面试题 04.10. 检查子树
     * <p>
     * 检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。
     * 如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。
     * <p>
     * 示例1:
     * 输入：t1 = [1, 2, 3], t2 = [2]
     * 输出：true
     * 示例2:
     * 输入：t1 = [1, null, 2, 4], t2 = [3, 2]
     * 输出：false
     * 提示：
     * <p>
     * 树的节点数目范围为[0, 20000]。
     */
    private static void test02() {
        TreeNode root1 = new TreeNode(new TreeNode(new TreeNode(12), new TreeNode(72), 43),
                new TreeNode(new TreeNode(32), new TreeNode(56), 65), 21);
        TreeNode root2 = new TreeNode(new TreeNode(32), new TreeNode(56), 65);
        System.out.println(checkSubTree(root1, root2));
    }

    private static boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }
        if (t1.val == t2.val) {
            return checkSubTree(t1.left, t2.left) && checkSubTree(t1.right, t2.right);
        } else {
            return checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
        }
    }

    /**
     * 103. 二叉树的锯齿形层次遍历
     * <p>
     * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     * <p>
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回锯齿形层次遍历如下：
     * <p>
     * [
     * [3],
     * [20,9],
     * [15,7]
     * ]
     */
    private static void test01() {
        TreeNode root = new TreeNode(new TreeNode(new TreeNode(12), new TreeNode(72), 43),
                new TreeNode(new TreeNode(32), new TreeNode(56), 65), 21);
        System.out.println(zigzagLevelOrder(root));
    }

    private static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> dq = new LinkedList<>();

        boolean flag = true;
        dq.add(root);
        while (!dq.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int cnt = dq.size();
            for (int i = 0; i < cnt; i++) {
                TreeNode current = dq.poll();
                if (current.left != null) {
                    dq.offer(current.left);
                }
                if (current.right != null) {
                    dq.offer(current.right);
                }
                if (flag) {
                    level.add(current.val);
                } else {
                    level.add(0, current.val);
                }
            }
            flag = !flag;
            result.add(level);
        }
        return result;
    }

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(TreeNode left, TreeNode right, int val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }
}