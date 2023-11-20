package com.timmy.dataalg.leetcode

import com.timmy.dataalg.bean.ListNode
import com.timmy.dataalg.print

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
请你将两个数相加，并以相同形式返回一个表示和的链表。
你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例 1：
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.

示例 2：
输入：l1 = [0], l2 = [0]
输出：[0]

示例 3：
输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]
 */
fun main() {

    //    val l1 = ListNode(2)
    //    val l12 = ListNode(4)
    //    val l13 = ListNode(3)

    val l1 = ListNode(9)
    val l12 = ListNode(9)
    val l13 = ListNode(9)
    val l14 = ListNode(9)
    val l15 = ListNode(9)
    l1.next = l12
    l12.next = l13
    l13.next = l14
    l14.next = l15

    //    val l2 = ListNode(5)
    //    val l22 = ListNode(6)
    //    val l23 = ListNode(4)
    //    l2.next = l22
    //    l22.next = l23

    val l2 = ListNode(9)
    val l22 = ListNode(9)
    val l23 = ListNode(9)
    l2.next = l22
    l22.next = l23

    val res = addTwoNumbers(l1, l2)
    res.print()

}

/**
 * 1、审题：输入两个链表，链表表示一个数值，其中每个节点表示一个数字，从个位数开始往后连接
 * - 现在需要将两个链表中标识的数值进行相加，也是使用链表进行标识，并返回
 * 2、解题：
 * - 遍历两个链表，获取数字，相加，获取到个位数和偏移量，不断遍历直到两个链表都遍历完
 * - 最后还需要判断偏移值是否大于0，大于0需要再增加一个节点
 */
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    val head = ListNode(-1)
    var node = head
    var node1 = l1
    var node2 = l2
    var offset = 0

    while (node1 != null || node2 != null) {
        val num1 = node1?.`val` ?: 0
        val num2 = node2?.`val` ?: 0

        val sum = num1 + num2 + offset
        val num = sum % 10
        offset = sum / 10

        val newNode = ListNode(num)
        node.next = newNode

        node = newNode

        node1 = node1?.next
        node2 = node2?.next
    }
    if (offset > 0) {
        val newNode = ListNode(offset)
        node.next = newNode
    }

    return head.next
}