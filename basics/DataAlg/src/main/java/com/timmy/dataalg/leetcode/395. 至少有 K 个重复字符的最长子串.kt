package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import kotlin.math.max

/**
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
如果不存在这样的子字符串，则返回 0。

示例 1：
输入：s = "aaabb", k = 3
输出：3
解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。

示例 2：
输入：s = "ababbc", k = 2
输出：5
解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。

提示：
1 <= s.length <= 104
s 仅由小写英文字母组成
1 <= k <= 105
 */
fun main() { //val res = longestSubstring("aaabb", 3)
    val res = longestSubstring("ababbc", 2) //    val res = longestSubstring("a", 1)
    println("res:$res")
}

/**
 * 1、动态规划解法， dp[i][j] 标识字符串位置从i到j的子串，是否匹配成功
 * - 转台转移方程式，没用上啊
 * 2、这也超出时间限制！
 */
fun longestSubstring(s: String, k: Int): Int {
    val n = s.length
    val dp = Array(n) {
        BooleanArray(n) { false }
    }

    for (i in 0 until n) {
        for (j in i until n) {
            val len = j - i + 1
            if (len < k) {
                continue
            }

            // 判断前一个位置的子串是否为true，且当前位置字符被包含在其中，那肯定子串是命中的
            val preStr = s.substring(i, j)
            val currChar = s[j]
            if (j >= 1 && dp[i][j - 1] && preStr.contains(currChar)) {
                dp[i][j] = true
                continue
            }

            val subStr = s.substring(i, j + 1)
            if (check395(subStr, k)) {
                dp[i][j] = true
            }
        }
    }

    dp.print()
    var resMax = 0
    for (i in 0 until n) {
        for (j in i until n) {
            if (dp[i][j]) {
                resMax = max(resMax, (j - i + 1))
            }
        }
    }
    return resMax
}

/**
 * 1、审题
 * - 输入一个字符串和数字k，求字符串s的一个最长子串，要求子串中每个字母出现的次数不少于k次，返回最长子串的长度，否则返回0
 * 2、解题
 * - 该提有两个关键点，一个是求子串，一个是子串中字母出现的个数
 * - 有两种解法：回溯算法，穷举所有的子串，然后判断当前处理的子串是否满足条件
 * -- 第二种解法是动态规划，dp[i][j] = boolean，标识字符串从i到j是否有满足条件的子串
 * 3、回溯算法，超出时间限制
 */
var res395 = Int.MIN_VALUE
fun longestSubstring2(s: String, k: Int): Int {
    for (i in s.indices) {
        longestSubstringBack(s, k, 0, i)
    }
    return if (res395 == Int.MIN_VALUE) 0 else res395
}

/**
 * 递归算法的目的：就是为了穷举所有的子串
 * - 从0开始到后面所有位置元素的所有情况
 * - 接下来递归判断从1开始到后面位置元素的情况，循环往复，start位置不断往后移动
 */
fun longestSubstringBack(s: String, k: Int, start: Int, end: Int) {
    val subStr = s.substring(start, end + 1)
    println("start:$start,end:$end,subStr:$subStr")

    // 判断是否符合题目要求
    val len = end - start + 1
    if (len > res395 && check395(subStr, k)) {
        res395 = len
    }
    for (i in start + 2 until s.length) {
        longestSubstringBack(s, k, start + 1, i)
    }
}

/**
 * 判断字符子串，所有的字符的个数都大于k
 */
fun check395(s: String, k: Int): Boolean {
    val map = mutableMapOf<Char, Int>()
    for (i in s.indices) {
        val num = map.getOrDefault(s[i], 0)
        map[s[i]] = num + 1
    }
    map.forEach {
        if (it.value < k) {
            return false
        }
    }
    return true
}
