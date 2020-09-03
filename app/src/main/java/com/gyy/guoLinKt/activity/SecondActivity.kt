package com.gyy.guoLinKt.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.gyy.guoLinKt.R
import com.gyy.guoLinKt.kotlin.showToast
import kotlinx.android.synthetic.main.activity_second.*

/***
 * 1、学习Material Design
 * 2、接收序列化数据
 * 3、action跳转
 * 4、接收Activity跳转带Extra参数的
 * 5、回传到上一个activity的数据setResult
 */
class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

//        加上这句toolbar才会显示出来,加载id
        setSupportActionBar(my_toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }

//        val keyvalue = intent.getStringExtra("KEY")
//        Log.e("gyy", "keyvalue is $keyvalue")

//        验证序列号跳转的
//        val fruit = intent.getSerializableExtra("serial_data") as Fruit
//        val fruit = intent.getParcelableExtra("serial_data") as Fruit
//        Log.e(TAG, "onCreate: fruit:${fruit.name}")

        button2.setOnClickListener {
            val intent = Intent()
            intent.putExtra("BACK_DATA", "IT IS BACK DATA")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> "backup".showToast()
            R.id.delete -> "delete".showToast()
            R.id.settings -> "settings".showToast()
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    companion object {
        private const val TAG = "SecondActivity"

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
