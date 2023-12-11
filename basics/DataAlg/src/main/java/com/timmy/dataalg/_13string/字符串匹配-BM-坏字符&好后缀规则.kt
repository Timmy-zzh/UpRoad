package com.timmy.dataalg._13string

import com.timmy.dataalg.print

fun main() {
    val res = bmMatch("abcacabdc", "abd")
    println("res:$res")
}

/**
 * 字符串匹配：
 * - 之前的BF，RK算法都是模式串与主串字符比较判断，未匹配时顺序往后移动一位继续判断，效率太低
 * - BM算法是想通过一些规律，当未匹配时，可以一次移动多位
 * 坏字符规则实现：
 * - 坏字符规则，模式串与主串中的字符从后往前遍历匹配，如果遇到不匹配的情况，则记录该字符在模式串的下标位置si，
 * -- 并查找该字符在模式串中最后出现的下标位置xi，最后计算出模式串往后移动的位数（si-xi）
 * 好后缀规则：
 * - 当坏字符不在子串的最后一位，而是在前面位置时，这个时候说明当前的子串与模式串后缀子串有部分内容是匹配的 {u}
 * -
 */
fun bmMatch(a: String, p: String): Int {
    val aLen = a.length
    val pLen = p.length

    val SIZE = 256    // 散列表大小
    val bc = IntArray(SIZE)
    generateBC2(bc, p, pLen)

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

fun generateBC2(bc: IntArray, p: String, pLen: Int) {
    for (i in bc.indices) {
        bc[i] = -1
    }

    for (i in 0 until pLen) {
        bc[p[i].code] = i
    }
    bc.print()
}





