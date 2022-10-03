package com.sha.leetcode.dp;

import org.junit.Test;


/**
 *
 * 【总结】
 *  只能去a区域，
 *  只能去b区域，
 *  可以去a区域 也可以去b区域。
 *
 *
 * 司机要平均分到两个区域里干活。司机个数是 偶数个。
 * 诉求：所有司机加起来的总创收最多
 * <p>
 * 0 [9,13]  0号司机去a区域干活赚9块，去b区域干活赚13块。
 * 1 [45,60] 1号司机去a区域干活赚45块，去b区域干活赚60块。
 * ......
 * <p>
 * 分析：这个题目也是一个dp问题。但是和一般的dp不一样的就是，要保证两个区域中的司机个数是 一样的。
 * 如果没有这个限制条件，那么就是一个从左到右的尝试模型。i 号司机 去a区域 or 去b区域两个情况。
 * 有了这个限制需要新增 保证两个区域司机个数相等的逻辑。
 * 怎么保证呢？去a区域前，先看a区域是不是满了，a满了，就只能去b区域。
 * 去b区域前，先看b区域是不是满了，b满了，就只能去a区域。
 *
 * 靠着记忆力 我还憋对了。
 *
 * 【卡壳点1】只能进a区域，和只能进b区域。为什么只能进a区域用的时候 rest == income.len - i?  只能进b区域就很简单了啦，a区域没有位置了，就只能进b区域。
 *
 * O(n)吗？ 毕竟所有的司机都只遍历了一遍。
 *
 */
public class DidiDriversIntoTwoArea {

    @Test
    public void test() {
        int[][] income = {{9, 13}, {45, 60}, {50, 10}, {30, 25}}; // 期待 13+60+50+30=153
        int maxMoney = maxMoney(income);
        System.out.println("sha " + maxMoney);
    }


    private int maxMoney(int[][] income) {
        // 计算每个区域应该去多少司机.   a区域还能装几个司机
        int N = income.length / 2;

        return process(income, 0, N);
    }

    /**
     * @param income
     * @param i      当前分配到第i号司机了
     * @param rest   a区域还能装几个司机
     * @return 返回所有司机平均分给a和b，整体收入最大是多少
     */
    private int process(int[][] income, int i, int rest) {
        if (i == income.length) {
            return 0;
        }

        // 只能进a区域. 【卡壳点1】如果剩下的司机个数等于 a区域的rest。就都进a区域。为什么这么判断，而不是判断rest==0？？？
        if (income.length - i == rest) { // a区域已经没有位置了。
            return income[i][0] + process(income, i + 1, rest - 1);
        }
        // 只能进b区域
        if (rest == 0) {
            return income[i][1] + process(income, i + 1, rest);
        }

        // 能进a区域也能进b区域
        // i号司机进a区域
        int p1 = income[i][0] + process(income, i + 1, rest - 1);
        // i号司机进b区域
        int p2 = income[i][1] + process(income, i + 1, rest);
        return Math.max(p1, p2);
    }
}
