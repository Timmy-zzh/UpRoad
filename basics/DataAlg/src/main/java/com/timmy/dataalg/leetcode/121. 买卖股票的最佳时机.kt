package com.timmy.dataalg.leetcode

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

提示：
1 <= prices.length <= 105
0 <= prices[i] <= 104
 */
fun main() {
//    val res = maxProfit(intArrayOf(7, 1, 5, 3, 6, 4))
    val res = maxProfit(intArrayOf(7,6,4,3,1))
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个数组，数组中的元素表示某只股票在某天的价格，
 * - 现在需要在合适的时间，买入股票，并在其后某一天卖出，求最大收益，如果无收益，则不买入
 * 2、解题：
 * - 动态规划
 * - 状态转移方程式，dp[i]表示在这一天股票买卖获得的最大收益
 * -- 先找到股票价格的最小值，
 * - 单调队列，升序
 * -- 队列为空，入队
 * -- 遍历元素比队尾更小，出队列，一直出到队尾元素更小
 * -- 遍历元素比队尾元素值大，则入栈，并保存数组的下标值，
 * 3、思路问题：
 * - 买卖股票的规律，最本质的问题是，在最低价时买入，在最高价时卖出
 * - 所以在遍历的过程中，需要找到最低价，然后在后面的日子中，尝试卖出股票获得利润，并求最大的利润
 */
fun maxProfit(prices: IntArray): Int {
    val n = prices.size
    var resMax = 0
    var minV = Int.MAX_VALUE
    for (i in 0 until n) {
        if (minV > prices[i]) {
            minV = prices[i]
        }
        val tempV = prices[i] - minV
        if (resMax < tempV) {
            resMax = tempV
        }
    }
    return resMax
}