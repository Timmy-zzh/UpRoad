package com.timmy.dataalg.leetcode

import com.timmy.dataalg.printList

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
回文串 是正着读和反着读都一样的字符串。

示例 1：
输入：s = "aab"
输出：[["a","a","b"],["aa","b"]]

示例 2：
输入：s = "a"
输出：[["a"]]

提示：
1 <= s.length <= 16
s 仅由小写英文字母组成
 */
fun main() {

    //    val res = partition("aab")
    val res = partition("cbbbcc")
    res.printList()
}

/**
 * 1、审题：
 * - 输入一个字符串，对字符串进行分割，要求分割后的所有子串都是回文串，求分割的所有方案
 * 2、解题：回溯法
 * - 对字符串进行分割，采用for循环遍历实现，从起始位置开始遍历，循环的位置作为遍历的结束位置
 * - 遍历结束位置，继续调用递归函数，直到字符串分割到尾部
 * - 每次记录分割的子串，保存在集合itemList中，最后返回所有的方案
 */
var resList1 = ArrayList<List<String>>()
fun partition(s: String): List<List<String>> {
    processP(s, 0, arrayListOf<String>())
    return resList1
}

fun processP(s: String, i: Int, itemList: ArrayList<String>) {
    println("processP s:$s,i:$i,itemList:$itemList")
    val len = s.length
    if (i == len) {
        val newList = ArrayList<String>()
        newList.addAll(itemList)
        if (checkPalindromeList(newList)) {
            println("-- newList:$newList")
            resList1.add(newList)
        }
        return
    }

    for (j in i + 1..len) {
        val item = s.substring(i, j)
        println("item:$item")
        itemList.add(item)
        processP(s, j, itemList)
        itemList.remove(item)
    }
}

fun checkPalindromeList(newList: java.util.ArrayList<String>): Boolean {
    newList.forEach {
        if (!checkPalindrome(it)) {
            return false
        }
    }
    return true
}

fun checkPalindrome(s: String): Boolean {
    var left = 0
    var right = s.length - 1
    while (left < right) {
        if (s[left] != s[right]) {
            return false
        }
        left++
        right--
    }
    return true
}