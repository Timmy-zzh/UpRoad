package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import java.util.Stack

/**
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
整数除法仅保留整数部分。
你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。
注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。

示例 1：
输入：s = "3+2*2"
输出：7

示例 2：
输入：s = " 3/2 "
输出：1

示例 3：
输入：s = " 3+5 / 2 "
输出：5

提示：
1 <= s.length <= 3 * 105
s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
s 表示一个 有效表达式
表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
题目数据保证答案是一个 32-bit 整数
 */
fun main() {
        val res = calculate(" 3+5 / 2 ")
//    val res = calculate(" 3+2*2")
    println("res:$res")
}

/**
 * 2、解题
 * - 使用栈数据结构来解决
 * - 遍历字符串中的字符，使用preSign保存前一次操作运算符的标记-
 * - 继续遍历，遇到数字则临时存储起来，当遇到下一个运算符时，则处理上一个运算符的逻辑
 * - 如果上一个运算符是加减操作符（+ -），则将当前的数字和操作符一同保存到栈中， 更新preSign的值
 * - 当遇到运算符时乘除操作符时（* /）,则取出栈顶元素（乘除操作符前一个数字）和当前数字进行运算，将新计算的结果入栈，并更新preSign的值
 * 3、注意：
 * - 去除空格符，
 * - 遍历到单个数字的时候，数字的值不断叠加
 * - 遍历到最后一个字符的时候，进行处理
 */
fun calculate(s: String): Int {
    var num = 0
    var preSign = '+'
    var stack = Stack<String>()

    for (i in s.indices) {
        val ch = s[i]
        if (ch.isDigit()) { // 是否是数字，数字的话叠加
            num = num * 10 + (ch - '0')
        }

        // 遇到了运算操作符，不能是空格，或者是最后一个字符
        if (!ch.isDigit() && ch != ' ' || i == s.length - 1) { // 判断之前的运算符操作符，
            when (preSign) {
                '+' -> stack.push(num.toString())
                '-' -> stack.push("-$num")
                '*' -> {
                    val preNum = stack.pop().toInt()
                    val resNum = preNum * num
                    stack.push(resNum.toString())
                }
                '/' -> stack.push((stack.pop().toInt() / num).toString())
                else -> println(ch)
            }

            // 更新
            preSign = ch
            num = 0
        }
    }

    var res = 0
    while (stack.isNotEmpty()){
        res += stack.pop().toInt()
    }

    return res
}

/**
 * 1、审题：计算器实现
 * 2、解题：使用队列结构保存数字
 * - 去除空格内容
 * - 双指针遍历，遇到数字和加减符号入队列，遇到乘除则从队尾取一个数字，然后接着从字符中取出下一个数字，进行乘除操作
 * - 进行乘除操作后，将结果添加到队尾中
 * 3、当输入的字符串够多时，超出时间显示，不让全部存储起来，看来得边遍历边计算才行
 * - 解题思路：遍历字符串中字符，纯数字和运算符分别作为元素保存到队列中，
 * - 从队尾开始遍历队列，当遇到乘除优先级更高的操作符时，先进行处理（将操作符后面的数字取出进行运算操作，再入队列），
 * - 最后就是遍历新的队列，对所有的数字进行加减操作符运算
 * 4、还是得用栈来解决
 */
fun calculate2(s: String): Int {
    val newS = s.replace(" ", "")
    println("newS:$newS")
    val size = newS.length
    var right = 0
    var left = 0
    val list = mutableListOf<String>()
    while (right < size) {
        if (newS[right].isDigit()) {
            right++
        } else { // 先将之前的数字保存下来，保存到队列中
            list.add(newS.substring(left, right))

            // 将运算符取出来，全部入队列即可
            val ch = newS[right]
            list.add(ch.toString())

            right++
            left = right
        }
    }
    list.add(newS.substring(left, right))
    list.print()

    if (list.isEmpty()) {
        return 0
    }

    // 遍历队列,找到乘除运算符，进行计算将结果再保存到新的队列中
    val newList = mutableListOf<String>()
    while (list.isNotEmpty()) {
        val first = list.removeFirst()
        if (isDigitAll(first) || first == "+" || first == "-") {
            newList.add(first)
        } else { // 乘除
            // 取出前面一个
            val prev = newList.removeLast()
            val last = list.removeFirst()
            val itemRes = calc(prev, last, first).toString()
            newList.add(itemRes)
        }
    }
    newList.print()

    // 计算最后的结果
    var res = newList.removeFirst()
    while (newList.isNotEmpty()) {
        val first = newList.removeFirst()
        if (first == "+") {
            val last = newList.removeFirst()
            res = (res.toInt() + last.toInt()).toString()
        } else {
            val last = newList.removeFirst()
            res = (res.toInt() - last.toInt()).toString()
        }
    }

    return res.toInt()
}

fun isDigitAll(str: String): Boolean {
    str.forEach {
        if (!it.isDigit()) {
            return false
        }
    }
    return true
}

fun calc(prev: String, last: String, tag: String): Int {
    return when (tag) {
        "*" -> prev.toInt() * last.toInt()
        "/" -> prev.toInt() / last.toInt()
        else -> 0
    }
}
