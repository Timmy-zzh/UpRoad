package com.timmy.dataalg._13string

import com.timmy.dataalg.print
import java.util.Stack

/**
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。

函数 myAtoi(string s) 的算法如下：

读入字符串并丢弃无用的前导空格
检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
返回整数作为最终结果。
注意：

本题中的空白字符只包括空格字符 ' ' 。
除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。

示例 1：
输入：s = "42"
输出：42
解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
第 1 步："42"（当前没有读入字符，因为没有前导空格）
^
第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
^
第 3 步："42"（读入 "42"）
^
解析得到整数 42 。
由于 "42" 在范围 [-231, 231 - 1] 内，最终结果为 42 。

示例 2：
输入：s = "   -42"
输出：-42
解释：
第 1 步："   -42"（读入前导空格，但忽视掉）
^
第 2 步："   -42"（读入 '-' 字符，所以结果应该是负数）
^
第 3 步："   -42"（读入 "42"）
^
解析得到整数 -42 。
由于 "-42" 在范围 [-231, 231 - 1] 内，最终结果为 -42 。
示例 3：

输入：s = "4193 with words"
输出：4193
解释：
第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
^
第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
^
第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
^
解析得到整数 4193 。
由于 "4193" 在范围 [-231, 231 - 1] 内，最终结果为 4193 。

 */
fun main() { //    val res = myAtoi("-91283472332")
    //    val res = myAtoi("2147483648")

    //    val res = myAtoi("   -42")
    val res = myAtoi("4193 with words")
    println(res)
}


/**
 * 总结：
 * - 不使用long类型，只使用int类型保存结果值，并在计算过程中判断边界值
 * - res每次在原有基础上翻10倍，
 * - 遍历过程中注意结束位置
 */
fun myAtoi(s: String): Int {
    var sign = 1    // 标识正负号
    var step = 1    // 乘与的倍数
    var res = 0
    for (i in s.indices) {
        val ch = s[i]
        if (ch == '-' && i + 1 < s.length && isNum(s[i + 1])) {
            sign = -1
        } else if (ch == '+' && i + 1 < s.length && isNum(s[i + 1])) {
            sign = 1
        } else if (ch == ' ') { // 为空继续遍历
            continue
        } else if (!isNum(ch)) { // 字母或其他字符，直接return
            break
        } else { // 数字
            val num = ch - '0'

            /**
             * 在原来基础上剩余10，并加上新的数字num
             * - 判断
             */
            if (res > Int.MAX_VALUE / 10 || (res == Int.MAX_VALUE / 10 && num > Int.MAX_VALUE % 10)) { // 再乘与10，肯定大于maxvalue
                return if (sign == 1) {
                    Int.MAX_VALUE
                } else {
                    Int.MIN_VALUE
                }
            }

            res = res * 10 + num
            println("num:$num, res:$res,step:$step")

            if (i + 1 < s.length && !isNum(s[i + 1])) { // 下一个字符不是数字
                break
            }
        }
    }
    return res * sign
}

/**
 * 1、审题：输入一个字符串，字符串中包含了空格，字母和数字，数字还区分正负类型，从数字中解析出数字并返回
 * 2、解题：
 * - 遍历字符串，遇到空格和字母，反正只要不是数字的直接过滤
 * - 遍历到正负号，需要判断下一位是否是数组
 * 3、不能使用long类型，需要在从栈中取出字符的过程中，进行判断，怎么判断呢？
 * - 将最大值除于10，如果已经
 */
fun myAtoi2(s: String): Int {
    var isZheng = true
    val stack = Stack<Int>()
    for (i in s.indices) {
        val ch = s[i]
        if (ch == '-' && i + 1 < s.length && isNum(s[i + 1])) {
            isZheng = false
        } else if (ch == '+' && i + 1 < s.length && isNum(s[i + 1])) {
            isZheng = true
        } else if (ch == ' ') { // 为空继续遍历
            continue
        } else if (!isNum(ch)) { // 字母或其他字符，直接return
            break
        } else { // 数字
            stack.push(ch - '0')
            if (i + 1 < s.length && !isNum(s[i + 1])) { // 下一个字符不是数字
                break
            }
        }
    }

    stack.print() // 取出栈中的数字，组成数字
    var step = 1
    var res = 0
    while (stack.isNotEmpty()) {
        val num = stack.pop()

        // 处理越界情况
        if ((res + num * step) > Int.MAX_VALUE) {
            return if (isZheng) {
                Int.MAX_VALUE
            } else {
                Int.MIN_VALUE
            }
        }
        res += num * step
        step *= 10
    }
    if (!isZheng) {
        res *= -1
    }
    return res
}

fun isNum(c: Char): Boolean {
    return c in '0'..'9'
}
