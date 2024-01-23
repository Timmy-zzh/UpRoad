package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
你可以假设除了整数 0 之外，这个整数不会以零开头。

示例 1：
输入：digits = [1,2,3]
输出：[1,2,4]
解释：输入数组表示数字 123。

示例 2：
输入：digits = [4,3,2,1]
输出：[4,3,2,2]
解释：输入数组表示数字 4321。

示例 3：
输入：digits = [0]
输出：[1]

 */
fun main() { //    val res = plusOne(intArrayOf(1,2,3))
    //    val res = plusOne(intArrayOf(4,3,2,1))
    //    val res = plusOne(intArrayOf(0))
    val res = plusOne(intArrayOf(4, 3, 2, 9))
    res.print()
}

/**
 * 1、审题：
 * - 输入一个由单个数字组成的数组，数组所有元素在一起就是一个完整的数字，
 * - 在该数字基础上增加1，将结果使用数组表示并返回
 * 2、解题
 * - 判断数组最后一位是否是数字9，是的话就需要进位了，不断从后往前遍历
 */
fun plusOne(digits: IntArray): IntArray {
    digits.print()

    val resArr = mutableListOf<Int>()
    val n = digits.size
    var offset = 0
    for (i in n - 1 downTo 0) {
        val num = digits[i]
        if (i == n - 1 && num != 9) {   // 最后一个数字不为9，最后一个数字加1，返回
            digits[i] = num + 1
            return digits
        }

        // 最后一个是9，需要增加，并进位处理
        val sum = if (i == n - 1) {
            num + offset + 1
        } else {
            num + offset
        }
        val realNum = sum % 10
        offset = sum / 10
        resArr.add(0, realNum)
    }
    if (offset != 0) {
        resArr.add(0, offset)
    }
    resArr.print()

    return resArr.toIntArray()
}