package com.timmy.practice.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.timmy.practice.room.bean.User
import com.timmy.practice.room.dao.UserDao

/**
 * 三、创建Room数据库
 * - Database注解
 * - 获取Dao层对象
 * - 伴生对象，创建数据库实例
 */
@Database(
    // 多个表格使用逗号分隔
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserRoomDB : RoomDatabase() {

    abstract fun userDao(): UserDao

    //
    companion object {
        private const val DB_NAME = "user.db"

        @Volatile
        private var INSTANCE: UserRoomDB? = null

        fun getDatabase(context: Context): UserRoomDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDB::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}