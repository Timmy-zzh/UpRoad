package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

示例 1：
输入：nums = [1,2,3]
输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

示例 2：
输入：nums = [0]
输出：[[],[0]]

 */
fun main() {
    val res = subsets(intArrayOf(1, 2, 3))
    res.print()
}

/**
 * 1、审题
 * - 输入一个整数数组，数组元素不同，求数组的所有子集，需要包含空子集
 * 2、解题
 * - 查找所有的数组子集，要求子集元素不能相同
 * - 有两个问题：
 * -- a、如何遍历查找所有的子集？
 * -- b、如果过滤相同的子集？
 * - 回溯，递归解法，从第0个字符开始遍历
 * 3、总结：
 * - 回溯法，效率太低了，需要减枝处理
 */
fun subsets(nums: IntArray): List<List<Int>> {
    val resList = mutableListOf<List<Int>>()

    val itemList = mutableListOf<Int>()
    subsetsReal(0, nums.size, nums, itemList, resList)

    return resList
}

/**
 * 1、这样处理，速度100%
 */
fun subsetsReal(i: Int, n: Int, nums: IntArray, itemList: MutableList<Int>,
    resList: MutableList<List<Int>>) {
    println("i=$i,n=$n,item:$itemList")
    val newList = ArrayList<Int>(itemList) // 指针引用，需要重新复制一份数据
    resList.add(newList)
    if (i == n) {
        return
    }

    for (j in i until n) { // 对于每个元素，每次有两种选择方式，选择，或则不元素
        itemList.add(nums[j])
        subsetsReal(j + 1, n, nums, itemList, resList)
        itemList.remove(nums[j])
    }
}

fun subsetsReal2(i: Int, n: Int, nums: IntArray, itemList: MutableList<Int>,
    resList: MutableList<List<Int>>) {
    println("i=$i,n=$n,item:$itemList")
    val newList = ArrayList<Int>(itemList) // 指针引用，需要重新复制一份数据
    if (!resList.contains(newList)) {
        resList.add(newList)
    }
    if (i == n) {
        return
    }

    for (j in i until n) { // 对于每个元素，每次有两种选择方式，选择，或则不元素
        itemList.add(nums[j])
        subsetsReal2(j + 1, n, nums, itemList, resList)
        itemList.remove(nums[j])
    }
}

fun subsetsReal1(i: Int, n: Int, nums: IntArray, itemList: MutableList<Int>,
    resList: MutableList<List<Int>>) {
    println("i=$i,n=$n,item:$itemList")
    if (i == n) {
        val newList = ArrayList<Int>(itemList) // 指针引用，需要重新复制一份数据
        if (!resList.contains(newList)) {
            resList.add(newList)
        }
        return
    }

    for (j in i until n) { // 对于每个元素，每次有两种选择方式，选择，或则不元素
        subsetsReal1(j + 1, n, nums, itemList, resList)

        itemList.add(nums[j])
        subsetsReal1(j + 1, n, nums, itemList, resList)
        itemList.remove(nums[j])
    }
}










