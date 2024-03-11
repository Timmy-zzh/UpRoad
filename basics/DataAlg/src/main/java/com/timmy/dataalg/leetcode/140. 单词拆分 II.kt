package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给定一个字符串 s 和一个字符串字典 wordDict ，在字符串 s 中增加空格来构建一个句子，
 * 使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可能的句子。
注意：词典中的同一个单词可能在分段中被重复使用多次。

示例 1：
输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
输出:["cats and dog","cat sand dog"]

示例 2：
输入:s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
输出:["pine apple pen apple","pineapple pen apple","pine applepen apple"]
解释: 注意你可以重复使用字典中的单词。

示例 3：
输入:s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
输出:[]

提示：
1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s 和 wordDict[i] 仅有小写英文字母组成
wordDict 中所有字符串都 不同
 */
fun main() { //    val list = mutableListOf<String>()
    //    list.add("apple")
    //    list.add("pen")
    //    list.add("applepen")
    //    list.add("pine")
    //    list.add("pineapple")
    //    val res = wordBreak("pineapplepenapple", list)
    //    println("res:$res")

    val list = mutableListOf<String>()
    list.add("cat")
    list.add("cats")
    list.add("and")
    list.add("sand")
    list.add("dog")
    val res = wordBreak2("catsanddog", list)

    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个字符串，和一个字符字典，要求使用字典中的单词，将字符串进行分割，最终形成一句话返回
 * 2、解题：回溯算法
 * - 对输入的字符串进行拆分，根据字符字典进行拆分，并将拆分后的单词进行保存到List集合中，在回溯的时候添加到list集中使用万后，要进行remove
 * -
 */
val strListRes = mutableListOf<List<String>>()
fun wordBreak2(s: String, wordDict: List<String>): List<String> {

    wordBreak2Back(s, wordDict, mutableListOf())

    println("wordBreak2Back strListRes:$strListRes")
    // 将List<String> 集合转成字符串，随后再添加到list集合中返回
    val res = mutableListOf<String>()
    strListRes.forEach { strListItem ->
        val sb = StringBuilder()
        println("forEach====== strListItem:$strListItem")
        strListItem.print()
        strListItem.forEach {
            sb.append(it).append(" ")
        }
        var itemStr = sb.toString()
        itemStr = itemStr.removeRange(itemStr.length - 1 until itemStr.length )
        println("itemStr:$itemStr")
        res.add(itemStr)
    }

    return res
}

fun wordBreak2Back(s: String, wordDict: List<String>, strList: MutableList<String>) {
    println("-- wordBreak2Back s:$s,strList:$strList,wordDict:$wordDict")
    if (s.isEmpty()) {
        println("s.isEmpty strList:$strList")
        val list = ArrayList<String>()
        list.addAll(strList)
        strListRes.add(list)
        println("s.isEmpty strListRes:$strListRes")
        return
    }

    /**
     * 遍历字符串，获取字符字典中的单词与字符串前面部分进行比较
     */
    for (i in s.indices) {
        val str = s.substring(0, i + 1)
        if (wordDict.contains(str)) {
            println("wordBreak2Back str:$str")
            strList.add(str)

            wordBreak2Back(s.substring(i + 1), wordDict, strList)

            strList.remove(str)
        }
    }
}
