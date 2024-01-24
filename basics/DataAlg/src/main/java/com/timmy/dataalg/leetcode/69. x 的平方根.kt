package com.timmy.dataalg.leetcode

/**
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。

示例 1：
输入：x = 4
输出：2

示例 2：
输入：x = 8
输出：2
解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 */
fun main() {

    //        val res = mySqrt(4)
    //        val res = mySqrt(8)
    //        val res = mySqrt(2)
    /**
     * 46341 的平方等于         2147488281
     * 46341 的平方等于         2147488281
     * Int的最大值为2的31次方-1： 2147483647
     */ //    val res = mySqrt(2147395600)
    val res = mySqrt(2147483647)
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个非负整数x，（0或正整数），求x的平方根
 * 2、解题
 * - 开平方根，是y*y = x,现在知道x，要求的y的值
 * -
 * 3、总结
 * - 遍历大小，因为x的最大值是Int.MAX,所以循环为Max、2
 * - Int值得范围，需要判断，将Int类型扩展为Long类型
 * 4、其他解法
 * - 二分查找法，也要判断相乘超过int最大值范围处理
 */
fun mySqrt(x: Int): Int {
    if (x == 0 || x == 1) {
        return x
    }
    val n = Int.MAX_VALUE / 2
    println("x:$x,n:$n")
    for (i in 1..n) {
        val sqrt: Long = i * i.toLong()
        if (sqrt == x.toLong()) {
            return i
        } else if (sqrt > x.toLong()) {
            return i - 1
        }
    }
    return 0
}