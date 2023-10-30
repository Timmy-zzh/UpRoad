package com.timmy.practice.room

import android.view.View
import com.timmy.libbase.base.activity.BaseVbActivity
import com.timmy.practice.room.databinding.ActivityMainBinding

/**
 * JetPack练习：Room
 * - 创建数据库
 * - 数据库操作：CRUD
 * - 数据库升级
 */
class MainActivity : BaseVbActivity<ActivityMainBinding>() {

    override fun initListener() {

    }

    override fun initData() {
    }

    fun createRoom(view: View) {

    }

    fun insert(view: View) {}
    fun delete(view: View) {}
    fun update(view: View) {}
    fun query(view: View) {}
    fun queryAll(view: View) {
        val userList = RoomHelper.getInstance().getUsers(this)
        println(userList)
    }

    fun upgrad(view: View) {}
}