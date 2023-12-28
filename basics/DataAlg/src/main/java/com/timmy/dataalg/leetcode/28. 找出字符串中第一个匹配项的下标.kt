package com.timmy.dataalg.leetcode

/**
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * 如果 needle 不是 haystack 的一部分，则返回  -1 。

示例 1：
输入：haystack = "sadbutsad", needle = "sad"
输出：0
解释："sad" 在下标 0 和 6 处匹配。
第一个匹配项的下标是 0 ，所以返回 0 。

示例 2：
输入：haystack = "leetcode", needle = "leeto"
输出：-1
解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 */
fun main() {
    //    val res = strStr("leetcode", "leeto")
    //    val res = strStr("sadbutsad", "sad")
    val res = strStr("mississippi", "issip")
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入两个字符串，前面字符串作为主串，后面作为部分串，在主串中查找判断是否存在一个子串和部分串相同，存在返回下标位置，没有则返回-1
 * 2、解题：
 * - 正常遍历主串，并与部分串中的字符进行匹配，如果匹配则部分串往后移动，当部分串移动到末尾时，则表示找到了匹配项目
 * - 返回主串的开始下标位置，否则返回-1
 */
fun strStr(haystack: String, needle: String): Int {
    var j = 0
    for (i in haystack.indices) {
        if (haystack[i] == needle[j]) {
            println("i:$i,j:$j") // 遇到开头相等的了，需要使用二次while循环，遍历部分串，继续内部判断
            for (x in needle.indices) {
                if (i + x < haystack.length && haystack[i + x] == needle[j + x]) { // 找到了
                    if (x == needle.length - 1) {
                        return i
                    }
                } else {
                    break
                }
            }
        } else {
            j = 0
        }
    }
    return -1
}