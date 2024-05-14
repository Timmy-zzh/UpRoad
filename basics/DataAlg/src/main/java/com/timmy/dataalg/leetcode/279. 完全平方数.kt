package com.timmy.dataalg.leetcode

import kotlin.math.min
import kotlin.math.sqrt

/**
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。

示例 1：
输入：n = 12
输出：3
解释：12 = 4 + 4 + 4

示例 2：
输入：n = 13
输出：2
解释：13 = 4 + 9

提示：
1 <= n <= 104
 */
fun main() {
    val res = numSquares(6405)
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个数字n，n是多个完全平方数的和，求完全平方数的数量最少的个数值，并返回
 * - 完全平方数：是一个数字等于另外一个数的平方值，
 * 2、解题：
 * - 回溯法
 * - 对数字n，不断取平方根的值，最不济也能减少1，反正一定有一个结果值
 * - 我们要求一个最小值
 */
var res279 = Int.MAX_VALUE
fun numSquares(n: Int): Int {
    numSquaresBack(n, 0)
    return res279
}

fun numSquaresBack(n: Int, i: Int) {
    println("numSquaresBack n:$n, i:$i")
    if (n <= 0 || res279 <= i) {
        if (n == 0) {
            res279 = min(res279, i)
        }
        return
    }

    // 求n的平方根
    val sqrt = sqrt(n.toDouble()).toInt()
    println("numSquaresBack --- sqrt:$sqrt")
    for (x in sqrt downTo 1) {
        numSquaresBack(n - x * x, i + 1)
    }
}
