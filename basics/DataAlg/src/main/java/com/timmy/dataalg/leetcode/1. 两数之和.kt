package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
你可以按任意顺序返回答案。

示例 1：
输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。

示例 2：
输入：nums = [3,2,4], target = 6
输出：[1,2]

示例 3：
输入：nums = [3,3], target = 6
输出：[0,1]
 */
fun main() {
    val res = twoSum(intArrayOf(2, 7, 11, 15), 9)
    res.print()
}

/**
 * 1、审题：输入一个数组和一个目标值，从数组中取出两个元素，元素和为目标值，返回这两个元素的对应下标
 * 2、解题：
 * - 双层遍历法：第一层遍历到数组中的单个元素，第二层遍历后面的元素中是否存在差值，时间复杂度为n的平方
 * - 散列表法：每遍历完一个元素，将该元素值作为键，元素的下标作为值保存到map中；
 * -- 再遍历元素的时候，先从散列表map中查询是否存在该元素的差值，是的话返回下标的数组
 */
fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    for (i in nums.indices) {
        if (map.containsKey(target - nums[i])) {
            return intArrayOf(i, map[target - nums[i]]!!)
        }
        map[nums[i]] = i
    }
    return intArrayOf()
}