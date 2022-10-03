package com.sha.leetcode.createsdatatruct;

import org.junit.Test;

import java.util.HashMap;

/**
 * 【总结1】想清楚cur是作为尾替换掉原来的尾，那么就是从tailMap中找cur-1，并且从tailMap删除cur-1。并且从headMap删除cur
 *             cur是作为头替换掉原来的头，那么就从headMap中找cur+1,并且从headMap中删除cur+1。并且从tailMap删除cur
 * 流媒体，比如一部电影。在服务端存成了很多小块。每块都有一个序号。序号从1到100.
 * 如果2，3先到了端侧。不能播放。先缓存在端侧。等到1到了端侧，就可以一起播放 1，2，3了。
 * <p>
 * 模拟这种过程。
 * <p>
 * 【核心】搞两个hashmap，head，tail。每次来一个就放入head，和tail中。都放。  然后怎么串起来（2，3）我就不理解了。第一次看视频的时候就没有整明白。
 * O(n)
 *
 * 这个题目我跌跌撞撞还是理清楚了呀。
 *
 */
public class LiveStream {
    @Test
    public void test() {
        MessageBox messageBox = new MessageBox();
        messageBox.receive(2, "2");
        messageBox.receive(3, "3");
        messageBox.receive(1, "1");
        messageBox.receive(5, "5");
        messageBox.receive(4, "4");
    }


    class MessageBox {
        private HashMap<Integer, Node> headMap;
        private HashMap<Integer, Node> tailMap;
        private int waitPoint;

        public MessageBox() {
            headMap = new HashMap<>();
            tailMap = new HashMap<>();
            waitPoint = 1;
        }

        public void receive(int num, String info) {
            Node cur = new Node(info);
            // 不管三七二十一，先入headMap和 tailMap
            headMap.put(num, cur);
            tailMap.put(num, cur);

            // 看 尾表, 头表 中是不是有 num-1 和 num+1.尝试把能串起来的都串成一个单链表。
            if (tailMap.containsKey(num - 1)) { // num -1 去尾表中找。因为cur想要接入 num-1的后面
                Node node = tailMap.remove(num - 1);
                node.next = cur;

                // 把cur 也从 头表中删除，因为目前cur已经取代了 num-1作为新的tail了
                headMap.remove(num);
            }
            if (headMap.containsKey(num + 1)) { // num +1 去 headmap中查。  这个我就不理解了. 因为num抢占了 num+1的头的位置。num会变成新head。num+1作为的是尾巴，所以tailmap中的num+1肯定是要留着的
                // num+1 做不了头了，num会插入在num+1的前面。所以，num+1要从headmap中remove掉。
                Node remove = headMap.remove(num + 1);
                cur.next = remove;
                // num作为新头，保留在headmap中的记录，删除num在tailmap中的记录
                tailMap.remove(num);
            }


            // 可能headmap中只有一个元素了，tailmap中也只有一个元素了。这个是理想情况。
            // 实际上 headmap和tailmap中可能还是会有多个元素。
            // 如果num是等待的元素。那么就可以触发打印了。打印完就可以把num从headmap中删除，把打印的最后一个元素从tailmap中删除
            if (num == waitPoint) {
                Node remove = headMap.remove(num);
                int currentIndex = num;
                System.out.print(remove.info + " ");
                while (remove.next != null) {
                    currentIndex++;
                    remove = remove.next;
                    System.out.print(remove.info + " ");
                }
                tailMap.remove(currentIndex); // 这里的currentIndex我开始尽然算错了。我以为从while出来后 currentIndex指向了单链表的最后的null。实际上currentIndex指向的就是单链表最后的那个元素。
                waitPoint = currentIndex + 1; // 这一步我竟然忘记了

            }
        }
    }

    class Node {
        public String info;
        public Node next;

        public Node(String str) {
            info = str;
        }
    }
}
