package com.timmy.dataalg._03stack

import kotlin.math.max

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
示例 1：
输入：s = "(()"
输出：2
解释：最长有效括号子串是 "()"

示例 2：
输入：s = ")()())"
输出：4
解释：最长有效括号子串是 "()()"

示例 3：
输入：s = ""
输出：0
 */
fun main() {
    val res = longestValidParentheses(")()())")
    println("res:$res")
}

/**
 * 1、审题：输入一个由左右括号组成的字符串，求最长有效括号的子串的长度
 * 2、解题：动态规划解法
 * == 动态规划 dp[x] 表示到x位置，有效括号子串的长度，默认刚开始全是0，到位置i的时候，如果不是0，
 * = 说明这个位置存的值就是当前位置到前面value值得位置，所表示的子串是有效括号
 * - 一对括号是有效的子串，多对括号在一起有效也符合要求，
 * - 存在两种情况，...(),第i个字符是右括号")",判断i-1位置是不是左括号"(",是的话，dp[i] = dp[i-2]+2
 * - ....(()),第i个字符是右括号，且第i-1也是右括号，要找到相对应左括号的位置是"(",则dp[i] = dp[i-1] +2 + dp[i-dp[i-1]-2]
 * --对应左括号的位置怎么计算呢？ --》x = i-dp[i-1]-1
 *
 */
fun longestValidParentheses(s: String): Int {
    val size = s.length
    var res = 0
    val dp = IntArray(size)
    dp.forEach { dp[it] = 0 }
    for (i in 1 until size) {
        val ch = s[i]
        if (ch == ')') {    // 右括号-前一个是左括号
            if (s[i - 1] == '(') {
                if (i == 1) {
                    dp[i] = 2
                } else {
                    dp[i] = dp[i - 2] + 2
                }
            } else if (i - dp[i - 1] > 0 && s[i - dp[i - 1] - 1] == '(') {
                if (i - dp[i - 1] >= 2) {
                    dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2]
                } else {
                    dp[i] = dp[i - 1] + 2
                }
            }
        }
        res = max(res, dp[i])
    }
    return res
}