package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。

示例 1：
输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。

示例 2：
输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
注意，你可以重复使用字典中的单词。

示例 3：
输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false

提示：
1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s 和 wordDict[i] 仅由小写英文字母组成
wordDict 中的所有字符串 互不相同
 */
fun main() {
//    val list = mutableListOf<String>()
//    list.add("leet")
//    list.add("code")
//    val res = wordBreak("leetcode", list)
    val list = mutableListOf<String>()
    list.add("dog")
    list.add("s")
    list.add("gs")
    val res = wordBreak("dogs", list)
    //    val list = mutableListOf<String>() //    list.add("apple") //    list.add("pen")
    //    val res = wordBreak("applepenapple", list)
    //    val list = mutableListOf<String>()
    //    list.add("cats")
    //    list.add("dog")
    //    list.add("sand")
    //    list.add("and")
    //    list.add("cat")
    //    val res = wordBreak("catsandog", list)
    println("res:$res")
}

/**
 * 2、动态规划解法：
 * - 状态转移方程式
 * -- dp[i] 标识字符串从0到i位置是否存在匹配的情况
 * -- dp[0]等于true
 * - 双层for循环
 * -- 外层for循环，遍历字符串s，每当遍历某个字符时，就与字符集合中的所有单个字符进行比较
 * -- 从后往前遍历，直到遍历到字符开头，如果匹配，则dp[x] = dpx[x-size]的值，size为字符集合中单词的长度
 * 3、牛逼：
 * - 完全靠自己的思路实现通过，并且得到双百结果，厉害
 * - 继续努力，先保证正确率，后提高速度
 */
fun wordBreak(s: String, wordDict: List<String>): Boolean {
    val n = s.length
    val dp = Array(n + 1) {
        false
    }
    dp[0] = true
    dp.print()
    println("n :$n")

    for (i in 0 until n) {  // 遍历字符串s
        println("i:$i")
        for (w in wordDict.indices) {    // 遍历字符串集合
            val word = wordDict[w]
            if (word.length > i + 1) {
                continue
            }
            if (check139(s, i, word)) {
                println("for i:$i,word:$word")
                dp[i + 1] =  dp[i + 1] || dp[i - (word.length - 1)]
                dp.print()
            }
        }
    }
    dp.print()
    return dp[n]
}

/**
 * 字符串s，从i开始往前遍历，word字符串从后往前遍历
 */
fun check139(s: String, i: Int, word: String): Boolean {
    println("check139 i:$i,word:$word")
    val size = word.length
    for (j in size - 1 downTo 0) {
        if (word[j] != s[i - (size - 1 - j)]) {
            return false
        }
    }
    return true
}

/**
 * 1、审题：
 * - 输入一个目标字符串，和字符串数组，现在要判断目标字符串是否能由字符串数组中的单词构成
 * - 字符串数组中的单词可以多次使用
 * 2、解题：
 * - 回溯算法
 * - 遍历字符串数组中的单词，获取的单词判断与目标字符串是否存在前缀匹配
 * - 如果存在则目标单词减掉这部分，继续与字符串数组中的单词进行比较，直到目标字符串为空，说明找到目标值
 * - 或者最后目标字符串不为空，但是经过一趟遍历后字符串数组中的单词没有匹配的前缀单词，则说明匹配不了，直接返回false
 * 3、超出时间限制
 * - 换成动态规划实现
 */
var res = false
fun wordBreak1(s: String, wordDict: List<String>): Boolean {
    wordBreakBack(s, wordDict)
    return res
}

fun wordBreakBack(s: String, wordDict: List<String>) {
    println("wordBreakBack s:$s")
    if (res) {
        return
    }
    if (s.isEmpty()) {
        res = true
        return
    }
    for (i in wordDict.indices) {
        val wordItem = wordDict[i]
        if (s.startsWith(wordItem)) {
            wordBreakBack(s.substring(wordItem.length), wordDict)
        }
    }
}
