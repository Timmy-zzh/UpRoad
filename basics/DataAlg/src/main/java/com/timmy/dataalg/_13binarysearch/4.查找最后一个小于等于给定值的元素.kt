package com.timmy.dataalg._13binarysearch

fun main() {
    val arr = intArrayOf(2, 4, 4, 6, 7, 8, 8, 8, 11, 18)
    val res: Int = find4(arr, arr.size, 19)
    println("res:$res")
}

/**
 * 1、审题：查找最后一个小于等于给定值的元素为止
 * 2、解题:二分查找
 * - 查询位置小于目标值，判断mid+1是否大于目标值
 * - 查询值等于目标值，一样判断
 * - 查询值大于目标值，high = mid-1
 */


fun find4(arr: IntArray, size: Int, target: Int): Int {
    var low = 0
    var high = size - 1
    var mid: Int
    while (low <= high) {
        mid = low + (high - low) / 2
        if (arr[mid] <= target) {
            if (mid == size - 1 || arr[mid + 1] > target) {
                return mid
            } else {
                low = mid + 1
            }
        } else {
            high = mid - 1
        }
    }
    return -1
}

















