package com.sha.leetcode.dp;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/target-sum/ 494
 * <p>
 * You are given an integer array nums and an integer target.
 * <p>
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
 * <p>
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
 * Return the number of different expressions that you can build, which evaluates to target.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 20  这个一看 就知道 从左到右模型的 +or- 会解决不了。老师说过2^16 还是2^18次方才有戏来着。我忘记了。
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 */
public class Leetcode494 {
    @Test
    public void test() {
//        Input: nums = [1,1,1,1,1], target = 3
//        Output: 5
        int[] ints = {1, 1, 1, 1, 1};
        int targetSumWays = findTargetSumWays2(ints, 3);
        System.out.println("sha " + targetSumWays);
    }


    /**
     * 从左到右模型。
     * <p>
     * 每个位置元素之前加+ 或者-。展开。2^arr.length
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays1(int[] nums, int target) {
        return process1(nums, 0, target);
    }

    /**
     * 这个直接提交，竟然通过了。我的神啊。
     *
     * @return
     */
    private int process1(int[] nums, int index, int target) {
        if (index == nums.length) {
            return target == 0 ? 1 : 0;
        }

        // index位置上 用 +. 后续返回的满足条件的ways
        int p1 = process1(nums, index + 1, target - nums[index]);
        // index位置上 用 -. 后续返回的满足条件的ways
        int p2 = process1(nums, index + 1, target + nums[index]);
        return p1 + p2;
    }


    /**
     * 加入傻缓存. --- 我一开始还想用dp[][]二维数组来加傻缓存，实际上用hashmap加傻缓存就行。
     *
     * 收益：1168ms 到 245ms
     * 09/26/2022 21:05	Accepted	245 ms	42.9 MB	java
     * 09/26/2022 20:04	Accepted	1168 ms	41.3 MB	java
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays2(int[] nums, int target) {
        HashMap<String, Integer> map = new HashMap<>(); // 得到了所有元素的和，没有哪一个元素是负数
        return process2(nums, 0, target, map); // 数组长度就是vertical axis，sum的两倍就是 horizontal axis
    }

    private int process2(int[] nums, int index, int target, HashMap<String, Integer> memo) {
        if (index == nums.length) {
            return target == 0 ? 1 : 0;
        }

        if (memo.containsKey(index + "_" + target)) {
            return memo.get(index + "_" + target);
        }

        // index位置上 用 +. 后续返回的满足条件的ways
        int p1 = process2(nums, index + 1, target - nums[index], memo);
        // index位置上 用 -. 后续返回的满足条件的ways
        int p2 = process2(nums, index + 1, target + nums[index], memo);
        memo.put(index + "_" + target, p1 + p2);
        return p1 + p2;
    }






}
