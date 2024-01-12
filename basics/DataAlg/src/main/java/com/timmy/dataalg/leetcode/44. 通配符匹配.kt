package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给你一个输入字符串 (s) 和一个字符模式 (p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：
'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符序列（包括空字符序列）。
判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。

示例 1：
输入：s = "aa", p = "a"
输出：false
解释："a" 无法匹配 "aa" 整个字符串。

示例 2：
输入：s = "aa", p = "*"
输出：true
解释：'*' 可以匹配任意字符串。

示例 3：
输入：s = "cb", p = "?a"
输出：false
解释：'?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 */
fun main() {

    //        val res = isMatch1("aa", "a")
    //    val res = isMatch1("aa", "*")
    //        val res =  isMatch1("cb", "?a")
    //    val res = isMatch1("adceb", "*a*b")
    //      val res =    isMatch1("", "******")
    //    val res = isMatch1("abcabczzzde", "*abc???de*")
    //    val res = isMatch1("acdcb", "a*c?b")
    val res = isMatch1("bbbababbbbabbbbababbaaabbaababbbaabbbaaaabbbaaaabb", "*b********bb*b*bbbbb*ba") // 超出时间限制
    println("res:$res")
}

/**
 * 动态规划算法：
 * - 状态转移方程式：二维数组
 * - 先处理好第一行，然后根据前面的结果判断下一行
 * -- 这比之前的模式串匹配情况要简单很多了
 * - 注意dp以主串和模式串的长度作为比较标准
 * 总结：这个地方有点没转过弯弯来呢？？？
 * - 当模式串为通配符*时，他可以匹配零个或多个主串字符，
 * -- 匹配0个字符时，模式串位置减1，可以理解为通配符前面的字符串与当前的主串子串进行匹配，也就是模式串减少一位，主串不变的位置dp值
 * --- 也即是 dp[pi][sj] = dp[pi-1][sj]
 * -- 匹配多个字符，也就是使用这个星*，也就是主串位置不变，模式串匹配值与左侧位置字符相同
 * --- dp[pi][sj] = dp[pi][sj-1]
 */
fun isMatch1(s: String, p: String): Boolean {
    println("s:$s, p:$p")
    val sLen = s.length
    val pLen = p.length

    /**
     *  二维矩阵
     *  - 行是模式串，列是主串
     */
    val dp = Array(pLen + 1) {
        BooleanArray(sLen + 1) { false }
    }
    dp[0][0] = true

    /**
     * 第一行处理
     * - 模式串为空，主串有值，全是false
     */

    /**
     * 第一列处理
     * - 主串为空，模式串有值，模式串可能是？或*可以匹配
     */
    for (pj in 1..pLen) {
        val pCh = p[pj - 1] // 模式串字符
        if (pCh == '*') {   // 只有*才有必要处理
            dp[pj][0] = dp[pj - 1][0]
        } else {
            break
        }
    }
    dp.print()

    for (pi in 1..pLen) {
        for (sj in 1..sLen) { //            println("pi:$pi, sj:$sj")
            val pCh = p[pi - 1] // 模式串字符
            val sCh = s[sj - 1] // 模式串字符
            if (pCh == '?') {
                dp[pi][sj] = dp[pi - 1][sj - 1]
            } else if (pCh == '*') {
                dp[pi][sj] = dp[pi][sj - 1] || dp[pi - 1][sj]
                /**
                 * 匹配0个主串字符   dp[pi - 1][sj]
                 * 匹配多个字符     dp[pi][sj - 1]
                 */
            } else if (pCh == sCh) {
                dp[pi][sj] = dp[pi - 1][sj - 1]
            } else {
                dp[pi][sj] = false
            }
        }
    }

    dp.print()
    return dp[pLen][sLen]
}

fun isMatch12(s: String, p: String): Boolean {
    println("s:$s, p:$p")
    val sLen = s.length
    val pLen = p.length

    /**
     *  二维矩阵
     *  - 行是模式串，列是主串
     */
    val dp = Array(pLen + 1) {
        BooleanArray(sLen + 1) { false }
    }
    dp[0][0] = true

    /**
     * 第一行处理
     * - 模式串为空，主串有值，全是false
     */

    /**
     * 第一列处理
     * - 主串为空，模式串有值，模式串可能是？或*可以匹配
     */
    for (pj in 1..pLen) {
        val pCh = p[pj - 1] // 模式串字符
        if (pCh == '*') {   // 只有*才有必要处理
            dp[pj][0] = dp[pj - 1][0]
        }
    }
    dp.print()

    for (pi in 1..pLen) {
        for (sj in 1..sLen) {
            println("pi:$pi, sj:$sj")
            val pCh = p[pi - 1] // 模式串字符
            val sCh = s[sj - 1] // 模式串字符
            if (pCh == '?') {
                dp[pi][sj] = dp[pi - 1][sj - 1]
            } else if (pCh == '*') {
                dp[pi][sj] = dp[pi][sj] || dp[pi - 1][sj - 1] || dp[pi - 1][sj]   // 匹配0个主串字符

                for (sjj in sj + 1..sLen) { // 匹配多个主串字符
                    println("pi:$pi, sj:$sj ,sjj:$sjj")
                    dp[pi][sjj] = dp[pi][sjj] || dp[pi][sjj - 1]
                }
                dp.print()
            } else if (pCh == sCh) {
                dp[pi][sj] = dp[pi - 1][sj - 1]
            } else {
                dp[pi][sj] = false
            }
        }
    }

    dp.print()
    return dp[pLen][sLen]
}

/**
 * 1、审题：
 * - 通配符匹配，s是主串，p是模式串，主串全部是字母组成，模式串由字母和通配符（？，*）组成
 * -- 其中通配符？可以匹配任意单个字符，通配符*可以匹配任意个字符，包括空字符，求模式串与主串是否可以匹配成功
 * 2、解题：回溯算法
 * - 不断遍历模式串与主串中的字符，会出现三种情况
 * -- 都是字母，看是否相等，不相等直接中断了
 * -- 模式串是通配符？，他可以匹配任意单个字符
 * -- 模式串是通配符*，可以匹配任意多个字符，或0个字符
 * - 最后当模式串或主串遍历到末尾时结束，如果刚好两个字符串都到达末尾，则说明匹配成功
 * 3、总结：
 * - 模式串字符为*，匹配0个主串字符，方法编写错误：
 * -- isMatchReal(si, pi + 1, sLen, pLen, s, p) // 匹配0个
 * - 匹配主串长度为0的情况
 * - 匹配模式次以*结尾的情况
 * - 回溯法遇到长字符串，会出现执行时长过长情况
 * - 需要使用动态规划算法
 */
var isMatched = false
fun isMatch3(s: String, p: String): Boolean {

    isMatchReal(0, 0, s.length, p.length, s, p)

    return isMatched
}

fun isMatchReal(si: Int, pi: Int, sLen: Int, pLen: Int, s: String,
    p: String) { //    println("si:$si,pi:$pi,sLen:$sLen,pLen:$pLen")
    if (isMatched) {
        return
    }
    if (si == sLen && pi == pLen) {
        isMatched = true
        return
    }
    if (si > sLen || pi > pLen) {
        return
    }
    if (pi == pLen) {
        return
    }

    if (p[pi] == '?') {
        isMatchReal(si + 1, pi + 1, sLen, pLen, s, p)
    } else if (p[pi] == '*') {
        isMatchReal(si, pi + 1, sLen, pLen, s, p) // 匹配0个

        // 匹配多个
        for (j in si + 1..sLen) {
            isMatchReal(j, pi + 1, sLen, pLen, s, p)
        }
    } else if (si < sLen && p[pi] == s[si]) {
        isMatchReal(si + 1, pi + 1, sLen, pLen, s, p)
    } else {
        isMatched = false
        return
    }
}









