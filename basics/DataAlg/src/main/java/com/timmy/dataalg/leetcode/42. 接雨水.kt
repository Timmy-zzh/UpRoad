package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import kotlin.math.max
import kotlin.math.min

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

示例 1：
输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。

示例 2：
输入：height = [4,2,0,3,2,5]
输出：9
 */
fun main() {
    val res = trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1))
    println("res:$res")
}

/**
 * 1、审题
 * - 输入一个数组，数组的元素大小可以看成柱子的高度，柱子之间可以形成容器空间，用来接雨水
 * - 求這些数组形成的容器，可以接收的最大雨水量
 * 2、还不够高效，使用动态规划解法
 * - 我们在遍历柱子i的时候，当前柱子能装多少水，取决于柱子的左右两侧是否有阻挡？
 * -- 如果在柱子i的左右两侧都有比他更高的柱子时，当前柱子就可以装水，且水的多少是左右柱子最小值减去当前柱子的高度
 * - 通过动态规划dp数组，用来保存区域的最大值，也就是dp[i] 表示在区域[0,i]范围的最大值，这样就能快速的拿到在位置i时，左侧的最大值
 * -- 同样dp[i] 右侧[i,size]也通过同样规则，计算出右侧最大值
 * 3、其他解法： 单调栈
 */
fun trap(height: IntArray): Int {
    height.print()
    val size = height.size
    if (size < 3) {
        return 0
    }
    var res = 0
    val leftMax = IntArray(size) { 0 }
    leftMax[0] = height[0]
    for (i in 1 until size) {
        leftMax[i] = max(leftMax[i - 1], height[i])
    }

    val rightMax = IntArray(size) { 0 }
    rightMax[size - 1] = height[size - 1]
    for (i in size - 2 downTo 0) {
        rightMax[i] = max(rightMax[i + 1], height[i])
    }

    leftMax.print()
    rightMax.print()

    for (i in 0 until size) {
        res += min(leftMax[i], rightMax[i]) - height[i]
    }

    return res
}

fun trap3(height: IntArray): Int {
    height.print()
    val size = height.size
    if (size < 3) {
        return 0
    }
    var res = 0
    val maxVal = height.max()
    for (item in 1..maxVal) {
        println("第几层：$item")
        var isStart = false
        var itemSum = 0
        for (i in 0 until size) {
            if (height[i] < item && isStart) { // isStart说明之前已经有左侧阻挡了，
                itemSum++
            }

            /**
             * 碰到比层高item高的元素，说明找到了左侧阻挡
             * - isStart标记为true，中间小于item的空间，就是蓄水池空间，
             * - 当再次遇到比item高的元素，说明找到了左侧阻挡，将中间的蓄水池部分添加到res结果中
             * - 同时也作为左侧蓄水池阻挡，将itemSum设置为0
             */
            if (height[i] >= item) {
                isStart = true
                res += itemSum
                itemSum = 0
            }

        }
    }
    return res
}

/**
 * 1、审题
 * - 输入一个数组，数组的元素大小可以看成柱子的高度，柱子之间可以形成容器空间，用来接雨水
 * - 求這些数组形成的容器，可以接收的最大雨水量
 * 2、解题
 * - 两层遍历法，外层遍历查找单个容器的左侧高度，内层循环查找容器右侧高度
 * - 查找的左侧高度，需要比右侧高度要高，可能存在覆盖的问题，所以采用dp[i]的方式保存，动态变化容器的雨水量
 * 3、按行递增计算
 * - 找到数组元素的最大值，让后从第一行开始网上遍历，找寻每一行空闲的位置，空闲位置需要保证左右两侧都有阻挡才行
 * - Error超出时间限制
 */
fun trap2(height: IntArray): Int {
    height.print()
    val size = height.size
    if (size < 3) {
        return 0
    }
    var res = 0
    val maxVal = height.max()
    for (item in 1..maxVal) {
        println("第几层：$item")
        for (i in 1 until size - 1) {
            if (height[i] < item && checkRange(i, item, height)) { // 要保证当前元素的高度小于item的值，且左右两侧有阻挡
                res++
            }
        }
    }
    return res
}

fun checkRange(i: Int, item: Int, height: IntArray): Boolean {
    var left = i - 1
    var right = i + 1
    while (left >= 0 && right < height.size) {
        if (height[left] >= item && item <= height[right]) {
            println("left:$left,i:$i,right:$right")
            return true
        }
        if (height[left] < item) {
            left--
        }
        if (item > height[right]) {
            right++
        }
    }
    return false
}

/**
 * 1、审题
 * - 输入一个数组，数组的元素大小可以看成柱子的高度，柱子之间可以形成容器空间，用来接雨水
 * - 求這些数组形成的容器，可以接收的最大雨水量
 * 2、解题
 * - 两层遍历法，外层遍历查找单个容器的左侧高度，内层循环查找容器右侧高度
 * - 查找的左侧高度，需要比右侧高度要高，可能存在覆盖的问题，所以采用dp[i]的方式保存，动态变化容器的雨水量
 */
fun trap1(height: IntArray): Int {
    height.print()

    val size = height.size
    if (size < 3) {
        return 0
    }
    val dp = IntArray(size) { 0 }

    var left = 0
    for (right in 2 until size) {

        val rightVal = height[right]  // 往前查找容器左侧高度，需要大于等于才行,且中间间隔需要大于等于2
        println("left:$left, right:$right, rightVal:$rightVal")

        var index = right
        while (index >= left) {     // 在区间【left，right】之间找一个最大值
            if (height[index] > rightVal) {
                break
            }
            index--
        }
        println("index:$index, right:$right")

        // 求出区间【index，right】之间的容器大小
        if (right - index > 1) { // 需要有间隔才行
            var area = 0
            for (i in index + 1 until right) {
                area += (rightVal - height[i])
            }
            println("area:$area")
            dp[right] = dp[index] + area

            left = right
        } else {
            dp[right] = dp[right - 1]
        }
    }

    dp.print()

    return dp[size - 1]
}




