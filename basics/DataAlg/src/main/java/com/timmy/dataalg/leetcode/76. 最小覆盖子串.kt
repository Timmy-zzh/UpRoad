package com.timmy.dataalg.leetcode


/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

注意：
对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
如果 s 中存在这样的子串，我们保证它是唯一的答案。

示例 1：
输入：s = "ADOBECODEBANC", t =
输出："BANC"
解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。

示例 2：
输入：s = "a", t = "a"
输出："a"
解释：整个字符串 s 是最小覆盖子串。

示例 3:
输入: s = "a", t = "aa"
输出: ""
解释: t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。
 */
fun main() {

    //        val res = minWindow("ADOBECODEBANC", "ABC")
    //        val res = minWindow("a", "aa")
    val res = minWindow("a", "a") //    val res = minWindow("ab", "b")
    //    val res = minWindow("cabwefgewcwaefgcf", "cae")
    println("res:$res")
}

/**
 * 2、直接双指针解法
 * - left，right指针
 * 3、总结优化
 * - 不使用两个map集合，直接截取字符串是否效率更高？
 */
fun minWindow(s: String, t: String): String {

    // 异常情况
    if (s.length < t.length) {
        return ""
    }
    val tLen = t.length
    val sLen = s.length
    var res = ""
    println("s:$s,sLen:$sLen , t:$t,tLen:$tLen")
    var left = 0

    // 创建两个map，用于存储字母出现的次数
    val tMap = mutableMapOf<Char, Int>()
    t.forEach { ch ->   // 将字符t的字符出现的次数保存下来
        val count = tMap.getOrDefault(ch, 0)
        tMap[ch] = count + 1
    }
    val sMap = mutableMapOf<Char, Int>()

    // 不包含右侧指针后移，包含，左侧指针右移
    for (right in 0 until sLen) {
        val ch = s[right]
        val count = sMap.getOrDefault(ch, 0)
        sMap[ch] = count + 1

        while (left <= right - tLen + 1) {

            if (isCover(sMap, tMap)) {
                println("isCover ---->left:$left, right:$right")
                val resTemp = s.substring(left, right + 1)
                if (res.isEmpty() || resTemp.length < res.length) {
                    res = resTemp
                }

                sMap[s[left]] = sMap[s[left]]!! - 1
                left++
            } else {
                break
            }
        }
    }

    return res
}

/**
 * 1、审题：
 * - 输入一个主串s，字符串t，在主串中找到一个子串si，要求子串si包含了字符串t中的所有元素
 * - 并返回最短子串si
 * 2、解题：
 * - 从主串s中查找一个子串si，
 * - 有两个问题：
 * -- 如何判断s的一个子串中是否全部覆盖了字符串t的所有字符？
 * -- s的子串si如何变化大小？si是需要不断移动的
 * =》 使用哈希表存储字符串t的字母出现的次数，和s子串的字母情况
 * 3、问题
 * - 时间复杂度m*n，超出时间限制
 * - 寻求其他解法：
 */
fun minWindow1(s: String, t: String): String {

    // 异常情况
    if (s.length < t.length) {
        return ""
    }
    val tLen = t.length
    val sLen = s.length
    var res = ""
    println("s:$s,sLen:$sLen , t:$t,tLen:$tLen")

    // 创建两个map，用于存储字母出现的次数
    val tMap = mutableMapOf<Char, Int>()
    val sMap = mutableMapOf<Char, Int>()

    t.forEach { ch ->   // 将字符t的字符出现的次数保存下来
        val count = tMap.getOrDefault(ch, 0)
        tMap[ch] = count + 1
    }

    for (i in 0 until tLen) {  // 将字符串s前面的字母出现的次数保存下来
        val ch = s[i]
        val count = sMap.getOrDefault(ch, 0)
        sMap[ch] = count + 1
    }

    /**
     * 双指针解法
     * - 不断遍历字符串s的字母，这个就是右侧指针right
     * - 并定义一个左侧指针left
     * -- 规则，左侧指针到右侧指针，一定要大于等于tLen,
     * -- 因为右侧指针不断往右扩大，左侧指针从开始位置不断往右遍历，判断s子串sMap与tMap是否存在覆盖情况，
     * -- 当左侧指针left不断往右移动时，存在更小长度的子串覆盖情况，则left往右更新
     */
    var left = 0 // 判断s子串与t，是否存在覆盖
    if (isCover(sMap, tMap)) {
        res = s.substring(left, tLen)
    }

    for (i in tLen until sLen) {

        // left也可能有更新，则sMap也需要更新
        sMap.clear()
        var x = left
        while (x <= i) {
            val ch = s[x]
            val count = sMap.getOrDefault(ch, 0)
            sMap[ch] = count + 1
            x++
        }

        // left不断往后移动
        var leftTemp = left
        val sMapTemp = mutableMapOf<Char, Int>()
        sMapTemp.putAll(sMap)
        var resTemp: String

        while (leftTemp <= i - tLen + 1) {

            // 判断当前保存的s子串，是否覆盖
            if (isCover(sMapTemp, tMap)) {
                resTemp = s.substring(leftTemp, i + 1)
                println("isCover ---->leftTemp:$leftTemp,i:$i ,resTemp:$resTemp ,res:$res")
                if (res.isEmpty() || resTemp.length < res.length) {
                    res = resTemp
                    left = leftTemp
                }
            }

            sMapTemp[s[leftTemp]] = sMapTemp[s[leftTemp]]!! - 1

            // 左侧指针left往后移动
            leftTemp++
        }
    }

    return res
}

fun isCover(sMap: MutableMap<Char, Int>, tMap: MutableMap<Char, Int>): Boolean {
    println("isCover sMap:$sMap , tMap:$tMap")
    if (sMap.size < tMap.size) {
        return false
    }
    tMap.forEach { entry ->
        val ch = entry.key
        val num = entry.value

        // 不符合的情况，直接返回false
        if (!sMap.containsKey(ch)) { // 不包含
            return false
        }

        // 包含了，但是数量更小
        if (sMap[ch]!! < num) {
            return false
        }

        // 单个字母，包含，且数量大于等于tMap
    }
    return true
}
