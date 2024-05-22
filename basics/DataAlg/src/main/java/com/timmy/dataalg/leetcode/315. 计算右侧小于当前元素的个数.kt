package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给你一个整数数组 nums ，按要求返回一个新数组 counts 。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。

示例 1：
输入：nums = [5,2,6,1]
输出：[2,1,1,0]
解释：
5 的右侧有 2 个更小的元素 (2 和 1)
2 的右侧仅有 1 个更小的元素 (1)
6 的右侧有 1 个更小的元素 (1)
1 的右侧有 0 个更小的元素

示例 2：
输入：nums = [-1]
输出：[0]

示例 3：
输入：nums = [-1,-1]
输出：[0,0]

提示：
1 <= nums.length <= 105
-104 <= nums[i] <= 104
 */
fun main() {
    val res = countSmaller(intArrayOf(5, 2, 6, 1))
    res.print()
}

/**
 * 1、审题
 * - 输入一个数组，要求返回一个数组，新数组的元素值，表示在原数组的位置后面的元素，比当前位置元素值小的个数
 * 2、解题
 * - 双层遍历法
 */
fun countSmaller(nums: IntArray): List<Int> {
    val size = nums.size
    val resList = Array(size) { 0 }

    for (i in 0 until size) {
        var count = 0
        for (j in i + 1 until size) {
            if (nums[i] > nums[j]) {
                count++
            }
        }
        resList[i] = count
    }

    return resList.toList()
}