package com.timmy.dataalg._17dynamic

import com.timmy.dataalg.print

fun main() {
//    call01BackpackDynamic()
    call01BackpackDynamicV2()
}

/**
 * 0-1背包
 * 动态规划解法：
 * - 使用一个boolean类型的二维数组，标识每个物品在决策后的状态，
 * - （二维数组，行为5标识有五个物品，分为五个阶段，列为背包的容量大小，从0到9，boolean类型结果值标识该物品，在当前重量情况下已经做出了决策）
 * -- 一维数组用于保存5个物品的决策阶段，
 * -- 二维元素，标识该物品在对应重量情况下的决策结果状态值
 * - 首先第一个物品，有两种决策结果，要么不装入背包，要么装入，这就有初始值了
 * - 有了第一个物品的决策值，后面从第二个物品开始，需要根据第一个物品的决策结果值，来确定第二个物品的决策结果
 * - 最后遍历二维数组的最后一列，找到最大值得决策结果
 */
fun call01BackpackDynamic() {
    val weights = intArrayOf(2, 2, 4, 6, 3)
    val n = 5
    val w = 9
    var maxW = Int.MIN_VALUE
    var status = Array<BooleanArray>(5) {
        BooleanArray(10) { false }
    }
    status.print()

    // 第一个物品处理
    status[0][0] = true
    if (weights[0] <= w) {
        status[0][weights[0]] = true
    }

    for (i in 1 until 5) {
        for (j in 0..w) { // 第i个物品不放入背包
            if (status[i - 1][j]) {
                status[i][j] = true
            }
        }

        //  放入背包
        for (j in 0..w) { // 第i个物品不放入背包
            if (status[i - 1][j] && j + weights[i] <= w) {
                status[i][j + weights[i]] = true
            }
        }
    }
    status.print()

    for (ww in 9 downTo 0){
        if (status[4][ww]){
            println("MaxWeight:$ww")
            break
        }
    }
}

/**
 * 使用一维数组保存状态值
 */
fun call01BackpackDynamicV2() {
    val weights = intArrayOf(2, 2, 4, 6, 3)
    val n = 5
    val w = 9
    var maxW = Int.MIN_VALUE
    var status =BooleanArray(10) { false }

    status.print()

    // 第一个物品处理
    status[0] = true
    if (weights[0] <= w) {
        status[weights[0]] = true
    }

    for (i in 1 until 5) {
        for (j in 0..w) { // 第i个物品不放入背包
            if (status[j]) {
                status[j] = true
            }
        }

        //  放入背包
        for (j in 0..w) { // 第i个物品不放入背包
            if (status[j] && j + weights[i] <= w) {
                status[j + weights[i]] = true
            }
        }
    }
    status.print()

    for (ww in 9 downTo 0){
        if (status[ww]){
            println("MaxWeight:$ww")
            break
        }
    }
}