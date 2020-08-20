package com.gyy.guoLinKt.kotlin

import androidx.room.*
import kotlinx.coroutines.selects.select

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Delete
    fun deleteUser(deleteUser: User)

    @Query("select * from User")
    fun loadAllUser(): List<User>

    @Query("select * from User where age > :age")
    fun loadUsersOldderThan(age: Int): List<User>

    @Query("delete from User where lastName == :lastName")
    fun deleteUserByLastName(lastName: String): List<User>


}