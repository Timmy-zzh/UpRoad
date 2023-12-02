package com.timmy.dataalg._17dynamic

import com.timmy.dataalg.print
import kotlin.math.min

/**
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。

示例 1：
输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
输出：11
解释：如下面简图所示：
2
3 4
6 5 7
4 1 8 3
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

示例 2：
输入：triangle = [[-10]]
输出：-10
 */
fun main() {
    val triggle = ArrayList<ArrayList<Int>>()
    triggle.add(arrayListOf(2))
    triggle.add(arrayListOf(3, 4))
    triggle.add(arrayListOf(6, 5, 7))
    triggle.add(arrayListOf(4, 1, 8, 3))

    val res = minimumTotal(triggle)
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个集合，集合中存储有三角形的数量递增的元素，从第顶部元素开始往下行走，行走规则只能是走相邻的两个位置，
 * - 求到达最底层的最小路径和
 * 2、解题：动态规划
 * - 使用一个二维数组，每一层保存上一层到下一层的路径之和，然后查找最后一层的最小值
 */
fun minimumTotal(triangle: List<List<Int>>): Int {
    var minRes = Int.MAX_VALUE
    val row = triangle.size
    val column = triangle[row - 1].size
    val dp = Array(row) {
        IntArray(column) {
            0
        }
    }
    dp.print()

    dp[0][0] = triangle[0][0]
    for (i in 0 until row - 1) {
        for (j in 0 until triangle[i].size) {
            if (j == 0) {
                dp[i + 1][0] = dp[i][0] + triangle[i + 1][0]
                dp[i + 1][1] = dp[i][j] + triangle[i + 1][1]
            } else {
                dp[i + 1][j] = min(dp[i + 1][j], dp[i][j] + triangle[i + 1][j])
                dp[i + 1][j + 1] = dp[i][j] + triangle[i + 1][j + 1]
            }
        }
    }
    dp.print()

    for (i in 0 until column) {
        if (dp[row - 1][i] < minRes) {
            minRes = dp[row - 1][i]
        }
    }

    return minRes
}