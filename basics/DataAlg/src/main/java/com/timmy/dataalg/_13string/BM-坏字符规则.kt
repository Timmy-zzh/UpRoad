package com.timmy.dataalg._13string

import com.timmy.dataalg.print

fun main() {
    val res = badStr("abcacabdc", "abd")
    println("res:$res")
}

/**
 * 字符串匹配：坏字符规则实现
 * 1、审题：输入主串a，和模式串p，查找模式串在主串中的匹配位置，第一个匹配位置，如果没有则返回-1
 * 2、解题思路：坏字符规则
 * - 判断主串部分的子串字符与模式串字符是否匹配，沿着模式串从后往前遍历比较
 * - 如果全部匹配上了，直接返回主串的遍历位置，
 * -- 如果其中一个字符没有匹配上，则子串中的该字符就叫做坏字符，在模式串匹配的位置记为si
 * -- 然后查找该字符，在模式串中最后出现的位置xi，如果没有则为-1;
 * -- 最后计算出模式串往后移动的位数： si-xi
 * 3、实现
 * - 创建散列表bc，遍历模式串，记录模式串中每个字符最后出现的位置，用于遇到坏字符是查找
 * - 外层遍历主串a-i，从0位置开始，内存遍历模式串，从后往前遍历查找
 * -- 判断是否遇到不匹配的字符，也就是坏字符，
 * -- 没有遇到不配的情况，说明查找到结果，直接返回i
 * -- 如果遇到了，则从散列表中取出该字符的位置，然后进行模式串的位置移动
 */
fun badStr(a: String, p: String): Int {
    val aLen = a.length
    val pLen = p.length

    val SIZE = 256    // 散列表大小
    val bc = IntArray(SIZE)
    generateBC(bc, p, pLen)

    var i = 0
    while (i <= aLen - pLen) { // 外层主串遍历
        println("while i:$i")

        var si = pLen - 1 // 坏字符在模式串中的位置
        for (j in (pLen - 1)downTo 0) { // 内层模式串遍历
            if (a[i + j] != p[j]) {
                println("a[i + j] != p[j]")
                break
            }
            --si
            println("a[i + j] == p[j] si:$si")
        }
        println("si:$si")

        if (si < 0) {  // 找了匹配位置
            return i
        }

        val xi = bc[a[i + si].code] // 坏字符在散列表中的位置
        val offset = si - xi // 移动位置
        i += offset
        println("xi:$xi,offset:$offset,i:$i")
    }

    return -1
}

fun generateBC(bc: IntArray, p: String, pLen: Int) {
    for (i in bc.indices) {
        bc[i] = -1
    }

    for (i in 0 until pLen) {
        bc[p[i].code] = i
    }
    bc.print()
}





