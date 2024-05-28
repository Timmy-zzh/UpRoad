package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import kotlin.math.max
import kotlin.math.min

/**
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。

示例 1：
输入：nums = [1,2,3,4,5]
输出：true
解释：任何 i < j < k 的三元组都满足题意

示例 2：
输入：nums = [5,4,3,2,1]
输出：false
解释：不存在满足题意的三元组

示例 3：
输入：nums = [2,1,5,0,4,6]
输出：true
解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6

提示：
1 <= nums.length <= 5 * 105
-231 <= nums[i] <= 231 - 1
进阶：你能实现时间复杂度为 O(n) ，空间复杂度为 O(1) 的解决方案吗？
 */
fun main() {
    val res = increasingTriplet(intArrayOf(1, 6, 2, 5, 1))
    println("res:$res")
}

/**
 * 2、双向遍历解法
 * - 按照题目要求，要找的是一个升序的三元子序列，那只要在数组范围i[1,N-1]位置，存在一个值
 * - 满足条件，左侧范围存在一个元素小于nums[i],右侧范围存在一个元素大于nums[i]，即可
 * 解题：
 * - 创建两个数组left，right，分别标识left[i]左侧是否有一个元素小于当前nums[i]的值，存在的话left[i]结果为1，否则为0
 * - 数组right为大于当前值
 * - 最后遍历查看left，right的元素值，是否都大于0
 */
fun increasingTriplet(nums: IntArray): Boolean {
    val n = nums.size
    val left = Array(n) { 0 }
    val right = Array(n) { 0 }

    var minV = nums[0]
    for (i in 1 until n - 1) {
        if (minV < nums[i]) {
            left[i] = 1
        }
        if (minV > nums[i]) {
            minV = nums[i]
        }
    }
    var maxV = nums[n - 1]
    for (i in n - 2 downTo 1) {
        if (nums[i] < maxV) {
            right[i] = 1
        }
        if (nums[i] > maxV) {
            maxV = nums[i]
        }
    }
    left.print()
    right.print()

    for (i in 1 until n - 1) {
        if (left[i] > 0 && right[i] > 0) {
            return true
        }
    }
    return false
}

/**
 * 1、审题：
 * - 输入一个数组，判断数组中是否存在一个递增子序列的三元组，存在返回true
 * 2、解题：
 * - 一层遍历法：使用一个标记判断当前递增子序列的长度即可
 * -- 题目要求的是递增子序列，不是说的连续子序列，使用一个标记实现不了
 * - 动态规划法：也不行啊
 */
fun increasingTripletDp(nums: IntArray): Boolean {
    val n = nums.size
    val dp = Array(n) { 1 }

    for (i in 1 until n) {
        var x = i - 1
        while (x >= 0) {
            if (nums[i] > nums[x]) {
                dp[i] = max(dp[i], dp[x] + 1)
            }
            x--
        }
        if (dp[i] == 3) {
            return true
        }
    }
    dp.print()
    return false
}