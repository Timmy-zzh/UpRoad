package com.timmy.dataalg.leetcode

import kotlin.math.min

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
算法的时间复杂度应该为 O(log (m+n)) 。

示例 1：
输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2

示例 2：
输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 */
fun main() {
//    val res = findMedianSortedArrays(intArrayOf(1, 3), intArrayOf(2))
    val res = findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(3,4))

    println("res:$res")
}


/**
 * 1、审题：
 * - 输入两个正序数组，求这两个数组中的中位数
 * 2、解题：
 * -2.1 二分法搜索，使用二分查找法进行区间排除
 * - 通过输入的两个数组，知道两个数组的总长度k，中位数，也就是总的一半的位置（k/2），我们要找的就是k/2的位置的值，注意k区分奇偶情况
 * -- 如何k为奇数，则所求为 k/2的位置； k为偶数，则所有为（k/2-1 , k/2）两位置的平均值
 * -2.2. 从两个数组都取一半的数据进行比较，也就是数组A取 mid/2, 数组B也取 mid/2的位置，进行比较，有三种情况
 * --A的mid/2位置，小于数组B的mid/2位置大小，则说明数组A中[0,mid/2]的区间的值都不是我们要找的位置，A数组需要从mid/2 +1 位置继续查询
 * --反过来，A数组的mid/2位置值，大于数组B的mid/2的位置值，说明数组B的[0,mid/2]返回数据，都需要排除
 * --相等的话，按照第一种情况处理
 * - 2.3
 * -- 排除后，剩下要比较的返回就是mid= mid - mid/2, 继续以这个值为mid搜索范围进行判断
 * -- 假设上面是情况1，则后面的情况 A数组需要从mid/2+1的位置开始加上
 * -- 最后mid值为1就是我们所求的
 * 3、总结：
 * - 调用findMeidan方法传入的参数，为什么要多加1,？
 * - 二分查找算法，是不断减少搜索范围，直到搜索范围为1
 * -- 这道题可以转化为求两个数组中的第k小的数
 */
fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    val len1 = nums1.size
    val len2 = nums2.size
    val totalLen = len1 + len2

    val mid = totalLen / 2
    if (totalLen % 2 != 0) {   //奇数
        return findMeida(nums1, nums2, mid + 1)
    } else {
        return (findMeida(nums1, nums2, mid) + findMeida(nums1, nums2, mid + 1)) / 2
    }
}

/**
 * k标识要查找的位置
 */
private fun findMeida(nums1: IntArray, nums2: IntArray, k: Int): Double {
    val length1 = nums1.size
    val length2 = nums2.size
    println("findMeida length1:$length1, length2:$length2 ,k:$k")

    // 每个数组开始的位置,这个是会变化的
    var startIndex1 = 0
    var startIndex2 = 0

    // 不断变化，最后有可能回到数组末尾位置

    // 查找的区间值
    var rangeK = k

    while (true) {
        println("while startIndex1=$startIndex1, startIndex2=$startIndex2,rangeK=$rangeK ")
        if (startIndex1 >= length1) { // 数组1到头了，说明查找的值在数组2中
            return nums2[startIndex2 + rangeK - 1].toDouble()
        }

        if (startIndex2 >= length2) {
            return nums1[startIndex1 + rangeK - 1].toDouble()
        }

        if (rangeK == 1) { //只剩下最后一个区间位置了，取更小值
            if (nums1[startIndex1] < nums2[startIndex2]) {
                return nums1[startIndex1].toDouble()
            } else {
                return nums2[startIndex2].toDouble()
            }
        }

        // 正常情况
        val half = rangeK / 2
        val newIndex1 = min(length1, startIndex1 + half) - 1
        val newIndex2 = min(length2, startIndex2 + half) - 1

        if (nums1[newIndex1] <= nums2[newIndex2]) { // 数组1的前半部分返回值更小[0,midK]，需要剔除
            rangeK -= (newIndex1 - startIndex1 + 1)
            startIndex1 = newIndex1 + 1
        } else {
            rangeK -= (newIndex2 - startIndex2 + 1)
            startIndex2 = newIndex2 + 1
        }
    }
}