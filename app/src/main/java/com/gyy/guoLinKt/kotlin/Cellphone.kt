package com.gyy.guoLinKt.kotlin

import androidx.room.Entity
import androidx.room.PrimaryKey

//使用data来定义一个数据类。当数据类中只有一行代码时，{}可以省略。必须要指定变量的类型才可以
//会自动帮我们实现数据类中的一些操作
data class Cellphone(val brand: String, val price: Double)

//学习livedata
data class Usr(val firstName: String, val lastName: String, val age: Int)

//学习ROOM
@Entity
data class User(val firstName: String, val lastName: String, var age: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

@Entity
data class Book(val name: String, val pages: Int, var author: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

