package com.timmy.dataalg._03stack

import java.util.Stack

/**
 * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
请你计算该表达式。返回一个表示表达式值的整数。

注意：
有效的算符为 '+'、'-'、'*' 和 '/' 。
每个操作数（运算对象）都可以是一个整数或者另一个表达式。
两个整数之间的除法总是 向零截断 。
表达式中不含除零运算。
输入是一个根据逆波兰表示法表示的算术表达式。
答案及所有中间计算结果可以用 32 位 整数表示。


示例 1：
输入：tokens = ["2","1","+","3","*"]
输出：9
解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
示例 2：

输入：tokens = ["4","13","5","/","+"]
输出：6
解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
示例 3：

输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
输出：22
解释：该算式转化为常见的中缀算术表达式为：
((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
 */
fun main() {

    val res =
        evalRPN(arrayOf("10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"))
    println(res)
}

/**
 * 审题：输入一个字符串，内容为算术表达式，以逆波兰方式表示，现在要根据输入的内容计算出表达式最终的值
 * 解决思路：遍历字符串，遍历到数字时，入栈，当办理到算术运算符时，则取出栈中的两个数字进行计算后，再存入栈中，遍历到最后，就是计算结果
 */
fun evalRPN(tokens: Array<String>): Int {
    val stack: Stack<String> = Stack<String>()
    val list = listOf("+", "-", "*", "/")
    tokens.forEach { str ->
//        stack.print()
        if (list.contains(str)) {
            val next = stack.pop()
            val prev = stack.pop()
            val numRes = calc(prev, next, str)
            stack.push(numRes)
        } else {
            stack.push(str)
        }
    }
    return stack.pop().toInt()
}

fun calc(prev: String?, next: String?, str: String): String? {
    val num1 = prev?.toInt() ?: 0
    val num2 = next?.toInt() ?: 0
    return when (str) {
        "+" -> (num1 + num2).toString()
        "-" -> (num1 - num2).toString()
        "*" -> (num1 * num2).toString()
        "/" -> (num1 / num2).toString()
        else -> ""
    }
}
