package com.sha.leetcode.twodimensionsarr;

import org.junit.Test;

/**
 * 【总结1】考核我会不会枚举所有的正方形。N^2 * N. 考核我会不会用技巧验证正方形是不是满足条件。
 * <p>
 * [[1,1,1],
 * [1,0,1],
 * [1,1,1]]
 * return the number of elements in the largest square subgrid
 * 返回最大方形子网格中的元素数
 * square的边框上都是1.
 * <p>
 * <p>
 * 枚举所有的正方形。然后验证每个正方形是不是满足条件的。
 * 在m高 n宽的 grid中。我选择两个点的组成一个 renctgle 。一起有几个矩形 m*n * m*n种可能
 * 在一个N*N的grid中。先点一个点有 N^2 种可能。长方形的长度0-N.所以，最后是 N^3种可能。
 * <p>
 * <p>
 * 【time complex】 O(N^3)
 */
public class Largest1BorderedSquare1139 {
    @Test
    public void test() {
        int[][] grid = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] grid2 = {{1,1,0,0}};
        int[][] grid3 = {{0}};
        int[][] grid4 = {{1,1},{1,0}}; // 1
        int ans = largest1BorderedSquare(grid);
        System.out.println("sha " + ans);
    }


    public int largest1BorderedSquare(int[][] grid) {
        int width = grid[0].length;
        int height = grid.length;

        int ans = 0;
        // 准备好两个等规模的二维数组。分别记录的是从该点起向右有几个连续的1.  从该点起向下有几个连续的1
        int[][] right = new int[height][width];
        int[][] down = new int[height][width];
        // 我选择了从下往上，从右往左的遍历方式
        for (int i = height - 1; i >= 0; i--) {
            for (int j = width - 1; j >= 0; j--) {
                if (grid[i][j] == 0) { //自己是0。那么从自己开始向下和向右的连续的1的个数就算0.
                    right[i][j] = 0;
                    down[i][j] = 0;
                } else {
                    right[i][j] = (j + 1) >= width ? 1 : right[i][j + 1] + 1;
                    down[i][j] = (i + 1) >= height ? 1 : down[i + 1][j] + 1;
                    ans = 1; // 证明至少有一个1
                }
            }
        }

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                int leftTopValue = grid[h][w]; // 这个点就是正方形左上角的点。所有的都枚举出来了。
                // 以 （h,w）这个点作为 正方形的左上点。看正方形的宽度能延申到多长。
                int maxWidth = Math.min(width - w, height - h); // 这个 maxWidth 就是 矩阵的对角线中有几个点
                for (int j = 1; j < maxWidth; j++) { // 从1开始，那就是从有4个元素的矩阵开始。j就是矩阵的对角线中有几个线段。 这里没有取 =maxWidth的情况。
                    // 长方形的左上角的点确定了，边长也确定了。如果这里还要for循环正方形的四个边，检查是否都是1那么就不是
                    // N^3了。
                    // 如果能直接查到 这个正方形是不是满足条件的。那就能做到 验证 O(1)
                    // 左上点(h,w) 左下点(h+j,w) 右上点(h,w+j).检查这三个点的右边下边 长度为j的线条是否 < 能查到的线条长度。
                    // 线条都是1组成的。

                    // 【卡壳点1】我这里开始把 = 也加上了。导致出错。为什么 = 不行？？？ 对角线上连了j条线段，那么一起连接的点的个数肯定是 j+1. right down表中存的多是点的个数。
                    // 这个细节很容易被忽视啊.  我主要是没有理解透,j在这里的含义.导致我加了 =
                    if (right[h][w] > j && down[h][w] > j && right[h + j][w] > j && down[h][w + j] > j) {
                        ans = Math.max(ans, (j+1) * (j+1));
                    }
                }
            }
        }
        return ans;
    }
}
