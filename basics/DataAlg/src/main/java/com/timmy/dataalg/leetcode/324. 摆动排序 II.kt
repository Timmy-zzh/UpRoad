package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
你可以假设所有输入数组都可以得到满足题目要求的结果。

示例 1：
输入：nums = [1,5,1,1,6,4]
输出：[1,6,1,5,1,4]
解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。

示例 2：
输入：nums = [1,3,2,2,3,1]
输出：[2,3,1,3,1,2]

提示：
1 <= nums.length <= 5 * 104
0 <= nums[i] <= 5000
题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果

进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 */
fun main() {
    wiggleSort(intArrayOf(1, 5, 1, 1, 6, 4)) //    wiggleSort(intArrayOf(1,3,2,2,3,1))

}

/**
 * 1、审题：
 * - 输入一个数组，要求对数组元素进行重新排列，最终结果是元素值的大小是波动排列的
 * - 位置0小，位置1大，位置2小，位置3大 。。。。
 * 2、解题：
 * - 题目要求，nums数组位置i[0 ~ size-1]的元素大小，i为偶数，他就小于左右的元素，i为奇数，就大于左右位置的元素
 * - 怎么做呢？？？
 * - 遇到相同大小的元素怎么处理？
 * - 回溯算法进行处理，遍历出所有可以的方案，然后进行裁剪，
 */
fun wiggleSort(nums: IntArray): Unit {
    val n = nums.size
    val visited = Array(n) { false }
    val copyNums = Array(n) { 0 }     // 复制一份原始数组，数据不动的
    for (i in 0 until n) {
        copyNums[i] = nums[i]
    }

    for (i in 0 until n) {
        visited[i] = true
        val count = 0
        nums[count] = copyNums[i]
        wiggleSortBack(nums, copyNums, visited, count, n)
        visited[i] = false
    }

    println("last===========")
    for (j in 0 until n){
        nums[j]= resList324[j]
    }
    nums.print()
}

/**
 * i: 为当前遍历的数组元素位置
 * n：为数组大小
 */
var res324: Boolean = false
var resList324 = mutableListOf<Int>()
fun wiggleSortBack(nums: IntArray, copyNums: Array<Int>, visited: Array<Boolean>, count: Int,
    n: Int) {
    if (res324) {
        return
    }
    println("count--:$count")
    print("nums--:")
    nums.print()
    if (count > 0 && !checkWiggle(nums, count)) { // 校验当前处理的元素
        return
    }
    if (count == n - 1) {   // 说明知道了最终的结果，不必再往下遍历了
        res324 = true
        for (j in 0 until n){
            resList324.add(nums[j])
        }
        return
    }

    for (i in 0 until n) {
        if (visited[i]) {
            continue
        }
        visited[i] = true
        val newCount = count + 1
        nums[newCount] = copyNums[i]
        wiggleSortBack(nums, copyNums, visited, newCount, n)
        visited[i] = false
    }

}

// 当前遍历到第 i 个位置的元素， 判断他与前面元素的大小
fun checkWiggle(nums: IntArray, i: Int): Boolean {
    return if (i % 2 == 1) {   // 奇数位置 -》 大
        nums[i] > nums[i - 1]
    } else {
        nums[i] < nums[i - 1]
    }
}
