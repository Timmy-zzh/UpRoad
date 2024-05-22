package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import kotlin.math.min

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
你可以认为每种硬币的数量是无限的。

示例 1：
输入：coins = [1, 2, 5], amount = 11
输出：3
解释：11 = 5 + 5 + 1

示例 2：
输入：coins = [2], amount = 3
输出：-1

示例 3：
输入：coins = [1], amount = 0
输出：0

提示：
1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104
 */
fun main() {

    //    val res = coinChange(intArrayOf(1, 2, 5), 100)
//    val res = coinChange(intArrayOf(1, 2, 5), 11)
        val res = coinChange(intArrayOf(2), 3)
    println("res:$res")
}

/**
 * 回溯算法会导致超出时间限制
 * 改为动态规划解法：
 * - dp的大小为amount+1，dp[i]的值为兑换总金额为i时，使用的总零钱最少数量
 * - dp[0]=0, 状态转移 dp[i] = min(dp[i-element...-多种情况]) + 1
 */
fun coinChange(coins: IntArray, amount: Int): Int {
    val dp = Array(amount + 1) { amount + 1 }
    dp[0] = 0
    val size = coins.size
    for (count in 1..amount) {
        for (i in 0 until size) {
            val coin = coins[i]
            if (count - coin >= 0) {
                dp[count] = min(dp[count - coin] + 1, dp[count])
            }
        }
    }
    dp.print()
    return if (dp[amount] == amount + 1) -1 else dp[amount]
}

/**
 * 1、审题：
 * - 零钱兑换，输入零线的面额大小值，和总金额，求兑换总金额所需要的最少零线的数量
 * 2、解题
 * - 回溯算法，不断从零钱数组中取出一张零钱，用于减少总金额的大小值，直到总金额等于0，所求零钱数量就是结果值
 */
var res322: Int = Int.MAX_VALUE
fun coinChange1(coins: IntArray, amount: Int): Int {
    coinChangeBack322(coins, 0, amount)
    return if (res322 == Int.MAX_VALUE) -1 else res322
}

fun coinChangeBack322(coins: IntArray, count: Int, amount: Int) {
    if (res322 != Int.MAX_VALUE && count >= res322) {
        return
    }
    if (amount <= 0) {
        if (amount == 0 && count < res322) {
            res322 = count
        }
        return
    }
    for (i in coins.indices) {
        coinChangeBack322(coins, count + 1, amount - coins[i])
    }
}
