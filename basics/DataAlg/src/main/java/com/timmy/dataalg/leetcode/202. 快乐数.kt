package com.timmy.dataalg.leetcode

import kotlin.math.pow

/**
 * 编写一个算法来判断一个数 n 是不是快乐数。

「快乐数」 定义为：
对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
如果这个过程 结果为 1，那么这个数就是快乐数。
如果 n 是 快乐数 就返回 true ；不是，则返回 false 。

示例 1：
输入：n = 19
输出：true
解释：
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

示例 2：
输入：n = 2
输出：false

提示：
1 <= n <= 231 - 1
 */
fun main() {
    val res = isHappy(7)
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个数字，判断该数字是否是快乐数，
 * - 快乐数定义，对每个位子的数字进行求平方和，经过多次超过后得到的结果是1，则是快乐数
 * 2、解题：
 * - 递归求解，传入一个数字，对数字中的每个数字进行求平方和，如果结果为1则返回true，否则继续递归
 * - 有可能有些数字他就是不会返回1，这个时候怎么求解？怎么结束递归
 * - 使用一个set集合，保存已经求得的结果值，如果后面遇到相同的结果值，则说明出现循环了，直接返回false
 */
fun isHappy(n: Int): Boolean {
    var set = mutableSetOf<Int>()
    return isHappyRecur(n, set)
}

fun isHappyRecur(n: Int, set: MutableSet<Int>): Boolean {
    println("isHappyRecur n:$n")
    // 判断是否出现循环了
    if (set.contains(n)) {
        return false
    }
    if (n == 1) {     // 找到快乐数
        return true
    }
    set.add(n)

    // 计算num中每个数字的平方和
    var num = n
    var sum = 0
    while (num != 0) {
        val temp = num % 10
        sum += (temp.toDouble().pow(2.0).toInt())
        num /= 10
    }

    println("isHappyRecur sum:$sum")
    return isHappyRecur(sum, set)   // 继续往下递归
}
