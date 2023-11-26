package com.timmy.dataalg._13string

/**
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。

示例 1：
输入：s = "the sky is blue"
输出："blue is sky the"

示例 2：
输入：s = "  hello world  "
输出："world hello"
解释：反转后的字符串中不能存在前导空格和尾随空格。

示例 3：
输入：s = "a good   example"
输出："example good a"
解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
 */
fun main() { //    val res = reverseWords("the sky is blue")
    //    val res = reverseWords("  hello world  ")
    val res = reverseWords("a good   example")
    println(res)
}

/**
 * 1、审题：输入一个字符串，字符串由单词和空格组成，需要将字符串中的单词翻转，并返回
 * 2、解题：
 * - 遍历字符，遍历到字符时，需要将整个单词都拿到，并保存在List集合中
 * - 遍历到空格时，continue，
 * - 全部结束，将集合中的单词拼接，并加上空格
 * 3、其他解法
 * - 先按照空格分割，
 * - 将单个单词添加到list集合中
 * - 反转集合单词的顺序，再拼接
 */
fun reverseWords(s: String): String {
    val sb = StringBuilder()
    val item = StringBuilder()
    val list = mutableListOf<String>()
    for (index in s.indices) {
        val ch = s[index]
        if (ch == ' ') {
            if (item.isNotEmpty()) {
                list.add(item.toString())
            }
            item.clear()
            continue
        }
        item.append(ch)
    }
    if (item.isNotEmpty()) {
        list.add(item.toString())
    }

    for (i in list.size - 1 downTo 0) {
        sb.append(list[i])
        if (i != 0) {
            sb.append(" ")
        }
    }
    return sb.toString()
}