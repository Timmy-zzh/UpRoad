package com.timmy.dataalg.leetcode

import kotlin.math.max
import kotlin.math.min

/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
返回容器可以储存的最大水量。
说明：你不能倾斜容器。

示例 1：
输入：[1,8,6,2,5,4,8,3,7]
输出：49
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

示例 2：
输入：height = [1,1]
输出：1
 */
fun main() {
        val res = maxArea(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7))
//    val res = maxArea(intArrayOf(1, 1))
    println("res:$res")
}

/**
 * 1、保存左侧最高的柱子，好像也不行，还有距离，最后求的是面积
 * - 双指针解法：
 * - 使用左右两个指针分别指向容器的两侧，表示当前容器的容量
 * - 不断移动两侧短板的位置，并向内靠近
 */
fun maxArea(height: IntArray): Int {
    var i = 0
    var j = height.size - 1
    var maxRes = 0
    while (i < j) {
        var area: Int
        val width = j - i
        if (height[i] < height[j]) {
            area = height[i] * width
            i++
        } else {
            area = height[j] * width
            j--
        }
        maxRes = max(maxRes, area)
    }
    return maxRes
}

/**
 * 1、审题：
 * - 输入一个数组，每个数组元素表示一个当前柱子的高度，从数组中所有柱子中选择两个柱子组成容器，里面可以容纳最多的水
 * 2、解题：
 * - 两层for循环法
 * -- 外层循环定位容器的右侧柱子，内层定位容器左侧柱子，在右侧柱子不动，左侧柱子变化的情况下容器的最大值
 * 3、疑问：
 * - 当数组为空时，数组只有一个元素是是否需要特殊处理
 * - 例子1中，如果最左侧的y轴可以算作容器壁的话，那最大值就是7*9=63 --》不能以y轴作为容器壁，
 * - 数组大小大于等于2
 * 4、时间复杂度：O(n平方)，超过时间限制
 */
fun maxArea1(height: IntArray): Int {
    var maxRes = 0
    val size = height.size
    for (i in 1 until size) {
        val right = height[i]
        for (j in 0 until i) {
            val left = height[j]
            val area = (i - j) * min(left, right)
            if (area > maxRes) {
                maxRes = area
            }
        }
    }
    return maxRes
}

















