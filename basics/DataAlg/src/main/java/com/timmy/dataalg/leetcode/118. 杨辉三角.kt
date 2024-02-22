package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
在「杨辉三角」中，每个数是它左上方和右上方的数的和。

示例 1:
输入: numRows = 5
输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

示例 2:
输入: numRows = 1
输出: [[1]]
 */
fun main() {
    val res = generate(5)
    res.print()
}

/**
 * 1、审题：
 * - 输入数字numRows表示扬辉三角具有多少行，杨辉三角的特点是最外层元素值是1，其他位置的值，是他上方两个位置元素的和
 * 2、解题：
 * - 杨辉三角最后返回的是一个二维List集合
 * - 每一层集合，第一位元素和最后一位元素的值为1
 * - 中间元素的值，是上一层集合的两个元素的和
 * - 遍历层数rows，设置每层集合的值，并最终返回集合
 */
fun generate(numRows: Int): List<List<Int>> {
    val resList = mutableListOf<List<Int>>()
    val list1 = arrayListOf(1)
    resList.add(list1)
    for (i in 1 until numRows) {    // 表示是第几层
        val itemList = Array(i + 1){1}
        itemList[0] = 1
        itemList[i] = 1
        for (n in 1 until i) {
            val preList = resList[i - 1]    // 上一层集合
            itemList[n] = preList[n - 1] + preList[n]
        }
        resList.add(itemList.toList())
    }
    return resList
}