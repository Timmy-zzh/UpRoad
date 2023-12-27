package com.timmy.dataalg.leetcode

import com.timmy.dataalg.bean.ListNode
import com.timmy.dataalg.print

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

示例 1：
输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]

示例 2：
输入：head = [1], n = 1
输出：[]

示例 3：
输入：head = [1,2], n = 1
输出：[1]
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
    val res = removeNthFromEnd(head, 5)
    res.print()
}

/**
 * 1、审题：输入一个链表，和需要删除的倒数第几个位置，
 * - 现在需要找到这个倒数的链表节点，并进行删除
 * 2、解题：
 * - 要删除一个链表的结点，首先要在链表中找到这个节点，并持有要删除结点的前缀节点
 * - 设置虚拟头结点，后面好返回
 * - 找到要删除的结点，采用双指针方法，遍历结点，让快指针先走n步骤，接着满指针才进行遍历
 */
fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    val empthNode: ListNode? = ListNode(-1)
    empthNode!!.next = head
    var i = 0
    var fast = empthNode
    var slow = empthNode // slow就是要删除的结点,要是删除结点的前一个结点

    while (fast != null) {
        fast = fast.next
        if (i > n) {
            slow = slow?.next
        }
        i++
    }
    slow?.next = slow?.next?.next
    return empthNode.next
}