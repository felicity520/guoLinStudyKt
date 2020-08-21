package com.gyy.guoLinKt.kotlin

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {

    @Insert
    fun insertUser(book: Book): Long

    @Query("select * from Book")
    fun loadAllBooks(): List<Book>
}