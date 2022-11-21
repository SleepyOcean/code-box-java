package com.sleepyocean.exercise;

import java.util.*;

/**
 * 剑指Offer刷题集
 *
 * @author sleepyocean
 **/
public class PointToOffer {
    public static void main(String[] args) {
        test18();
    }

    /**
     * 剑指 Offer 18. 删除链表的节点
     * <p>
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     * 返回删除后的链表的头节点。
     * 注意：此题对比原题有改动
     * <p>
     * 示例 1:
     * 输入: head = [4,5,1,9], val = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * <p>
     * 示例 2:
     * 输入: head = [4,5,1,9], val = 1
     * 输出: [4,5,9]
     * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     * <p>
     * 说明：
     * 题目保证链表中节点的值互不相同
     * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
     */
    private static void test18() {
        ListNode head = new ListNode(new ListNode(new ListNode(new ListNode(new ListNode(41), 9), 1), 5), 4);
        head = deleteNode(head, 4);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    private static ListNode deleteNode(ListNode head, int val) {
        ListNode pre = head;
        ListNode next = head;
        while (next != null) {
            if (val == next.val) {
                if (head == next) {
                    head = next.next;
                }
                pre.next = next.next;
            }
            pre = next;
            next = next.next;
        }
        return head;
    }

    /**
     * 剑指 Offer 09. 用两个栈实现队列
     * <p>
     * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
     * <p>
     * 例 1：
     * 输入：
     * ["CQueue","appendTail","deleteHead","deleteHead"]
     * [[],[3],[],[]]
     * 输出：[null,null,3,-1]
     * <p>
     * 示例 2：
     * 输入：
     * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
     * [[],[],[5],[2],[],[]]
     * 输出：[null,-1,null,null,5,2]
     * <p>
     * 提示：
     * 1 <= values <= 10000
     * 最多会对 appendTail、deleteHead 进行 10000 次调用
     */
    private static void test09() {
        CQueue obj = new CQueue();
        obj.appendTail(12);
        obj.appendTail(45);
        obj.appendTail(23);
        System.out.println(obj.deleteHead());
        System.out.println(obj.deleteHead());
        System.out.println(obj.deleteHead());
    }

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * <p>
     * 例如，给出
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     * <p>
     * #            3
     * #           / \
     * #          9  20
     * #            /  \
     * #           15   7
     * <p>
     * 限制：
     * 0 <= 节点个数 <= 5000
     */
    private static void test07() {
        TreeNode tree = buildTree(new int[]{1, 2}, new int[]{1, 2});

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(tree);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            System.out.println(node.val);
        }
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        int length = preorder.length;
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = buildTree(0, 0, length - 1, indexMap, preorder);
        return root;
    }

    public static TreeNode buildTree(int preRootIndex, int inorderStart, int inorderEnd, Map<Integer, Integer> indexMap, int[] preorder) {
        if (inorderStart > inorderEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preRootIndex]);
        int inRootIndex = indexMap.get(preorder[preRootIndex]);
        if (preRootIndex + 1 < preorder.length) {
            root.left = buildTree(preRootIndex + 1, inorderStart, inRootIndex - 1, indexMap, preorder);
        }
        if (preRootIndex + (inRootIndex - inorderStart) + 1 < preorder.length) {
            root.right = buildTree(preRootIndex + (inRootIndex - inorderStart) + 1, inRootIndex + 1, inorderEnd, indexMap, preorder);
        }
        return root;
    }

    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     * <p>
     * 示例 1：
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     * <p>
     * 限制：
     * 0 <= 链表长度 <= 10000
     */
    private static void test06() {
        ListNode head = new ListNode(1);
        ListNode next = new ListNode(3);
        head.next = next;
        next.next = new ListNode(2);
        int[] result = reversePrint(head);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public static int[] reversePrint(ListNode head) {
        Stack<Integer> list = new Stack<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int[] result = new int[list.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = list.pop();
        }
        return result;
    }

    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * <p>
     * 示例 1：
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     * <p>
     * 限制：
     * 0 <= s 的长度 <= 10000
     */
    private static void test05() {
        System.out.print(replaceSpace("We are happy."));
    }

    private static String replaceSpace(String s) {
        return s.replaceAll("\\s", "%20");
    }

    /**
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * <p>
     * 示例:
     * 现有矩阵 matrix 如下：
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     * 给定 target = 20，返回 false。
     * <p>
     * 限制：
     * 0 <= n <= 1000
     * 0 <= m <= 1000
     */
    private static void test04() {
        System.out.println(findNumberIn2DArray(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 20));
    }

    private static boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length > 0 && matrix[i][0] <= target && matrix[i][matrix[i].length - 1] >= target) {
                int[] row = matrix[i];
                int cLength = row.length;

                // 二分查找
                int left = 0;
                int right = cLength - 1;

                while (left <= right) {
                    int mid = (right + left) / 2;
                    if (row[mid] == target)
                        return true;
                    else if (row[mid] < target)
                        left = mid + 1;
                    else if (row[mid] > target)
                        right = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * 找出数组中重复的数字。
     * <p>
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * <p>
     * 示例 1：
     * 输入：
     * [2, 3, 1, 0, 2, 5, 3]
     * 输出：2 或 3
     * <p>
     * 限制：
     * <p>
     * 2 <= n <= 100000
     */
    private static void test03() {
        System.out.println(findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
    }

    public static int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) return nums[i];
        }
        return 0;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
        }

        public ListNode(ListNode next, int val) {
            this.val = val;
            this.next = next;
        }
    }

    static class CQueue {
        Stack<Integer> s1, s2;

        public CQueue() {
            s1 = new Stack<>();
            s2 = new Stack<>();
        }

        public void appendTail(int value) {
            s1.push(value);
        }

        public int deleteHead() {
            if (s2.empty()) {
                while (!s1.empty()) {
                    s2.push(s1.pop());
                }
            }
            if (s2.empty()) {
                return -1;
            } else {
                return s2.pop();
            }
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}