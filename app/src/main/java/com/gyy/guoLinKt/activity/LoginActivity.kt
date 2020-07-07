package com.gyy.guoLinKt.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gyy.guoLinKt.R
import kotlinx.android.synthetic.main.activity_login.*

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
                }else{
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
            val edits = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
            edits.putString("name", "Tom")
            edits.putInt("age", 21)
            edits.putBoolean("married", true)
            edits.apply()
        }
        //读取SP的内容，打log判断
        btn_restore.setOnClickListener {
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val name = prefs.getString("name", "")
            val age = prefs.getInt("age", 0)
            val married = prefs.getBoolean("married", false)
            Log.d(TAG, "name is $name,age is $age,marry or is $married")
        }


    }

    companion object {
        const val TAG = "LoginActivity"
    }
}