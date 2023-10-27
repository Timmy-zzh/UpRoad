package com.timmy.dataalg._01array

import java.util.Arrays

/**
 * 01-实现一个支持动态扩容的数组
 *
 */
fun main() {

}

/**
 * 支持动态扩容的数组：
 * 1、他是一个数组，有初始化容量大小
 * 2、增：当不断往数组中添加元素时，需要扩展数组的容量，要将原先保存的元素复制一份，添加到新的数组中
 * 3、删：删除一个元素后，要保持数组元素是连续保存的，在中间位置删除后，后面的元素列表页需要往前移动
 * 4、该：只要知道元素下标位置就可以修改
 * 5、查：通过元素下标位置直接获取
 */
private class DynamicArray<E>(val initNum: Int = 8) {

    // 可变化的
    private var elements = mutableListOf<E>()

    //    private var elements: List<E> = listOf()
    private var size = initNum
    private var index = 0

    fun add(element: E) {
        if (index >= size) {
            // 扩容
            val newSize = size * 2
            val newElements = mutableListOf<E>()
            for (i in elements.indices) {
                newElements[i] = elements[i]
            }
            elements = newElements
        }
        elements[index] = element
        index++
    }

}