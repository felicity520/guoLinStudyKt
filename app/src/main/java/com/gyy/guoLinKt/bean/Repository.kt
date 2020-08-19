package com.gyy.guoLinKt.bean

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyy.guoLinKt.kotlin.Usr

object Repository {

    fun getUser(userId: String): LiveData<Usr> {
        val liveData = MutableLiveData<Usr>()
        liveData.value = Usr(userId, userId, 0)
        return liveData
    }


    fun reFresh() {

    }
}