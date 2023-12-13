package com.timmy.dataalg._13string

import com.timmy.dataalg.print
import kotlin.math.max

fun main() {
//    val res = bmMatch("abcacabdc", "abd")
    val res = bmMatch("abcacabcbcabcabcbacabc", "cabcab")
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
 * - 创建suffix数组：用于保存模式串后缀子串，在不同长度下，在前面部分出现相同子串的下标位置
 * - 创建prefix数组，用于保存模式串后缀子串，的长度标识下，是否与模式串前缀子串相匹配
 * -- 还是有点没太明白prefix数组的作用
 * - 通过计算当存在好后缀时，最大移动的位数
 */
fun bmMatch(a: String, p: String): Int {
    val aLen = a.length
    val pLen = p.length

    val size = 256    // 散列表大小
    val bc = IntArray(size)
    generateBC2(bc, p, pLen)

    val suffix = IntArray(pLen)
    val prefix = BooleanArray(pLen)
    generateGS(p, pLen, suffix, prefix)

    var i = 0
    while (i <= aLen - pLen) { // 外层主串遍历
        println("while i:$i")

        var si = pLen - 1 // 坏字符在模式串中的位置
        for (j in (pLen - 1) downTo 0) { // 内层模式串遍历
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
        var offset = si - xi // 坏字符规则-移动位置
        println("xi:$xi,offset - bad :$offset,i:$i")
        if (si < pLen - 1) { // 存在好后缀清空，计算可以移动的位数
            val offsetGood = callGoodNext(si, pLen, suffix, prefix)
            println("xi:$xi,offsetGood :$offsetGood,i:$i")
            offset = max(offset, offsetGood)
        }

        i += offset
        println("xi:$xi,offset:$offset,i:$i")
    }

    return -1
}

fun callGoodNext(si: Int, pLen: Int, suffix: IntArray, prefix: BooleanArray): Int {
    /**
     * 坏字符位置是si
     * 好后缀字符的长度是： pLne- si + 1
     */
    val k = pLen - 1 - si
    if (suffix[k] != -1) { // 不为-1，说明前面存在相同的字符子串，可以移动位置
        return si - suffix[k] + 1  // 坏字符在模式串中的位置
    }

    /**
     * prefix 不知道怎么用了
     * - 上面suffix[k] ==-1,说明好后缀在前面字符没有相同的子串
     * - 那好后缀的子串，是否存在和前缀子串相同的情况呢？如果存在prefix为true，可移动位数为i
     */
    for (i in si + 2 until pLen) {
        if (prefix[pLen - i]) {
            return i
        }
    }

    return pLen
}

/**
 * 填充suffix与prefix数组元素数据
 * - suffix数组标识模式串后缀子串，在模式串前面部分是否存在相同的子串，存在则保存起始位置，不存在值为-1
 * - prefix数组标记，后缀子串长度，是否与模式串前缀子串相匹配，匹配则保存true
 * -- prefix存储的是后缀子串与前缀子串匹配的数据
 */
fun generateGS(p: String, pLen: Int, suffix: IntArray, prefix: BooleanArray) {

    // 默认值 -1，与false
    for (i in 0 until pLen) {
        suffix[i] = -1
        prefix[i] = false
    }

    /**
     * 两层循环，
     * - 外层循环i从0到pLen，标识模式串从哪个字符开始遍历了，
     * - 内存循环，往前遍历，从i往前遍历，看是否与模式串的后缀子串相匹配k，k月减少
     */
    for (i in 0 until pLen - 1) { // 查找模式串前面子串
        var j = i   // 当前遍历到的字符的下标位置
        var k = 0  // 后缀子串的长度

        while (j >= 0 && p[j] == p[pLen - k - 1]) {    // 前面字符，是否与后缀子串相匹配
            --j
            ++k
            suffix[k] = j + 1           //  j + 1 精妙，之前用的是j
        }
        if (j < 0) {    // j<0标识后缀子串，与前缀子串匹配
            prefix[k] = true
        }
    }

    println("suffix.print()")
    suffix.print()
    println("prefix.print()")
    prefix.print()
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





