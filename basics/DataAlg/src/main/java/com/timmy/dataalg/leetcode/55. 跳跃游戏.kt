package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。

示例 1：
输入：nums = [2,3,1,1,4]
输出：true
解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。

示例 2：
输入：nums = [3,2,1,0,4]
输出：false
解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。

 */
fun main() {

        val res = canJump(intArrayOf(2, 3, 1, 1, 4))
//    val res = canJump(intArrayOf(3, 2, 1, 0, 4))
    println("res:$res")
}

/**
 * 2、解题
 * - 使用一个右侧指针right标记当前位置可以到达的最大长度
 * - 这个right会根据当前元素位置i，和对应的元素值num，不断往后移动，，知道最后一个元素
 * - 如果遇到中途是0的元素，且right的值也是0，则直接返回false
 */
fun canJump(nums: IntArray): Boolean {
    val n = nums.size
    var right = 0
    for (i in 0 until n) {
        if (i == n - 1) {
            return true
        }

        val num = nums[i]
        if (num == 0 && right == i) { // 无法往后跳跃了
            return false
        }

        if (i + num > right) {
            right = i + num
        }
    }
    return false
}

/**
 * 1、审题：
 * - 输入一个非负整数数组，（非负整数，就是0和正整数），每个元素的值代表这个元素可以往右跳跃的最大长度（是最大长度，他也可以不用跳那么远）
 * - 判断从数组0开始，是否可以调到数组最后一个位置，并返回boolean结果
 * 2、解题：
 * - 动态规划解法
 * - 状态转移方程式：dp[i] 是boolean类型，标识数组i位置是否可以到达
 * - 遍历数组元素，遍历到的元素，取出这个值，继续遍历数组值得长度，将后面的位置的dp位置标记为true，说明可以到达
 * - 最后返回dp[n-1]
 * 3、时间复杂度是n方法
 * - 改进
 */
fun canJump1(nums: IntArray): Boolean {
    val n = nums.size
    val dp = Array(n) { false }
    dp[0] = true
    for (i in 0 until n) {
        val num = nums[i]
        if (dp[i]) {
            var x = 1
            while (x <= num && i + x < n) {
                dp[i + x] = true
                x++
            }
        }
    }
    dp.print()

    return dp[n - 1]
}