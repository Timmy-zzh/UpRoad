package com.timmy.dataalg._01array

/**
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。

示例 1：
输入：nums = [1,2,0]
输出：3
示例 2：

输入：nums = [3,4,-1,1]
输出：2
示例 3：

输入：nums = [7,8,9,11,12]
输出：1

 */
fun main() {
    val nums = intArrayOf(3, 4, -1, 1)
    val res = firstMissingPositive(nums)
    println("res=$res")
}

/**
 * 审题：输入一个数组，里面包含整数（正整数和负整数），要求找出缺失的最小正整数
 * - 如输入0,1,2，那就缺少3
 * - 输入 -1,1,3,4， 缺失的就是2
 * - 输入 7、8、9、11、12，缺失的最小正整数就是1
 * 解决思路：
 * - 先遍历找出最大的数字，并保存在数组中
 * - 然后遍历数组，从1开始遍历，元素为空就是我们要找到
 *
 * 出错：[2147483647]
 * -》Line 4: Exception in thread "main" java.lang.NegativeArraySizeException
 */
fun firstMissingPositive(nums: IntArray): Int {
    val maxNum = nums.max()
    val array: IntArray = IntArray(maxNum + 1)

    for (i in array.indices) {
        array[i] = Int.MIN_VALUE
    }

    for (i in nums.indices) {
        if (nums[i] >= 0)
            array[nums[i]] = nums[i]
    }

    for (j in 1 until maxNum + 1) {
        if (array[j] == Int.MIN_VALUE) {
            return j
        }
    }
    return maxNum + 1
}