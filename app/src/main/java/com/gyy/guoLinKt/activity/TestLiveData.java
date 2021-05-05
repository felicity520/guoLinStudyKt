package com.gyy.guoLinKt.activity;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.gyy.guoLinKt.bean.LocationUtil;

/**
 * @author ADMIN
 */
public class TestLiveData extends LiveData<String> {

    private static final String TAG = "TestLiveData";

    private static TestLiveData sInstance;

    private LocationUtil mLocationUtil;

    /**
     * 设计为单例模式
     */
    public static TestLiveData getInstance() {
        if (sInstance == null) {
            sInstance = new TestLiveData();
        }
        return sInstance;
    }

    private TestLiveData() {
        //创建一个获取位置的对象
        mLocationUtil = new LocationUtil();
    }

    /**
     * 当有一个处于活跃状态的观察者监听LiveData时会被调用，这表示开始获取位置信息。
     */
    @Override
    protected void onActive() {
        Log.e(TAG, "onActive-------------: ");
    }

    /**
     * 当没有任何处于活跃状态的观察者监听LiveData时会被调用。
     * 由于没有观察者在监听了，所以也没必要继续去获取位置信息了，
     * 这只会消耗更多的电量等等，因此就可以停止获取位置信息了。
     */
    @Override
    protected void onInactive() {
        Log.e(TAG, "onInactive-------------: ");
    }


}


