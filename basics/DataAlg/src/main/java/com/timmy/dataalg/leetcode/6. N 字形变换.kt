package com.timmy.dataalg.leetcode

import java.lang.StringBuilder

/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：

P   A   H   N
A P L S I I G
Y   I   R
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
请你实现这个将字符串进行指定行数变换的函数：

string convert(string s, int numRows);

示例 1：
输入：s = "PAYPALISHIRING", numRows = 3
输出："PAHNAPLSIIGYIR"

示例 2：
输入：s = "PAYPALISHIRING", numRows = 4
输出："PINALSIGYAHRPI"
解释：
P     I    N
A   L S  I G
Y A   H R
P     I

示例 3：
输入：s = "A", numRows = 1
输出："A"
 */
fun main() {
    var res = convert("PAYPALISHIRING", 3)
    println("res3:$res")
    res = convert("PAYPALISHIRING", 4)
    println("res4:$res")

}

/**
 * 1、审题：画图理解更好
 * - 输入一个字符串和一个标识行数的数字，将字符按照N字形进行排列
 * - 分析发现，排列后以一列和旁边的往上斜作为一个单元，进行遍历，每个单位的数量为 n+(n-2),因为斜坡的数量上下位置没有元素，所以减2处理
 * 2、解题：
 * - 采用两层循环，
 * - 外层以以行数为基准 ，并通过单位数量计算出每行的遍历的元素位置，除了第一行和最后一行每个单元都是一个字符
 * - 中间行，每个单元都包含两个字符，
 */
fun convert(s: String, numRows: Int): String {
    val length = s.length
    if (length <= numRows || numRows == 1) {
        return s
    }
    val sb = StringBuilder()
    val itemCount = numRows + (numRows - 2) // 每个单元的字符个数

    for (item in 0..length / itemCount) { // 第一行, col 为列
        if (item * itemCount < length) {
            sb.append(s[item * itemCount])
        }
    }

    // 中间行
    for (i in 1 until numRows - 1) {
        for (item in 0..length / itemCount) { // 中间行i, item 为第几个单元
            if (item * itemCount + i < length) {
                sb.append(s[item * itemCount + i])
            }
            if (((item + 1) * itemCount - i) < length) {
                sb.append(s[(item + 1) * itemCount - i])
            }
        }
    }

    // 最后一行
    for (item in 0..length / itemCount) { // 第一行, col 为列
        if (item * itemCount + numRows - 1 < length) {
            sb.append(s[item * itemCount + numRows - 1])
        }
    }

    return sb.toString()
}