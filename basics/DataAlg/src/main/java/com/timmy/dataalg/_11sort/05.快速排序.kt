package com.timmy.dataalg._11sort

import com.timmy.dataalg.print

fun main() {
    val arr = intArrayOf(4, 5, 6, 3, 2, 1)
    arr.print()
    quickSort(arr, 0, arr.size - 1)
    arr.print()
}

/**
 * 快速排序
 * - 用的也是分治思想，将数组分成两个部分，区分标准是选择一个锚点数值
 * - 锚点选的是区域中最后一个元素，前一个部分的元素值小于秒点，后一个元素值大于锚点
 *
 */
fun quickSort(arr: IntArray, p: Int, q: Int) {
    if (p >= q) {
        return
    }

    // 核心是找到锚点元素，并进行分区
    val point = point(arr, p, q)
    println("point=$point")

    quickSort(arr, p, point - 1)
    quickSort(arr, point + 1, q)
}

/**
 * 快速排序：
 * - 以最后一个元素q，作为标准
 * - 遍历数组元素，判断遍历到的元素值和标准值的大小，如果比标准大的，先不动，
 * - 遇到小的，需要与前面的大的元素进行交换，这样最后大的位置与标准值进行交换
 */
fun point(arr: IntArray, p: Int, q: Int): Int {
    val value = arr[q]
    var i = p // 大于标准值得位置
    for (j in p until q) {
        if (arr[j] < value) {
            swap(arr, i, j)
            i++
        }
    }
    swap(arr, i, q)
    println("i=$i -- ${arr.print()}")
    return i
}

fun swap(arr: IntArray, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}












