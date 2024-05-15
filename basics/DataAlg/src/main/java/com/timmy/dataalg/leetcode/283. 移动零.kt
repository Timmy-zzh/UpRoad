package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
请注意 ，必须在不复制数组的情况下原地对数组进行操作。

示例 1:
输入: nums = [0,1,0,3,12]
输出: [1,3,12,0,0]

示例 2:
输入: nums = [0]
输出: [0]

提示:
1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1
进阶：你能尽量减少完成的操作次数吗？
 */
fun main() {
    //    moveZeroes(intArrayOf(0, 1, 0, 3, 12))
    moveZeroes(intArrayOf(1, 0))
}

/**
 * 1、审题：
 * - 输入一个数组，数组由整数和0组成，现在要讲所有的0移动到数组的末尾，同时保持非0元素的顺序
 * 2、解题：
 * - 双指针解法
 * - 左指针left指向当前遍历的元素，右指针right指向后面的非0元素，当左指针遇到0元素时，需要与右指针right进行交换，直到右指针遍历到尾部
 */
fun moveZeroes(nums: IntArray): Unit {
    nums.print()

    val n = nums.size
    var right = 0
    for (left in 0 until n) {
        if (nums[left] == 0) {
            right = left
            while (right < n && nums[right] == 0) {  // 找到右指针不为0的元素
                right++
            }
            println("swap n:$n, right:$right")
            if (right < n) {
                swap283(nums, left, right) // 左右指针元素交换
            }
        }
        if (right == n) {
            break
        }
    }

    nums.print()
}

fun swap283(nums: IntArray, left: Int, right: Int) {
    val temp = nums[left]
    nums[left] = nums[right]
    nums[right] = temp
}
