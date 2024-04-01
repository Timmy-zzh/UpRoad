package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import kotlin.math.max

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
测试用例的答案是一个 32-位 整数。

示例 1:
输入: nums = [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。

示例 2:
输入: nums = [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。

提示:
1 <= nums.length <= 2 * 104
-10 <= nums[i] <= 10
nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数
 */
fun main() { //    val res = maxProduct(intArrayOf(2, 3, -2, 4))
    //        val res = maxProduct(intArrayOf(-2,0,-1))
    //        val res = maxProduct(intArrayOf(-3, -1, -1))
    //        val res = maxProduct(intArrayOf(2, -5, -2, -4, 3))
    //        val res = maxProduct(intArrayOf(1, 2, -1, -2, 2, 1, -2, 1, 4, -5, 4))
    val res = maxProduct(intArrayOf(-2, 3, -4))
    println("res:$res")
}

/**
 * 回溯算法实现
 * - 从第一个元素开始，不断往后叠加到不同位置的元素之积，求积最大的子数组
 */
var maxRes: Int = 0
fun maxProduct(nums: IntArray): Int {
    val n = nums.size
    if (n == 1) {
        return nums[0]
    }
    for (j in 0 until n) {
        maxProductBack(nums[j], j, nums)
    }
    return maxRes
}

fun maxProductBack(res: Int, i: Int, nums: IntArray) {
    println("maxProductBack res=$res,i=$i")
    if (res > maxRes) {
        maxRes = res
    }
    if (i == nums.size - 1) {
        return
    }

    maxProductBack(res * nums[i + 1], i + 1, nums)
}

/**
 * 1、审题：
 * - 输入一个整数数组，求其中一个连续子数组，这个连续的子数组的乘积最大
 * 2、解法：动态规划
 * - 状态转移方程式 dp[i] 表示到数组位置i的时候，他的最大乘积的值
 * - 当nums[i]为负数和零是时，dp[i]等于nums[i]
 * - 当num[i]=0时，如上
 * - 大于0时，dp[i]=dp[i]*nums[i](dp[i-1]要求大于0)否则等于num[i]
 * 3、动态规划解法，再挣扎一下吧
 * - 考虑多种情况，不能光考虑当前元素和前一个元素的乘积，得一起考虑，前面两个值得乘积大于0，则使用乘积
 * -- dp[i-1]*nums[i] >0
 * - 是否需要考虑三个值呢
 */
fun maxProductDp(nums: IntArray): Int {
    val dp = nums.copyOf()
    dp.print()
    for (i in 1 until nums.size) {
        if (i < 2) {
            if (nums[i] * dp[i - 1] > 0) {
                dp[i] = dp[i - 1] * nums[i]
            } else {
                dp[i] = nums[i]
            }
        } else {
            if (nums[i] * nums[i - 1] > 0) { // 考虑前后两个
                dp[i] = max(dp[i - 1] * nums[i], nums[i - 1] * nums[i])
            } else {
                dp[i] = nums[i]
            }

            // 考虑前后三个大于0
            if (nums[i] * nums[i - 1] * nums[i - 2] > 0 || nums[i] * nums[i - 1] * nums[i - 2] > 0) {
                dp[i] = max(nums[i] * dp[i - 1], nums[i] * nums[i - 1] * nums[i - 2])
            }
        }
    }
    dp.print()
    return dp.max()
}