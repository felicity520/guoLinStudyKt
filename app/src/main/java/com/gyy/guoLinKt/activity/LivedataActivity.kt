package com.gyy.guoLinKt.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.gyy.guoLinKt.R
import com.gyy.guoLinKt.bean.TestViewModule


class LivedataActivity : AppCompatActivity() {

    companion object {
        const val TAG = "LivedataActivity"
    }

    private lateinit var testViewModule: TestViewModule

    private val liveData: MutableLiveData<String> = MutableLiveData()

    private val liveDataForever: MutableLiveData<String> = MutableLiveData()

    var testLivedata: TestLiveData = TestLiveData.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata)

        //实验一：
        Log.e(TAG, " onCreate------")
        liveData.observe(this, Observer {
            Log.e(TAG, " observe------$it")
        })
//        liveData.value = "onCreate"
        Thread {
            liveData.postValue("nvankvnKV")
        }


        //实验二：
        liveDataForever.observeForever {
            Log.e(TAG, " observeForever------$it")
        }

//        实验三：
        testViewModule = ViewModelProvider(this)[TestViewModule::class.java]
        testViewModule.getStatus().observe(this, Observer {
            Log.e(TAG, " testViewModule------$it")
        })
        testViewModule.getStatus().value = "我是testViewModule"

//        实验四:map转换
        testViewModule.getMapData().observe(this, Observer {
            Log.e(TAG, " mapViewModule 111------$it")

        })
        testViewModule.mutableLiveData.value = "222"

        testViewModule.getMap2Data().observe(this, Observer {
            Log.e(TAG, " getMap2Data------$it")
        })

//        实验四：
        testViewModule.getSwitchData().observe(this, Observer {
            Log.e(TAG, " getSwitchData------$it")

        })

//        实验五：
        testViewModule.getMediatorData().observe(this, Observer {
            Log.e(TAG, " getMediatorData------$it")

        })

        testViewModule.setStatus("124")

//        实验6：liveData switchMap的详细说明用法
        val liveData1 = MutableLiveData<String>()
        val liveData2 = MutableLiveData<String>()
        val liveSourceData = MutableLiveData<Boolean>()
        val liveData: LiveData<String> = Transformations.switchMap(liveSourceData) {
            if (it) {
                liveData1
            } else {
                liveData2
            }
        }
        liveData.observe(this, Observer {
//            打印结果：switchMap之后的liveData------333
            Log.e(TAG, " switchMap之后的liveData------$it")
        })
        liveSourceData.value = true
        liveData1.value = "333"
        liveData2.value = "444"

        testMediator()
    }

    private fun testMediator() {
        val mediatorLiveData = MediatorLiveData<String>()

        val liveData5 = MutableLiveData<String>()
        val liveData6 = MutableLiveData<String>()
        mediatorLiveData.addSource(liveData5, Observer {
            mediatorLiveData.value = it
            Log.e(TAG, " mediatorLiveData liveData5------$it")
        })
        mediatorLiveData.addSource(liveData6, Observer {
            mediatorLiveData.value = it
            Log.e(TAG, " mediatorLiveData liveData6------$it")
        })
        mediatorLiveData.observe(this, Observer {
            Log.e(TAG, " mediatorLiveData------$it")
        })

        liveData5.value = "liveData5的数据"
        //liveData6.setValue("liveData6");
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, " onStart------")
//        liveData.value = "onStart"
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, " onResume------")
//        liveData.value = "onResume"
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, " onPause------")
//        liveData.value = "onPause"

    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, " onStop------")
        liveData.value = "onStop"

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, " onDestroy------")
        liveData.value = "onDestroy"

//        需要手动移除
        liveDataForever.removeObserver {

        }
    }
}