package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

示例 1：
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

示例 2：
输入：nums = [0,1]
输出：[[0,1],[1,0]]

示例 3：
输入：nums = [1]
输出：[[1]]
 */
fun main() {
    val permute = permute(intArrayOf(1, 2, 3))
    permute.print()
}

/**
 * 1、审题：
 * - 输入一个没有重复元素的数组，要求重新排列成一个新的数组，这不就是n的阶乘嘛
 * 2、解题：
 * - 递归，回溯算法，分治
 * - 将数组元素分为两个部分，每次遍历数组元素时，判断当前遍历的元素是否已经选过了
 * - 没选中过，则选泽，并将选中的元素保存到set集合中
 * - 最后将遍历到所有情况保存到结果中
 */
val resList = mutableListOf<List<Int>>()
fun permute(nums: IntArray): List<List<Int>> {
    val itemList = mutableListOf<Int>()
    permuteReal(0, nums, itemList)
    return resList
}

fun permuteReal(i: Int, nums: IntArray, itemList: MutableList<Int>) {
    println("permuteReal=== i:$i")
    if (i == nums.size) { // copy
        val item = mutableListOf<Int>()
        itemList.forEach {
            item.add(it)
        }
//        itemList.print()
        resList.add(item)
        return
    }

    for (j in nums.indices) {
        val num = nums[j]
        if (!itemList.contains(num)) {
            itemList.add(num)
            permuteReal(i + 1, nums, itemList)
            itemList.remove(num)
        }
    }
}
