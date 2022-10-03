package com.sha.leetcode.dp;

import org.junit.Test;

import java.util.Arrays;

public class Leetcode1755ClosestSubsequenceSum2 {
    @Test
    public void test() {
        int[] nums = {5, -7, 3, 5};
        int ans = minAbsDifference(nums, 6);
        System.out.println("sha " + ans);
    }

    public static int[] l = new int[1 << 20];
    public static int[] r = new int[1 << 20];

    public int minAbsDifference(int[] nums, int goal) {
        if (nums.length == 1) {
            int p1 = Math.abs(nums[0] - goal);
            int p2 = Math.abs(0 - goal);
            return Math.min(p1, p2);
        }

        int half = (int) Math.ceil(nums.length / 2);


        int le = process(nums, l, half - 1, 0, 0, 0);
        int re = process(nums, r, nums.length - 1, half, 0, 0);

        int ans = getAns(goal, le, re);

        return ans;

    }

    /**
     * 【总结】我用了分治还是超时，问题就出在这里。 整合分治得到的 左边区域的答案  和 右边区域的答案 用时太多。
     * 我用的是set集合。老师用的是 数组。------ 我就应该想到要用数组的。数组快呀。反正最长的数组长度也就为 2^20.
     * 老师还把数组排序了。
     *
     * @param goal
     * @param le
     * @param re
     * @return
     */
    private int getAns(int goal, int le, int re) {
        // 为什么要排序？？？
        Arrays.sort(l, 0, le);
        Arrays.sort(r, 0, re--);

        int ans = Math.abs(goal);
        for (int i = 0; i < le; i++) {
            int rest = goal - l[i];

            // TODO 这里还没有整明白.
            int j = 0;
            while (j < re) {
                if (Math.abs(rest - r[j + 1]) <= Math.abs(rest - r[j])) {
                    j++;
                }
                break;
            }


//            while (re > 0 && Math.abs(rest - r[re - 1]) <= Math.abs(rest - r[re])) {
//                re--;
//            }
            ans = Math.min(ans, Math.abs(rest - r[j]));
        }
        return ans;
    }

    /**
     * 【总结】这个递归没有写错
     * <p>
     * 返回的是 sumof子序列
     *
     * @param nums
     * @param i
     * @return
     */
    private int process(int[] nums, int[] set, int end, int i, int sum, int filling) {
        if (i == end + 1) { // 已经来到了最后一个位置的下一个位置了。是检查结果的时候了
            set[filling++] = sum;
            return filling;
        }

        // i位置上的数要
        filling = process(nums, set, end, i + 1, sum + nums[i], filling);
        // i位置上的数不要
        filling = process(nums, set, end, i + 1, sum, filling);
        return filling;
    }
}
