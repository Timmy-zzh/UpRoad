package com.timmy.dataalg._01array

import com.timmy.dataalg.print


/**
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
你返回所有和为 0 且不重复的三元组。
注意：答案中不可以包含重复的三元组。

示例 1：
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
解释：
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
注意，输出的顺序和三元组的顺序并不重要。

示例 2：
输入：nums = [0,1,1]
输出：[]
解释：唯一可能的三元组和不为 0 。

示例 3：
输入：nums = [0,0,0]
输出：[[0,0,0]]
解释：唯一可能的三元组和为 0 。
 */
fun main() {
    println("15. 三数之和")
//    val nums = intArrayOf(-1, 0, 1, 2, -1, -4)
    val nums = intArrayOf(1, -1, -1, 0)
    val res = threeSum(nums)

    println("res:")
    for (list in res) {
        list.print()
    }
}

/**
 * 解法：
 * - 先升序排序
 * - 双指针解法
 * - 三层循环必须要有，因为已经是排序了的，第一层循环可以过滤掉相同的元素i
 * - 后面的第二个，第三个元素，在第一层元素i位置后取得，我们从后面的元素中选择两个，
 * --左边第二个元素从i+1开始往后，右边第三个元素从n-1开始往前，并判断和，
 * - 左右两个元素不断while迭代处理
 */
fun threeSum(nums: IntArray): List<List<Int>> {
    nums.print()
    if (nums.size < 3) {
        return listOf(listOf())
    }
    val sorted = nums.sorted()
    sorted.print()

    val resMutableList = mutableListOf<List<Int>>()
    for (i in sorted.indices) {

        println("i=$i,num=${sorted[i]}")
        if (i > 0 && sorted[i] == sorted[i - 1]) {
            // 第一层元素，相同的话，进行过滤
            continue
        }

        var left = i + 1
        var right = sorted.size - 1
        println("left=$left,right=$right")
        while (left < right) {
            val num1 = sorted[i]
            val num2 = sorted[left]
            val num3 = sorted[right]
            println("i=$i,num1=${sorted[i]}")
            println("left=$left,num2=${sorted[left]}")
            println("right=$right,num3=${sorted[right]}")
            val sum = num1 + num2 + num3

            if (sum == 0) {
                resMutableList.addAll(listOf(listOf(num1, num2, num3)))
                right--
                while (right > 0 && sorted[right] == sorted[right + 1]) {
                    right--
                }

                left++
                while (left < sorted.size && sorted[left] == sorted[left - 1]) {
                    left++
                }
            } else if (sum > 0) {
                // 右侧左移，去重
                right--
                while (right > 0 && sorted[right] == sorted[right + 1]) {
                    right--
                }
            } else {
                left++
                while (left < sorted.size && sorted[left] == sorted[left - 1]) {
                    left++
                }
            }
        }
    }

    return resMutableList
}

/**
 * 审题：输入一个数组，从数组中找出三个元素，三个元素的和为0，且三个元素不能相同
 * 解法：三层for循环，
 * - 判断三个数相加的和，并且要去除三个元素相等的情况
 * 有问题：因为存在相同的元素，会存在两个三元组选中的三个元素相同，
 * - 需要先进行排序，然后进行过滤
 */
fun threeSumE(nums: IntArray): List<List<Int>> {
    if (nums.size < 3) {
        return listOf(listOf())
    }
    val resMutableList = mutableListOf<List<Int>>()

    for (i in nums.indices) {
        for (j in nums.indices) {
            for (k in nums.indices) {
                val num1 = nums[i]
                val num2 = nums[j]
                val num3 = nums[k]
                if (num1 != num2 && num2 != num3 && num1 != num3 && (num1 + num2 + num3 == 0)) {
                    resMutableList.addAll(listOf(listOf(num1, num2, num3)))
                }
            }
        }
    }
    return resMutableList
}