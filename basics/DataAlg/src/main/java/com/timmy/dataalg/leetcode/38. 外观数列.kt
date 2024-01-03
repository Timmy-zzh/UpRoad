package com.timmy.dataalg.leetcode

import java.lang.StringBuilder

/**
 *
 * 给定一个正整数 n ，输出外观数列的第 n 项。
「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
你可以将其视作是由递归公式定义的数字字符串序列：
countAndSay(1) = "1"
countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。

前五项如下：
1.     1
2.     11
3.     21
4.     1211
5.     111221
第一项是数字 1
描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
要 描述 一个数字字符串，首先要将字符串分割为 最小 数量的组，每个组都由连续的最多 相同字符 组成。然后对于每个组，
先描述字符的数量，然后描述字符，形成一个描述组。要将描述转换为数字字符串，先将每组中的字符数量用数字替换，再将所有描述组连接起来。

例如，数字字符串 "3322251" 的描述如下图：

示例 1：
输入：n = 1
输出："1"
解释：这是一个基本样例。

示例 2：
输入：n = 4
输出："1211"
解释：
countAndSay(1) = "1"
countAndSay(2) = 读 "1" = 一 个 1 = "11"
countAndSay(3) = 读 "11" = 二 个 1 = "21"
countAndSay(4) = 读 "21" = 一 个 2 + 一 个 1 = "12" + "11" = "1211"
 */
fun main() {
    val res = countAndSay(6)
    println("res:$res")
}

/**
 * 1、审题
 * - 使用字符对已有字符进行描述，默认n=1，描述为1
 * - n=2时，是对n=1的结果进行描述，描述结果为1个1，也就是：11
 * - n=其他，如此类推，第n项是对第n-1项的结果的描述
 * 2、解题
 * - 递归
 * - n不断减少直到n=1结果为1
 */
fun countAndSay(n: Int): String {
    if (n == 1) {
        return "1"
    }
    val text = countAndSay(n - 1)
    val sb = StringBuilder() // 对text进行表述，结果保存在sb中
    var count = 1
    val size = text.length
    for (i in 1 until size) {
        if (text[i] == text[i - 1]) {
            count++
        } else { // 不相等
            sb.append(count).append(text[i - 1])

            count = 1
        }
    }

    sb.append(count).append(text[size - 1])

    return sb.toString()
}