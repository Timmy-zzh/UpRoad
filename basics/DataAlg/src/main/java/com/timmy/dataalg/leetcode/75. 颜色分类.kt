package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
必须在不使用库内置的 sort 函数的情况下解决这个问题。

示例 1：
输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]

示例 2：
输入：nums = [2,0,1]
输出：[0,1,2]
 */
fun main() {
//    val nums = intArrayOf(2, 0, 2, 1, 1, 0)
    val nums = intArrayOf(2, 0, 1)
    nums.print()
    sortColors(nums)
    nums.print()
}

/**
 * 1、审题：
 * - 输入一个数组，数组元素值由0,1，2分别标识红色，白色和蓝色，
 * - 现在对数组元素进行排序，要求排序后的数组升序为红白蓝色，原地排序，不使用新的数组
 * 2、解题：
 * - 冒泡排序
 * - 快速排序
 * 3、总结
 * - 双指针解法，先找到元素0，再排序元素1
 * - 一次n循环遍历即可
 */
fun sortColors(nums: IntArray): Unit {
    val n = nums.size
    for (i in 0 until n) {
        for (j in 0 until n - i - 1) { // 比较j与j+1的值，并进行交换
            if (nums[j] > nums[j + 1]) {
                val temp = nums[j]
                nums[j] = nums[j + 1]
                nums[j + 1] = temp
            }
        }
    }
}