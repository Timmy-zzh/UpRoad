package com.timmy.dataalg.leetcode

/**
 * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。

例如 arr = [2,3,4] 的中位数是 3 。
例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
实现 MedianFinder 类:

MedianFinder() 初始化 MedianFinder 对象。
void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。

示例 1：
输入
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
输出
[null, null, null, 1.5, null, 2.0]

解释
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0

提示:
-105 <= num <= 105
在调用 findMedian 之前，数据结构中至少有一个元素
最多 5 * 104 次调用 addNum 和 findMedian
 */
fun main() {
    val medianFinder = MedianFinder();
    medianFinder.addNum(1);    // arr = [1]
    medianFinder.addNum(2);    // arr = [1, 2]
    var res = medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
    println("res:$res")
    medianFinder.addNum(3);    // arr[1, 2, 3]
    res = medianFinder.findMedian(); // return 2.0
    println("res2 :$res")
}

/**
 * 1、审题：
 * - 求一个类的中位数，先不断往这个类中add数字，add后的数组在类中以升序方式保存，
 * - 取中位数的时候，判断类中集合的个数，偶数的话需要将两个中间数字求和取平均值返回
 * 2、解题：
 * - 使用 multualList集合保存添加的数字，并且保持升序，随后取出中位数
 * - 二分法查找与保存，
 * 3、总结：
 * - 二分查找法判断条件
 */
class MedianFinder() {

    val list = mutableListOf<Int>()

    fun addNum(num: Int) {
        if (list.isEmpty()) {
            list.add(num)
        } else { // 二分法查找，先找到位置，再插入
            val position = findPosition(list, num)
            println("num:$num,pos:$position")
            list.add(position, num)
        }
    }

    // 找到 》=他的位置
    private fun findPosition(list: MutableList<Int>, num: Int): Int {
        var left = 0
        var right = list.size - 1
        while (left <= right) {
            val mid = left + (right - left) / 2
            println("list:$list")
            println("num:$num,left:$left,right:$right,mid:$mid")
            if (list[mid] == num) {
                return mid
            } else if (list[mid] > num) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return left
    }

    fun findMedian(): Double {
        if (list.isEmpty()) {
            return 0.0
        } else if (list.size % 2 == 1) {    // 奇数
            return list[list.size / 2].toDouble()
        } else { // 偶数
            val size = list.size
            return (list[size / 2] + list[size / 2 - 1]) / 2.toDouble()
        }
    }

}