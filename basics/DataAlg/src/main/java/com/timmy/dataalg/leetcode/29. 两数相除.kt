package com.timmy.dataalg.leetcode

import kotlin.math.abs

/**
 * 给你两个整数，被除数 dividend 和除数 divisor。将两数相除，要求 不使用 乘法、除法和取余运算。
整数除法应该向零截断，也就是截去（truncate）其小数部分。例如，8.345 将被截断为 8 ，-2.7335 将被截断至 -2 。
返回被除数 dividend 除以除数 divisor 得到的 商 。
注意：假设我们的环境只能存储 32 位 有符号整数，其数值范围是 [−231,  231 − 1] 。本题中，如果商 严格大于 231 − 1 ，
则返回 231 − 1 ；如果商 严格小于 -231 ，则返回 -231 。

示例 1:
输入: dividend = 10, divisor = 3
输出: 3
解释: 10/3 = 3.33333.. ，向零截断后得到 3 。

示例 2:
输入: dividend = 7, divisor = -3
输出: -2
解释: 7/-3 = -2.33333.. ，向零截断后得到 -2 。

 */
fun main() { //    val res = divide(10, 3)
    //    val res = divide(7, -3)
//    val res = divide(-2147483648, -1)
    val res = divide(-2147483648, 2)
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入两个整数，进行相除，要求不能使用乘法，除法，取余运算，可以使用加减法了，求出最后相除的结果并返回
 * 2、解题：
 * - 使用减法，除数不断减去被除数，while循环值不能小于0
 * - 正负号问题
 * - 32位大小限制，
 * - 不用考虑被除数为0
 * 3、数值范围处理：
 * - 数值范围是 [−231,  231 − 1]
 * -- -2的31次方 = -2147483648
 * -- 2的31次方再减去1 = 2147483647
 */
fun divide(dividend: Int, divisor: Int): Int {
    var sign = 1
    sign = if (dividend < 0) (sign * -1) else sign
    sign = if (divisor < 0) (sign * -1) else sign
    var res = 0

    // 如果是最小值 Int.MIN_VALUE -》 转换成绝对值还是最小值，转换不了了
    var dividendAbs = abs(dividend)
    val divisorAbs = abs(divisor)
    println("dividend:$dividend ，dividendAbs：$dividendAbs,divisor:$divisor ,divisorAbs：$divisorAbs,")

    if (divisorAbs == 1 && dividend == Int.MIN_VALUE) {
        if (divisor == -1) {
            return Int.MAX_VALUE
        }
        return dividendAbs * sign
    }

    println("dividendAbs:$dividendAbs ,divisorAbs:$divisorAbs")
    while (dividendAbs >= divisorAbs) {
        res++
        dividendAbs -= divisorAbs
    }

    return res * sign
}