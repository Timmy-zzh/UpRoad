package com.timmy.dataalg.leetcode

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。

示例 1:
输入: s = "anagram", t = "nagaram"
输出: true

示例 2:
输入: s = "rat", t = "car"
输出: false

提示:
1 <= s.length, t.length <= 5 * 104
s 和 t 仅包含小写字母
进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
fun main() {
}

/**
 * 1、审题：
 * - 输入两个字符串s和t，判断两个字符串是否是字母异位词
 * -- 字母异位词的定义是，两个字符串的字母和总个数相同，但是组合方式不同
 * 2、解题
 * - 题目中的条件是s和t都是由小写字母组成，所以可以使用数组，数组大小26（总共字母26个）来记录每个字母出现的次数
 * - 先记录s出现的，接着遍历字符串t，没出现一次字母减少1，最后判断是否存在字母个数不为0的，都是0则说明s和t是字母异位词
 * 3、进阶解法： 使用 HashMap 保存每个字符出现的次数
 */
fun isAnagram(s: String, t: String): Boolean {
    val size = 26
    val numArr = Array(size) { 0 }

    s.forEach {
        numArr[it - 'a'] = numArr[it - 'a'] + 1
    }

    t.forEach {
        numArr[it - 'a'] = numArr[it - 'a'] - 1
    }

    numArr.forEach {
        if (it != 0) {
            return false
        }
    }
    return true
}