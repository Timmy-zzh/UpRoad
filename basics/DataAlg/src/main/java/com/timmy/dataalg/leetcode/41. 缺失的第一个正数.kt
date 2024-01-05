package com.timmy.dataalg.leetcode

/**
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。

示例 1：
输入：nums = [1,2,0]
输出：3

示例 2：
输入：nums = [3,4,-1,1]
输出：2

示例 3：
输入：nums = [7,8,9,11,12]
输出：1
 */
fun main() {
//        val res = firstMissingPositive(intArrayOf(1, 2, 0))
//        val res = firstMissingPositive(intArrayOf(3, 4, -1, 1))
//        val res = firstMissingPositive(intArrayOf(7, 8, 9, 11, 12))
//    val res = firstMissingPositive(intArrayOf(1, 2, 3, 10, 2147483647, 9))
    val res = firstMissingPositive(intArrayOf(1,2,3,10,2147483647,9))
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个数组，是未排序的，找出这个数组中未出现的最小正整数
 * 2、解题：
 * - 最小正整数，是不包括0的，
 * - 遍历数组，过滤0和负数，找出数组中最小的元素，分三种情况
 * -- 1、从1开始到后面的数字都在数组中出现，那结果在该范围的后面数字
 * -- 2、数据1存在，中间的数字存在缺失，所有需要找出这个数字
 * -- 3、数字1到前面的数字不存在，则结果是1
 * - 先遍历找出数组中的最小值，如果最小值大于1，则返回1
 * -- 如果最小值就是1，则找出最大值，使用boolean数组标记该数字是否出现过，未出现过则是所求结果
 */
fun firstMissingPositive(nums: IntArray): Int {
    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE
    for (i in nums.indices) {
        if (nums[i] <= 0) {
            continue
        }
        if (nums[i] < min) {
            min = nums[i]
        }
        if (nums[i] > max) {
            max = nums[i]
        }
    }

    println("min:$min,max:$max")

    if (min != 1) {
        return 1
    }

    /**
     * 创建数组
     * - 烹饪数组个数是最大值时，内存直接不够了
     * - 改用hashmap
     */

    //    val arr = BooleanArray(if (max >= Int.MAX_VALUE) Int.MAX_VALUE else max + 2) { false }
    val map = mutableMapOf<Int, Boolean>()
    for (i in nums.indices) {
        if (nums[i] <= 0) {
            continue
        }
        map[nums[i]] = true
    }
    println("map:$map")

   val size =  if (max >= Int.MAX_VALUE) Int.MAX_VALUE else max + 1
    for (i in 1..size) {
        if (!map.contains(i)) {
            return i
        }
    }

    return 1
}