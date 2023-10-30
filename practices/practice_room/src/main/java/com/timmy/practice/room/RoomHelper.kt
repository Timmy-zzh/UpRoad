package com.timmy.practice.room

import android.content.Context
import com.timmy.practice.room.bean.User

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

    fun getUsers(context: Context): List<User> {
        val userList = UserRoomDB.getDatabase(context).userDao().selectAll()
        return userList
    }
}