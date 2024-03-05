package com.timmy.dataalg.leetcode

/**
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。

示例 1 ：
输入：nums = [2,2,1]
输出：1

示例 2 ：
输入：nums = [4,1,2,1,2]
输出：4

示例 3 ：
输入：nums = [1]
输出：1

提示：
1 <= nums.length <= 3 * 104
-3 * 104 <= nums[i] <= 3 * 104
除了某个元素只出现一次以外，其余每个元素均出现两次。
 */
fun main() { //    val res = singleNumber(intArrayOf(2,2,1))
    val res = singleNumber(intArrayOf(4, 1, 2, 1, 2))
    println("res:$res")
}

fun singleNumber(nums: IntArray): Int {
    var res = 0
    for (i in nums.indices) {
        res = res xor nums[i]
    }
    return res
}

/**
 * 1、审题：
 * - 输入一个数组，数组中的元素只有一个元素值出现一次，其他元素值都出现了两次
 * - 找出这个只出现一次的元素值，并返回
 * 2、解题：
 * - 使用散列表保存遍历的元素，采用单层for循环
 * - 之前没有存在的则保存，二次出现则删除（set）
 * 3、优化
 * - 异或运算 xor，两个相同的元素异或后结果等于二进制0
 */
fun singleNumber1(nums: IntArray): Int {
    val set = mutableSetOf<Int>()
    for (i in nums.indices) {
        if (set.contains(nums[i])) {
            set.remove(nums[i])
        } else {
            set.add(nums[i])
        }
    }
    return set.single()
}