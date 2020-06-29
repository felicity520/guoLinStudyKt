package com.gyy.guoLinKt.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gyy.guoLinKt.R
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
//        val keyvalue = intent.getStringExtra("KEY")
//        Log.e("gyy", "keyvalue is $keyvalue")

        button2.setOnClickListener {
            val intent = Intent()
            intent.putExtra("BACK_DATA", "IT IS BACK DATA")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }

    companion object {
        fun actionStart(context: Context, data1: String, data2: String) {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("", data1)
            intent.putExtra("", data2)
            context.startActivity(intent)
        }

        //        升级版
        fun actionStart1(context: Context, data1: String, data2: String) {
            val intent = Intent(context, SecondActivity::class.java).apply {
                putExtra("", data1)
                putExtra("", data2)
            }
            context.startActivity(intent)
        }
    }
}
