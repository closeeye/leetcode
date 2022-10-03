package com.sha.leetcode.slidewindow;

import org.junit.Test;

import java.util.Arrays;

/**
 * 【总结1】这个题目还让我 复习了一把 什么是 全排列。
 * 【总结2】为什么要先排序。
 * [3,1,5,7] k=2
 * 3，1 比一场。
 * 5，7 比一场。
 * 如果 3，5 比了一场，1，7 就没法比了。
 * 这就是先排序的意义，先把小的先安排了。避免出现 更强的对更弱的。
 *
 *
 * <p>
 * 给一个arr。代表每个人的能力值。再给定一个非负k
 * 如果两个人的能力刚好相差k，这两个人就能组成1场比赛。
 * 一局比赛只有两个人。
 * 一起最多能组成多少场比赛？
 * <p>
 * <p>
 * <p>
 * 第一次听这个题的时候，我想到了arr要先排序，然后用滑动窗口。
 * 关键点我都想到了。我也不知道我怎么想到的。感觉只是把我会的往上面套。
 * 但是呢，细节我就想不清楚了。
 * <p>
 * <p>
 * <p>
 * 这个题目的暴力解我都想不出来。这是怎么了？
 * a b c d
 * 4*3 = 12
 * a
 * b    ab
 * c    ac
 * d    ad
 * b
 * a    ba
 * c    bc
 * d    bd
 * c
 * a    ca
 * b    cb
 * d    cd
 * d
 * a    da
 * b    db
 * c    dc
 * 什么是全排列？？ 从3个数中选3个数重新组合排列。3*2*1 =6。这就是全排列
 * 全排列出来了。最后的组成的赛场肯定都在这个全排列中。
 * 终于理解这个暴力的方法了。
 * 全排列一个一个的试，看是不是能力差为k。是的，就证明找到了一组。
 * 再搞一个set集合，已经比赛了的，就不能重新比了。帮助过滤。
 * 【问题1】代码上如何实现 全排列呢？ for循环套for循环
 * <p>
 * 【时间复杂度】不是O(N)。是O(N*logN)
 */
public class TwoTwoCompete {
    @Test
    public void test() {

    }

    public int maxPairNum(int[] arr, int k) {
        Arrays.sort(arr);
        int N = arr.length;
        int l = 0;
        int r = 0;

        int ans = 0;
        // 搞一个等长的boolean的数组，标记，哪些位置用过了，哪些位置没用过
        boolean[] usedArr = new boolean[N];

        while (r < N && l < N) { // 【问题1】会出现 l跑到r右边去的情况吗？因该不会啊。对的，就是不会出现。
            // 如果l使用过了，l++。r不会出现遇到已经使用过的情况

            // l，r指向同一个人，一个人没法比赛。r右移一位。

            // r的能力 - l的能力 == k。比赛。ans+1. l和r都右移一位。

            // r的能力 - l的能力 > k. l右移一位。

            // r的能力 - l的能力 < k. r右移一位。
        }
        return ans;
    }
}
