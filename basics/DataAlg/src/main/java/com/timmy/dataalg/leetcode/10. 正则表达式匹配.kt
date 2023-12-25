package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

示例 1：
输入：s = "aa", p = "a"
输出：false
解释："a" 无法匹配 "aa" 整个字符串。

示例 2:
输入：s = "aa", p = "a*"
输出：true
解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。

示例 3：
输入：s = "ab", p = ".*"
输出：true
解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 */
fun main() { //            isMatch("aa", "a")
    //    isMatch("aa", "a*")
    //        isMatch("ab", ".*")
    //    val res = isMatch("aab", "c*a*b")
    val res = isMatch("mississippi", "mis*is*p*.")
    println("res:$res")

    //    println("isMatch:$isMatch")
}

/**
 * 解法：动态规划，状态转移方程式
 * 1、审题：字符串s，和模式串p，两个进行匹配，主串主要由字符组成，模式串由字符和通配符*和.共同组成
 * - 其中通配符.匹配任意一个字符，通配符*匹配零个或多个前面的字符
 * - 所以通配符*，可以与前面的一个字符组合作为一个空字符，也可以作为多个相同字符xx进行匹配
 * 2、解题：
 * - 状态表采用二维数组dp，使用boolean类型，二维数组的维度分别表示主串和字符串的长度，
 * - dp[i][j] 表示主串s第i个字符与模式串p第j个字符，是否进行进行匹配
 * -- 他们之间是存在递进关系的
 *
 * - 情况1：主串和模式串串都是字符，这种情况需要两个字符都相等，dp值才为true
 * - 情况2：模式串字符是通配.，则模式串可与主串任意字符匹配
 * - 情况3：模式串是通配符* ，则需要匹配前面字符0个或多个情况，dp[i] = dp[i-2]
 * -- 多个情况，需要p[j-2] 与s[j] 字符相等
 * 3、dp值以长度为标准，所以dp[0][0]=true
 * - dp的元素值，标识主串s不同长度字符，与模式串p不同长度字符下，的匹配情况
 */
fun isMatch(s: String, p: String): Boolean {
    val sLen = s.length
    val pLen = p.length
    println("s=$s ,sLen=$sLen,p=$p, pLen=$pLen")

    val dp = Array(sLen + 1) {
        BooleanArray(pLen + 1) { false }
    }
    dp[0][0] = true // 主串与模式串长度都为0时，匹配结果为true
    // 第一行处理
    for (j in 1..pLen) {
        if (p[j - 1] == '*') {
            dp[0][j] = dp[0][j - 2]
        }
    }
//    dp.print()

    for (i in 1..sLen) {    // 如果主串是空的，模式串p是一个点号".",他们也是匹配的，所以主串从0开始
        for (j in 1..pLen) {
            if (p[j - 1] == '.') {   // 情况1：模式串可以匹配任意一个字符
                dp[i][j] = dp[i - 1][j - 1]
            } else if (p[j - 1] == '*') {   // 情况2：模式串字符是星号*，可匹配零个或多个前一个字符
                // 先匹配0个字符
                dp[i][j] = dp[i][j - 2] // 匹配多个，需要主串的遍历字符与模式串*前面的一个字符相同

                if ((p[j - 2] == s[i - 1] || p[j - 2] == '.')) { // 主串的字符和模式串前一个字符能够匹配的情况
                    dp[i][j] = dp[i][j] || dp[i - 1][j]
                }
            } else if (p[j - 1] == s[i - 1]) { // 情况3：都是字符，要相等才行,第一行只匹配特殊字符
                dp[i][j] = dp[i - 1][j - 1]
            }
        }
//        println("i=$i")
//        dp.print()
    }

    return dp[sLen][pLen]
}

/**
 * 1、审题：输入一个字符串s 和正则表达式p ,正则表达式由字符.和字符*组成，
 * - 其中.匹配任意单个字符，字符* 匹配零个或多个字符，当两个字符串最后都遍历到最后一个字符时，说明匹配成功
 * 2、解题：
 * - 回溯算法，递归实现，回溯算法是穷举所有可能，复杂度是指数级别的
 * - 同时遍历字符串s和正则p，都从第0个开始，判断遍历到的正则字符如果是字符.,则匹配字符串任意一个字符
 * -- 如果遍历到正则是字符*，则匹配0个或任意多个字符，这时候可以遍历字符串后面的所有字符拿过来比较
 * - 最后当两个字符遍历到最后结束时，进行判断是否匹配
 * 3、审题问题：
 * - 题目的意思是字符* 可以匹配前面的哪一个元素零个或多个，是存在限制的，限制的是只能匹配字符*前面的哪个字符
 * -- 匹配零个是什么意思呢？让前面哪个字符消失，
 * 4、虽然理解了题意
 * - 但使用回溯算法，还是写不出来，实现不了*匹配的多个前面字符的情况，还是要使用动态规划解法
 */
var isMatch = false
fun isMatch2(s: String, p: String): Boolean {
    println("s=$s,p=$p")
    isMatch = false
    strMatch(0, 0, s, p)
    return isMatch
}

/**
 * todo
 * 用于匹配0个或多个前面的哪一个元素
 * isMatch("aab", "c*a*b") 结果既然为true，还是没搞懂咋出题的
 */
fun strMatch(si: Int, pi: Int, str: String, p: String) {
    val sLen = str.length
    val pLen = p.length
    println("si=$si,sLen=$sLen,  pi=$pi,pLen=$pLen")
    if (isMatch) {
        return
    }
    if (si == sLen || pi == pLen) {
        if (si == sLen && pi == pLen) {
            isMatch = true
        }
        return
    }
    val sC = str[si]
    val pC = p[pi]
    if (pC == sC) {
        strMatch(si + 1, pi + 1, str, p)
    } else if (pC == '.') {
        strMatch(si + 1, pi + 1, str, p)
    } else if (pC == '*') {
        strMatch(si, pi + 1, str, p)  // 匹配0个，将前面的哪个字符去掉
        for (j in si + 1..sLen) { // p一个*字符，匹配多个前面的字符
            strMatch(j, pi + 1, str, p)
        }
    } else {
        strMatch(si, pi + 1, str, p)    // 模式串自己走
    }
}

// * 匹配0个或多个
fun strMatchNor(si: Int, pi: Int, str: String, p: String) {
    val sl = str.length
    val pl = p.length
    println("si=$si,sl=$sl,  pi=$pi,pl=$pl")
    if (si == sl || pi == pl) {
        if (si == sl && pi == pl) {
            isMatch = true
        }
        return
    }
    val sC = str[si]
    val pC = p[pi]
    if (pC == sC) {
        strMatchNor(si + 1, pi + 1, str, p)
    } else if (pC == '.') {
        strMatchNor(si + 1, pi + 1, str, p)
    } else if (pC == '*') {
        for (j in si..sl) { // p一个*字符，匹配零个或多个s字符
            strMatchNor(j, pi + 1, str, p)
        }
    } else {
        isMatch = false
        return
    }
}



