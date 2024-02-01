package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
"AAJF" ，将消息分组为 (1 1 10 6)
"KJF" ，将消息分组为 (11 10 6)

注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
题目数据保证答案肯定是一个 32 位 的整数。

示例 1：
输入：s = "12"
输出：2
解释：它可以解码为 "AB"（1 2）或者 "L"（12）。

示例 2：
输入：s = "226"
输出：3
解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。

示例 3：
输入：s = "06"
输出：0
解释："06" 无法映射到 "F" ，因为存在前导零（"6" 和 "06" 并不等价）。
 */
fun main() {

    //    val res = numDecodings("12")
    //    val res = numDecodings("226")
    //        val res = numDecodings("06")
    //        val res = numDecodings("9")
    //        val res = numDecodings("111111111111111111111111111111111111111111111")
    val res = numDecodings("2101")
    println("res:$res")
}

/**
 * 2、动态规划解法
 * - 使用一维数组，dp[i] 表示从数字字符0到字符位置i，一共有多少中解码结果
 * - 其中dp[i]的求解分两种情况：i是第一个的意思
 * -- 开头遇到0，直接break
 * -- dp[i]=dp[i-1] + dp[i-2] ==>这思路和爬楼梯有点相似啊
 * 3、总结
 * - 动态规划
 * - 状态转移方程式，状态变换
 * -- 搞定一页，加油
 */
fun numDecodings(s: String): Int {
    println("s:$s")
    if (s.isEmpty() || s.startsWith("0")) {
        return 0
    }

    val n = s.length
    val dp = IntArray(n + 1) { 0 }
    dp[0] = 1
    dp[1] = 1
    for (i in 2..n) {
        println("------i:$i")
        val num1 = s[i - 1] - '0'
        println("num1:$num1")
        if (num1 != 0) {    // 一个数字
            dp[i] = dp[i - 1]
        }
        dp.print()

        val num2 = s[i - 2] - '0'
        val num = num2 * 10 + num1
        if (9 < num && num <= 26) { // 需要大于9，不然前面的两个数组就是0x，以0开头的肯定不行
            println("num:$num")
            dp[i] = dp[i] + dp[i - 2]
        }
        dp.print()
    }
    println("last")
    dp.print()

    return dp[n]
}

/**
 * 1、审题
 * - 输入一个由数字组成的字符串，数字的由来是通过字母转换而来，现在需要进行解码成字母，
 * - 因为同一个数字，有可能由不同的字母转换生成，所以解码的结果也不同，求同一个数字字符串，可以正确解码为多少组字母
 * 2、解题
 * - 回溯解法
 * - 在递归的过程中，遍历数字数组，其中数字数组一次可能遍历一个数字，或者两个数字，（注意字母对应的数字为 1-26）
 * - 在遍历的时候需要判断过滤
 * - 注意单个遍历，不能以0开头
 * - 最后遍历完所有数字数组，说明遍历结束，计数器res返回
 * 3、超出时间限制：111111111111111111111111111111111111111111111
 * - 使用动态规划解法吧
 */
var resCount = 0
fun numDecodings1(s: String): Int {
    if (s.isEmpty() || s.startsWith("0")) {
        return 0
    }

    numDecodingsResl(0, s.length, s)

    return resCount
}

fun numDecodingsResl(i: Int, n: Int, s: String) {
    println("numDecodingsResl i:$i,n:$n")
    if (i >= n) {
        if (i == n) {
            resCount++
        }
        return
    }

    val num1 = s[i] - '0'
    if (num1 <= 0) {
        return
    }

    // 一次解码一个数字
    numDecodingsResl(i + 1, n, s)

    // 解码两个数字
    if (i < n - 1) {
        val num2 = s[i + 1] - '0'
        val num = num1 * 10 + num2
        if (num > 26) {
            return
        }

        // 两个数字
        numDecodingsResl(i + 2, n, s)
    }

}
