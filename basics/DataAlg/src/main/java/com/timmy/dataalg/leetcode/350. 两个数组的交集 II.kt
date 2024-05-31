package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，
 * 应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。

示例 1：
输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2,2]

示例 2:
输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[4,9]

提示：
1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000

进阶：
如果给定的数组已经排好序呢？你将如何优化你的算法？
如果 nums1 的大小比 nums2 小，哪种方法更优？
如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 */
fun main() {
    //    intersect(intArrayOf(1, 2, 2, 1), intArrayOf(2, 2))
    //    intersect(intArrayOf(4, 7, 9, 7, 6, 7), intArrayOf(5, 0, 0, 6, 1, 6, 2, 2, 4))

    var temp = 0

    if ((temp++) / 3 == 0) {
        println("aldfjlka")
    }

}

/**
 * 1、审题：
 *  - 输入两个数组，两个数组中有可能存在相同的元素，而且同一个数字在数组中可能出现多次，要求出在两个数组中都出现的元素
 *  2、解题
 *  - 先对两个数组进行排序，在找出长度更小的数组，进行遍历
 *  - 在短数组遍历的过程中，在长数组中进行查找，
 *  -- 遇到相同的数字，将该数字保存到集合中，并往后移动一位
 *  -- 遇到更大的数字，暂停
 *  -- 遇到更小的数字，往后移动，直到等于或大于的数字
 */
fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
    nums1.sort()
    nums2.sort()
    nums1.print()
    nums2.print()

    val n1 = nums1.size
    val n2 = nums2.size
    val resList = mutableListOf<Int>()
    var longP = 0
    if (n1 < n2) {
        for (i in 0 until n1) {
            val shortV = nums1[i]
            while (longP < n2 && shortV >= nums2[longP]) {
                if (shortV == nums2[longP]) { // 相等,后移,退出while循环
                    resList.add(shortV)
                    longP++
                    break
                } else { // shortV 大，后移
                    longP++
                }
            }
        }
    } else {
        for (i in 0 until n2) {
            val shortV = nums2[i]
            while (longP < n1 && shortV >= nums1[longP]) {
                if (shortV == nums1[longP]) { // 相等,后移
                    resList.add(shortV)
                    longP++
                    break
                } else { // shortV 大，后移
                    longP++
                }
            }
        }
    }
    resList.print()
    return resList.toIntArray()
}