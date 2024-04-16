package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import java.lang.Math.pow
import java.lang.StringBuilder

/**
 * 颠倒给定的 32 位无符号整数的二进制位。

提示：
请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，
并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。

示例 1：
输入：n = 00000010100101000001111010011100
输出：964176192 (00111001011110000010100101000000)
解释：输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。

示例 2：
输入：n = 11111111111111111111111111111101
输出：3221225471 (10111111111111111111111111111111)
解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。

提示：
输入是一个长度为 32 的二进制字符串

进阶: 如果多次调用这个函数，你将如何优化你的算法？
 */
fun main() {
    val res = reverseBits(43261596)
    //    val res = reverseBits(964176192)
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个长度为32位的二进制字符串，他是无符号的，要求将这个二进制前后颠倒，并返回其十进制类型结果值
 * 2、解题：
 * - 看题目输入的是一个int类型的值，将这个值转换成String类型，还是要先转成二进制？
 * - 拿到string类型的二进制结果后，前后颠倒，接着输出对应的十进制结果值
 * 3、解法：
 * - 1、输入的是十进制的数字，有可能存在负数，需要将输入的数字转成二进制字符串
 * - 2、将二进制字符串前后字符调换，这个很容易
 * - 3、最后是将前后调换过的二进制字符串转换成数字，注意没有符号位
 * - 核心点还是在于将输入的数字转成二进制字符串，
 * 4、MD，题解也看不懂
 * - 用的是位运算
 */
fun reverseBits(n: Int): Int {
    val binaryStr = num2Binary(n) // 补齐32位
    val realBinArr = Array(32) { '0' }

    var j = realBinArr.size - 1
    for (i in binaryStr.length - 1 downTo 0) {
        realBinArr[j] = binaryStr[i]
        j--
    }
    realBinArr.print()
    println("n:$n,toString:$binaryStr,realBinArr:$realBinArr")

    val size = realBinArr.size

    for (i in 0 until size / 2) { // 前后交换
        val temp = realBinArr[i]
        realBinArr[i] = realBinArr[size - 1 - i]
        realBinArr[size - 1 - i] = temp
    }
    realBinArr.print()

    var resL = 0L   // 遍历二进制数字，转换成十进制值
    for (i in size - 1 downTo 0) {
        resL += realBinArr[i].code * pow(2.0, (size - 1 - i).toDouble()).toLong()
    }
    return resL.toInt()
}


/**
 * 输入的是十进制数字，需要先转成二进制
 * - 如何转成二进制呢？ 除2取余法
 * - 就是讲原始数字除于2，可以得到结果商和余数
 * - ...
 * - 继续使用商除于2，直到结果等于0，将所有的余数逆序组合就是原始数据的二进制表达式
 * 例子： 求34的二进制表达式
 * 34 / 2 = 17 % 0
 * 17 / 2 = 8  % 1
 * 8 /  2 = 4  % 0
 * 4  / 2 = 2  % 0
 * 2 /  2 = 1  % 0
 * 1  / 2 = 0  % 1
 * -- > 最终结果是： 100010
 *
 * 如果是负数，怎么处理？？
 */
fun num2Binary(n: Int): String {
    val sb = StringBuilder()
    var num = n
    while (num != 0) {
        val remain = num % 2 // 余数
        num /= 2 // 商
        sb.append(remain)
    }
    return sb.reverse().toString()
}
