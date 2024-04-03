package com.timmy.dataalg.leetcode

/**
 * 峰值元素是指其值严格大于左右相邻值的元素。
给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
你可以假设 nums[-1] = nums[n] = -∞ 。
你必须实现时间复杂度为 O(log n) 的算法来解决此问题。

示例 1：
输入：nums = [1,2,3,1]
输出：2
解释：3 是峰值元素，你的函数应该返回其索引 2。

示例 2：
输入：nums = [1,2,1,3,5,6,4]
输出：1 或 5
解释：你的函数可以返回索引 1，其峰值元素为 2；
或者返回索引 5， 其峰值元素为 6。

提示：
1 <= nums.length <= 1000
-231 <= nums[i] <= 231 - 1
对于所有有效的 i 都有 nums[i] != nums[i + 1]
 */
fun main() {
}

/**
 * 1、审题：
 * - 输入一个数组，找到数组中元素值，大于左右数据值的元素位置
 * - 要求算法复杂度为log n
 * 2、解题
 * - 使用二分查找法
 * - 要求峰值大于左右相邻的数组元素，
 */
fun findPeakElement(nums: IntArray): Int {
    var left = 0
    var right = nums.size - 1
    if (nums.size == 1) {
        return 0
    }

    while (left <= right) {

        val mid = left + (right - left) / 2
        if (mid == 0) { // 数组头
            if (nums[mid] > nums[mid + 1]) {
                return mid
            }
            left = mid + 1
        } else if (mid == nums.size - 1) {    // 数组尾
            if (nums[mid] > nums[mid - 1]) {
                return mid
            }
            right = mid - 1
        } else {
            if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                return mid
            }
            if (nums[mid] < nums[mid - 1]) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
    }
    return left
}