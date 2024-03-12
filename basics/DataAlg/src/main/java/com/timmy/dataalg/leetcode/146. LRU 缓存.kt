package com.timmy.dataalg.leetcode

/**
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
实现 LRUCache 类：
LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；
如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。

示例：
输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4

提示：
1 <= capacity <= 3000
0 <= key <= 10000
0 <= value <= 105
最多调用 2 * 105 次 get 和 put
 */
fun main() {
    val lRUCache = LRUCache(2)
    lRUCache.put(1, 1) // 缓存是 {1=1}
    println("put(1, 1) ")
    lRUCache.print()

    lRUCache.put(2, 2) // 缓存是 {1=1, 2=2}
    println("put(2, 2) ")
    lRUCache.print()

    lRUCache.get(1) // 返回 1
    println(".get(1)")
    lRUCache.print()

    lRUCache.put(3, 3) // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
    println("put(3, 3)")
    lRUCache.print()

    val get = lRUCache.get(2) // 返回 -1 (未找到)
    println("get(2)")
    println("get:$get")
    lRUCache.print()

    lRUCache.put(4, 4) // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
    println("put(4, 4)")
    lRUCache.print()

    lRUCache.get(1) // 返回 -1 (未找到)
    println("get(1)")
    lRUCache.print()

    lRUCache.get(3) // 返回 3
    println("get(3)")
    lRUCache.print()

    lRUCache.get(4) // 返回 4
    println("get(4)")
    lRUCache.print()

}

/**
 * 1、审题：
 * - 完成LRUCache，最近最少使用的缓存列表队列实现
 * - 他有get和put方法，put的元素采用链表结构保存，当数据容量超过capacity时，需要删除队列
 * - 每次操作时的数据，都需要提到链表队头位置
 * 2、解题：
 * - put数据，采用hashmap方式保存数据，value值作为ListNode节点，进行连接
 * -- 并判断当前size大小，如果不用hashMap，就自己维护一个Node链表结构
 * 3、其他解法
 * - hash表+双向链表
 */
class LRUCache(private val capacity: Int) {

    var size = 0
    var emptyNode = LruNode(-1, -1)

    /**
     * 遍历链表节点，判断key值是否相同
     * - 相同，还需要将该结点提到前面来
     */
    fun get(key: Int): Int {
        if (emptyNode.next == null) {
            return -1
        }
        var node = emptyNode.next
        var pre: LruNode? = emptyNode     // 前继节点
        while (node != null) {
            if (node.key == key) { // 先删除，后插入
                val resValue = node.value
                pre?.next = node.next

                node.next = emptyNode.next
                emptyNode.next = node

                return resValue
            }
            node = node.next
            pre = pre?.next
        }
        return -1
    }

    /**
     * - 先判断是否存在相同key值，存在则更新
     * - 不存在，说明需要新插入，此时需要判断size大小是否超过阈值
     */
    fun put(key: Int, value: Int) {
        if (emptyNode.next == null) { // 插入一个新节点
            emptyNode.next = LruNode(key, value)
            size++
            return
        }
        var node = emptyNode.next
        var pre: LruNode? = emptyNode     // 前继节点
        while (node != null) {      // 遇见相等的了，更新,元素位置移到前面去
            if (node.key == key) {
                node.value = value

                pre?.next = node.next

                node.next = emptyNode.next
                emptyNode.next = node

                return
            }
            node = node.next
            pre = pre?.next
        }

        if (size == capacity) {     // 则需要减去尾部节点，再插入
            var i = 0
            var node = emptyNode.next
            var pre: LruNode? = emptyNode     // 前继节点
            while (i < capacity - 1 && node != null) {
                node = node.next
                pre = pre?.next
                i++
            }
            pre!!.next = null
            size--
        }

        // insert，插入到头部
        val newNode = LruNode(key, value)
        newNode.next = emptyNode.next
        emptyNode.next = newNode
        size++
    }

    fun print() {
        var node = emptyNode.next
        println("---- print size:$size")
        while (node != null) {      // 遇见相等的了，更新,元素位置移到前面去
            print("${node.value},")
            node = node.next
        }
        println()
    }

}

data class LruNode(val key: Int, var value: Int) {
    var next: LruNode? = null
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */