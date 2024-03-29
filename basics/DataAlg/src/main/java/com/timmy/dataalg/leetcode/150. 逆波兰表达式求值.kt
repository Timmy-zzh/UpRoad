package com.timmy.dataalg.leetcode

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

提示：
1 <= tokens.length <= 104
tokens[i] 是一个算符（"+"、"-"、"*" 或 "/"），或是在范围 [-200, 200] 内的一个整数

逆波兰表达式：
逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
逆波兰表达式主要有以下两个优点：
去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
 */
fun main() {
//    val tokens = arrayOf("2","1","+","3","*")
//    val tokens = arrayOf("4","13","5","/","+")
    val tokens = arrayOf("10","6","9","3","+","-11","*","/","*","17","+","5","+")
    val res = evalRPN(tokens)
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个字符串数组，這些字符串具有逆波兰表达式规则，遇到加减乘除运算符，需将前面的两个元素取出来进行计算后，再与数组中的数字进行处理
 * 2、解题
 * - 栈结构解法
 * - 遍历数组，遇到数字直接入栈
 * - 当遇到运算符时，取出栈中的两个字符数字（注意顺序）
 */
fun evalRPN(tokens: Array<String>): Int {

    val stack = Stack<String>()

    for (i in tokens.indices) {
        val numStr = tokens[i]
        if (numStr.length == 1 && isOperate(numStr)) {
            val num2 = stack.pop()
            val num1 = stack.pop()
            val result = operate(num1, num2, numStr)
            stack.push(result)
        } else {
            stack.push(numStr)
        }
    }

    return stack.peek().toInt()
}

fun operate(num1Str: String, num2Str: String, operate: String): String {
    val num1 = num1Str.toInt()
    val num2 = num2Str.toInt()
    val res = when (operate) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        "*" -> num1 * num2
        "/" -> num1 / num2
        else -> "0"
    }
    return res.toString()
}

fun isOperate(numStr: String): Boolean {
    return numStr == "+" || numStr == "-" || numStr == "*" || numStr == "/"
}
