package com.timmy.dataalg.leetcode

/**
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。

给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。

示例 1：
输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4

示例 2：
输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1

示例 3：
输入：nums = [1], target = 0
输出：-1
 */
fun main() {
    val res = search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 3)
    println("res:$res")
}

/**
 * 1、审题
 * - 输入一个数组，但是这个数组经过旋转，导致并不是正常的升序排序，现在需要在这样一个数组中查找目标值
 * 2、解题
 * - 还是二分查找法
 * - 二分查找，取中间位置，前后两部分一定有一段是有序的，判断目标值是否在这个区间内，是的话，继续二分查找
 * - 如果目标值，不在升序范围内，则在另一区间查找，最终left，与right区间位置相等，否则那就是没有目标值
 */
fun search(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size - 1
    var mid: Int
    while (left <= right) {
        mid = left + (right - left) / 2
        if (target == nums[mid]) {
            return mid
        } else if (nums[left] <= nums[mid]) {    // 左侧范围有序
            if (nums[left] <= target && target < nums[mid]) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        } else if (nums[mid] <= nums[right]) {   // 右侧范围有序
            if (nums[mid] <= target && target <= nums[right]) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
    }
    return -1
}