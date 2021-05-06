package com.gyy.guoLinKt.bean

import android.util.Log
import androidx.lifecycle.*
import com.gyy.guoLinKt.activity.LivedataActivity

class TestViewModule : ViewModel() {

    private val muLiveData1: MutableLiveData<String> = MutableLiveData("我是")
    private val muLiveData2: MutableLiveData<String> = MutableLiveData("郭媛媛")

    /**
     * 返回一个MutableLiveData对象
     */
    fun getStatus(): MutableLiveData<String> {
        return muLiveData1
    }

    fun setStatus(data: String) {
        muLiveData1.value = data
    }

    /**
     * LiveData是一个抽象类，可被继承，但不可通过new创建
     * MutableLiveData是LiveData的子类，继承了LiveData
     */
    public val mutableLiveData: MutableLiveData<String> = MutableLiveData("111")

    //map的方法一
    private val mapLiveData: LiveData<Int> = Transformations.map(mutableLiveData) {
        it.toInt()
    }

    //map的方法二://        it.toInt()
    private val map2LiveData: LiveData<Int> = mutableLiveData.map {
//        setMapData() //这里有个问题
        it.toInt()
    }

    private fun setMapData(a: Int): LiveData<Int> {
        val mutableLiveData: MutableLiveData<Int> = MutableLiveData()
        mutableLiveData.value = a + 2
        Log.e(TAG, "setSwitchData: " + mutableLiveData.value)
        return mutableLiveData
    }

    /**
     * 返回一个MutableLiveData对象，方便外部获取
     */
    fun getMapData(): LiveData<Int> {
        return mapLiveData
    }

    /**
     * 返回一个MutableLiveData对象，方便外部获取
     */
    fun getMap2Data(): LiveData<Int> {
        return map2LiveData
    }

    private val mu2LiveData: MutableLiveData<Int> = MutableLiveData(123)

    private val switchLiveData: LiveData<String> = Transformations.switchMap(mu2LiveData) {
        setSwitchData(it)
    }

    fun getSwitchData(): LiveData<String> {
        return switchLiveData
    }

    private fun setSwitchData(a: Int): LiveData<String> {
        val mutableLiveData: MutableLiveData<String> = MutableLiveData()
        mutableLiveData.value = (a + 2).toString()
        Log.e(TAG, "setSwitchData: " + mutableLiveData.value)
        return mutableLiveData
    }

    companion object {
        private const val TAG = "LivedataActivity"
    }

    /**
     * 学习MediatorLiveData
     */
    fun getMediatorData(): MediatorLiveData<String> {
        muLiveData1.value = "wo"
        muLiveData2.value = "shi"
        val mediatorLiveData: MediatorLiveData<String> = MediatorLiveData()
        mediatorLiveData.addSource(muLiveData1, Observer {
            Log.e(LivedataActivity.TAG, " getMediatorData muLiveData1------$it")
        })
        mediatorLiveData.addSource(muLiveData2, Observer {

        })
        return mediatorLiveData
    }


}