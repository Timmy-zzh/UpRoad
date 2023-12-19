package com.timmy.dataalg._13string

fun main() {
    val res = kmp("abcacabdc", "abd")

    //    val res = kmp("abcacabcbcabcabcbacabc", "cabcab")
    println("res:$res")
}

/**
 * 1、审题：
 * - a字符串为主串，p字符串为模式串，求模式串p在主串a是否存在相同子串，存在的话返回第一次出现的开始位置，不存在则返回-1
 * 2、解题
 * = 之前用的bm匹配算法，现在使用kmp匹配算法
 * = bm算法在匹配的时候，是从后往前遍历比较模式串的，遇到不匹配的字符（坏字符），采用坏字符和好后缀规则，
 * -- 好后缀规则：保存的是后缀子串不同长度的子串，与前面的子串匹配时匹配成功情况下的起始下标位置，使用suffix保存
 * -- 还有一种情况是用prefix保存好后缀的后缀子串与模式串的前缀子串匹配的情况，使用的是boolean类型数组保存
 * = kmp算法，也是讲模式串与主串进行匹配，遇到不匹配的字符（坏字符），前面已匹配的部分叫做好前缀，
 * --kmp算法要做的是根据模式串找到相关规则，让模式串能够多移动几位
 * - 使用next数组，保存模式串不同长度的子串，与模式串的最长前缀子串的匹配情况，
 * - 那kmp算法在实现的时候，主串按顺序从前往后遍历i，j是当前模式串匹配的位置，当遇到坏字符时需要动态变化
 * - 正常情况，i与j的字符匹配了，j不断递增，j最终遍历到模式串的最后位置，则找到匹配结果值
 * - 当遇到坏字符时，首先判断模式串的匹配位置j是否处于第0位，时的话j=0不变，i递增继续往后遍历其他的字符
 * -- 当遇到坏字符是，j大于0，说明前面有好前缀部分，这个时候j需要往后移动，那移动的位数多少呢？根据next数组来计算
 * -- 怎么计算？next数组保存的好前缀的最长后缀子串，匹配的模式串最长前缀子串的下标位置，j更新到这个位置的后面字符，继续判断，
 */
fun kmp(a: String, p: String): Int {
    val aLen = a.length
    val pLen = p.length
    val next: IntArray = getNexts(p, pLen)

    var j = 0
    for (i in 0 until aLen) {
        while (j > 0 && a[i] != p[j]) {
            j = next[j - 1] + 1
        }

        if (a[i] == p[j]) {
            j++
        }
        if (j == pLen) {
            return i - pLen + 1
        }
    }
    return -1
}

/**
 *  求Next数组:
 * - b表示模式串，m表示模式串的长度
 * - Next数组保存的是模式串，不同长度的子串，与模式串前缀子串相匹配的情况，要求最长长度
 * -- 如果存在，则next数组下标值为遍历到的前缀子串的下标值，元素值是相匹配的最长前缀子串的下标值
 * - 默认next[0] 为-1，因为子串只有一个字符，不存在前缀子串，遍历从i=0开始
 * - next[i] 的情况，采用动态规划的思路来计算，如果next[i-1]匹配的最长前缀子串的下标值为k-1,如果b[i]==next[k],则next[i]=k
 * - 如果不匹配，k的值变化，
 */
fun getNexts(p: String, pLen: Int): IntArray {
    val next = IntArray(pLen)
    next[0] = -1
    var k = -1  // k表示next的元素值，标识i情况下，最长可匹配前缀子串的下标，匹配不了，就是-1
    for (i in 1 until pLen) {   // 外层循环遍历的模式串，范围[1,plen-1]
        while (k != -1 && p[k + 1] != p[i]) { // 内存循环，是遇到不匹配的字符时，k值得变化
            k = next[k]
        }

        if (p[k + 1] == p[i]) {
            k++
        }
        next[i] = k
    }

    return next
}