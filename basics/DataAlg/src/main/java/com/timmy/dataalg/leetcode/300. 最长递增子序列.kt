package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import kotlin.math.max

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的
子序列。

示例 1：
输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。

示例 2：
输入：nums = [0,1,0,3,2,3]
输出：4

示例 3：
输入：nums = [7,7,7,7,7,7,7]
输出：1

提示：
1 <= nums.length <= 2500
-104 <= nums[i] <= 104
进阶：
你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 */
fun main() { //    val res = lengthOfLIS(intArrayOf(10, 9, 2, 5, 3, 7, 101, 18))
    val res = lengthOfLIS(intArrayOf(0, 1, 0, 3, 2, 3))
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个数组，求最长子序列，要求子序列是升序，子序列中的数字在原数组中不必是连续的
 * 2、解题：
 * - 动态规划解法，状态转移方程式： dp[i]表示当前元素位置，升序子序列的最大长度，
 * -- 状态转移方程式，dp[i+1]的值计算，要求在区间返回[0~i]找到比num[i]更小的值，找到一个比他小的值，就可以中断往前遍历查找了
 * - 最后求dp[i]中的最大值
 */
fun lengthOfLIS(nums: IntArray): Int {
    val n = nums.size
    val dp = Array(n) { 1 }

    for (i in 1 until n) {
        var index = i - 1
        while (index >= 0) {
            if (nums[index] < nums[i]) {
                dp[i] = max(dp[index] + 1, dp[i])
            }
            index--
        }
    }
    dp.print()

    return dp.max()
}