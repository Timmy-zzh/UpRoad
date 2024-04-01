package com.timmy.dataalg._17dynamic

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
测试用例的答案是一个 32-位 整数。
子数组 是数组的连续子序列。

示例 1:
输入: nums = [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。

示例 2:
输入: nums = [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
fun main() {
    val res = maxProfit(intArrayOf(2, 3, -2, 4))
    println("res=$res")
}

/**
 * 1、审题：
 * - 输入一个数组，需要找出最长的连续子数组，并将子数组的乘机返回
 * 2、解题：动态规划
 * - 使用一维数组保存遍历到该元素时连续子数组的乘积
 * - 从第1个遍历，判断前一个元素与当前元素差值是否为1，-->没明白，为什么要判断他的差值是否为1
 */
fun maxProduct(nums: IntArray): Int {
    if (nums.size == 1) {
        return nums[0]
    }
    var maxRes = 0
    val dp = IntArray(nums.size) {
        0
    }
    for (i in 1 until nums.size) {
        if (nums[i] == nums[i - 1] + 1) {
            if (dp[i - 1] != 0) {
                dp[i] = dp[i - 1] * nums[i]
            } else {
                dp[i] = nums[i - 1] * nums[i]
            }
        }
    }
    dp.forEach {
        if (maxRes < it) {
            maxRes = it
        }
    }
    return maxRes
}