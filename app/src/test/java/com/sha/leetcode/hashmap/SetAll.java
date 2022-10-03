package com.sha.leetcode.hashmap;

/**
 * 传统的hashmap的put get 都要有，还要一个setAll方法。
 * 限制，不能改变性能，put get 都还要维持 O(1)
 *
 * 这个很简单。put 和 get 都保持一样。
 * 每次put 和 get的元素上都要记录一个count。
 * count表示操作的先后顺序，从0开始，一直往上递增。
 *
 * setAll的话 就记录一个all= value，count++.
 * get的时候拿到了元素x，并且比较count，如果小于 setAll的count。那么得到的值，就是setAll中设置的值。
 */
public class SetAll {
}
