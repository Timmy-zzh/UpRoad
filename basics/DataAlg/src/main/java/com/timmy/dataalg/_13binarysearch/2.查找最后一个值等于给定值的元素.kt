package com.timmy.dataalg._13binarysearch

fun main() {
    val arr = intArrayOf(1, 3, 4, 5, 6, 8, 8, 8, 11, 18)
    val res: Int = find2(arr, arr.size, 8)
    println("res:$res")
}

/**
 * 查找最后一个等于给定值的元素位置
 * 1、审题：
 * 2、解题：二分查找方，
 */
fun find2(arr: IntArray, size: Int, target: Int): Int {
    var low = 0
    var high = size - 1
    var mid: Int
    while (low <= high) {
        mid = low + (high - low) / 2
        if (target < arr[mid]) {
            high = mid - 1
        } else if (arr[mid] < target) {
            low = mid + 1
        } else {
            if (mid == size - 1 || target < arr[mid + 1]) {
                return mid
            } else {
                low = mid + 1
            }
        }
    }
    return -1
}
