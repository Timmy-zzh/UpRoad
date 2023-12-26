package com.timmy.dataalg.leetcode

import java.lang.StringBuilder

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
如果不存在公共前缀，返回空字符串 ""。

示例 1：
输入：strs = ["flower","flow","flight"]
输出："fl"

示例 2：
输入：strs = ["dog","racecar","car"]
输出：""
解释：输入不存在公共前缀
 */
fun main() { //    val res = longestCommonPrefix(arrayOf("flower","flow","flight"))
    //    val res = longestCommonPrefix(arrayOf("dog","racecar","car"))
    val res = longestCommonPrefix(arrayOf("ab", "a"))
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个字符串数组，遍历所有的字符串，查找所有字符的最长前缀
 * 2、解题：
 * - 双层for循环解法
 * -- 外层for循环遍历数组中的每个字符
 * -- 内层for循环查找遍历后的最长前缀字符
 */
fun longestCommonPrefix(strs: Array<String>): String {
    var res: String = strs[0]
    for (i in 1 until strs.size) {
        val itemStr = strs[i]
        var x = 0
        var h = 0
        val sb = StringBuilder()
        while (x < res.length && h < itemStr.length) {
            if (res[x] == itemStr[h]) {
                sb.append(res[x])
                x++
                h++
            } else {
                break
            }
        }
        res = sb.toString()

        if (res.isEmpty()) {
            return ""
        }
    }
    return res
}