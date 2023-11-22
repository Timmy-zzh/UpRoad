package com.timmy.dataalg._14string

import com.timmy.dataalg.print

/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。

示例 1：
输入：s = ['h','e','l','l','o']
输出：['o','l','l','e','h']

示例 2：
输入：s = ['H','a','n','n','a','h']
输出：['h','a','n','n','a','H']

 */
fun main() { //    val s = charArrayOf('h', 'e', 'l', 'l', 'o')
    val s = charArrayOf('H', 'a', 'n', 'n', 'a', 'h')
    s.print()
    reverseString(s)
    s.print()
}

/**
 * 1、审题：字符串中的字符翻转
 * 2、解题：单层遍历，前后两个元素对调
 */
fun reverseString(s: CharArray): Unit {
    val size = s.size
    var temp: Char
    for (i in 0 until size / 2) {
        temp = s[i]
        s[i] = s[size - i - 1]
        s[size - i - 1] = temp
    }

}