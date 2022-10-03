package com.sha.leetcode.dp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 【总结】考的就是分治
 * 本题的分治还是比较简单的，左边有2^20种子序列结果 ，右边也有 2^20种 子序列结果。
 * 结果可能仅仅来自左边
 * 可能仅仅来自右边
 * 可能同时来自两边。左边选一个结果，右边选一个结果。二者相加，得到一个新的结果很接近 goal
 * <p>
 * You are given an integer array nums and an integer goal.
 * <p>
 * You want to choose a subsequence of nums such that the sum of its elements is the closest possible to goal. That is, if the sum of the subsequence's elements is sum, then you want to minimize the absolute difference abs(sum - goal).
 * <p>
 * Return the minimum possible value of abs(sum - goal).
 * <p>
 * Note that a subsequence of an array is an array formed by removing some elements (possibly all or none) of the original array.
 * Example 1:
 * Input: nums = [5,-7,3,5], goal = 6
 * Output: 0
 * Explanation: Choose the whole array as a subsequence, with a sum of 6.
 * This is equal to the goal, so the absolute difference is 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 40
 * -10^7 <= nums[i] <= 10^7
 * -10^9 <= goal <= 10^9
 * <p>
 * 这个题目我想到了用背包问题。每个元素，我可以选择要或者不要。
 * <p>
 * 但是，dp搞不定。通过看数据量得知.
 * <p>
 * 标准是 不能超过10^8。超过了，就证明dp没有戏了。
 * <p>
 * 老师说 数组长度小 可以选择用 分治。 分治，恰巧是我的弱点。
 *
 * 【卡壳点1】我用了分治 还是超时了。 是哪里还可以优化呢？
 */
public class Leetcode1755ClosestSubsequenceSum {
    @Test
    public void test() {
        int[] nums = {5, -7, 3, 5};
        int ans = minAbsDifference(nums, 6);
        System.out.println("sha " + ans);
    }

    public int minAbsDifference(int[] nums, int goal) {
        if (nums.length == 1) {
            int p1 = Math.abs(nums[0] - goal);
            int p2 = Math.abs(0 - goal);
            return Math.min(p1, p2);
        }

        int half = (int) Math.ceil(nums.length / 2);
        HashSet<Integer> leftSet = new HashSet<>();
        HashSet<Integer> rightSet = new HashSet<>();
        process(nums, leftSet, half - 1, 0, 0);
        process(nums, rightSet, nums.length - 1, half, 0);

        // 在左侧找最佳答案
        int ans1 = findAnsFromSingleArea(leftSet, goal);
        // 在右侧找最佳答案
        int ans2 = findAnsFromSingleArea(rightSet, goal);

        // 在两边找最佳答案
        int ans3 = findAns(leftSet, rightSet, goal);

        return Math.min(Math.min(ans1, ans2), ans3);

    }

    /**
     * 返回的是 sumof子序列
     *
     * @param nums
     * @param i
     * @return
     */
    private void process(int[] nums, HashSet<Integer> set, int end, int i, int sum) {
        if (i == end + 1) { // 已经来到了最后一个位置的下一个位置了。是检查结果的时候了
            set.add(sum);
            return;
        }

        // i位置上的数要
        process(nums, set, end, i + 1, sum + nums[i]);
        // i位置上的数不要
        process(nums, set, end, i + 1, sum);
    }

    private int findAns(HashSet<Integer> leftSet, HashSet<Integer> rightSet, int goal) {
        // 从leftset中挑一个，从rightset中挑一个。二者相加得到sum。找sum最接近goal的那个。
        Iterator<Integer> iteratorLeft = leftSet.iterator();
        int ans = Integer.MAX_VALUE;
        while (iteratorLeft.hasNext()) {
            Integer left = iteratorLeft.next();
            Iterator<Integer> iteratorRight = rightSet.iterator();
            while (iteratorRight.hasNext()) {
                Integer right = iteratorRight.next();
                ans = Math.min(Math.abs(left + right - goal), ans);
            }
        }
        return ans;
    }

    private int findAnsFromSingleArea(HashSet<Integer> set, int goal) {
        int ans = Integer.MAX_VALUE;
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            ans = Math.min(Math.abs(iterator.next() - goal), ans);
        }
        return ans;
    }


}
