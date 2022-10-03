package com.sha.leetcode.numberofchange;

import org.junit.Test;

/**
 * 一个arr中只有BG两种字符。让G都在左边，B都在右边。
 * 只能相邻的两个元素进行交换，
 * 求：一起要交换几次
 */
public class BG {

    @Test
    public void testBG() {
        char[] chars = {'B', 'G', 'G', 'B', 'G'}; // 2次就够了
        int exchangeTimes = exchangeTimes(chars);
        System.out.println("sha " + exchangeTimes);
    }

    /**
     * O(N)
     *
     * @return
     */
    private int exchangeTimes(char[] chars) {
        int ans = 0;
        // l，r都先指向0位置的元素。l指向的是下一个G可以交换到的位置。r一步一步的往右走遇到了一个G就停下来记录一下结果
        int l = 0;
        int r = 0;
        int len = chars.length;
        while (l <= r && r < len) {
            if (chars[r] == 'G') {
                // 计算要交换的次数，不用真的交换
                ans += r - l;
                r++;
                l++;
            } else {
                r++;
            }
        }


        return ans;
    }


}
