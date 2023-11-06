package com.timmy.dataalg._04queue

import com.timmy.dataalg.print
import java.util.PriorityQueue

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
返回 滑动窗口中的最大值 。

示例 1：
输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
1 [3  -1  -3] 5  3  6  7       3
1  3 [-1  -3  5] 3  6  7       5
1  3  -1 [-3  5  3] 6  7       5
1  3  -1  -3 [5  3  6] 7       6
1  3  -1  -3  5 [3  6  7]      7

示例 2：
输入：nums = [1], k = 1
输出：[1]
 */
fun main() {
    val res = maxSlidingWindow(intArrayOf(1, 3, -1, -3, 5, 3, 6, 7), 3)
    res.print()
}


/**
 * 1、审题：输入一个数组和一个滑动窗口的大小，让滑动窗口在数组中移动，我们只能看到滑动窗口中的元素，
 * 现在需要找出每次移动过程中滑动窗口内值最大的元素，放在一个数组中并返回
 * 2、解题：想到了大顶堆
 * - 刚开始将滑动窗口数量的数据放到大顶堆中，取出最大值，
 * - 然后移动元素，大顶堆同样删除元素，并新增新的元素，知道遍历结束
 * 3、超出时间限制：
 * - 需要在原有数组遍历基础上，再在优先队列（大顶堆）中查找最大值，时间复杂度为O(n*k)
 * 4、优化：使用优先级队列保存，元素值和元素的位置，在遍历的时候，判断大顶堆顶部的元素是否是需要remove的位置，是的话就去除
 */
fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
    val size = nums.size
    val resArr = IntArray(size - k + 1)
    // 大顶堆
    val queue = PriorityQueue<Int> { o1, o2 -> o2 - o1 }

    for (j in 0 until size - k + 1) {
        if (queue.isEmpty()) {
            for (i in 0 until k) {
                queue.add(nums[i])
            }
        } else {
            queue.remove(nums[j - 1])
            queue.add(nums[j + k - 1])
        }

        val peek = queue.peek()
        resArr[j] = peek
    }

    return resArr
}














