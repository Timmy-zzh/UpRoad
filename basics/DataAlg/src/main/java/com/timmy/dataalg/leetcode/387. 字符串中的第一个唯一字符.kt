package com.timmy.dataalg.leetcode

/**
 * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。

示例 1：
输入: s = "leetcode"
输出: 0

示例 2:
输入: s = "loveleetcode"
输出: 2

示例 3:
输入: s = "aabb"
输出: -1

提示:
1 <= s.length <= 105
s 只包含小写字母
 */
fun main() {
    val res = firstUniqChar("leetcode")
    println("res:$res")
}

/**
 * 1、审题
 * - 输入一个字符串，查找字符串中是否有不重复的字符，有的话返回第一个，没有的话返回-1
 * 2、解题
 * - 单层for循环，再使用map保存每个字符出现的次数，
 * - 查找次数为1的字符的位置，没有返回-1
 * 3、总结
 * - 还可以使用26位的数组，用于保存每个数字出现的次数，然后二次遍历判断该字符出现的数字是否是1
 */
fun firstUniqChar(s: String): Int {
    val map = mutableMapOf<Char, Int>()
    for (i in s.indices) {
        val num = map.getOrDefault(s[i], 0)
        map[s[i]] = num + 1
    }

    map.forEach {
        if (it.value == 1) {
            return s.indexOf(it.key)
        }
    }
    return -1
}