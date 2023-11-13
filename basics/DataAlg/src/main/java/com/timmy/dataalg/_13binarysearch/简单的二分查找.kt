package com.timmy.dataalg._13binarysearch

fun main() {
    val arr = intArrayOf(2, 5, 6, 7, 11, 18)
    val res = simpleBinSearch(arr, arr.size, 5)
    println("res=$res")
}

/**
 * 1、审题：输入一个有序数组，和目标值，判断数组中是否存在与目标值相等的元素，存在返回下标，否则返回-1
 * 2、解题：二分查找实现
 * - 有序数组
 */
fun simpleBinSearch(arr: IntArray, n: Int, value: Int): Int {
    var low = 0
    var high = n - 1
    var mid: Int
    while (low <= high) { // 除于2，也可以往左偏移1位
        mid = low + (high - low) / 2

        if (arr[mid] == value) {
            return mid
        } else if (arr[mid] > value) {
            high = mid - 1
        } else {
            low = mid + 1
        }
    }
    return -1
}