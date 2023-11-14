package com.timmy.dataalg._13binarysearch

fun main() {
    val arr = intArrayOf(2, 4, 4, 6, 7, 8, 8, 8, 11, 18)
    val res: Int = find3(arr, arr.size, 5)
    println("res:$res")
}

/**
 * 1、审题：查找第一个大于等于给定值的元素
 * 2、解题：二分查找法
 * - 遍历到小于目标值的元素时，判断mid+1是否大于
 * - 遍历到等于目标值的时候，判断mid-1是否小于
 * - 遍历到大于目标值的时候，high =mid-1
 */
fun find3(arr: IntArray, size: Int, target: Int): Int {
    var low = 0
    var high = size - 1
    var mid: Int
    while (low <= high) {
        mid = low + (high - low) / 2
        if (target <= arr[mid]) {
            if (mid == 0 || arr[mid - 1] < target) {
                return mid
            } else {
                high = mid - 1
            }
        } else {
            low = mid + 1
        }
    }

    return -1
}

