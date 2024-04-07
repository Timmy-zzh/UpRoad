package com.timmy.dataalg.leetcode

import java.lang.StringBuilder
import kotlin.math.abs

/**
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
如果小数部分为循环小数，则将循环的部分括在括号内。
如果存在多个答案，只需返回 任意一个 。
对于所有给定的输入，保证 答案字符串的长度小于 104 。

示例 1：
输入：numerator = 1, denominator = 2
输出："0.5"

示例 2：
输入：numerator = 2, denominator = 1
输出："2"

示例 3：
输入：numerator = 4, denominator = 333
输出："0.(012)"

提示：
-231 <= numerator, denominator <= 231 - 1
denominator != 0
 */
fun main() {

//            val res = fractionToDecimal(4, 333)
    //    val res = fractionToDecimal(2, 1)
    //    val res = fractionToDecimal(1, 2)

    //    val res = fractionToDecimal(1, 6)
    //        val res = fractionToDecimal(7, -12)
    //    val res = fractionToDecimal(22, 7)
    //    val res = fractionToDecimal(-22, -2)
//    val res = fractionToDecimal(-50, 8)
    val res = fractionToDecimal(-1, -2147483648)
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入分子和分母，求分子与分母相除的结果，如果除不尽，且存在循环的小数出现时，则返回循环部分就行，返回相除结果
 * 2、解题：
 * - 核心问题是判断是否出现循环小数，这块涉及到数学除法和取余数的规则
 * - 使用map保存余数，当余数相同时，说明出现循环了，直接返回，当余数为0时，说明整除了
 * 3、总结：
 * - 相除取余数处理保存到map中，判断是否出现相同的余数，就可以知道是否会出现循环
 * - 分子等于0特殊处理
 * - 负数处理
 * - 精度问题处理
 * - 小数点位置查询，（不要写成固定的了）
 */
fun fractionToDecimal(numerator: Int, denominator: Int): String {
    if (numerator == 0) {
        return "0"
    }
    val map = HashMap<Long, Long>()
    val sb = StringBuilder()

    var divRes: Long // = numerator / denominator  // 相除结果值
    var divMain: Long // = numerator % denominator  // 相除余数
    var frag = 1
    if (numerator < 0) {
        frag *= -1
    }
    if (denominator < 0) {
        frag *= -1
    }

    var realNumerator = abs(numerator.toLong())
    var realDenominator = abs(denominator.toLong())
    if (frag < 0) {
        sb.insert(0, "-")
    }

    println(
        "fractionToDecimal realNumerator:$realNumerator ,realDenominator:$realDenominator")
    /**
     * 循环条件：
     * - 分子小于分母，
     * - 如果没有出现相同的也要循环，出现相同的余数，则退出循环
     */
    while (true) {
        divRes = realNumerator / realDenominator
        divMain = realNumerator % realDenominator
        println(
            "while realNumerator:$realNumerator ,realDenominator:$realDenominator,divRes:$divRes,divMain:$divMain")

        /**
         * 拿到相除的结果和余数
         * - 先将相除结果，保存到sb中,如果为0，则需要添加点号
         * - 接着看余数，如果余数为0，说明整除了，直接return返回
         * -- 如果余数不为0，分子剩余10，接着循环处理
         * -- 并且使用set保存余数的值
         */
        sb.append(divRes)
        if (divMain != 0L && divMain < realDenominator && !sb.contains(".")) {
            sb.append(".")
        }

        if (divMain == 0L) {
            return sb.toString()
        }
        if (map.contains(divMain)) {
            break
        }
        map.put(divMain, map.size.toLong())
        realNumerator = (divMain * 10).toLong()
    }

    println("sb:$sb,set:$map") // 查找循环小数开始的位置
    val index = map[divMain]!! + sb.indexOf(".")+1

    println("divMain:$divMain, index:$index")
    sb.insert(index.toInt(), "(").append(")")

    return sb.toString()
}

/**
 * 1、审题：
 * - 输入分数的分子分母，求分子分母相除的结果，如果存在小数，则将小数部分保存在括号里，最终以字符串形式返回
 * 2、解题：
 * - 分子分母相除，用double双精度对结果赋值，结果如果有小数，则插入括号
 * - 括号是要括起来，循环的小数
 * - 能够整除的不使用括号，解题的关键是如何判断小数中是否出现循环数字
 */
fun fractionToDecimal1(numerator: Int, denominator: Int): String {
    val resD: Double = numerator * 1.0 / denominator.toDouble()
    println("resD:$resD")
    if (numerator % denominator == 0) { // 可以整除，直接返回
        return resD.toInt().toString()
    }

    val resS = resD.toString()
    if (!resS.contains(".")) {  // 没有小数
        return resS
    }

    // 判断是否有循环的小数


    val resSb = StringBuilder(resS)
    for (i in resS.indices) {
        if (resS[i] == '.') {
            resSb.insert(i + 1, "(")
            break
        }
    }
    resSb.append(")")
    return resSb.toString()
}