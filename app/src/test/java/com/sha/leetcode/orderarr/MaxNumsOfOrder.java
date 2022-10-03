package com.sha.leetcode.orderarr;

import org.junit.Test;

/**
 * 对arr一个子数组排序,但让arr整体有序返回满足设定的子数组最短的多长
 * <p>
 * 这个题目我感觉是技巧，我完全想不到这种搞法。
 * O(n)
 * 从左到右遍历一次，index指向的位置之前所有的元素中最大的那个 max ，和index位置上的比较，如果 index位置上的大些那么就打对勾，如果小些，就画x。
 * index右移一位，然后更新max。然后重复这种判断。
 * <p>
 * 从右到左遍历一次，index指向的位置之后的所有的元素中最小的那个 min，和index位置上的比较，如果index位置上的大些那么打x，如果小些，就打对勾。
 * index左移一位，然后更新min。然后重复这种操作。
 * <p>
 * 最后看最两端的x的索引。围起来的就是最短的需要调整的范围。
 */
public class MaxNumsOfOrder {

    @Test
    public void test() {
        int[] arr = {1, 2, 4, 3, 5, 6, 7}; // 期待的是2
        int maxNumOfSubArr = getMaxNumOfSubArr(arr);
        System.out.println("sha " + maxNumOfSubArr);
    }

    private int getMaxNumOfSubArr(int[] arr) {
        int ans = 0;

        int len = arr.length;

        int l = -1;
        int r = -1;

        // 先从左到右遍历
        int index = 1;
        int temp = arr[0];
        while (index < len) {
            if (temp > arr[index]) {
                r = index;
            } else {
                temp = arr[index]; // 更新max。这个我竟然忘记了。照着中文思路些都写忘记了
            }
            index++;
        }

        // 再从右往左遍历
        index = len - 2;
        temp = arr[len - 1];
        while (index >= 0) {
            if (temp < arr[index]) {
                l = index;
            } else {
                temp = arr[index]; // 更新min。这个我竟然忘记了。照着中文思路些都写忘记了
            }
            index--;
        }
        return r - l + 1;
    }
}
