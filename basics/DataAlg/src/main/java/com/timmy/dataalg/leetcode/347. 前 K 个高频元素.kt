package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import java.util.PriorityQueue

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。

示例 1:
输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]

示例 2:
输入: nums = [1], k = 1
输出: [1]

提示：
1 <= nums.length <= 105
k 的取值范围是 [1, 数组中不相同的元素的个数]
题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 */
fun main() {
    topKFrequent(intArrayOf(1,1,1,2,2,3),2)
}

/**
 * 1、审题：
 * - 输入一个数组和一个数字k，要求从数组中找出相同数字出现的频率，求频率出现最高的k个数
 * 2、解法
 * - for循环，找到每个数组出现的次数，使用map保存
 * - 再使用优先级队列，只保留出现次数value，靠前的k个数
 */
fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    nums.forEach {
        val num = map.getOrDefault(it, 0)
        map[it] = num + 1
    }

    val priorityQueue = PriorityQueue<Map.Entry<Int, Int>>(k) { o1, o2 -> o2!!.value - o1!!.value }

    map.forEach {
        val key = it.key
        val value = it.value
        println("key:$key , value:$value")

        priorityQueue.add(it)
    }

    val resList = mutableListOf<Int>()
//    priorityQueue.forEach {
//        println("priorityQueue.forEach :${it.key} , ${it.value}")
//        resList.add(it.key)
//    }

    for (i in 0 until k){
        val poll = priorityQueue.poll()!!
        resList.add(poll.key)
    }
    resList.print()
    return resList.toIntArray()
}