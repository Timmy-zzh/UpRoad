package com.timmy.dataalg.leetcode

import java.lang.Math.pow
import kotlin.math.pow

/**
 * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回 该列名称对应的列序号 。
 *
例如：
A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28
...

示例 1:
输入: columnTitle = "A"
输出: 1

示例 2:
输入: columnTitle = "AB"
输出: 28

示例 3:
输入: columnTitle = "ZY"
输出: 701

提示：
1 <= columnTitle.length <= 7
columnTitle 仅由大写英文组成
columnTitle 在范围 ["A", "FXSHRXW"] 内
 */
fun main() {
//    val res = titleToNumber("ZY")
    val res = titleToNumber("AB")
    println("res:$res")
}

/**
 * 1、审题
 * - 输入一个字符串，由大写字母组成，标识excel表格列名称，标识表格的序列号，
 * - 根据对应规则，返回字符串对应的表格是第几列
 * 2、解题
 * - 先搞清楚规则:
 * - A表示1， B-2 。。 Z-26   1*26的0次方
 * - AA-27，AB-28， 。。。 AZ-52     += 26的1次方
 * - BA-53 ....          BZ-78
 * - ...
 * - ZA-677              ZZ-702
 * - AAA-703   27 += 26的2次方
 * - AAAA-18279   703 += 26的3次方
 *
 * == 规律：
 * - A~Z --> （x-'A'+1）*26[0]  []标识26的0次方为1
 * - AA~ZZ --> +=之前的Z（26）
 * -- 遍历，先找第一位字母 (a-'A'+1)*26[1] + (b-'A'+1)*26[0]
 * -- ZA = (26-1+1)*26 + 1*1 = 677
 * -- AAA = ('A'-'A'+1)*26[2] + 26[1] + 26[0] = 703
 */
fun titleToNumber(columnTitle: String): Int {
    val n = columnTitle.length
    var sum = 0
    for (i in 0 until n) {
        val num = columnTitle[i].code // 求轮训获取到的数字
        val x = n - i - 1 // 几次方
        sum += ((num - 'A'.code + 1) * 26.toDouble().pow(x.toDouble())).toInt()
    }
    return sum
}