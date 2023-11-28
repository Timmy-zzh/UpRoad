package com.timmy.dataalg._17dynamic

fun main() {
    val weights = intArrayOf(2, 2, 4, 6, 3)
    val n = 5
    val w = 9
//    call01Backpack(0, 0, weights, n, w)
    call01BackpackV2(0, 0, weights, n, w)
    println("maxW:$maxW")
}

/**
 * 0-1背包问题
 * 1、审题：给出一堆物品，每个物品具有对应的重量，和一个背包，背包最大容量为w=9，
 * - 现在要将这一堆物品装入背包中，求转入物品的最大重量
 * 2、解题
 * - 回溯算法，使用递归代码实现
 * - 从第0个物品开始决策，决策结果有两种，一个是放入，一个是不放入背包
 * - 不放入背包，则背包中的重量不增加
 * - 放入，则背包重量在原有基础上增加，在放入前要判断背包的重量不能超过最大容量w
 * - 递归结束条件：背包装满了，或者遍历到最后一个物品
 */
var maxW = Int.MIN_VALUE

/**
 * i:当前遍历到的物品是第几个
 * wb：当前背包的重量
 * weights:所有物品； n：物品个数； w:背包最大容量
 */
fun call01Backpack(i: Int, wb: Int, weights: IntArray, n: Int, w: Int) {
    if (wb == w || i == n) {
        if (wb > maxW) {
            maxW = wb
        }
        return
    }

    // 不放入背包
    call01Backpack(i + 1, wb, weights, n, w)

    // 第i个物品，放入背包中，背包重量增加
    if (wb + weights[i] <= w) {
        call01Backpack(i + 1, wb + weights[i], weights, n, w)
    }
}

/**
 * 还是使用回溯算法思想
 * - 但回溯算法存在很多重复计算，可通过备忘录保存已经计算过的数据
 */
var mem = Array<BooleanArray>(5) {
    BooleanArray(10) { false }
}

fun call01BackpackV2(i: Int, wb: Int, weights: IntArray, n: Int, w: Int) {
    if (wb == w || i == n) {
        if (wb > maxW) {
            maxW = wb
        }
        return
    }

    if (mem[i][wb]) {
        return
    }
    mem[i][wb] = true

    // 不放入背包
    call01Backpack(i + 1, wb, weights, n, w)

    // 第i个物品，放入背包中，背包重量增加
    if (wb + weights[i] <= w) {
        call01Backpack(i + 1, wb + weights[i], weights, n, w)
    }
}
