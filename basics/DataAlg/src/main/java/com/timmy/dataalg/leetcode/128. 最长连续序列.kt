package com.timmy.dataalg.leetcode

import java.lang.Integer.max


/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 * 提示：
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
fun main() {
    val res = longestConsecutive(intArrayOf(100, 4, 200, 1, 3, 2))
//    val res = longestConsecutive(intArrayOf(-1000000000, 1, 2, 3, 9, 1000000000))
    println("res:$res")
}

/**
 * 2、换个思路,
 * - 遍历数组中的元素,先将元素保存到set集合中,
 * - 二次遍历,查找当前元素-1是否是set集合中,
 * -- 如果在说明已经处理,不再遍历,
 * -- 如果不再,从当前元素开始继续往后遍历,直到后面连续的元素都已遍历过了,并记录长度
 * 3、总结: 找规律
 */
fun longestConsecutive(nums: IntArray): Int {
    val set = mutableSetOf<Int>()
    val n = nums.size

    for (i in 0 until n) {
        set.add(nums[i])
    }

    var res = 0
    for (i in 0 until n) {
        if (!set.contains(nums[i] - 1)) {   // 从当前元素作为新的区间范围,开始遍历
            var currNum = nums[i]
            var size = 1
            while (set.contains(currNum + 1)) {
                currNum++
                size++
            }
            res = max(res, size)
        }
    }
    return res
}

/**
 * 1、审题
 * - 输入一个数组,数组元素未排序,需要从数组中找出连续的元素的最长长度
 * 2、解题
 * - 1层遍历法,先找到数组中的最大,最小值,进行for循环
 * - 在遍历的过程中,判断元素是否存在,需要内层for循环
 * - 最后判断元素是否是连续的,需要通过标示判断,
 * -- 如何判断元素是连续的,使用一层for循环,使用map,先保存,
 * 3、超过时间限制
 * - -1000000000,1,2,3,9,1000000000
 * - 找出数组中的最小,最大值,然后进行遍历,会超过时间限制
 */
fun longestConsecutive1(nums: IntArray): Int {
    val n = nums.size
    val map = mutableMapOf<Int, Int>()
    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE
    for (i in 0 until n) {
        map[nums[i]] = nums[i]
        if (min > nums[i]) {
            min = nums[i]
        }
        if (max < nums[i]) {
            max = nums[i]
        }
    }

    var res = 0
    var item = 0
    for (i in min..max) {
        if (map.containsKey(i)) {
            item++
            if (res < item) {
                res = item
            }
        } else {
            item = 0
        }
    }
    return res
}

