package com.timmy.dataalg.leetcode

import kotlin.math.abs

/**
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。

示例 1：
输入：x = 2.00000, n = 10
输出：1024.00000

示例 2：
输入：x = 2.10000, n = 3
输出：9.26100

示例 3：
输入：x = 2.00000, n = -2
输出：0.25000
解释：2-2 = 1/22 = 1/4 = 0.25
 */
fun main() { //    val res = myPow(2.0, 10)
    //    val res = myPow(2.0, -2)
    //    val res = myPow(2.10, 3)
    //    val res = myPow(0.00001, 2147483647)
    val res = myPow(2.00000,
        -2147483648) // 超出时间限制 //    val res = myPow(1.00000, -2147483648) //    val res = myPow(-1.00000, 2147483647) //    val res = myPow(1.0000000000001, -2147483648)   // 解答错误
    println("res:$res")
}


fun myPow(x: Double, n: Int): Double {
    val N: Long = n.toLong()

    return if (N >= 0) {
        myPowReal(x, N)
    } else {
        /**
         * x的n次方，如果n小于零，结果是（1/x）的|n|次方
         * 也就是 1/(x的|n|) 此房的小数
         */
        1 / myPowReal(x, -N)
    }
}

fun myPowReal(x: Double, N: Long): Double {
    if (N == 0L) {
        return 1.0
    }
    val y = myPowReal(x, N / 2)
    return if (N % 2 == 0L) {  // 偶数
        y * y
    } else { // 奇数
        x * y * y
    }
}

/**
 * 2.1、遍历的方式
 *  val res = myPow(2.00000, -2147483648) // 超出时间限制
 *  3、总结：
 *  - 当遇到Int的最大值最小值时，for循环遍历需要执行Max次，会出现超时情况
 *  == 解决：采用数学公式，因为x的n次方，等于x的n/2 再乘以 x的n/2次方，之前要遍历n次的计算，现在只需要遍历n/2遍
 *  === 偶数是两个n/2, 奇数是两个n/2再乘与x
 *  == 最大值问题，题目没说只能用32位的int类型，所以可以将n扩展为long类型，这样就不能管Int.MIN最小值取反会超过范围了
 */
fun myPow2(x: Double, n: Int): Double {
    if (x == 1.0 || x == -1.0) {
        if (n <= Int.MIN_VALUE) {
            return x
        }
        return x
    }
    if (n == 0) {
        return 1.0
    }
    var xR = x
    var nR = n
    if (n < 0) {
        xR = 1 / x
        if (n == Int.MIN_VALUE) {
            nR = Int.MAX_VALUE
        } else {
            nR = abs(n)     // 最小值，abs的值编程-1了
        }
    }
    var res: Double = 1.0
    for (i in 0 until nR) {
        res *= xR
    }
    return res
}

/**
 * 1、审题：
 * - 求x的n次幂，
 * 2、解题：
 * - 递归，f(n) = x * f(n-1)
 * 3、问题
// 栈溢出了
 */
fun myPow1(x: Double, n: Int): Double {
    if (n == 0) {
        return 1.0
    }
    var xR = x
    var nR = n
    if (n < 0) {
        xR = 1 / x
        nR = abs(n)
    }
    return xR * myPow1(xR, nR - 1)
}