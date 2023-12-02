package com.timmy.dataalg._17dynamic

import com.timmy.dataalg.print
import kotlin.math.max

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。

示例 1：
输入：[7,1,5,3,6,4]
输出：5
解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。

示例 2：
输入：prices = [7,6,4,3,1]
输出：0
解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
fun main() {
    val res = maxProfit(intArrayOf(7, 1, 5, 3, 6, 4))
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个数组，数组元素标识当天的股票价格，用户当前可以选择买入或不买入，买入后在后面的日子可以卖出，
 * - 求股票经过多天的交易后，可获得的最大利润是多少？
 * 2、解题：
 * - 动态规划解法
 * - 查找状态转移方程式
 * --定义一个数组，数组中表示从第1天开始到最后一天，获得的最大利润值，
 * --数组中默认利润都是0，从第一天开始遍历，第一天的结果计算逻辑是前面第0天如果买入，在第1天卖出获得利润，如果前面日子价格高，那就不买入了
 * -- 后面第i天的利润，是不断遍历前面的日子，假设前面的日子某天如果买入了股票，并且价格低，则在当前卖出获得的利润，加上前天日子的利润就是总利润了
 * 3、优化：单层遍历
 * - 股票交易，其实最大利润，就是在最低点买入，最高点卖出，中间的部分可以忽略
 */
fun maxProfit(prices: IntArray): Int {
    var maxRes = 0      // 最高的利润价格
    var minPrice = Int.MAX_VALUE
    for (i in prices.indices) {
        if (minPrice > prices[i]) {     // 找到当天最低的价格
            minPrice = prices[i]
        }
        val temp = prices[i] - minPrice   // 当前的总利润
        if (temp > maxRes) {
            maxRes = temp
        }
    }
    return maxRes
}

fun maxProfitV1(prices: IntArray): Int {
    val size = prices.size
    val dp = IntArray(size) {
        0
    }

    for (day in 1 until size) {
        for (pre in 0 until day) {
            if (prices[pre] < prices[day]) {
                dp[day] = max(dp[day], dp[pre] + (prices[day] - prices[pre]))
            }
        }
    }
    dp.print()

    var maxRes = 0
    dp.forEach {
        if (maxRes < it) {
            maxRes = it
        }
    }
    return maxRes
}