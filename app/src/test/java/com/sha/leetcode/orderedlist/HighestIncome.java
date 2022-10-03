package com.sha.leetcode.orderedlist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 【总结1】就是有序表排序
 * 【总结2】floorKey这个我今天第一次用。小于等于当前key的那个key
 * <p>
 * <p>
 * 给定hard和money数组。hard是任务的难度，money是对应的工资。 两个数组一样长。
 * 给定ability数组，长度为M。ability[j]表示j号人的能力。
 * 每一号工作都有无限多个岗位，难度和收入都一样。
 * 但是人的能力必须 >= 这份工作的难度，才能正常上班。
 * <p>
 * <p>
 * 诉求：返回ans[]，长度是M, ans[j]表示j号人能得到的最高收入。
 * <p>
 * 这个题目就是一个排序题目。-- 【难也难在这里。思路很简单，但是我转化不成代码。】
 * 先按照难度把hard数组排序。
 * 然后同一级别难度中的保留收入最高的。其他的删除。
 * 1 1
 * 3 2
 * 不同级别的，如果级别更高的收入还不如级别低的，也删除
 * 2
 * 2
 * <p>
 * 最后得到一个集合中的元素的hard 和 money都是单调递增的。
 * 然后只要根据每个人的ability找到小于等于ability又最接近的hard对应的money就是的了。
 */
public class HighestIncome {

    @Test
    public void test() {
        int[] hard = new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3};
        int[] money = new int[]{9, 7, 3, 10, 8, 6, 7, 6, 5};
        int[] ability = new int[]{2, 3, 4};// 结果应该是10 10 10
        int[] ans = highestIncome(hard, money, ability);
        System.out.println("sha " + ans[0] + " " + ans[1] + " " + ans[2]);
    }

    private int[] highestIncome(int[] hard, int[] money, int[] ability) {
        int[] ans = new int[ability.length];
        // 先把hard和money两个数组绑定起来。方便根据hard排序后，money也跟着走了。
        int len = hard.length;
        Job[] job = new Job[len];
        for (int i = 0; i < len; i++) {
            job[i] = new Job(hard[i], money[i]);
        }
        Arrays.sort(job, new MyCompare());

        // 删除这部分我不会了。 我记得老师用了treemap。但是我完全不知道怎么弄的了。
        // 【细节1】把工作一份一份的存入treemap中。 为什么放入treemap中需要先排序呢?
        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
        treeMap.put(job[0].hard, job[0].money);
        Job pre = job[0]; // 第一份工作肯定要，这是难度最低，并且是在难度最低档中最赚钱的。
        for (int i = 1; i < len; i++) {
            // 如果hard一样，money高的留下。如果hard高，且money也高的留下
            if (job[i].hard != pre.hard && job[i].money > pre.money) {
                pre = job[i];
                treeMap.put(pre.hard, pre.money);
            }
        }

        // 遍历每个人的能力，找到每个人能胜任的最赚钱的工作
        int lenAbility = ability.length;
        for (int j = 0; j < lenAbility; j++) {
            Integer key = treeMap.floorKey(ability[j]);// The floorKey() method is used to return the greatest key less than or equal to given key from the parameter.
            ans[j] = treeMap.get(key);
        }

        return ans;
    }

    class Job {
        public int hard;
        public int money;

        public Job(int hard, int money) {
            this.hard = hard;
            this.money = money;
        }
    }

    class MyCompare implements Comparator<Job> {

        @Override
        public int compare(Job job1, Job job2) {
            // 这里的排序我还漏掉了一个【细节】。hard一样，money最多的排在前面
            return (job1.hard != job2.hard) ? ((job1.hard - job2.hard)) : (job2.money - job1.money);
        }
    }
}
