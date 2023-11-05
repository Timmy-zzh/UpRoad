package com.timmy.dataalg._04queue

/**
 * 设计实现双端队列。

实现 MyCircularDeque 类:

MyCircularDeque(int k) ：构造函数,双端队列最大为 k 。
boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true ，否则返回 false 。
boolean insertLast() ：将一个元素添加到双端队列尾部。如果操作成功返回 true ，否则返回 false 。
boolean deleteFront() ：从双端队列头部删除一个元素。 如果操作成功返回 true ，否则返回 false 。
boolean deleteLast() ：从双端队列尾部删除一个元素。如果操作成功返回 true ，否则返回 false 。
int getFront() )：从双端队列头部获得一个元素。如果双端队列为空，返回 -1 。
int getRear() ：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1 。
boolean isEmpty() ：若双端队列为空，则返回 true ，否则返回 false  。
boolean isFull() ：若双端队列满了，则返回 true ，否则返回 false 。


示例 1：

输入
["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
[[3], [1], [2], [3], [4], [], [], [], [4], []]
输出
[null, true, true, true, false, 2, true, true, true, 4]

解释
MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
circularDeque.insertLast(1);			        // 返回 true
circularDeque.insertLast(2);			        // 返回 true
circularDeque.insertFront(3);			        // 返回 true
circularDeque.insertFront(4);			        // 已经满了，返回 false
circularDeque.getRear();  				// 返回 2
circularDeque.isFull();				        // 返回 true
circularDeque.deleteLast();			        // 返回 true
circularDeque.insertFront(4);			        // 返回 true
circularDeque.getFront();				// 返回 4


 */
fun main() {
    val circularDeque = MyCircularDeque(3); // 设置容量大小为3
    circularDeque.print()

    var insertRes = circularDeque.insertLast(1)                    // 返回 true
    println("insertLast(1) $insertRes")
    circularDeque.print()

    insertRes = circularDeque.insertLast(2);                    // 返回true
    println("insertLast(2) $insertRes")
    circularDeque.print()

    insertRes = circularDeque.insertFront(3);                    // 返回 true
    println("insertFront(3) $insertRes")
    circularDeque.print()

    insertRes = circularDeque.insertFront(4);                    // 已经满了，返回 false
    println("insertFront(4) $insertRes")
    circularDeque.print()

    var getRes = circularDeque.getRear();                // 返回 2
    println("getRear $getRes")
    circularDeque.print()

    val isFull = circularDeque.isFull();                        // 返回 true
    println("isFull $isFull")
    circularDeque.print()

    val deleteLast = circularDeque.deleteLast();                    // 返回 true
    println("deleteLast $deleteLast")
    circularDeque.print()

    val insertFront = circularDeque.insertFront(4);                    // 返回 true
    println("insertFront(4) $insertFront")
    circularDeque.print()

    val front = circularDeque.getFront();                // 返回 4
    println("getFront $front")
    circularDeque.print()
}

private fun MyCircularDeque.print() {
    print("[")
    this.elements.forEach {
        print("$it ,")
    }
    println("]")
    println("head:$head -- tail:$tail")
    println()

}

/**
 * 1、审题，设置一个双端的循环队列，
 * - 可以设置默认队列大小，在队列头插入删除数据，或在队列尾部插入删除数据，
 * - 判断队列是否已经满了，还是空了
 * 2、解题：因为没有容量扩展功能，队列采用数组作为底层数据结构，通过设置容量来初始化数组大小，
 * --数组的大小在原来容量的基础上加1,
 * - 插入元素，分为在头部和尾部插入，需要两个指针标识头、尾部的位置，
 * - 当队列满了的时候，需要判断：head = (tail + 1) % size
 * - 双端队列，可以在队列的两头进行元素的插入和删除，所以需要两个指针标识队头和队尾的位置，刚开始队头队尾指针都为0，head=0，tail=0
 * --当在队头插入数据时，新的位置是：（head-1+size）%size； 队尾插入元素则新元素新位置是：（tail+1）%size
 * 3、总结：
 * - 创建默认数组时，数组大小在原来的基础上加1， --》为什么这么设计？
 * - 队头、队尾指针的设计，在队头指针插入，直接使用当前的head位置，使用完后，head位置前移 (head-1+size)%size
 * - 队尾指针插入，先后移， tail=(tail+1)%size，
 * --所以队头指针head是当前元素位置的前一个标识，要去队头元素时，先要后移
 * --队尾指针刚好是队尾指针元素的位置，直接获取
 * --是否为空判断: head == tail
 * --是否满： (tail+1)%size == head
 * 4、双端链表解题方式
 */
class MyCircularDeque(k: Int) {

    val elements: IntArray
    private var size: Int
    var head = 0
    var tail = 0

    init {
        elements = IntArray(k + 1)
        size = elements.size
    }

    fun insertFront(value: Int): Boolean {
        if (isFull()) {
            return false
        }
        elements[head] = value
        head = (head - 1 + size) % size
        return true
    }

    fun insertLast(value: Int): Boolean {
        if (isFull()) {
            return false
        }
        tail = (tail + 1) % size
        elements[tail] = value
        return true
    }

    fun deleteFront(): Boolean {
        if (isEmpty()) {
            return false
        }
        head = (head + 1) % size
        elements[head] = -1
        return true
    }

    fun deleteLast(): Boolean {
        if (isEmpty()) {
            return false
        }
        elements[tail] = -1
        tail = (tail - 1 + size) % size
        return true

    }

    fun getFront(): Int {
        if (isEmpty()) {
            return -1
        }
        val front = (head + 1) % size
        return elements[front]
    }

    // getLasts
    fun getRear(): Int {
        if (isEmpty()) {
            return -1
        }
        return elements[tail]

    }

    fun isEmpty(): Boolean {
        return head == tail
    }

    fun isFull(): Boolean {
        return (tail + 1) % size == head
    }

}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * var obj = MyCircularDeque(k)
 * var param_1 = obj.insertFront(value)
 * var param_2 = obj.insertLast(value)
 * var param_3 = obj.deleteFront()
 * var param_4 = obj.deleteLast()
 * var param_5 = obj.getFront()
 * var param_6 = obj.getRear()
 * var param_7 = obj.isEmpty()
 * var param_8 = obj.isFull()
 */
