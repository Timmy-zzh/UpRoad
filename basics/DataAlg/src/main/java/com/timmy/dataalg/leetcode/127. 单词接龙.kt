package com.timmy.dataalg.leetcode


/**
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：

每一对相邻的单词只差一个字母。
对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
sk == endWord
给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。

示例 1：
输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
输出：5
解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。

示例 2：
输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
输出：0
解释：endWord "cog" 不在字典中，所以无法进行转换。

提示：
1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord、endWord 和 wordList[i] 由小写英文字母组成
beginWord != endWord
wordList 中的所有字符串 互不相同
 */
fun main() {
//    val res = ladderLength("hit", "cog", arrayListOf("hot", "dot", "dog", "lot", "log", "cog"))
    val res = ladderLength("leet", "code", arrayListOf("lest","leet","lose","code","lode","robe","lost"))
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个开始单词beginWord，和结束单词endWord，和一个单词集合wordList，单词集合中的每个单词相互之间都相差一个单词
 * - 现在要计算从开始单词，到结束单词，通过单词集合中的元素，的转变次数，
 * 2、解题：
 * - 首先结束单词一定要要在单词集合中，
 * - 开始单词和结束单词，单词个数要相同
 * - 最核心的算法是通过单词集合，进行单词的转换，
 * -- 回溯算法
 * - 以开始单词begin作为基础，遍历集合，从中找到只相差一个字母的单词，将该单词从集合中剔除，并以转变的单词为基础，继续从删除后的集合中查找
 * - 直到转变后的单词是结束单词endWord
 */
var resInt = Int.MAX_VALUE
fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
    if (beginWord.length != endWord.length) {
        return 0
    }
    if (!wordList.contains(endWord)) {
        return 0
    }

    // 结束单词endWord在集合中，如何通过结束单词逆推导到开始单词beginWord？
    ladder(1, beginWord, endWord, wordList)
    if (resInt == Int.MAX_VALUE) {
        return 0
    }
    return resInt
}

fun ladder(count: Int, beginWord: String, endWord: String, wordList: List<String>) {
    println("ladder i=$count, beginWord:$beginWord,endWord:$endWord,wordList:$wordList")
    if (endWord == beginWord) {
        if (resInt > count) {
            resInt = count
        }
        return
    }
    if (wordList.isEmpty()) {
        return
    }

    for (i in wordList.indices) {
        val word = wordList[i]
        if (checkDiffOneLetter(beginWord, word)) {
            println("checkDiffOneLetter - true")
            val newList = ArrayList<String>()
            newList.addAll(wordList)
            newList.remove(word)
            ladder(count + 1, word, endWord, newList)
        }
    }
}

/**
 * 检查单词的差异，是否只相差一个字母，
 * - 遍历后保存单词
 * - 而后遍历另一个单词的字母，相同的则删除，不同的保存
 */
fun checkDiffOneLetter(beginWord: String, word: String): Boolean {
    println("checkDiffOneLetter beginWord:$beginWord,word:$word")
    val wordSet = ArrayList<Char>()
    for (i in beginWord.indices) {
        val wd = beginWord[i]
        wordSet.add(wd)
    }

    var diffCount = 0
    for (i in word.indices) {
        val wd = word[i]
        if (wordSet.contains(wd)) { // 相同，则删除
            wordSet.remove(wd)
        } else {
            diffCount++
        }
    }
    return diffCount == 1 && wordSet.size == 1
}
