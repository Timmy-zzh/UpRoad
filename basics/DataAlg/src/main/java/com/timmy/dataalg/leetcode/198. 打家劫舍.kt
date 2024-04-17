package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import kotlin.math.max

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

示例 1：
输入：[1,2,3,1]
输出：4
解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
偷窃到的最高金额 = 1 + 3 = 4 。

示例 2：
输入：[2,7,9,3,1]
输出：12
解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
偷窃到的最高金额 = 2 + 9 + 1 = 12 。

提示：
1 <= nums.length <= 100
0 <= nums[i] <= 400
 */
fun main() { //    val res = rob(intArrayOf(1, 2, 3, 1))
    //    val res = rob(intArrayOf(2,7,9,3,1))

    val res = rob(intArrayOf(2, 1, 1, 2))
    println("res:$res")

}

/**
 * 1、审题：
 * - 输入一个正整数数组，元素值表示每个房间存放的金额，现在要计算偷窃所能获得的最大金额
 * - 限制条件是相邻房间不能同时被盗，否则会引起警报
 * 2、解题：动态规划解法
 * - 状态转移方程式如何求取？ dp[i] 标识到第i间房，所盗取的最大金额
 * - 问题是如何标记有的房间偷，隔壁房间不偷呢？ 再来一个bool数组的标识是否被光临了，
 * - 或者采用奇偶位置数，进行叠加
 * = dp[i] 可以选择偷，也可以选择不偷，
 * == 偷的话，那就是dp[i] = dp[i-2] + num[i]
 * == 不偷 ，     dp[i] = dp[i-1]
 */
fun rob(nums: IntArray): Int {
    val dp = Array(nums.size + 1) { 0 }
    dp[1] = nums[0]

    for (i in 1 until nums.size) {
        dp[i + 1] = max((dp[i - 1] + nums[i]), dp[i])
    }
    dp.print()

    return dp.max()
}