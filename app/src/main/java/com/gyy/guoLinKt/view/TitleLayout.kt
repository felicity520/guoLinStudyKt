package com.gyy.guoLinKt.view

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.gyy.guoLinKt.R
import kotlinx.android.synthetic.main.title.view.*

class TitleLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    init {
        Log.e("gyy", "TitleLayout context is $context.toString(),this $this")
        //E/gyy: TitleLayout context is com.gyy.guoLinKt.activity.TestActivity@3ab6658.toString(),this com.gyy.guoLinKt.view.TitleLayout{641bb46 V.E...... ......I. 0,0-0,0}
        LayoutInflater.from(context).inflate(R.layout.title, this)//第二个参数表示添加一个父布局，this代表TitleLayout

        back.setOnClickListener {
            //as代表强制类型转换，这里指的是 (Activity)context
            val activity = context as Activity
            activity.finish()
        }

        edit.setOnClickListener {
            Toast.makeText(context, "按了编辑", Toast.LENGTH_SHORT).show()
        }
    }
}