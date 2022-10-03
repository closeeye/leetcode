package com.sha.leetcode.hashmap;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 小写字母组成字符串,放在数组中,某两个字符串所含的字符类完全一样,返回arr有多少类
 * <p>
 * 这个题目比较简单。
 * 找到了每个字符串的摘要，摘要一样的，归为一类。
 * <p>
 * 这里用 int类型的数字作为 字符串的摘要。
 * <p>
 * 这一题也有点 bit题录的影子。
 */
public class StringsClassify {
    @Test
    public void test() {
        int classify = classify(new String[]{"aab", "ab", "c"});
        System.out.println("sha " + classify);
    }


    private int classify(String[] strings) {
        Set<Integer> ans = new HashSet();
        int length = strings.length;
        int i = 0;
        while (i < length) {
            char[] chars = strings[i].toCharArray();
            int temp = 0;
            for (int index = 0; index < chars.length; index++) {
                temp |= (1 << (chars[index] - 'a')); // 【卡壳点1】应该是 |=。不是 +=
            }
            ans.add(temp);
            i++;
        }
        return ans.size();
    }
}
