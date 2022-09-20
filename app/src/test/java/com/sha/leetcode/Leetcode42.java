package com.sha.leetcode;

import org.junit.Test;

import java.io.*;
import java.util.*;

import androidx.annotation.NonNull;

/**
 * https://leetcode.com/problems/trapping-rain-water/ 42
 * 单调栈的题目
 */
public class Leetcode42 {

    @Test
    public void test() {
        int[] input = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int trap = new Solution().trap(input);
        System.out.println("sha " + trap);
    }


    class Solution {
        public int trap(int[] height) {
            // 完全没有思路呀。我少了什么，为什么会没有思路。暴力解的思路竟然都没有

            // 花了两个多小时看了stack方案后，终于理顺了 stack方案的实现。
            // 之前我遇到的 monatonic stack的题。都是 单调递增的。这个是第一个我遇到的单调递减的题目。
            // 不包头，不包尾。popIndex 右边第一个比较大 rightBigIndex，和左边第一个比较大 leftBigIndex。
            // min(rightBig, leftBig) - pop 着就是高度。
            // 宽度是 rightBigIndex - leftBigIndex - 1.
            int ans = 0;
            Stack<Integer> stack = new Stack<Integer>();
            for (int i = 0; i < height.length; i++) {
                if (stack.isEmpty()) {
                    stack.push(i);
                    continue;
                }

                while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                    // 需要pop收集结果了
                    int popIndex = stack.pop();
                    if (!stack.isEmpty()) {
                        int leftBigIndex = stack.peek();
                        ans += (i - leftBigIndex - 1) * (Math.min(height[leftBigIndex], height[i]) - height[popIndex]);
                    }

                }
                stack.push(i);
            }
            return ans;
        }
    }

}

