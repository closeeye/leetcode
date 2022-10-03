package com.sha.leetcode;

import org.junit.Test;

/**
 * 打怪兽问题。
 * 可以贿赂怪兽，贿赂要花钱，花钱后我的钱减少，我的能力加上怪兽的ability。就是我手上还有的钱。
 * 也可以直接忽略某个能力比我弱的怪兽，我的钱和能力都不变。
 * 两个数组 d 怪兽的能力，  p 消灭怪兽要花的钱
 * 求从左到右，花最少的钱通关。
 * 最开始我的ability是0.
 * 【能不能改动态规划】要看数据规模，如果数据规模是 怪兽100个，贿赂怪兽和怪兽吐出来的钱也就是10以内，那么改动态规划是有价值的。
 * 【根据题目的数据范围 猜解法】 作为dp维度的变量的取值范围要尽量小一点，让dp表能放下。 规模是10的8次方之内都是能放下的。
 */
public class LeetcodeGuaishou {

    @Test
    public void test() {

    }

    /**
     * 解决方案1
     * 这个就是一个动态规划吧。
     *
     * @param d
     * @param p
     * @return
     */
    public static long func1(int[] d, int[] p) {
        return process(d, p, 0, 0);
    }

    /**
     * 从左到右打怪兽。
     * 目前，你的能力是 ability，你来到了index号怪兽面前，如果要通过后续所有的怪兽，请返回需要花的最少钱数
     * <p>
     * 这种是 如果怪兽的ability取值范围比较小，就用 ability 和 index作为dp的两个维度
     *
     * @param d
     * @param p
     * @param ability
     * @param index
     * @return 返回我花掉的钱
     */
    public static long process(int[] d, int[] p, int ability, int index) {
        if (index == d.length) {
            return 0;
        }
        if (ability < d[index]) { // index位置上的怪兽能力比我强，这个钱我一定要花
            return p[index] + process(d, p, ability + d[index], index + 1);
        } else { // 我的ability 比d[index]大，我就有选择了，a）直接过 b）贿赂
            return Math.min(
                    process(d, p, ability, index + 1),
                    p[index] + process(d, p, ability + d[index], index + 1));
        }
    }


    /**
     * 解决方案2
     * 暴力方法：
     * 1.先算我贿赂所有的怪兽 一起要花多少钱 allMoney。 我要花的钱 肯定最后是 <= allMoney的。
     * 2.从0元 开始试，一直试到 allMoney。第一次遇到的能通关的钱 就是答案。花最少的钱通关
     */
    public static int minMoney2(int[] d, int[] p) {
        int allMoney = 0;
        for (int i = 0; i < p.length; i++) {
            allMoney += p[i];
        }
        for (int money = 0; money < allMoney; money++) {
            if (process2(d, p, d.length - 1, money) != -1) {
                return money;
            }
        }
        return allMoney;
    }

    /**
     *
     * 这个感觉怎么也像是dp。仅仅是从右边往左边推算。如果 index位置上的怪兽我刚好能拿下。那么index-1位置完成后，就需要给我留 刚好让我哪些index位置上的怪兽的钱。
     * 如果怪兽的贿赂钱取值范围 明显比怪兽的ability小，那么就用 index 和 money作为dp的两个维度
     *
     * @param d
     * @param p
     * @param index
     * @param money
     * @return
     */
    public static long process2(int[] d, int[] p, int index, int money) {
        if (index == -1) { // 还没有遇到怪兽呢
            return money == 0 ? 0 : -1;
        }
        // index >= 0
        // 1）不贿赂 index这个位置上的怪兽
        long preMaxAbility = process2(d, p, index - 1, money);
        long p1 = -1;
        if (preMaxAbility != -1 && preMaxAbility >= d[index]) { // 只有当上 index-1 这个位置上满足这个条件，才能保证index位置上能成功贿赂怪兽
            p1 = preMaxAbility;
        }
        // 2）贿赂 index这个位置上的怪兽 当前怪兽要花掉 p[index]。所以，只有在 index-1位置做完选择后，我还有 money-p[index]. 我在 index位置上才刚好有贿赂index位置上的怪兽的钱
        long preMaxAbility2 = process2(d, p, index - 1, money - p[index]);
        long p2 = -1;
        if (preMaxAbility2 != -1) {
            p2 = d[index] + preMaxAbility2;
        }
        return Math.max(p1, p2);
    }


}
