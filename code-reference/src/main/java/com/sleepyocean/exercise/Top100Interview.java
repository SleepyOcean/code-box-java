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

}