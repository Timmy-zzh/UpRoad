package com.timmy.practice.room

import android.content.Context
import com.timmy.practice.room.bean.User
import java.util.concurrent.Executors

/**
 * 数据库使用管理类
 */
class RoomHelper {
    companion object {
        @Volatile
        private var instance: RoomHelper? = null

        fun getInstance(): RoomHelper {
            if (instance == null) {
                instance = RoomHelper()
            }
            return instance!!
        }
    }

    fun insert(context: Context, user: User): Long {
        val id = UserRoomDB.getDatabase(context).userDao().insert(user)
        return id
    }

    fun delete(context: Context, id: Long): Int {
        return UserRoomDB.getDatabase(context).userDao().deleteById(id)
    }

    fun update(context: Context, user: User): Int {
        return UserRoomDB.getDatabase(context).userDao().update(user)
    }

    fun getUsers(context: Context): List<User> {
        val userList = UserRoomDB.getDatabase(context).userDao().selectAll()
        return userList
    }


}