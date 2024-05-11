package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
请 不要使用除法，且在 O(n) 时间复杂度内完成此题。

示例 1:
输入: nums = [1,2,3,4]
输出: [24,12,8,6]

示例 2:
输入: nums = [-1,1,0,-3,3]
输出: [0,0,9,0,0]

提示：
2 <= nums.length <= 105
-30 <= nums[i] <= 30
保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内

进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组 不被视为 额外空间。）
 */
fun main() {
    val res = productExceptSelf(intArrayOf(1, 2, 3, 4))
}

/**
 * 1、审题：
 * - 输入一个数组，现在需要返回一个同样大小的数组，数组中没有元素的值，是原始数组中其他元素的乘积值
 * 2、解题：
 * - 暴力解法，双层for循环
 * - 时间复杂度要O(n)，还不能用除法，
 * -》 左右乘积表解法：使用两个数组left,right,分别保存一个区间的元素乘积总值，
 * -- 例如left[i] 表示元素i左侧所有元素的乘积值，left[0]=1, left[i]= left[i-1]*num[i-1]
 * -- 最后所有结果数组的值 res[i]= left[i] * right[i]
 */
fun productExceptSelf(nums: IntArray): IntArray {
    val size = nums.size
    val left = Array(size + 1) { 1 }
    val right = Array(size + 1) { 1 }
    val res = Array(size) { 1 }

    for (i in 1..size) {
        left[i] = left[i - 1] * nums[i - 1]
    }

    for (i in size - 2 downTo 0) {
        right[i] = right[i + 1] * nums[i + 1]
    }

    for (i in 0 until size) {
        res[i] = left[i] * right[i]
    }
    left.print()
    right.print()
    res.print()

    return res.toIntArray()
}