package com.timmy.dataalg._17dynamic

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

 */
fun main() {

    //    val coins = intArrayOf(1, 2, 5)
    //    val res = coinChange(coins, 11)

    val coins = intArrayOf(2147483647)
    val res = coinChange(coins, 2)

    //    val coins = intArrayOf(2)
    //    val res = coinChange(coins, 3)
    println("res:$res")
}

/**
 * 1、审题：
 * - 给定一个数组，数组元素标识硬币的金额，每种硬币有无限数量，现在要从硬币中选择最少个数的硬币，总和等于数据amount
 * 2、解题：
 * - 使用回溯算法
 * - 每次可以从数组中获取一枚硬币，则总金额减少，下一次又可以从数组中获取一枚硬币，等总金额减少到0结束，如果负数了则结束
 */
var minCoins = Int.MAX_VALUE
fun coinChangeBack(coins: IntArray, amount: Int): Int {
    coinChangeReal(0, coins, amount)
    if (minCoins == Int.MAX_VALUE) {
        return -1
    }
    return minCoins
}

private fun coinChangeReal(i: Int, coins: IntArray, amount: Int) {
    if (amount <= 0) {
        if (amount == 0 && i < minCoins) {
            minCoins = i
        }
        return
    }
    val size = coins.size
    for (j in 0 until size) {
        coinChangeReal(i + 1, coins, amount - coins[j])
    }
}


/**
 * 动态规划解法
 * - 方程式：coinNums[amount] = coinNums[amount-itme] +1
 * - 使用一维数组保存每种不同金额下，消耗的硬币个数，从amount往下减少到0
 * - 每次都有三种选择，金额相同时选择更小硬币数量
 * 3、继续优化
 */
fun coinChange(coins: IntArray, amount: Int): Int {
    val maxAmount = amount + 1
    val dp = IntArray(amount + 1) {
        maxAmount
    }
    dp[amount] = 0
    for (i in coins.indices) {
        if (amount - coins[i] >= 0) {
            dp[amount - coins[i]] = 1
        }
    }

    for (i in (amount - 1) downTo 0) {
        for (j in coins.indices) {
            if (i + coins[j] in 1 until amount) {
                dp[i] = min(dp[i + coins[j]] + 1, dp[i])
            }
        }
    }

    if (dp[0] == maxAmount) {
        return -1
    }

    return dp[0]
}

fun coinChangeV1(coins: IntArray, amount: Int): Int {
    val coinNums = IntArray(amount + 1) {
        Int.MAX_VALUE
    }
    coinNums[amount] = 0
    for (i in coins.indices) {
        if (amount - coins[i] >= 0) {
            coinNums[amount - coins[i]] = 1
        }
    }

    for (itemA in (amount - 1) downTo 0) {
        for (i in coins.indices) {
            if (itemA + coins[i] in 1 until amount && coinNums[itemA + coins[i]] != Int.MAX_VALUE) { // 之前有选中过
                if (coinNums[itemA + coins[i]] + 1 < coinNums[itemA]) { // 有更小值
                    coinNums[itemA] = coinNums[itemA + coins[i]] + 1
                }
            }
        }
    }

    coinNums.print()
    if (coinNums[0] == Int.MAX_VALUE) {
        return -1
    }

    return coinNums[0]
}
