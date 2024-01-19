package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
子数组 是数组中的一个连续部分。

示例 1：
输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。

示例 2：
输入：nums = [1]
输出：1

示例 3：
输入：nums = [5,4,-1,7,8]
输出：23
 */
fun main() {

    val res = maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4))

    //    val res = maxSubArray(intArrayOf(5, 4, -1, 7, 8))

    //    val res = maxSubArray(intArrayOf(-1))
    println("res:$res")
}

/**
 * 2、解法：
 * - 还的是动态规划解法，采用一位数组
 * - 状态转移方程式： dp[i] 表示已数组下标i元素为结束区间值，子数组的最大和
 * - 所以存在关联关系如下： 如果 dp[i-1]大于0，则dp[i]= dp[i-1]+nums[i], 否则 dp[i]=nums[i]
 */
fun maxSubArray(nums: IntArray): Int {
    val n = nums.size
    val dp = IntArray(n) { 0 }
    dp[0] = nums[0]

    for (i in 1 until n) {
        if (dp[i - 1] > 0) {
            dp[i] = dp[i - 1] + nums[i]
        } else {
            dp[i] = nums[i]
        }
    }

    dp.print()

    var res = dp[0]
    for (i in 1 until n) {
        if (res < dp[i]) {
            res = dp[i]
        }
    }
    return res
}

/**
 * 2、解题
 * - 动态规划，使用二维数组太占内存空间了，使用单调栈应该可以实现
 * - 遍历数组元素，使用双指针法，left指针定义了子数组的下标位置，右侧指针是数组的遍历元素位置
 * - 右侧指针不断往右扩展，子数组的和不断叠加上数组右侧元素，左侧元素的变化，需要根据左侧指针的元素是否大于0还是小于0
 * -- 如果小于0，那就可以挪动了，如果大于0，是否需要挪动呢？因为会存在left元素大于0，但是left+1元素值小于0，且是个很小的值
 * -- 这种情况下怎么处理好，
 * 3、总结
 * - 两层for循环还是不行，超过实现限制了，存在太多重复计算了，
 * - 得想办法取出这么多重复计算，怎么实现呢？
 */
fun maxSubArray2(nums: IntArray): Int {
    var res = nums[0]
    var left = 0
    val n = nums.size
    for (i in 1 until n) {
        /**
         * 以i为子数组右侧指针点；
         * - 不断往前遍历知道left位置，查找每个区间的最大值，和对应的left的位置
         * - 这个最大值就是sum的最大值，而left也是区间的左侧位置
         */
        var sum = nums[i]
        var leftMax = i
        var sumMax = nums[i]
        for (j in i - 1 downTo left) {
            sum += nums[j]
            if (sumMax < sum) {
                sumMax = sum
                leftMax = j
            }
        }

        // 计算最后的sum值，并与res比较
        if (res < sumMax) {
            res = sumMax
            left = leftMax
        }
    }
    return res
}

/**
 * 1、审题：
 * - 输入一个数组，元素由整数组成，有正有负，需要在数组中找到一个连续子数组，其和最大并返回
 * 2、解题
 * - 要求：连续子数组，和最大
 * - 动态规划解法：推导方程式 二维数组
 * - 数组结果值dp[i][j]：表示原始数组元素第i个到第j个的子数组的和
 * - 二维数组的左下角部分不可使用，因为i比j大，只能计算i比j的区域，每行从分割线开始计算
 * 3、问题：
 * - 数组元素过多，dp二维数组数量太大，会超出内存限制
 * - 一维数组能够实现吗？
 */
fun maxSubArray1(nums: IntArray): Int {
    val n = nums.size
    val dp = Array(n) {
        IntArray(n) { 0 }
    }
    dp.print()

    // 对角线赋值
    for (i in 0 until n) {
        dp[i][i] = nums[i]
    }

    // 右上区域部分
    for (i in 0 until n) {
        for (j in i + 1 until n) {
            dp[i][j] = dp[i][j - 1] + nums[j]
        }
    }
    dp.print()

    var res = Int.MIN_VALUE
    for (i in 0 until n) {
        for (j in i until n) {
            if (res < dp[i][j]) {
                res = dp[i][j]
            }
        }
    }
    return res
}