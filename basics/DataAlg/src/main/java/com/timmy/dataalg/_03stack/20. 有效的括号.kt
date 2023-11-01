package com.timmy.dataalg._03stack

import java.util.Stack

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
有效字符串需满足：
左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
每个右括号都有一个对应的相同类型的左括号。

示例 1：
输入：s = "()"
输出：true

示例 2：
输入：s = "()[]{}"
输出：true

示例 3：
输入：s = "(]"
输出：false
 */
fun main() {
    val valid = isValid("()")
    println(valid)

}


/**
 * 1、审题：输入一个字符串，字符串由各种括号组成，判断字符串括号是否是匹配的
 * 2、解题：遍历字符串元素，如果是括号左边部分，入栈，如果是括号右边部分，则从栈中取出栈顶元素，进行匹配
 * - 需要先使用map保存匹配的括号键值对
 *
 * 3、总结：注意遍历到右边括号时，从栈顶拿到的元素是左边括号
 */
fun isValid(s: String): Boolean {
    val map: Map<Char, Char> = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}'
    )
    val stack = Stack<Char>()
    s.forEach { ch ->
        if (map.containsKey(ch)) { // 左括号
            stack.push(ch)
        } else {
            // 右括号
            if (stack.isEmpty()) {
                return false
            }
            // 出栈-》比较
            val pop = stack.pop()
            if (map[pop] != ch) {
                return false
            }
        }
    }
    return stack.isEmpty()
}





















