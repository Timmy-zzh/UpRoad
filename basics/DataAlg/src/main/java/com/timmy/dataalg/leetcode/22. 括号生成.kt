package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import java.lang.StringBuilder
import java.util.Stack

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

示例 1：
输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]

示例 2：
输入：n = 1
输出：["()"]
 */
fun main() {
    val res = generateParenthesis(4)
    res.print()
}

/**
 * 回溯算法解法：
 * - 递归实现
 * -- 结束标记：遍历到n
 * -- 每次遍历，可以选择左右括号两种情况
 * -- 剪枝处理：左括号大于n，左括号小于右括号
 * -- 记录左右括号的个数
 */
fun generateParenthesis(n: Int): List<String> {
    val allList = mutableListOf<String>()
    backtrack(n, 0, 0, "", allList) //    allList.print()
    return allList
}

fun backtrack(n: Int, left: Int, right: Int, str: String, allList: MutableList<String>) {
    if (left > n || left < right) { //left < right ->左括号先行
        return
    }
    if (left == right && left == n) {
        allList.add(str)
    }

    backtrack(n, left + 1, right, "$str(", allList)
    backtrack(n, left, right + 1, "$str)", allList)
}

/**
 * 1、审题：
 * - 输入一个数字，数字表示小括号的对数，多个括号可以组合，成不同的样式，但是都是有效的，列举出所有组合而成且有效的括号组合
 * 2、解题：
 * - 先回溯，列举出生成的所有括号组合，然后判断生成的括号是否是有效的
 * - 括号是否有效，用栈结构判断
 * 3、回溯算法会超时啊
 */
fun generateParenthesis2(n: Int): List<String> {
    val allList = mutableListOf<String>()
    val sb = StringBuilder()
    for (i in 0 until n) {
        sb.append("(").append(")")
    }
    val parenthesis = sb.toString() // 记录左括号的个数，超过3个则不再继续遍历
    generateP(parenthesis, 0, parenthesis.length, 0, "", allList) //    allList.print()

    return allList.filter {
        isEffective(it)
    }.toSet().toList()
}

/**
 * 是否是有效的括号
 * - 采用栈结构存储遍历到的字符
 * - 遇到左括号入栈，遇到右括号，取出栈顶元素，看能否匹配，能匹配出栈，不能匹配，不是有效的括号
 * - 最后看栈里面是否为空
 */
fun isEffective(str: String): Boolean {
    val stack = Stack<Char>()
    str.forEach {
        if (it == '(') {
            stack.push(it)
        } else { // 右括号
            if (stack.isEmpty()) {
                return false
            }
            if (stack.peek() == '(') {  // 栈顶元素要是左括号，出栈，否则那就是不匹配了
                stack.pop()
            } else {
                return false
            }
        }
    }
    return stack.isEmpty()
}

fun generateP(parenthesis: String, index: Int, n: Int, leftNum: Int, str: String,
    list: MutableList<String>) {
    if (leftNum > n / 2) { // 去重，左括号的数量不能超过一半
        return
    }
    if (index == n) {
        list.add(str)
        return
    }
    for (i in 0 until n) {
        val ch = parenthesis[i]
        if (index == 0 && ch == ')') { // 过滤括号字符串，第一个是左括号的，肯定不是有效括号
            continue
        }
        var num = leftNum
        if (ch == '(') {
            num++
        }
        generateP(parenthesis, index + 1, n, num, str + ch, list)
        continue
    }
}










