package com.sha.leetcode.slidewindow;

import org.junit.Test;

public class RopeCoverPointers {
    @Test
    public void testRopeCoverPointers() {
        //{1, x, 3, x, 5, 6, 7, 8, 9, x, 11}
        // 3     4     5  3  4  3  2     1
        int[] arr = new int[]{1, 3, 5, 6, 7, 8, 9, 11};
        int maxPointers = getMaxPointers(arr, 4);

        // 理论上应该是
        System.out.println("sha maxPointers: " + maxPointers);
    }

    /**
     * 方案1：两个for循环。【N^2】
     * 方案2：让绳子的末端总是压着一个点，索引是end。顺序是arr中的索引顺序。然后在index之前的部分二分查找 arr[index]-k所在的位置 start。
     * 然后，end - start + 1.得到的就是以index点为末端，能压住的点的个数。 【NlogN】
     * 方案3：用滑动窗口【N】
     * 【卡壳点1】窗口滑动到最后出现窗口中的元素个数不够k个，不知道怎么处理
     *
     * @param arr
     * @param k
     * @return
     */
    private int getMaxPointers(int[] arr, int k) {
        int ans = 0;
        int l = 0;
        int r = 0;
        // l r先都停在0号位置，r往右边一步一步的滑动，先形成一个长度为k的slidewindow
        int len = arr.length;
        while (l <= r && r < len) {
            if (arr[r] - arr[l] < k) { // 窗口还没有形成 r要继续往右边走
                r++;
            } else if (arr[r] - arr[l] == k) { // 窗口刚刚形成。a) 收集ans。b) l++
                ans = Math.max(r - l + 1, ans);
                l++;
            } else if (arr[r] - arr[l] > k) { // r-- 窗口还没有爆掉， r 窗口宽度就爆掉了
                ans = Math.max(r - l, ans);
                l++;
            }
        }
        return ans;
    }
}
