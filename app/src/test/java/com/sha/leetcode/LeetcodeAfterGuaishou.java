package com.sha.leetcode;

import org.junit.Test;

public class LeetcodeAfterGuaishou {
    @Test
    public void test() {

    }


    /**
     * arr长度也不大
     * m比较小的情况下用的dp
     *
     * @param arr
     * @param m
     * @return
     */
    public static int max3(int[] arr, int m) {
        int N = arr.length;
        // 任何一个数 %m后 的取值一定是 【0..m-1】
        boolean[][] dp = new boolean[N][m];
        for (int i = 0; i < N; i++) { // dp表左边的第一列都是true。余数是0.我一个都不选的时候，余数就是0
            dp[i][0] = true;
        }
        dp[0][arr[0]%m] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < m; j++) {
                // dp[i][j] t or f
                dp[i][j] = dp[i -1][j];
                int cur = arr[i] % m;
                if (cur <= j) {
                    dp[i][j] |= dp[i - 1][j - cur];
                } else {
                    dp[i][j] |= dp[i - 1][m + j - cur];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) { // dp表最后一行，从左到右 遇到的第一个true 就是 ans
            if (dp[N-1][i]) {
                ans = i;
            }
        }
        return ans;
    }

    /**
     * arr[index] 巨大
     * m 巨大
     * arr.length 30以内
     *
     * 如果我从左到右，针对每个位置上的数，我可以选择要 或者 不要。最后展开就是2的30次方，等于 10的9次方。超过了10的8次方。
     *
     * 考虑把这30个数分成两组，左边15个右边15个。就变成了2的15次方 + 2的15次方 = 327868 .远远没有到10的8次方的规模
     */
//    public

}
