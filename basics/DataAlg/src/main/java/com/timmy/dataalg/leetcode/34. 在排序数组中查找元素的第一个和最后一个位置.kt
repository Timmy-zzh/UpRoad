package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
如果数组中不存在目标值 target，返回 [-1, -1]。
你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。

示例 1：
输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]

示例 2：
输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]

示例 3：
输入：nums = [], target = 0
输出：[-1,-1]

 */
fun main() { //    val res = searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 8)
    val res = searchRange(intArrayOf(2, 2), 2)
    res.print()
}

/**
 * 1、审题
 * - 输入一个数组和目标值，该数组是升序排序，但是其中有的元素是重复出现，要求改目标值在数组中是否存在，
 * -- 如果存在则返回该元素出现的第一个位置和最后出现的问题，没有则返回-1
 * 2、解题
 * - 要求时间复杂度是logn，还的使用二分查找法
 * - 分开来查找，先求该元素第一次出现的位置
 */
fun searchRange(nums: IntArray, target: Int): IntArray {
    val res = IntArray(2) { -1 }

    var left = 0
    var right = nums.size - 1
    var mid: Int = 0
    while (left <= right) {
        mid = left + (right - left) / 2
        if (target == nums[mid]) {
            if (mid == 0 || nums[mid - 1] != target) {
                res[0] = mid
                break
            } else {
                right = mid - 1
            }
        } else if (target < nums[mid]) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }

    if (res[0] == -1) {
        return res
    }

    /**
     * 或者继续二分查找计算
     */
    val start = mid
    for (i in start until nums.size) {
        if (target == nums[i] && (i == nums.size - 1 || nums[i + 1] != target)) {
            res[1] = i
            break
        }
    }

    return res
}