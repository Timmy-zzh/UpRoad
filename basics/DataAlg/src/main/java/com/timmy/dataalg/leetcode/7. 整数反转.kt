package com.timmy.dataalg.leetcode

import kotlin.math.abs

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
假设环境不允许存储 64 位整数（有符号或无符号）。

示例 1：
输入：x = 123
输出：321
示例 2：

输入：x = -123
输出：-321
示例 3：

输入：x = 120
输出：21
示例 4：

输入：x = 0
输出：0
 */
fun main() {
//    val res = reverse(-123)
    val res = reverse(120)
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个有符号的整数，就是说有可能给的是一个负数，现在需要返回这个整数的翻转结构，并保留符号
 * -- 题目要求不能存储64位的整数，是不让用long类型数据，不让用那怎么判断Int类型的区间范围呢？
 * 2、解题
 * - 先扩展类型，将int类型转换成long类型
 * - 判断是否是负数，负数的话，转成正整数
 * - 然后对正整数进行处理，从个位数从后往前处理，每次除于10，并使用结果数字接收，每次乘与10后再加上
 */
fun reverse(x: Int): Int {
    if (x == 0) return 0
    val isFuShu = x < 0
    var xL = x.toLong() // Long类型 //
    xL = abs(xL)
    var resL: Long = 0 // 存放结果的Long类型变量

    // 不断取出各个数字
    while (xL != 0L) {
        val num = xL % 10
        xL /= 10

        resL = resL * 10 + num

        println("xL:$xL,num:$num,resL:$resL")
    }
    resL = if (isFuShu) resL * -1 else resL
    if (resL > Int.MAX_VALUE || resL < Int.MIN_VALUE) {
        return 0
    }

    return resL.toInt()
}












