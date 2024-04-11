package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import java.lang.StringBuilder

/**
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。

示例 1：
输入：nums = [10,2]
输出："210"

示例 2：
输入：nums = [3,30,34,5,9]
输出："9534330"

提示：
1 <= nums.length <= 100
0 <= nums[i] <= 109
 */
fun main() { //        val res = largestNumber(intArrayOf(10, 2))
    val res = largestNumber(
        intArrayOf(432, 43243)) //    val res = largestNumber(intArrayOf(3, 30, 34, 5, 9))
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入由正整数组成的数组，需要将数组中的数字元素重新排列组合，生成一个最大的数字
 * 2、解题：
 * - 求组合后的最大数，就是找数组中第一位最大的数，如果第一位相同，则继续比较第二位，
 * - 还有一种情况，前缀相同，后面的不同，则后缀子串的第一个数字和前面子串的第一个数字进行比较，
 * - 遇到尾部的，就从头再来
 * -- 使用list集合的比较函数就可以求解
 */
fun largestNumber(nums: IntArray): String {
    // 全是0
    val filter = nums.filter { it == 0 }
    if (nums.size==filter.size){
        return "0"
    }

    var numStrList = nums.map { it.toString() }
    val resList = numStrList.sortedWith(object : Comparator<String> {
        override fun compare(o1: String?, o2: String?): Int {
            /**
             * 不断遍历两个数字字符中的数字，
             * 判断两个数字的大小，遇到o1大于o2的返回1
             * 相同的时候，返回0
             * - 当其中有个数字遍历到最后位置了，则判断长串的后缀子串第一位数字和字符的前缀子串第一位数字的大小，进行比较
             */
            var i1 = 0
            var i2 = 0
            val len1 = o1!!.length
            val len2 = o2!!.length
            while (i1 != len1 && i2 != len2) {
                if (o1[i1] > o2[i2]) {
                    return -1
                } else if (o1[i1] == o2[i2]) {
                    i1++
                    i2++
                    if (i1 == len1 && i2 == len2) {
                        return 0
                    }
                    if (i1 == len1) {
                        i1 = 0
                    }
                    if (i2 == len2) {
                        i2 = 0
                    }
                } else {
                    return 1
                }
            }
            return 0
        }
    })

    resList.print()

    val sb = StringBuilder()
    for (i in resList.indices) {
        sb.append(resList[i])
    }

    return sb.toString()
}