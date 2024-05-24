package com.timmy.dataalg.leetcode

/**
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x

示例 1：
输入：n = 27
输出：true

示例 2：
输入：n = 0
输出：false

示例 3：
输入：n = 9
输出：true

示例 4：
输入：n = 45
输出：false

提示：
-231 <= n <= 231 - 1
进阶：你能不使用循环或者递归来完成本题吗？
 */
fun main() { //    val res = isPowerOfThree(27)
    val res = isPowerOfThree(45)
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个数字n，判断该数字n是否是3的幂次值，
 * - 也就是说，3的多少次幂，最后能等于n； 反过来说也就是n可以一直除于3，最后能得到1
 * 2、解题：
 */
fun isPowerOfThree(n: Int): Boolean {
    if (n == 0) {
        return false
    }
    if (n == 1) {
        return true
    }
    if (n % 3 == 0) {
        return isPowerOfThreeBack(n / 3)
    }
    return false
}

fun isPowerOfThreeBack(n: Int): Boolean {
    if (n == 1) {
        return true
    }
    if (n % 3 == 0) {
        return isPowerOfThreeBack(n / 3)
    }
    return false
}
