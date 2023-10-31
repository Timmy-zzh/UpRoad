package com.timmy.practice.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
    version = 2,
    exportSchema = false
)
abstract class UserRoomDB : RoomDatabase() {

    abstract fun userDao(): UserDao

    // 伴生对象
    companion object {
        private const val DB_NAME = "user.db"

        @Volatile
        private var INSTANCE: UserRoomDB? = null

        // DCL
        fun getDatabase(context: Context): UserRoomDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDB::class.java,
                    DB_NAME
                )
                    .addMigrations(migrations_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private val migrations_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d("Room", "migrate")
                // user 表中，新增一个address字段
                val addColumnSql = "ALTER TABLE " + User.TABLE_NAME + " ADD COLUMN address TEXT"
                database.execSQL(addColumnSql)
            }
        }

    }

}