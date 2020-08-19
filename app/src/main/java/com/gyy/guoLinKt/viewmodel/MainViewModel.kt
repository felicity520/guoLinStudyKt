package com.gyy.guoLinKt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.gyy.guoLinKt.bean.Repository
import com.gyy.guoLinKt.kotlin.Usr

class MainViewModel(counterPara: Int) : ViewModel() {

    //学习switchMap-------------不带参数
    private val liveDataAny = MutableLiveData<Any>()

//    val userAny = Transformations.switchMap(liveDataAny) {
//    大概演示一下，这里一直报错就不继续了
//        Repository.reFresh()
//    }

    fun refresh() {
        //重点是在这里，只要调用setValue就会触发
        liveDataAny.value = liveDataAny.value
    }

    //学习switchMap-------------带参数

    //传入userId的类型是String，但是最终返回的LiveData类型
    private val liveData = MutableLiveData<String>()

    val user: LiveData<Usr> = Transformations.switchMap(liveData) { user ->
        Repository.getUser(user)
    }

    //获取LiveData，但是LiveData的数据也是调用其他的方法才能拿到的
//    fun getUserId(userId: String): LiveData<Usr> {
    //原本需要返回的代码
//        return Repository.getUser(userId)
//    }

    fun getUserId(userId: String) {
        //修改之后的代码
        liveData.value = userId
    }


    //学习map
    private val userLiveData = MutableLiveData<Usr>()

    val userName: LiveData<String> = Transformations.map(userLiveData) { user ->
        "${user.firstName}${user.lastName}"
    }

    //学习基本用法
//    var counter = counterPara

    private val _counter = MutableLiveData<Int>()

    //    val counter: LiveData<Int> = _counter
    val counter: LiveData<Int>
        get() = _counter

    val a: Int
        get() = 1

    init {
        _counter.value = counterPara
    }

    fun plusOne() {
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }

}