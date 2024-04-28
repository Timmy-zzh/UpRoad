package com.timmy.dataalg.leetcode

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。

示例 1:
输入: [3,2,1,5,6,4], k = 2
输出: 5

示例 2:
输入: [3,2,3,1,2,4,5,5,6], k = 4
输出: 4

提示：
1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104
 */
fun main() {

}

/**
 * 1、审题：
 * - 输入一个数组，数组中会存在重复元素，要求返回第k大的元素
 * 2、解题：
 * - 对数组中的元素进行排序（降序），取第k个元素返回
 */
fun findKthLargest(nums: IntArray, k: Int): Int {
    return nums.sortedDescending()[k-1]
}