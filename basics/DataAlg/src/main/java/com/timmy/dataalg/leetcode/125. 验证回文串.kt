package com.timmy.dataalg.leetcode

/**
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
字母和数字都属于字母数字字符。
给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。

示例 1：
输入: s = "A man, a plan, a canal: Panama"
输出：true
解释："amanaplanacanalpanama" 是回文串。

示例 2：
输入：s = "race a car"
输出：false
解释："raceacar" 不是回文串。

示例 3：
输入：s = " "
输出：true
解释：在移除非字母数字字符之后，s 是一个空字符串 "" 。
由于空字符串正着反着读都一样，所以是回文串。

提示：
1 <= s.length <= 2 * 105
s 仅由可打印的 ASCII 字符组成
 */
fun main() {
//    val res = isPalindrome("A man, a plan, a canal: Panama")
    val res = isPalindrome("race a car")
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个字符串，判断将干扰字符去除后，是否是回文串
 * 2、解题：
 * - 先将所有字符转成小写字母，去除非字母和数字的字符
 * - 最后通过双指针遍历，判断是否相等
 */
fun isPalindrome(s: String): Boolean {
    val lowercase = s.lowercase()
    val filter = lowercase.filter {
        it.isDigit() || it.isLowerCase()
    }
    println("filter:$filter")
    var left = 0
    var right = filter.length - 1
    while (left < right) {
        if (filter[left] != filter[right]) {
            return false
        }
        left++
        right--
    }
    return true
}