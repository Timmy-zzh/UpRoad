package com.timmy.dataalg._02linked

import com.timmy.dataalg.bean.ListNode

/**
 * 给你一个链表的头节点 head ，判断链表中是否有环。
如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
如果链表中存在环 ，则返回 true 。 否则，返回 false 。

示例 1：
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。

示例 2：
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。

示例 3：
输入：head = [1], pos = -1
输出：false
解释：链表中没有环。
 */
fun main() {
    val head1 = ListNode(3)
    val head2 = ListNode(2)
    val head3 = ListNode(0)
    val head4 = ListNode(-4)
    head1.next = head2
    head2.next = head3
    head3.next = head4
    head4.next = head2
    val hasCycle = hasCycle(head1)
    println("res:$hasCycle")

}


/**
 * 1、审题：输入一个链表，判断链表中是否存在环，
 * 2、解题：首先，单链表只有一个后继结点，但是有可能有多个结点指向当前结点，当一个结点指向了前面已经存在的结点时，
 * 就是产生了环
 * - 双指针法：使用两个快慢指针，如果存在环，则这两个指针一定会最终相遇
 */
fun hasCycle(head: ListNode?): Boolean {
    var fast = head
    var slow = head

    while (fast != null && fast.next != null) {
        slow = slow?.next
        fast = fast.next?.next

        if (slow == fast) {
            return true
        }
    }
    return false
}





















