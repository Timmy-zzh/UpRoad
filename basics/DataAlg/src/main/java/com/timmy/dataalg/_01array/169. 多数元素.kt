package com.timmy.dataalg._01array

/**
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
你可以假设数组是非空的，并且给定的数组总是存在多数元素。

示例 1：
输入：nums = [3,2,3]
输出：3

示例 2：
输入：nums = [2,2,1,1,1,2,2]
输出：2

 */
fun main() {
    println("169. 多数元素")

}

/**
 * 审题：输入一个数组，找出其中的一个元素，该元素在数组中出现的次数超过数组元素个数的一半以上
 * 解题：
 * - 遍历数组，将该元素和元素出现的次数+1，保存在map中
 * - 遍历map查找次数超过一半的元素返回
 */
fun majorityElement(nums: IntArray): Int {
    val map = mutableMapOf<Int, Int>()

    for (num in nums) {
        val size = map.getOrDefault(num, 0)
        map[num] = size + 1
    }
    for (entry in map) {
        if (entry.value > nums.size / 2) {
            return entry.key
        }
    }
    return 0
}