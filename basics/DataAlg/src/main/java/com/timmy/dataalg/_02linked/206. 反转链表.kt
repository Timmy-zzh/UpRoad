package com.timmy.dataalg._02linked

import com.example.leetcode.bean.ListNode
import com.timmy.dataalg.print

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
示例 1：
输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]

示例 2：
输入：head = [1,2]
输出：[2,1]
示例 3：

输入：head = []
输出：[]
 */
fun main() {
    val head1 = ListNode(1)
    val head2 = ListNode(2)
    val head3 = ListNode(3)
    val head4 = ListNode(4)
    val head5 = ListNode(5)
    head1.next = head2
    head2.next = head3
    head3.next = head4
    head4.next = head5
    head1.print()
    val reverseList = reverseList(head1)
    reverseList.print()
}

/**
 * 单链表反转：
 * 1、审题：输入一个链表，需要将该链表的所有结点进行翻转，原来的尾节点变成头结点，头结点变成尾节点
 * 2、解题：
 * - 遍历原始链表肯定的，遍历到一个结点就需要将该结点添加到新链表的头结点去，原先的链表遍历到的标志位不能丢失
 * -- 所以需要有一个结点，标识原始链表已遍历到的结点
 * -- 新链表需要接收遍历到的结点，插入到新链表的头部位置
 * 3、总结：
 * - 原始链表遍历
 * - 保存原始链表遍历到的结点位置
 * - 将遍历到的结点，插入到新链表中
 * - 可以不使用虚拟头结点
 */
fun reverseList(head: ListNode?): ListNode? {
    var curr = head
    var prev: ListNode? = null

    while (curr != null) {
        // 保存原始链表遍历到的位置，并移动到下一个节点
        val node = curr
        curr = curr.next

        // 当前遍历到的结点插入到新链表中
        node.next = prev
        prev = node
    }
    return prev
}

fun reverseList1(head: ListNode?): ListNode? {
    var prev = head
    var emptyHead = ListNode(-1)
    var tempNode: ListNode? = null
    while (prev != null) {
        tempNode = prev
        prev = prev.next

        tempNode.next = emptyHead.next
        emptyHead.next = tempNode
    }
    return emptyHead.next
}