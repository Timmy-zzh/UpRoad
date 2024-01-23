package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import kotlin.math.max

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，
 * 并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。

示例 1：
输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

示例 2：
输入：intervals = [[1,4],[4,5]]
输出：[[1,5]]
解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
fun main() {
    val intervals = arrayOf(
        intArrayOf(1, 3),
        intArrayOf(2, 6),
        intArrayOf(8, 10),
        intArrayOf(15, 18),
    )
    val res = merge(intervals)
    res.print()
}

/**
 * 1、审题
 * - 输入一个二维数组，其中单个小数组标识一个区间的集合，两个数组间会存在重叠情况，
 * - 将存在重叠的所有数组进行合并，并返回合并后的数组，和其他没有重叠的数组集合
 * 2、解题
 * - 问题1：如何判断两个区间存在重叠？ 开头大于别人的结尾，结尾大于别人的开头
 * - 將集合中所有的区间进行排序，按照区间的开头进行升序排序，
 * - 排序后，如果相邻的两个区间存在重叠则，第二个区间的left左侧值，应该小于等于第一个区间的右侧值
 * - 按照这个规律进行实现
 */
fun merge(intervals: Array<IntArray>): Array<IntArray> {
    val res = mutableListOf<IntArray>()

    intervals.print()
    intervals.sortBy {
        it[0]
    }
    println("sort after")
    intervals.print()

    intervals.forEach {
        val left = it[0]
        val right = it[1]

        if (res.isEmpty() || res[res.size - 1][1] < left) {
            res.add(it)
        } else { // 存在重叠，更新区间的最大值
            val itemInts = res[res.size - 1]
            itemInts[1] = max(itemInts[1], right)
        }
    }

    return res.toTypedArray()
}