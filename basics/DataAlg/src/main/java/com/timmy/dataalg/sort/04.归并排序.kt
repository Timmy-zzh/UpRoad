package com.timmy.dataalg.sort

import com.timmy.dataalg.print

fun main() {
    val arr = intArrayOf(4, 5, 6, 3, 2, 1)
    arr.print()
    mergeSort(arr, 0, arr.size - 1)
    arr.print()
}

/**
 * 归并排序
 * - 分治思想，递归实现
 * - 将数组不断进行拆分成两个部分，直到只有一个元素，然后再两两合并，合并后要保证有序，
 * - 在合并的时候需要申请外部数组用于临时保存数据
 */
fun mergeSort(arr: IntArray, p: Int, q: Int) {
    if (p >= q) {
        return
    }

    val r = (p + q) / 2 // 先拆分
    mergeSort(arr, p, r)
    mergeSort(arr, r + 1, q)

    // 最后是合并
    merge(arr, p, r, q)
}

fun merge(arr: IntArray, p: Int, r: Int, q: Int) {

    // 将数组中这两个部分的元素合并，[p,r] [r+1,q]
    val temp = IntArray(q - p + 1)
    var h = 0

    var i = p
    var j = r + 1
    while (i <= r || j <= q) {
        val num1 = if (i <= r) arr[i] else Int.MAX_VALUE
        val num2 = if (j <= q) arr[j] else Int.MAX_VALUE

        if (num1 <= num2) {
            temp[h++] = num1
            i++
        } else {
            temp[h++] = num2
            j++
        }
    }

    // 将临时数组中的数据temp移动到arr中
    h = 0
    for (m in p..q) {
        arr[m] = temp[h++]
    }

}











