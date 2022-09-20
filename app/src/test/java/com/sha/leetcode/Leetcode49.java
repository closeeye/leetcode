package com.sha.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Leetcode49 {
    @Test
    public void test() {
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> ans = new Solution().groupAnagrams(input);
        System.out.println(ans); //[[eat, tea, ate], [bat], [tan, nat]]
    }

    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            // 【我的思路】我感觉这个题目就是kmp算法问题。
            // 我的这个思路可能不是一个很优秀的思路。但是也是一个思路。
            // 我可以先把我第一遇到的第一个item（eat）作为key，存入map中。
            // 然后依次遍历 tea，tan，...
            // 看 tea，tan,... 能不能作为eateat的子串。能匹配上的，我就直接加入到eat的value中。
            //                                       不能匹配上的，我就直接作为key存入map中。
            // 一轮下来，我就找到了能和 eat组成队的其他元素了。
            // 感觉总是怪怪的。有点乱。没有遍历一遍源数组strs就搞定的那种丝滑的感觉。

            // 【fuck】我的方向和推荐方案完全不沾边。推荐方案是用 map。
            // value是一个数组arr，存放的是可以放一起的items。
            // key是 arr的唯一标识。比如1：排好序的字符串。"eat","ate","tea"，排好序后都是"aet"
            // 比如2：计算用的字符们的对应数量。

            //【details1】题目中给出了关键信息，rearranging the letters of a different word or phrase
            // 意思就是，只有26个英文字母。 所以这是 想到得到相同key的两种方式的线索。

            //【卡壳点1】字符串排序感觉都不会了呀。


            HashMap<String, List<String>> ans = new HashMap<String, List<String>>();
            for (int i = 0; i < strs.length; i++) {
                String key = sortStr(strs[i]);
                if (ans.containsKey(key)) {
                    List<String> value = ans.get(key);
                    value.add(strs[i]);
                } else {
                    List<String> valueList = new ArrayList<String>();
                    valueList.add(strs[i]);
                    ans.put(key, valueList);
                }
            }
            return new ArrayList(ans.values());
        }

        private String sortStr(String str) {
            if (str == null || str.length() == 0) {
                return "";
            }
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            return String.valueOf(arr);
        }
    }

}
