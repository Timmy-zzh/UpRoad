package com.timmy.dataalg.leetcode

/**
 * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。

示例 1：
输入：n = 10
输出：4
解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。

示例 2：
输入：n = 0
输出：0

示例 3：
输入：n = 1
输出：0

提示：
0 <= n <= 5 * 106
 */
fun main() { //    val res = countPrimes(499979)
    val res = countPrimes(10)
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个数字，求小于这个数字，大于0,这个区间范围的数字是质数的个数
 * 2、解题：
 * - 取区间范围，这个好说
 * - 在遍历的过程中，判断这个数字是否是质数，
 * 3、核心问题是求一个数字是否是质数
 * - 那什么是质数呢？ 只能被1和自己整除
 * -- 让这个数字n除于2到n-1，如果余数为0说明整除干净了，不是质数，否则就是质数
 * 4、超出时间限制：499979
 * - 求取质数应该有其他方式吧！
 * - 一个数总可以写成 a*b的等式
 */
fun countPrimes(n: Int): Int {
    if (n < 2) {
        return 0
    }
    var res = 0
    for (i in 2 until n) {
        println("for n:$i")
        if (isPrimes(i)) {
            println(" isPrimes countPrimes ---")
            res++
        }
        println()
    }
    return res
}

/**
 * 遍历的时候，取n的平方根
 *
 * 质数的定义是：在大于1的自然数中，除了1和他本身以外不再有其他因数的自然数
 * - 因为对于任意数x，通过枚举 [2,x-1] 中的每个数y，判断y是否能被x整除？ -- 时间复杂度为O(n)
 * - 考虑到如果y是x的因数，那么x/y必然是x的因数，因此只要校验y或者x/y即可，从中选择较小的那个数，
 * --他的区间落在[2,x的平方根]
 *
 * ？有电懂，也有点懵懂
 * - 为什么选择其中较小的那个数，取值区间范围是落在 [2,x的开平方根] 上呢？
 */
fun isPrimes(n: Int): Boolean {
    for (i in 2..n) {
        if (i * i > n) {
            break
        }
        if (n % i == 0) {
            return false
        }
    }
    return true
}

/**
 * 判断是否是质数
 * n/2 这快没有理解
 * 一个数可以写成a*b，如果一个数a和b只有一对数值，1和自己n，那就是质数
 * - 如果可以被2整除，那另一个数字就是 n/2, 其他的数字那就只能是比n/2更小了
 * = 所以循环的时候除于n/2就可以，中间有能被整除的说明不是质数，
 *
 * 还是超过时间限制，算法复杂度为 f(n)
 */
fun isPrimes2(n: Int): Boolean {
    var c = 0
    for (i in 2..n / 2 + 1) {
        c = i
        println("isPrimes for i:$i, c:$c")
        if (n % i == 0) {
            break
        }
    }
    println("isPrimes c:$c")
    return c > n / 2
}

// 超出时间限制
fun isPrimes1(n: Int): Boolean {
    for (i in 2 until n) {
        if (n % i == 0) {
            return false
        }
    }
    return true
}
