package com.timmy.dataalg._12binarysearch

fun main() {
    val arr = intArrayOf(1, 3, 4, 5, 6, 8, 8, 8, 11, 18)
    val res: Int = find(arr, arr.size, 8)
    println("res:$res")
}

/**
 * 1、审题：输入一个有序数组和目标值，数组中有的元素存在重复多个，在数组中查找第一个等于给定值的元素位置，没有返回-1
 * 2、解题：
 * - 给定的是有序数组中查询，使用二分查找法
 * - while循环遍历条件；边界值更新，最后是返回值处理
 */
fun find(arr: IntArray, size: Int, target: Int): Int {
    var low = 0
    var high = size - 1
    var mid: Int
    while (low <= high) {
        mid = low + (high - low) / 2
        if (target < arr[mid]) {
            high = mid - 1
        } else if (arr[mid] < target) {
            low = mid + 1
        } else { // 相等
            if (mid == 0 || arr[mid - 1] < target) {
                return mid
            } else {
                high = mid - 1
            }
        }
    }
    return -1
}

