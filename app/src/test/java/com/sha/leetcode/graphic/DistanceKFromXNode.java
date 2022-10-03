package com.sha.leetcode.graphic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * 【总结1】这个题目就是训练的是图的BFS.用queue怎么区分第一圈的nodes， 第二圈的nodes，第n圈的nodes？？ 这个能解决这个问题
 * 也就能解决了。
 * 使用queue的size。一圈处理完了，就更新第2圈的size。然后size--直到0.然后更新第3圈的size。
 * <p>
 * 【反思1】为什么stack有 stack.get(index)方法. queue却没有呢？
 * <p>
 * 给二叉树的头节点head，树上某个节点 target。正数K。
 * 从target开始，可以向上 也可以 向下走。
 * 返回与target节点距离是k的所有节点。
 * <p>
 * 这就是一个图的BFS.第k层的节点就是ans
 * <p>
 * 我开始想的是，把这个树上的节点都加上一个parent指针。目的，让child可以直接往上找到自己的parent。
 * 老师的方案是，搞个hashmap表。存<child, parent>.
 * 我为什么总是把问题想得这么复杂呢？用 hashmap不香吗？
 *
 * 【时间复杂度】应该就是bfs的时间复杂度。虽然是 2*bfs。最后也是bfs的时间复杂度。
 */
public class DistanceKFromXNode {
    @Test
    public void test() {

    }

    private List<Node> distanceKNodes(Node head, Node target, int k) {
        HashMap<Node, Node> parentsMap = buildParentsMap(head);

        // 图的bfs遍历
        // 第一圈 就是target这一个node。
        int size = 1;
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(target);

        Set<Node> set = new HashSet<>();
        while (size-- > 0) {
            Node poll = queue.poll();
            // 把poll出来的node的孩子都添加到queue中，也包括父亲。
            if (poll.left != null && !set.contains(poll.left)) {
                queue.add(poll.left);
                set.add(poll.left);
            }
            if (poll.right != null && !set.contains(poll.right)) {
                queue.add(poll.right);
                set.add(poll.right);
            }
            if (!set.contains(parentsMap.get(poll))) {
                queue.add(parentsMap.get(poll));
                set.add(parentsMap.get(poll));
            }

            if (size == 0 && k > 0) {
                size = queue.size(); // 下一圈的size
                k--;
            }
        }

        ArrayList<Node> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            ans.add(queue.poll());
        }
        return ans;
    }

    private HashMap<Node, Node> buildParentsMap(Node head) {
        HashMap<Node, Node> hashMap = new HashMap();
        Queue<Node> queue = new PriorityQueue<>();
        // 先把head添加进queue。激活queue。
        queue.add(head);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (poll.left != null) {
                hashMap.put(poll.left, poll);
                queue.add(poll.left);
            }
            if (poll.right != null) {
                hashMap.put(poll.right, poll);
                queue.add(poll.right);
            }
        }
        return hashMap;
    }

    class Node {
        public String value;
        public Node left;
        public Node right;

        public Node(String value) {
            this.value = value;
        }
    }
}
