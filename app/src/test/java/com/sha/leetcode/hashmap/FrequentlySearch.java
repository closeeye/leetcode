package com.sha.leetcode.hashmap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 数组{3,2,2,3,1},查询{0,3,2}
 * 意思就是在数组里下标0-3这个范围上,有几个2?答案返回2
 * 假设给你一个数组 arr
 * 对这个数组的查询非常频繁,请返回所有查询的结果.
 * <p>
 * 查询很频繁,肯定就是要搞一个什么索引结构,让查询 O(1).
 * 此题,用hashmap 作为查询索引就行.
 */
public class FrequentlySearch {

    @Test
    public void test() {
//        int[] arr = {3, 2, 2, 3, 1};
//        int ans = findNumberOfTarget(arr, 0, 3, 2);
        System.out.println("sha ans ");
    }

    private int findNumberOfTarget(int[] arr, int start, int end, int target) {
        // 建立索引
        HashMap<Integer, ArrayList<Integer>> indexMap = new HashMap<>();
        int i = 0;
        int len = arr.length;
        while (i++ < len) {
            int num = arr[i];
            if (indexMap.containsKey(num)) {
                indexMap.get(num).add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                indexMap.put(num, list);
            }
        }

        // 查询
        ArrayList<Integer> indexes = indexMap.get(target);
        // 在indexes中找最接近start但是 又 >=start的.和 最接近end 又 <=end的.
        int l = findNum(indexes, start, end, target, true);
        int r = findNum(indexes, start, end, target, false);
        System.out.println("sha " + l + " " + r);
        return r - l + 1;
    }


    private int findNum(List<Integer> arr, int l, int r, int target, boolean left) {
        if (l == r) {
            if (arr.get(r) == target) {
                return l;
            } else {
                if (left) {
                    return l++;
                } else {
                    return l--;
                }
            }
        }
        int mid = l + (r - l) >> 2;
        Integer midValue = arr.get(mid);
        int p = -1;
        if (target > midValue) {
            p = findNum(arr, mid, r, target, left);
        } else if (target < midValue) {
            p = findNum(arr, 0, mid, target, left);
        } else {
            return mid;
        }
        return p;
    }
}
