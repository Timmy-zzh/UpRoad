package com.timmy.dataalg._11sort

import com.timmy.dataalg.print

fun main() {
    val arr = intArrayOf(4, 5, 6, 3, 2, 1)
    arr.print()
    insert(arr)
    arr.print()
}

/**
 * 插入排序
 * 解题：
 * - 插入排序的思想是将数组分为两个区域，左边为已排序，右边为未排序
 * - 不断遍历，从右边未排序区域中选择元素，插入到左边有序区域中，并做好元素的比较移动
 * 总结：遍历到第一个元素刚好大于，需要将val与第一个位置的元素进行交换
 */
fun insert(arr: IntArray) {
    val size = arr.size
    for (i in 1 until size) {
        val value = arr[i]
        var n = i - 1
        for (j in i - 1 downTo 0) {
            n = j
            if (arr[j] > value) {
                arr[j + 1] = arr[j]
                if (j == 0) n--
            } else {
                break
            }
        }
        arr[n + 1] = value
    }
}











