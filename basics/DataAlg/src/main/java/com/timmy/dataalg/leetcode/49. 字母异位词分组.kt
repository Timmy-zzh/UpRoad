package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import java.util.Arrays

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
字母异位词 是由重新排列源单词的所有字母得到的一个新单词。

示例 1:
输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
输出: [["bat"],["nat","tan"],["ate","eat","tea"]]

示例 2:
输入: strs = [""]
输出: [[""]]

示例 3:
输入: strs = ["a"]
输出: [["a"]]
 */
fun main() {
    val strs = ArrayList<String>()
    strs.add("eat")
    strs.add("tea")
    strs.add("tan")
    strs.add("ate")
    strs.add("nat")
    strs.add("bat")
    val res = groupAnagrams(strs)
    res.print()
}

/**
 * 3.解题
 * - 思路还是一样，还是使用map键值对来保存数据
 * - 遍历字符串数组中的每个字符串，对字符串进行排序，以排序后的字符串作为key值，value为list集合
 * 4、总结：
 * - 核心是使用map保存每个字符串词根出现的情况，关键是key值得设置
 * - key值还可以使用字符串标识，采用字母加上出现的次数进行拼接
 */
fun groupAnagrams(strs: ArrayList<String>): List<List<String>> {
    val map: MutableMap<String, List<String>> = mutableMapOf()

    strs.forEach {
        val charArray = it.toCharArray()
        Arrays.sort(charArray)
        val key = String(charArray)
        val list: ArrayList<String> = map.getOrDefault(key, ArrayList()) as ArrayList<String>
        list.add(key)
        map[key] = list
    }

    val res = mutableListOf<List<String>>()
    map.forEach {
        res.add(it.value)
    }
    return res
}

/**
 * 1、审题：
 * - 输入一个字符串数组，字符串数组中的字符有单词词根组成相同的，也有不同的，
 * - 现在需要将单词词根相同的字符保存在一起，组成一个更大的数组返回
 * 2、解题：
 * - 遍历字符串数组中所有的字符，判断单个字符的词根是否有相同的，是相同的则保存到一起
 * -- 使用Map保存，key为单词词根，value为List<String>，
 * - 对于如何判断两个字符的组成词根是否相同，可使用26个单词字母数组来判断，
 * -- 如何做呢？创建一个int数组，大小为26个，遍历字符串中的每个单词，字母出现一次则在该单词位置数据+1
 * -- 其他的字符串也一样处理，如果两个单词最后判断你的一直则说明两个字符串的词根相同
 * - 太复杂了
 */
fun groupAnagrams1(strs: Array<String>): List<List<String>> {
    val itemMap: MutableMap<String, IntArray> = mutableMapOf()
    val map: MutableMap<String, List<String>> = mutableMapOf()

    strs.forEach {

        // 判断遍历的字符串，在map中是否存在，不存在创建，存在则add
        val itemNums = IntArray(26) { 0 }
        it.forEach { ch ->
            itemNums[ch - 'a'] = itemNums[ch - 'a'] + 1
        }
        itemMap[it] = itemNums
    }

    // 遍历字符串-数组 map中的数组是否存在相同的
    itemMap.forEach { // 是否有相同的
        val key = it.key
        val value = it.value

    }

    val res = mutableListOf<List<String>>()
    map.forEach {
        res.add(it.value)
    }
    res.print()
    return res
}