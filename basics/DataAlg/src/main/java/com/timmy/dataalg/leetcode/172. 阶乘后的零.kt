package com.timmy.dataalg.leetcode

/**
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1

示例 1：
输入：n = 3
输出：0
解释：3! = 6 ，不含尾随 0

示例 2：
输入：n = 5
输出：1
解释：5! = 120 ，有一个尾随 0

示例 3：
输入：n = 0
输出：0

提示：
0 <= n <= 104
进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？
 */
fun main() {
    val res = trailingZeroes(30)
    println("res:$res")
}

/**
 * 1、审题
 * - 深入一个数字，求数字的阶乘，返回阶乘结果后面带有的0的个数
 * 2、解题
 * - 阶乘结果为0，只有两种情况，一个是当前数字带0，将0的个数取出来，
 * - 还有一种情况是数字尾部为2与5，相乘结果会出现0，这种情况下也会出现一个0
 * 3、问题
 * - 25和30的阶乘结果不对 TODO
 */
fun trailingZeroes(n: Int): Int {
    var res = 0
    var tag = 0
    for (i in n downTo 1) {
        var num = i
        while (num % 10 == 0) {
            res++
            num /= 10
        }
        val numStr = num.toString()
        if (numStr.endsWith("2") || numStr.endsWith("5")) {
            tag++
            if (tag % 2 == 0) {
                res++
            }
            println("endsWith num:$num, numStr:$numStr,tag:$tag,res:$res")
        }
        println("num:$num, numStr:$numStr,tag:$tag,res:$res")
    }
    return res
}