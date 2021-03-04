package com.gyy.guoLinKt.bean

import retrofit2.Retrofit

object ServiceCreator {

    private const val baseUrl: String = "https://api.binstd.com/"

    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(baseUrl).build()

    //    <T>表示这是一个泛型方法
//    fun <T> boxIn(value: T) = Box(value)
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

}