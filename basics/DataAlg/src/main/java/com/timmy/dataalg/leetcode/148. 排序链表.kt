package com.timmy.dataalg.leetcode

import com.timmy.dataalg.bean.ListNode
import com.timmy.dataalg.print

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
示例 1：
输入：head = [4,2,1,3]
输出：[1,2,3,4]

示例 2：
输入：head = [-1,5,3,4,0]
输出：[-1,0,3,4,5]

示例 3：
输入：head = []
输出：[]

提示：
链表中节点的数目在范围 [0, 5 * 104] 内
-105 <= Node.val <= 105

进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 */
fun main() {
    val node4 = ListNode(4)
    val node2 = ListNode(2)
    val node1 = ListNode(1)
    val node3 = ListNode(3)

    node4.next = node2
    node2.next = node1
    node1.next = node3
    node4.print()
    val res = sortList(node4)
    res.print()
}

/**
 * 思路2：归并排序
 * - 先拆分，后合并
 * -- 如何做拆分呢？
 * -- 数组是通过二分法，将数组从中间位置mid进行拆分为两个区域，直到两个区域的元素为一，然后进行合并成一个有序的短区域链表，接着递归与其他有序链表进行合并
 * - 链表也是一样的思想，只不过通过遍历找到中间节点，
 * -- 两个链表进行合并
 */
fun sortList(head: ListNode?): ListNode? {
    if (head == null) {
        return null
    }
    return sortListBack(head, null)
}

/**
 * 先拆分，再合并
 * - 拆分找中间节点位置
 */
fun sortListBack(head: ListNode?, tail: ListNode?): ListNode? {
    println("sortListBack head:${head?.`val`}, tail:${tail?.`val`}")
    head.print()
    tail.print()

    if (head == null) {
        return null
    }
    if (head == tail) {
        return head
    }
    /**
     * 这快逻辑怎么处理？
     * 目标： 往下排序了
     */
    if (head.next == tail) {
        head.next = null  // 断开
        return mergeCall(head, tail)
    }

    // 找中间节点
    var fast: ListNode? = head
    var slow: ListNode? = head
    while (fast != tail) {
        fast = fast?.next
        slow = slow?.next
        if (fast != tail) {
            fast = fast?.next
        }
    }

    // 从中间截断
    val mid = slow
    println("sortListBack mid:${mid?.`val`}")

    // 继续递归拆分链表区间，最后进行合并成有序的链表
    val sortHead2 = mid?.next
    mid?.next = null

    val sortNode1 = sortListBack(head, mid)
    val sortNode2 = sortListBack(sortHead2, tail)

    return mergeCall(sortNode1, sortNode2)
}

/**
 * 合并两个链表
 * - 遍历两个链表，找到val值更大的结点，并添加到
 */
fun mergeCall(sortNode1: ListNode?, sortNode2: ListNode?): ListNode? {
    println("mergeCall sortNode1:${sortNode1?.`val`},sortNode2:${sortNode2?.`val`}")
    sortNode1.print()
    sortNode2.print()

    // 虚拟头结点
    val emptyNode = ListNode(-1)
    var tempNode: ListNode? = emptyNode
    var node1 = sortNode1
    var node2 = sortNode2
    while (node1 != null || node2 != null) {
        var num1 = Int.MAX_VALUE
        if (node1 != null) {
            num1 = node1.`val`
        }

        var num2 = Int.MAX_VALUE
        if (node2 != null) {
            num2 = node2.`val`
        }

        if (num1 < num2) {
            tempNode?.next = node1
            node1 = node1?.next
        } else {
            tempNode?.next = node2
            node2 = node2?.next
        }
        tempNode = tempNode?.next
    }
    println("mergeCall res emptyNode:${emptyNode.next?.`val`}")
    emptyNode.next.print()

    return emptyNode.next
}

/**
 * 1、审题： 输入一个链表，需要对链表中的元素进行升序排序，并最终返回链表头结点
 * 2、解题：
 * - 冒泡排序法，
 * - 从头结点开始，与next节点的value值进行比较，判断谁大，
 * -- 如果next指针的value值小，则相互交换value值，但是节点指针还是原来的，知识数据域交换了
 * -- 继续遍历节点不断往后移动到next节点并赋值，直到每次遍历都是最大值移动到末尾
 * 3、裁剪
 * - 判断外层循环，中此次是否发生元素交换，有交换则说明还有元素不是升序的，否则说明都已经排序好了，直接返回
 * - 但数据量大时，还是会超过时间限制
 */
fun sortList2(head: ListNode?): ListNode? {
    if (head == null) {
        return null
    } // 计算链表节点的个数
    var size = 0
    var node = head
    while (node != null) {
        size++
        node = node.next
    }

    for (i in 0 until size) { // 重置转移节点
        node = head
        var isSwip = false  // 是否发生交换
        for (j in 0 until size - i - 1) {
            val nextVal = node!!.next!!.`val`
            println("--- i:$i,j:$j")
            println("node.`val`:${node.`val`},nextVal:$nextVal")

            if (node.`val` > nextVal) { // 交换
                val temp = node.`val`
                node.`val` = nextVal
                node.next!!.`val` = temp

                isSwip = true
            }
            node = node.next
            head.print()
        }
        if (!isSwip) {
            break
        }
    }
    return head
}