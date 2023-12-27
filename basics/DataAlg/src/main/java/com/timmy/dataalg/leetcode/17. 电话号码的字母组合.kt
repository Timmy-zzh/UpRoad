package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

示例 1：
输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]

示例 2：
输入：digits = ""
输出：[]

示例 3：
输入：digits = "2"
输出：["a","b","c"]
 */
fun main() {
    val res = letterCombinations("23")
    res.print()
}

/**
 * 1、审题：
 * - 输入一个由数字组成的字符串，数字由2-9组成，且对应电话键盘的字母，其中2对应abc中的任意一个字母，他们存在一个对应关系（map）
 * -现在输入一个数字组成的字符，返回对应的字母组合
 * 2、解题：
 * - 回溯算法
 * -- 先将数字与字母的对应关系保存在map中，再取出数字字符中的元素，进行遍历，找到对应的字母列表，选择其中的一个
 * -- 不断回溯，到最后一个数字时，保存到list集合中返回
 */
fun letterCombinations(digits: String): List<String> {
    val listRes = mutableListOf<String>()
    if (digits.isEmpty()) {
        return listRes
    }
    val map = mapOf(
        "2" to "abc",
        "3" to "def",
        "4" to "ghi",
        "5" to "jkl",
        "6" to "mno",
        "7" to "pqrs",
        "8" to "tuv",
        "9" to "wxyz",
    )
    letterC(digits, map, 0, digits.length, "", listRes)
    return listRes
}

fun letterC(digits: String, map: Map<String, String>, i: Int, n: Int, str: String,
    list: MutableList<String>) {

    println("i:$i,str:$str,list:$list")

    if (i == n) {
        list.add(str)
        return
    }
    val c = digits[i]
    val letters = map[c.toString()]
    letters?.forEach {
        letterC(digits, map, i + 1, n, str + it, list)
    }
}
