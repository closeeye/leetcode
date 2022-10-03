package com.sha.leetcode.bit;

import org.junit.Test;

public class IntNNearestSomePowerOf2 {
    @Test
    public void test() {
        int ans = IntNNearestSomePowerOf2(120);
        System.out.println("sha " + ans);
    }


    /**
     * 入参：int N
     * 诉求：找到 >=N 且最近N的的一个 2^x 。  x就是答案
     * <p>
     * <p>
     * <p>
     * 第二次看到这个题，我想要了要把N搞成二进制的样子。bN 1000 0000 0000 0000 0000 0000 0000 0000
     * <p>
     * 找到 bN 中最左边的一个1，然后让这个1先右移一位。1100 0000 0000 0000 0000 0000 0000 0000 【1】
     * 1111 0000 0000 0000 0000 0000 0000 0000 【2】
     * 1111 1111 0000 0000 0000 0000 0000 0000 【4】
     * 1111 1111 1111 1111 0000 0000 0000 0000 【8】
     * 1111 1111 1111 1111 1111 1111 1111 1111 【16】
     * int类型只有32位。移动 1 2 4 8 16 就行了
     * 还有一个技巧，N先要被减1，求到了最接近n-1的 数ans后，最后让ans+1. 【这个我肯定是想不到的】
     *
     * @return
     */
    private int IntNNearestSomePowerOf2(int N) {
        int ans = -1;
        // 都不需要提取左边的第一个1. 直接一上来就开始右移就行了。
        N--;
        N |= N >>> 1;
        N |= N >>> 2;
        N |= N >>> 4;
        N |= N >>> 8;
        N |= N >>> 16;

        return N < 0 ? 1 : ans + 1;
    }
}
