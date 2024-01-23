package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import kotlin.math.max

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
问总共有多少条不同的路径？

示例 1：
输入：m = 3, n = 7
输出：28

示例 2：
输入：m = 3, n = 2
输出：3
解释：
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右
3. 向下 -> 向右 -> 向下

示例 3：
输入：m = 7, n = 3
输出：28

示例 4：
输入：m = 3, n = 3
输出：6
 */
fun main() {

    //    val res = uniquePaths(3, 7)
    //    val res = uniquePaths(3, 2)
//        val res = uniquePaths(7, 3)
//        val res = uniquePaths(3, 3)
    val res = uniquePaths(51, 9)
    println("res:$res")
}

/**
 * 2、动态规划解法
 * - 状态转移方程式，二维数组
 * - 其中dp[i][j] 标识到达第i行j列位置时一共有多少条路径
 * - 首先处理第一行和第一列
 * - 中间部分 dp[i][j]= dp[i][j-1] + dp[i-1][j],表示dp[i][j]是左侧位置和上面位置的路径之和
 */
fun uniquePaths(m: Int, n: Int): Int {

    val dp = Array(m) {
        IntArray(n) { 0 }
    }
    dp.print()

    // 第一行
    for (i in 0 until n) {
        dp[0][i] = 1
    }

    // 第一列
    for (i in 0 until m) {
        dp[i][0] = 1
    }

    for (i in 1 until m) {
        for (j in 1 until n) {
            dp[i][j] =dp[i - 1][j] +  dp[i][j - 1]
        }
    }
    dp.print()

    return dp[m - 1][n - 1]
}

/**
 * 1、审题:
 * - 输入网格大小（m*n）标识网格有m行n列，现在有个机器人在网络的左上角，需要移动到网格右下角
 * - 机器人移动规则是，一次只能移动一步，且只能往右移动，或往下移动一位
 * - 问一共有多少调移动路径
 * 2、解题：
 * - 贪心算法
 * - 从00位置开始不断移动，每次往下或往后移动移动，不断递归调用，知道移动到mn位置，此时路径数量自增
 * 3、超时
 * -  uniquePaths(51, 9) 输入值mn太大时，计算时间超时
 * 4、优化
 * - 动态规划
 */
var resPath = 0
fun uniquePaths1(m: Int, n: Int): Int {
    uniquePathsReal(1, 1, m, n)
    return resPath
}

fun uniquePathsReal(i: Int, j: Int, m: Int, n: Int) {
    println("uniquePathsReal: i=$i,j=$j")
    if (i == m && j == n) {
        resPath++
        return
    }
    if (i + 1 <= m) {
        uniquePathsReal(i + 1, j, m, n)
    }
    if (j + 1 <= n) {
        uniquePathsReal(i, j + 1, m, n)
    }
}
