package com.gyy.guoLinKt.activity

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.edit
import com.google.android.material.shape.AbsoluteCornerSize
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.RelativeCornerSize
import com.google.android.material.shape.ShapeAppearanceModel
import com.gyy.guoLinKt.R
import com.gyy.guoLinKt.kotlin.open
import com.gyy.guoLinKt.kotlin.studyPublic
import kotlinx.android.synthetic.main.activity_login.*

/**
 *学习material1.20中的控件
 */
class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val prefsGlobal = getSharedPreferences("mypassword", Context.MODE_PRIVATE)
        val editsGlobal = prefsGlobal.edit()
        val needrem = prefsGlobal.getBoolean("needrem", false)
        if (needrem) {
            val myaccount = prefsGlobal.getString("account", "")
            val mypassword = prefsGlobal.getString("password", "")
            account.setText(myaccount)
            password.setText(mypassword)
            editsGlobal.putBoolean("needrem", true)
        }

        btn_login.setOnClickListener {
            val account1 = account.text.trim().toString()
            val password1 = password.text.trim().toString()
            Log.e("gyytest", "account1 is $account1,password1 is $password1")
            if (account1.equals("123") && password1.equals("123")) {
                if (remePass.isChecked) {
                    editsGlobal.putString("account", account1)
                    editsGlobal.putString("password", password1)
                    editsGlobal.putBoolean("needrem", true)
                } else {
                    editsGlobal.clear()
                }
                editsGlobal.apply()
                val intent = Intent(this, TestActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "账号密码错误", Toast.LENGTH_SHORT).show()
            }
        }

        //保存数据到SP
        btn_save.setOnClickListener {
            //传统的java的写法
//            val edits = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
//            edits.putString("name", "Tom")
//            edits.putInt("age", 21)
//            edits.putBoolean("married", true)
//            edits.apply()

//            使用自己定义的扩展函数SharedPreferences.open来写
//            val edits = getSharedPreferences("data", Context.MODE_PRIVATE).open {
//                putString("name", "Tom")
//                putInt("age", 21)
//                putBoolean("married", true)
//            }

            //使用SharedPreferences.open来写
            getSharedPreferences("data", Context.MODE_PRIVATE).edit {
                putString("name", "Tom")
                putInt("age", 21)
                putBoolean("married", true)
            }
        }

        //读取SP的内容，打log判断
        btn_restore.setOnClickListener {
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val name = prefs.getString("name", "")
            val age = prefs.getInt("age", 0)
            val married = prefs.getBoolean("married", false)
            Log.d(TAG, "name is $name,age is $age,marry or is $married")
        }

        cancelNotification()


        studyMaterial()
    }

    private fun studyMaterial() {
        imageView?.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCornerSizes(ShapeAppearanceModel.PILL)
            .build()


//            .setTopLeftCorner(CornerFamily.CUT, RelativeCornerSize(0.3f))
//            .setTopRightCorner(CornerFamily.CUT, RelativeCornerSize(0.3f))
//            .setBottomRightCorner(CornerFamily.CUT, RelativeCornerSize(0.3f))
//            .setBottomLeftCorner(CornerFamily.CUT, RelativeCornerSize(0.3f))
//            .setAllCornerSizes(ShapeAppearanceModel.PILL)
//            .setTopLeftCornerSize(20f)
//            .setTopRightCornerSize(RelativeCornerSize(0.5f))
//            .setBottomLeftCornerSize(10f)
//            .setBottomRightCornerSize(AbsoluteCornerSize(30f))
    }

    private fun cancelNotification() {
        val manger = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //取消id为1的通知
        manger.cancel(1)
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}