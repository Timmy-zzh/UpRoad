package com.timmy.dataalg._11sort

import com.timmy.dataalg.print

fun main() {
    val arr = intArrayOf(4, 5, 6, 3, 2, 1)
    arr.print()
    select(arr)
    arr.print()
}

/**
 * 选择排序：
 * - 两层循环，外层循环定位左边的元素
 * - 内层循环找到最小的元素位置，然后与定位元素，进行交换处理
 */
fun select(arr: IntArray) {
    val size = arr.size
    for (i in 0 until size - 1) {
        var value = arr[i]
        var n = i
        for (j in i until size) {
            if (arr[j] < value) {
                value = arr[j]
                n = j
            }
        }

        // 交换
        if (n != i) {
            val temp = arr[i]
            arr[i] = arr[n]
            arr[n] = temp
        }
    }
}











