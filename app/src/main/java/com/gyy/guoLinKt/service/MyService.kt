package com.gyy.guoLinKt.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.gyy.guoLinKt.R
import com.gyy.guoLinKt.activity.TestActivity

class MyService : Service() {

    companion object {
        private const val TAG = "MyService"
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind--------------")
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate--------------")
        val notificationManger =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //通知渠道一旦创建就不能更改。更改无效
            val channel =
                NotificationChannel("myservice", "后台服务1", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManger.createNotificationChannel(channel)
        }
        val intent = Intent(this, TestActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val notification = NotificationCompat.Builder(this, "myservice")
            .setContentTitle("这是titleaaaa")
            .setSmallIcon(R.drawable.small_icon)
            .setContentIntent(pi)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
            .build()
        startForeground(1, notification)
        super.onCreate()
    }

    inline fun <T> bar() {

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand--------------")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy--------------")
        super.onDestroy()
    }
}
