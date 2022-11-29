package com.sleepyocean.exercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 刷题 - 热门 100
 **/
public class Top100Interview {

    public static void main(String[] args) {
        test1();
    }

    /**
     * 1. 两数之和
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * <p>
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] numSum(int[] nums, int target) {
        int[] index = new int[2];

        List<Integer> numList = Arrays.stream(nums).boxed().collect(Collectors.toList());

        for (int i = 0; i < numList.size(); i++) {
            if (numList.contains(target - numList.get(i)) && numList.indexOf(target - numList.get(i)) != i) {
                index[0] = i;
                index[1] = numList.indexOf(target - numList.get(i));
                return index;
            }
        }
        return index;
    }

    private static int[] numSumHashWay(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            if (numMap.containsKey(target - nums[i]) && numMap.get(target - nums[i]) != i) {
                return new int[]{numMap.get(target - nums[i]), i};
            }
            numMap.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    private static void test1() {
        int[] index = numSumHashWay(new int[]{3, 2, 3}, 6);
        System.out.println(String.format("[%s, %s]", index[0], index[1]));
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length != 0) {
            int m = nums2.length % 2;
            if (m == 0) {
                return Double.parseDouble(Integer.toString(nums2[m - 1] + nums2[m])) / 2;
            } else {
                return nums2[m];
            }
        } else if (nums2.length == 0 && nums1.length != 0) {
            int m = nums1.length % 2;
            if (m == 0) {
                return Double.parseDouble(Integer.toString(nums1[m - 1] + nums1[m])) / 2;
            } else {
                return nums1[m];
            }
        } else if (nums1.length == 0 && nums2.length == 0) {
            return 0;
        }
        int m1 = nums1[0], m2 = nums2[0], l1 = 0, l2 = 0;
        int count = 0;
        int size1 = nums1.length, size2 = nums2.length;
        int mid = (size1 + size2) / 2;
        boolean even = (size1 + size2) % 2 == 0;
        while (count < mid) {
            if (nums1[l1] < nums2[l2] && l1 < size1 - 1) {
                l1++;
                m1 = m2;
                m2 = nums2[l2];
            } else if (nums1[l1] >= nums2[l2] && l2 < size2 - 1) {
                l2++;
                m1 = m2;
                m2 = nums1[l1];
            } else if (l2 == size2 - 1 && nums1[l1] >= nums2[l2]) {
                l1++;
                m1 = m2;
                m2 = nums1[l1];
            } else if (l1 == size1 - 1 && nums1[l1] < nums2[l2]) {
                l2++;
                m1 = m2;
                m2 = nums2[l2];
            } else {
                System.out.println("other");
            }
            count++;
        }
        if (even) {
            return Double.parseDouble(Integer.toString(m1 + m2)) / 2;
        } else {
            if (nums1[l1] > nums2[l2]) {
                return nums1[l1];
            } else {
                return nums2[l2];
            }
        }
    }

    private static void test4() {
        System.out.println(findMedianSortedArrays(new int[]{1, 3, 8}, new int[]{4, 5, 7}));
    }

    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * <p>
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * <p>
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * @param s
     * @return
     */
    private static int lengthOfLongestSubstring(String s) {
        int max = s.length() == 0 ? 0 : 1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (j == s.length() || s.substring(i, j).contains(s.charAt(j) + "")) {
                    int length = j - i;
                    max = Math.max(max, length);
                    break;
                }
            }
        }
        return max;
    }

    private static int lengthOfLongestSubstringBySlideWindow(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        int n = s.length();

        int res = 0;
        // 窗口开始位置
        int start = 0;
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index]);
            res = Math.max(res, i - start + 1);
            last[index] = i + 1;
        }
        return res;
    }

    private static void test3() {
        System.out.println(lengthOfLongestSubstring("au"));
    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(1);
        boolean flag = false;
        int val = 0;

        ListNode current = head;
        do {
            val = (null != l1 ? l1.val : 0) + (null != l2 ? l2.val : 0) + (flag ? 1 : 0);
            if (val > 9) {
                flag = true;
                val = val - 10;
            } else {
                flag = false;
            }
            current.next = new ListNode(val);
            current = current.next;
            l1 = null != l1 ? l1.next : null;
            l2 = null != l2 ? l2.next : null;
        } while (null != l1 || null != l2);

        if (flag) {
            current.next = new ListNode(1);
        }
        return head.next;
    }

    /**
     * 1 -> 8
     * 0
     */
    private static void test2() {
        ListNode l1 = new ListNode(1);
        l1.next = null;
        int[] num1 = new int[]{8};

        for (int i = 0; i < num1.length; i++) {
            ListNode.addNode(l1, num1[i]);
        }

        ListNode l2 = new ListNode(0);
        l2.next = null;

        ListNode head = addTwoNumbers(l1, l2);
        while (null != head) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    /**
     * 2. 两数相加
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 示例：
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public static void addNode(ListNode head, int d) {
            ListNode newNode = new ListNode(d);// 实例化一个节点
            if (head == null) {
                head = newNode;
                return;
            }
            ListNode tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = newNode;
        }
    }

}