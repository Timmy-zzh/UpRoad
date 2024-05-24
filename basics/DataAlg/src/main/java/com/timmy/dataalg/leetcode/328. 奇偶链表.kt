package com.timmy.dataalg.leetcode

import com.timmy.dataalg.bean.ListNode
import com.timmy.dataalg.print

/**
 * 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。

示例 1:
输入: head = [1,2,3,4,5]
输出: [1,3,5,2,4]

示例 2:
输入: head = [2,1,3,5,6,4,7]
输出: [2,3,6,7,1,5,4]

提示:
n ==  链表中的节点数
0 <= n <= 104
-106 <= Node.val <= 106
 */
fun main() {
    val head = ListNode(1)
    val node2 = ListNode(2)
    val node3 = ListNode(3)
    val node4 = ListNode(4)
    val node5 = ListNode(5)
    head.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node5

    head.print()
    val res = oddEvenList(head)
    res.print()
}

/**
 * 1、审题：
 * - 输入一个链表，要求将链表的奇偶索引位置的结点分别组合在一起，最后是奇数位的结点在前，偶数位的结点在后，这样返回
 * 2、解题：
 * - 双指针解法
 * - 不断遍历链表节点，每次都找出两个节点
 * - 接着两个链表节点，分别添加到两条链表中，奇数位链表/偶数位链表，最后是合并起来返回
 */
fun oddEvenList(head: ListNode?): ListNode? {
    if (head == null) {
        return null
    }
    var oddNodeEmpty = ListNode(-1) // 奇数
    var evenNodeEmpty = ListNode(-1) // 偶数
    var addNode = oddNodeEmpty
    var evenNode: ListNode? = evenNodeEmpty
    var node = head

    while (node != null) {  // node是奇数
        println("addNode:${node.`val`}")
        addNode.next = node
        addNode = node

        node = node.next

        println("evenNode:${node?.`val`}")
        if (node != null) {   // 偶数位结点
            evenNode?.next = node
            evenNode = node

            node = node.next
        }
    }
    if (addNode.next != null) {
        addNode.next = null
    }
    evenNode?.next = null
    addNode.next = evenNodeEmpty.next

    return oddNodeEmpty.next
}