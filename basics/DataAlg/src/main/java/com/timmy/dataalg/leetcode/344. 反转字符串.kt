package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。

示例 1：
输入：s = ["h","e","l","l","o"]
输出：["o","l","l","e","h"]

示例 2：
输入：s = ["H","a","n","n","a","h"]
输出：["h","a","n","n","a","H"]

提示：
1 <= s.length <= 105
s[i] 都是 ASCII 码表中的可打印字符
 */
fun main() {
    reverseString(charArrayOf('h','e','l','l','o'))
}

/**
 * 1、审题：
 * - 输入一个字符串（字符数组），需要将字符串中的字符进行翻转
 * 2、解题：双指针法
 * - 单层for循环，遍历的时候，进行交换
 */
fun reverseString(s: CharArray): Unit {
    s.print()
    val n = s.size
    for (i in 0 until n / 2) {
        val temp = s[i]
        s[i] = s[n - 1 - i]
        s[n - 1 - i] = temp
    }
    s.print()
}