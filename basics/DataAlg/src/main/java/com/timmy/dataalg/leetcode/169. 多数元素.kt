package com.timmy.dataalg.leetcode

/**
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
你可以假设数组是非空的，并且给定的数组总是存在多数元素。

示例 1：
输入：nums = [3,2,3]
输出：3

示例 2：
输入：nums = [2,2,1,1,1,2,2]
输出：2

提示：
n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109

进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
fun main() {
    //    val res = majorityElement(intArrayOf(3, 2, 3))

    val res = majorityElement(intArrayOf(2, 2, 1, 1, 1, 2, 2))
    println("res:$res")
}

/**
 * 1、审题
 * - 输入一个数组，数组元素由数字组成，返回多数元素，多数元素指在数组中该元素出现次数大于一半的数字[n/2]
 * 2、解题： 散列表解法
 * - 遍历数组元素，将遍历到的元素和对应出现的次数，保存到map中
 * - 判断元素出现的次数是否大于n/2,命中则该元素是多数元素，返回
 */
fun majorityElement(nums: IntArray): Int {
    val n = nums.size
    val map = HashMap<Int, Int>();

    for (i in 0 until n) {
        val num = nums[i]
        if (!map.contains(num)) {
            map[num] = 1
        } else {
            val count = map[num]
            map[num] = count!! + 1
        }
        println("map:$map")
        if (map[num]!! > n / 2) {
            return num
        }
    }
    return 0
}