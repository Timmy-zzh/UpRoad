package com.timmy.dataalg.leetcode

/**
 * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。打乱后，数组的所有排列应该是 等可能 的。

实现 Solution class:
Solution(int[] nums) 使用整数数组 nums 初始化对象
int[] reset() 重设数组到它的初始状态并返回
int[] shuffle() 返回数组随机打乱后的结果

示例 1：
输入
["Solution", "shuffle", "reset", "shuffle"]
[[[1, 2, 3]], [], [], []]
输出
[null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
解释
Solution solution = new Solution([1, 2, 3]);
solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]

提示：
1 <= nums.length <= 50
-106 <= nums[i] <= 106
nums 中的所有元素都是 唯一的
最多可以调用 104 次 reset 和 shuffle
 */
fun main() {

}

/**
 * 1、审题：
 * - 输入一个数组，需要对数组进行打乱，打乱后返回一个数组，还能进行reset复原
 * 2、解题：
 * - 要能够复原，那肯定是要保持一个原始的数组才行，
 * - 核心是如何打乱，而且每次打乱得到的结果要求是机会相等，如何做？
 */
class Solution(nums: IntArray) {

    lateinit var srcList: IntArray

    init {
        srcList = nums
    }

    fun reset(): IntArray {
        return srcList
    }

    // 新创建一个数组，并返回,采用随机数，随机地从原始数组中获取一个元素，直到所有元素都获取完了
    fun shuffle(): IntArray {
        val resList = mutableListOf<Int>()
        val visited = Array(srcList.size) { false }
        createList(srcList, resList, 0, visited)

        return resList.toIntArray()
    }

    private fun createList(srcList: IntArray, resList: MutableList<Int>, i: Int,
        visited: Array<Boolean>) {
        if (i == srcList.size) {
            return
        }
        var randomIndex: Int
        while (true) {
            randomIndex = (srcList.indices).random()
            if (visited[randomIndex]) {
                continue
            }
            visited[randomIndex] = true
            resList.add(srcList[randomIndex])
            break
        }
        createList(srcList, resList, i + 1, visited)
    }

}