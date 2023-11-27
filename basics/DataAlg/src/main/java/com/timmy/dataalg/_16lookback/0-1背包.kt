package com.timmy.dataalg._16lookback

import com.timmy.dataalg.print

fun main() {

    //    val size = 10
    //    val items = IntArray(size){10}

    val items = intArrayOf(5, 9, 14, 24, 18, 22, 32, 17, 6, 21)
    backpack(0, 0, items, items.size, 100)
    println("maxW:$maxW")
}

/**
 * 题目要求：
 * - 有一个背包，最终重量为100kg，和一堆物品items，每个物品的重量对应数组元素大小，
 * - 现在要往背包里装东西，问如何装？可以使得背包装的东西最多
 * 审题：
 * - 物品要么装到背包中，要么不装进背包，不可拆分，这是0/1背包问题
 * 解题：
 * - 从第1个物品开始遍历，遍历结果有两种选择，要么装进背包，要么不装进
 * - 不断调用递归方法，参数不断变化
 * - 注意剪枝处理，减少不必要的计算
 */
var maxW = Int.MIN_VALUE
var list = mutableListOf<Int>()

/**
 * i:标识当前遍历到，需要处理的是第几个物品
 * cw：标识背包中，当前已经装进去的物品重量
 *
 * -- 后面三个参数不变
 * items：物品重量数组
 * n：物品数量
 * pw：背包总的重量
 */
fun backpack(i: Int, cw: Int, items: IntArray, n: Int, pw: Int) {
    if (cw == pw || i == n) { // 背包装满了，或者遍历到最后一个物品，结束
        if (cw > maxW) {
            maxW = cw
        }
        if (i == n) {
            // 遍历到最后一个物品了
            println("i==n, cw==$cw")
            list.print()
        }
        return
    }

    // 第i个物品不放入
    backpack(i + 1, cw, items, n, pw)

    // 放入，先判断背包装入这个物品后，是否超过总重量
    if (cw + items[i] <= pw) {
        list.add(items[i])
        backpack(i + 1, cw + items[i], items, n, pw)
        list.remove(items[i])
    }
}