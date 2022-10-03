package com.sha.leetcode.graphic;

import org.junit.Test;

import java.io.File;
import java.util.PriorityQueue;
import java.util.Queue;

public class CountFiles {

    @Test
    public void test() {

    }

    /**
     * 入参：给定一个目录。
     * 诉求：统计所有的文件个数，包含文件夹里面的文件。
     * <p>
     * 这个题目就很简单了啦。
     * 用递归可以做，脑中先出现一颗多叉树，每一层可能有文件 和 目录。
     * 每个节点向上返回自己及孩子一起有多少个文件。
     * <p>
     * BFS做，比递归更加简单。一层一层的遍历，发现了一个文件，ans就++，发现了一个文件夹，就入队列
     */
    private int countFiles(String dir) {
        // 首先先处理根目录
        File file = new File(dir);
        if (file.isFile()) {
            return 1;
        }


        int ans = 0;
        Queue<File> queue = new PriorityQueue<File>();
        queue.add(file);

        while (!queue.isEmpty()) {
            File[] files = queue.poll().listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    queue.add(files[i]);
                } else {
                    ans++;
                }
            }
        }
        return ans;
    }
}
