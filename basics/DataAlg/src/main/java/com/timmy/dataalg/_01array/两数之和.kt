package com.timmy.dataalg._01array

import com.timmy.dataalg.print

/**
 * 1、两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
//你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
//你可以按任意顺序返回答案。
//
//示例 1：
//输入：nums = [2,7,11,15], target = 9
//输出：[0,1]
//解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
//
//示例 2：
//输入：nums = [3,2,4], target = 6
//输出：[1,2]
//示例 3：
//
//输入：nums = [3,3], target = 6
//输出：[0,1]
 */
fun main() {
    val nums = intArrayOf(2, 7, 11, 15)
    val res = twoSum(nums, 9)
    res.print()
}

/**
 * 审题：输入一个数组和目标值，从数组中找出两个元素下标返回，这两个元素的和需要等于目标值target
 * 解题：
 * 1、双层遍历法，
 * - 第一层遍历找到一个元素，使用target减去元素值，在只有的元素中查找是否有等于差值的元素，有则组合成数组返回
 * 2、Hash表解法：
 * - 把遍历过得元素都保存起来，在遍历后面的元素的时候，先判断target减去该元素，在hash表中是否存在
 * - 空间换时间
 *
 * 总结：
 * - 按照第一种解法，外层遍历到一个元素，内层遍历都要遍历随后的元素，这样会出现一个区间会重复遍历多次
 * -- 这种场景，就可以考虑使用缓存的方式了，使用hashmap进行保存
 */
fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    for (i in nums.indices) {
        if (map.containsKey(target - nums[i])) {
            val j = map.getValue(target - nums[i]) // map[target - nums[i]]
            return intArrayOf(j, i)
        }
        map[nums[i]] = i
    }
    return intArrayOf()
}

fun twoSum2(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    for (i in nums.indices) {
        if (map.containsKey(target - nums[i])) {
            val j = map.getValue(target - nums[i]) // map[target - nums[i]]
            return intArrayOf(j, i)
        }
        map[nums[i]] = i
    }
    return intArrayOf()
}

fun twoSum1(nums: IntArray, target: Int): IntArray {
    for (i in nums.indices) {
//        println("i = $i")
        val num2 = target - nums[i]
        for (j in (i + 1..nums.lastIndex)) {
//            println("j = $j")
            if (num2 == nums[j]) {
                return intArrayOf(i, j)
            }
        }
    }
    return intArrayOf()
}