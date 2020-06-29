package com.gyy.guoLinKt.kotlin

//使用data来定义一个数据类。当数据类中只有一行代码时，{}可以省略。必须要指定变量的类型才可以
//会自动帮我们实现数据类中的一些操作
data class Cellphone(val brand: String, val price: Double)