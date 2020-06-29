package com.gyy.guoLinKt.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.gyy.guoLinKt.R

open class BaseActivity : AppCompatActivity() {

    lateinit var forceBrocast: ForceofflineReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
//        获取当前示例的Class对象，再调用simpleName获取当前示例的类名
        Log.e("BaseActivity", javaClass.simpleName)
        ActivityCollector.addActivity(this)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.ForceofflineReceiver")
        forceBrocast = ForceofflineReceiver()
        registerReceiver(forceBrocast, intentFilter)
    }

    inner class ForceofflineReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            AlertDialog.Builder(context).apply {
                setTitle("警告")
                setMessage("账号在异地登录，被强制下线")
                setCancelable(false)
                setPositiveButton("OK") { _, _ ->
                    ActivityCollector.finishAll()
                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                }
                show()
            }
        }

    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(forceBrocast)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }
}
