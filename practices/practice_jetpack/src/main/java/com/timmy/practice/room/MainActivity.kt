package com.timmy.practice.room

import android.util.Log
import android.view.View
import com.timmy.libbase.base.activity.BaseVbActivity
import com.timmy.practice.room.bean.User
import com.timmy.practice.room.databinding.ActivityMainBinding
import java.util.concurrent.Executors
import kotlin.random.Random

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

    fun insert(view: View) {
        runData {
            val user = User()
            user.uId = 1
            user.uName = "张三"
            user.uAge = 19
            user.uAddress = "lkjfds"
            user.uSex = 0

            RoomHelper.getInstance().insert(this, user)
        }
    }

    fun insertRandom(view: View) {
        runData {
            val user = User()
            user.uId = Random.nextInt(1000)
            user.uName = "李四 Random"
            user.uAge = Random.nextInt(100)
            user.uAddress = "123456 rr"
            user.uSex = 0

            Log.d("Room", user.toString())
            RoomHelper.getInstance().insert(this, user)
        }
    }

    fun delete(view: View) {
        runData {
            RoomHelper.getInstance().delete(this, 1)
        }
    }

    fun update(view: View) {
        runData {
            val user = User()
            user.uId = 1
            user.uName = "李四"
            user.uAddress = "111 rr"
            user.uAge = 24
            user.uSex = 1
            RoomHelper.getInstance().update(this, user)
        }
    }

    fun query(view: View) {}
    fun queryAll(view: View) {
        runData {
            val userList = RoomHelper.getInstance().getUsers(this)
            println(userList)
        }
    }

    fun upgrad(view: View) {}

    fun runData(runnable: Runnable) {
        Executors.newSingleThreadExecutor().execute(runnable)
    }
}