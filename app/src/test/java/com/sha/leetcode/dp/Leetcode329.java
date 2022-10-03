package com.sha.leetcode.dp;

import org.junit.Test;

import java.util.HashMap;

/**
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 * <p>
 * From each cell, you can either move in four directions: left, right, up, or down.
 * You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 * <p>
 * [[9,  9,  4],
 * [6,  6,  8],
 * [2,  1,  1]]
 * 1269
 * <p>
 * expected:4
 *
 *
 * 【总结1】加傻缓存用hashmap很好加啊。相关的元素之间用 ”_“连接起来做 key 就行。
 */
public class Leetcode329 {

    @Test
    public void test() {
        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        int longestIncreasingPath = longestIncreasingPath(matrix);
        System.out.println("sha " + longestIncreasingPath);
    }

    /**
     * 分别从二维数组中的每一个元素出发，看最长的 increase path是多长。
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        int ans = 1;
        HashMap<String, Integer> map = new HashMap<>();
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                ans = Math.max(longestIncreasingPathWithIndexXY(matrix, map, x, y), ans);
            }
        }
        return ans;
    }



    /**
     * 加傻缓存
     *
     * @param matrix
     * @param x
     * @param y
     * @return
     */
    private int longestIncreasingPathWithIndexXY(int[][] matrix, HashMap<String, Integer> memo, int x, int y) {
        // 走出去了就是0
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) {
            return 0;
        }

        if (memo.containsKey(x + "_" + y)) {
            return memo.get(x + "_" + y);
        }

        // 在（x，y）位置可以上下左右四个方向走，也就是有4个选择。
        // 那么我就每个方向都尝试一下，最后肯定能找到最长的那个选择。

        // up
        int p1 = 0;
        if (x > 0 && matrix[x][y] < matrix[x - 1][y]) { // 上面的那个数要真的大于自己才试一下
            p1 = longestIncreasingPathWithIndexXY(matrix, memo, x - 1, y);
        }
        // down
        int p2 = 0;
        if (x + 1 < matrix.length && matrix[x][y] < matrix[x + 1][y]) {
            p2 = longestIncreasingPathWithIndexXY(matrix, memo, x + 1, y);
        }
        // left
        int p3 = 0;
        if (y > 0 && matrix[x][y] < matrix[x][y - 1]) {
            p3 = longestIncreasingPathWithIndexXY(matrix, memo, x, y - 1);
        }

        // right
        int p4 = 0;
        if (y + 1 < matrix[0].length && matrix[x][y] < matrix[x][y + 1]) {
            p4 = longestIncreasingPathWithIndexXY(matrix, memo, x, y + 1);
        }
        int res = Math.max(Math.max(p1, p2), Math.max(p3, p4)) + 1;
        memo.put(x + "_" + y, res);
        return res;
    }


    /**
     * brute force。 timeout
     *
     * @param matrix
     * @param x
     * @param y
     * @return 1 + 后继中获得的最长path。1 是当前（x,y）这个点
     */
    private int longestIncreasingPathWithIndexXY1(int[][] matrix, int x, int y) {
        // 走出去了就是0
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) {
            return 0;
        }

        // 在（x，y）位置可以上下左右四个方向走，也就是有4个选择。
        // 那么我就每个方向都尝试一下，最后肯定能找到最长的那个选择。

        // up
        int p1 = 0;
        if (x > 0 && matrix[x][y] < matrix[x - 1][y]) { // 上面的那个数要真的大于自己才试一下
            p1 = longestIncreasingPathWithIndexXY1(matrix, x - 1, y);
        }
        // down
        int p2 = 0;
        if (x + 1 < matrix.length && matrix[x][y] < matrix[x + 1][y]) {
            p2 = longestIncreasingPathWithIndexXY1(matrix, x + 1, y);
        }
        // left
        int p3 = 0;
        if (y > 0 && matrix[x][y] < matrix[x][y - 1]) {
            p3 = longestIncreasingPathWithIndexXY1(matrix, x, y - 1);
        }

        // right
        int p4 = 0;
        if (y + 1 < matrix[0].length && matrix[x][y] < matrix[x][y + 1]) {
            p4 = longestIncreasingPathWithIndexXY1(matrix, x, y + 1);
        }
        return Math.max(Math.max(p1, p2), Math.max(p3, p4)) + 1;
    }


}
