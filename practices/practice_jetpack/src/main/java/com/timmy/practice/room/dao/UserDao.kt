package com.timmy.practice.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.timmy.practice.room.bean.User

/**
 * 二：创建Dao层
 */
@Dao
interface UserDao {

    // 插入单条数据
    @Insert
    fun insert(user: User): Long

    @Insert
    fun insertList(users: List<User>): List<Long>

    @Delete
    fun delete(user: User): Int

    // ** 删除单个数据，用的是Query
    @Query("DELETE FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_ID + " = :id")
    fun deleteById(id: Long): Int

    // 根据主键更新
    @Update
    fun update(user: User): Int

    @Query("SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_ID + " = :id")
    fun selectById(id: Long): User

    @Query("SELECT * FROM " + User.TABLE_NAME)
    fun selectAll(): List<User>

    // 查询数据库表中有多少条数量
    @Query("SELECT COUNT(*) FROM " + User.TABLE_NAME)
    fun count(): Int

}