package com.timmy.dataalg._11sort

import com.timmy.dataalg.print

fun main() {
    val arr = intArrayOf(4, 5, 6, 3, 2, 1)
    arr.print()
    bubble(arr)
    arr.print()
}

/**
 * 冒泡排序
 * - 排序的基本步骤有两步：比较和移动
 * - 冒泡排序，是两两相邻的元素进行比较和移动
 * 解题：
 * - 两层嵌套遍历，外层遍历i增加，每次都将一个最大的元素移动到最后面
 * - 内部循环，相邻元素比较移动，前面的元素更大，则进行相邻交换，
 */
fun bubble(arr: IntArray) {
    val size = arr.size
    for (i in 0 until size) {
        for (j in 0 until size - i - 1) {
            if (arr[j] > arr[j + 1]) {
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
        }
    }
}











