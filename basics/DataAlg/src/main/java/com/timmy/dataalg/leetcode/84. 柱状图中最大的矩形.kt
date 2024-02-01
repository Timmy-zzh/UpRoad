package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import java.util.Stack
import kotlin.math.max
import kotlin.math.min

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
求在该柱状图中，能够勾勒出来的矩形的最大面积。

示例 1:
输入：heights = [2,1,5,6,2,3]
输出：10
解释：最大的矩形为图中红色区域，面积为 10

示例 2：
输入： heights = [2,4]
输出： 4
 */
fun main() {

//    val res = largestRectangleArea(intArrayOf(2, 1, 5, 6, 2, 3))
        val res = largestRectangleArea(intArrayOf(2, 4))
//        val res = largestRectangleArea(intArrayOf(1, 1))
    println("res:$res")
}

/**
 * 2、单调栈解法
 * - 使用优先级队列 ArrayDeque
 * - 实现思路：在遍历的某个元素中，从前后两个方向查找可连续的最小值
 * -- 使用数组保存
 * - 外面单层for循环遍历数组元素，
 * - 内部使用单调栈进行存储，找到最小值得元素
 * - 在遍历过程中，如果单调队列是空的，则直接入栈，这个队列需要是最小堆，（顶部元素最小）
 * - 当里面有元素时，遍历所有的元素，判断队列中的元素是否大于当前值，如果大于则出栈，
 * - 这样来两趟，就可以查找到遍历元素位置的，前后方向的最小值，
 */
fun largestRectangleArea(heights: IntArray): Int {
    val n = heights.size
    var res = 0
    val stack = Stack<Int>() //    队列，用于保存当前数组元素下，往左侧和右侧方向，最近的最小值得区间
    val left = IntArray(n)
    val right = IntArray(n)

    for (i in 0 until n) { // 左侧最小值
        while (!stack.isEmpty() && heights[stack.peek()!!] >= heights[i]) {
            stack.pop()
        }
        left[i] = if (stack.isEmpty()) -1 else stack.peek()!!

        // 加入
        stack.add(i)
    }

    stack.clear()
    for (i in n - 1 downTo 0) { // 右侧最小值
        while (!stack.isEmpty() && heights[stack.peek()!!] >= heights[i]) {
            stack.pop()
        }
        right[i] = if (stack.isEmpty()) n else stack.peek()!!
        stack.add(i)
    }
    left.print()
    right.print()

    // 求结果
    for (i in 0 until n) {
        val area = (right[i] - left[i] - 1) * heights[i]
        res = max(res, area)
    }

    return res
}

/**
 * 既然超出内存限制，那就不使用二维数组，通过双指针控制
 * 还是双层for循环，求区间内的最小值，并求区间面积大小
 * 3、超过时间限制
 * - 两层for循环，在数组数量很大时，会出现遍历过久问题
 * - 使用单调栈吗
 */
fun largestRectangleArea2(heights: IntArray): Int {
    val n = heights.size
    var res = 0
    for (i in 0 until n) {
        var item = heights[i]
        if (res < item) {
            res = item
        }
        for (j in i + 1 until n) {
            item = min(item, heights[j])
            val area = item * (j - i + 1)
            if (res < area) {
                println("i:$i,j:$j,area:$area")
                res = area
            }
        }
    }
    return res
}

/**
 * 1、审题
 * - 输入一个非负整数数组，每个数组元素标识柱子的高度，求在這些柱子中可以组成的最大矩形的面积
 * 2、解题
 * - 双层for循环解法
 * -- 外层循环确定柱子的右侧，内层循环从最左侧开始遍历，查找可以组成的举行，比较得出最大的举行面积
 * - 好像动态规划解法更好解答，使用二维数组dp[i][j] 表示数组元素i到j位置的最小柱子高度，在然后求(j-i)的宽度，结果就是矩形面积了
 * - 先求dp二维矩阵的对角线的值，他是dp[i][i]=height[i]
 * - 状态转移方程式： dp[i][j] = min(height[j],dp[i][j-1])
 * 3、超出内存限制
 * - 当heights数组数量过大时，超出内存限制
 * - 使用单层for循环，加边界值，指针处理
 */
fun largestRectangleArea1(heights: IntArray): Int {
    val n = heights.size

    val dp = Array(n) {
        IntArray(n) { 0 }
    }
    dp.print()

    for (i in 0 until n) {
        dp[i][i] = heights[i]
    }

    for (i in 0 until n) {
        for (j in i + 1 until n) {
            dp[i][j] = min(dp[i][j - 1], heights[j])
        }
    }

    println("after")
    dp.print()
    var res = 0
    for (i in 0 until n) {
        for (j in i until n) {
            val area = dp[i][j] * (j - i + 1)
            if (res < area) {
                res = area
            }
        }
    }
    return res
}