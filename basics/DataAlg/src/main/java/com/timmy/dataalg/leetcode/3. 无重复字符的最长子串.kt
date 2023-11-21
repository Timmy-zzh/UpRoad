package com.timmy.dataalg.leetcode

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
示例 1:
输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

示例 2:
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

 */
fun main() {
//    val res = lengthOfLongestSubstring("abcabcbb")
    val res = lengthOfLongestSubstring("bbbbb")
    println("res=$res")
}

/**
 * 1、审题：输入一个字符串，查找一个最长子串，要求子串中没有重复字符
 * 2、解题：
 * - 遍历法，不断遍历字符串中的字符，将遍历到的字符，与已有子串判断是否包含，
 * -- 没有包含，子串增加
 * -- 如果包含，则找到包含的位置，并往后偏移一位，继续查找后面的字符
 */
fun lengthOfLongestSubstring(s: String): Int {
    val size = s.length
    var subStr = ""
    var longest = 0
    var start = 0 //标识子串在原始字符串开始的位置
    for (i in 0 until size) {
        val char = s[i]
        if (!subStr.contains(char)) {
            subStr += char
            if (subStr.length > longest) {
                longest = subStr.length
            }
        } else { // 包含
            // 重复字符在子串中的位置
            val index = subStr.indexOf(char)
            subStr = s.substring(start + index + 1, i + 1)
            start += index + 1
        }
    }

    return longest
}
















