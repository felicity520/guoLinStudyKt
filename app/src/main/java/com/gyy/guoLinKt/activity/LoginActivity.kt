package com.gyy.guoLinKt.activity

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


        btn_login.setOnClickListener {
            val account1 = account.text.trim().toString()
            val password1 = password.text.trim().toString()
            Log.e("gyytest","account1 is $account1,password1 is $password1")
            if (account1.equals("123") && password1.equals("123")) {
                val intent = Intent(this, TestActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "账号密码错误", Toast.LENGTH_SHORT).show()
            }
        }
    }
}