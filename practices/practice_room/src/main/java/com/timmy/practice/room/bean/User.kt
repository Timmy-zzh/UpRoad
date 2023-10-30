package com.timmy.practice.room.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * 一、创建实体类
 * 该类中规定数据表中，包含了那些字段
 */
@Entity(tableName = User.TABLE_NAME)
class User {

    companion object {
        const val TABLE_NAME = "user"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
    }

    //     主键
    // 字段-名称
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    var uId: Int = 0

    // 字段-名称
    @ColumnInfo(name = COLUMN_NAME)
    val aName: String? = null

    @ColumnInfo(name = COLUMN_AGE)
    val uAge: Int = 0

    @Ignore
    var uSex: Int = 0
}



