package com.timmy.dataalg.leetcode

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。

示例 1：
输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。

示例 2：
输入：s = "cbbd"
输出："bb"
 */
fun main() {
//    val res = longestPalindrome("babad")
    val res = longestPalindrome("cbbd")
    println("res:$res")
}

/**
 * 1、审题：输入一个字符串，寻找最长的回文子串
 * 2、解题：
 * - 两层遍历法
 * --外层遍历确定子串的左侧位置，内层遍历从子串左侧位置开始往后遍历，拿到子串的左右位置
 * --求该子串是否是回文串，是的话求解最长子串的长度并返回
 * 3、总结：动态规划解法
 */
fun longestPalindrome(s: String): String {
    val len = s.length
    var resStr = ""
    for (i in 0 until len) {
        for (j in i until len) {
            if (s[i] == s[j]) {
                val isPalindrome: Boolean = isPalindrome(s, i, j)
                val tempStr = s.substring(i, j + 1)
                if (isPalindrome && tempStr.length > resStr.length) {
                    resStr = tempStr
                }
            }
        }
    }
    return resStr
}

/**
 * 判断区间[start,end]是否是回文子串
 * -- 采用双指针解法
 */
fun isPalindrome(s: String, i: Int, j: Int): Boolean {
    var start = i
    var end = j
    while (start <= end) {
        if (s[start] == s[end]) {
            start++
            end--
        } else {
            return false
        }
    }
    return true
}
