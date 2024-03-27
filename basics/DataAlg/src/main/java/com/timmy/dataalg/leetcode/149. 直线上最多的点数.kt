package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 *
示例 1：
输入：points = [[1,1],[2,2],[3,3]]
输出：3

示例 2：
输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
输出：4

提示：
1 <= points.length <= 300
points[i].length == 2
-104 <= xi, yi <= 104
points 中的所有点 互不相同
 */
fun main() {

//        val points = arrayOf(intArrayOf(1, 1), intArrayOf(2, 2), intArrayOf(3, 3))

//        val points = arrayOf(intArrayOf(0, 0), intArrayOf(1, -1), intArrayOf(1, 1))

    // [[5151,5150],[0,0],[5152,5151]]
//    val points = arrayOf(intArrayOf(5151, 5150), intArrayOf(0, 0), intArrayOf(5152, 5151))

        val points = arrayOf(
            intArrayOf(1, 1),
            intArrayOf(3, 2),
            intArrayOf(5, 3),
            intArrayOf(4, 1),
            intArrayOf(2, 3),
            intArrayOf(1, 4),
        )


    //    val res = maxPoints1(points)
    val res = maxPoints(points)
    println("res:$res")

}

/**
 * 1、审题：
 * - 输入一个由坐标位置，组成的数组，求在由两个坐标之间连成的线上最多的点数
 * 2、解题
 * 暴力解法：
 * - 将数组中的点两层遍历，找到两个点，就可以确定这条线，接着进行第三层遍历数组中其他的点，判断遍历的点是否在前面那条直线上
 * 公式法：
 * - 外面两层遍历少不了，先确定直线，接着求出这条直线的算术表达式，最后判断剩下的点有多少符合算术表达式的
 *
 * 3、总结
 * - 解题思路： 三层for循环，前两层for循环确定直线表达式，
 * - 第三层判断点是否符合表达式
 * - 求取斜率，除数为0的情况判断
 * - 分辨率问题处理
 * 4、精度问题：
 * - 将求斜率会出现的精度问题，转变为乘法，就可以避免了
 */
fun maxPoints(points: Array<IntArray>): Int {
    val n = points.size
    if (n <= 2) {
        return n
    }

    var res = 2
    for (i in 0 until n - 1) {
        for (j in i + 1 until n) {
            val point1 = points[i]
            val point2 = points[j]

            var itemC = 2 // 遍历其他点
            for (w in 0 until n) {
                if (w == i || w == j) {
                    continue
                }
                val point = points[w]
                val leftV = (point2[1] - point1[1]) * (point[0] - point1[0])
                val rightV = (point2[0] - point1[0]) * (point[1] - point1[1])
                if (leftV == rightV) {
                    itemC++
                    if (itemC > res) {
                        res = itemC
                    }
                }
            }
        }
    }
    return res
}

fun maxPoints1(points: Array<IntArray>): Int {
    val n = points.size
    if (n <= 2) {
        return n
    }

    var res = 2
    for (i in 0 until n - 1) {
        for (j in i + 1 until n) {
            val point1 = points[i]
            val point2 = points[j]

            /**
             * 求直线表达式
             * - 先求斜率： k = （y2-y1）/(x2-x1)
             * - 直线上其他点（x，y）也符合，该等式， k = (y-y1)/(x-x1)
             * -> y = k*(x-x1) + y1
             *
             * 因为求出的斜率k存在精度问题，可以将除法转变为乘法进行计算
             * （y2-y1）     (y-y1)
             * --------- = ---------
             * (x2-x1)      (x-x1)
             *-->得出等式 （y2-y1）* (x-x1) = (x2-x1) * (y-y1)
             */
            var tag = false //            var k: Double? = null
            if (point2[0] - point1[0] == 0) {
                tag = true
            }

            //            else {
            //                k = (point2[1] - point1[1]) * 1.0 / (point2[0] - point1[0])
            //            }
            point1.print()
            point2.print() //            println("for for  k=$k")
            var itemC = 2 // 遍历其他点
            for (w in 0 until n) {
                println("for for for  w=$w,i=$i,j=$j")
                if (w == i || w == j) {
                    continue
                }
                val point = points[w]
                point.print()
                if (tag) {
                    println("point[0]=${point[0]}, point[1]=${point1[0]}")
                    if (point[0] == point1[0]) {
                        itemC++
                        if (itemC > res) {
                            res = itemC
                        }
                    }
                } else {

                    //                    val y = k!! * (point[0] - point1[0]) + point1[1]
                    //                    （y2-y1）* (x-x1) = (x2-x1) * (y-y1)
                    // 等式左边结果
                    val leftV = (point2[1] - point1[1]) * (point[0] - point1[0])
                    val rightV = (point2[0] - point1[0]) * (point[1] - point1[1])

                    println("leftV=$leftV,rightV=$rightV, point[1]=${point[1]}")
                    if (leftV == rightV) {
                        itemC++
                        if (itemC > res) {
                            res = itemC
                        }
                    }
                }
                println("res:$res")
            }
        }
    }
    return res
}