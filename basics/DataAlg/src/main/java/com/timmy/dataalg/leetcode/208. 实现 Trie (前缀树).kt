package com.timmy.dataalg.leetcode

import java.util.LinkedList

/**
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
请你实现 Trie 类：
Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。

示例：
输入
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
输出
[null, null, true, false, true, null, true]

解释
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 True
trie.search("app");     // 返回 False
trie.startsWith("app"); // 返回 True
trie.insert("app");
trie.search("app");     // 返回 True

提示：
1 <= word.length, prefix.length <= 2000
word 和 prefix 仅由小写英文字母组成
insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 */
fun main() {
    val trie = Trie()
    trie.insert("app")
    trie.insert("apple")
    trie.insert("orange")

    var res = trie.search("apps")
    println("search res:$res")
    res = trie.search("app")
    println("search res:$res")

    //     res = trie.startsWith("appw")
    //    println("startsWith res:$res")
}

/**
 * 1、审题：
 * - 实现前缀树，
 * 2、解题：
 * - 树形结构，那肯定是链表数据结构了，而且是多叉树，每个数的结点有26个子节点（26个英文字母）
 * - 业务操作有：增，查询，前缀查询
 * - 根节点为 rootNode(‘！’)
 * 3、打印问题： 层序遍历打印
 */
class Trie() {

    private val rootNode = TrieNode('@')

    /**
     * 插入一个字符串
     * - 遍历字符串中的字符，取出后
     * - 将其添加到根节点上，当前操作的结点在不断循环的过程中，也是一直从树结构的根节点不断往下拓展
     * - 操作节点的内容也是不断填充
     * - 打印查看的话，使用层序遍历,queue队列
     */
    fun insert(word: String) {
        var node = rootNode
        for (i in word.indices) {
            val ch = word[i]
            println("for ch:$ch")
            // 判断子节点集合中，是否已经有该数值节点存在，存在话就使用该结点，否则就需要新创建
            if (node.childs[ch - 'a']!=null){
                node = node.childs[ch - 'a']!!
            }else{
                val newNode = TrieNode(ch)      // 每次都是一个新的对象，被后面的apple树的结点覆盖了
                node.childs[ch - 'a'] = newNode
                node = newNode
            }
        }
            node.isEnd = true
        //        printTrie()
    }

    /**
     * 查询：
     * - 遍历字符串，刚好到最后一个字符的时候，也就是之前插入一个字符串的结束位置
     * - 所以在插入字符串的时候，最后一个节点需要标识是否是结束字符 - isEnd
     */
    fun search(word: String): Boolean {
        println("search:$word")
        var node = rootNode
        for (ch in word) {
            println("for ch:$ch")
            if (node.childs[ch - 'a'] == null) {
                return false
            }
            node = node.childs[ch - 'a']!!
        }
        return node.isEnd
    }

    fun startsWith(prefix: String): Boolean {
        println("startsWith:$prefix")
        var node = rootNode
        for (ch in prefix) {
            println("for ch:$ch")
            if (node.childs[ch - 'a'] == null) {
                return false
            }
            node = node.childs[ch - 'a']!!
        }
        return true
    }

    // 层序遍历
    private fun printTrie() {
        var node = rootNode
        val queue = LinkedList<TrieNode?>()
        queue.add(node)

        while (queue.isNotEmpty()) {
            val popNode = queue.pop()
            if (popNode == null) {
                println()
                continue
            }
            print("${popNode!!.ch} \t")
            val childs = popNode.childs
            for (childNode in childs) {
                if (childNode != null) {
                    queue.add(childNode)
                }
            }
            queue.add(null)
        }
    }
}

/**
 * 前缀树节点：
 * - 数据内容 char，字符
 * - 子类节点列表，限定26个子节点
 */
class TrieNode() {
    var childs = ArrayList<TrieNode?>(26)
    var ch: Char? = null
    var isEnd = false

    constructor(ch: Char) : this() {
        this.ch = ch
        for (i in 0 until 26) {
            childs.add(null)
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * var obj = Trie()
 * obj.insert(word)
 * var param_2 = obj.search(word)
 * var param_3 = obj.startsWith(prefix)
 */