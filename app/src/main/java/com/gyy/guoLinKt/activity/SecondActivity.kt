package com.gyy.guoLinKt.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.gyy.guoLinKt.R
import com.gyy.guoLinKt.adapter.FruitAdapter
import com.gyy.guoLinKt.adapter.FruitAdapterCard
import com.gyy.guoLinKt.bean.Fruit
import com.gyy.guoLinKt.kotlin.showToast
import kotlinx.android.synthetic.main.activity_second.*
import kotlin.concurrent.thread

/***
 * 1、学习Material Design
 * 2、接收序列化数据
 * 3、action跳转
 * 4、接收Activity跳转带Extra参数的
 * 5、回传到上一个activity的数据setResult
 */
class SecondActivity : AppCompatActivity() {

    val fruits = mutableListOf(
        Fruit("Apple", R.drawable.apple),
        Fruit("Banana", R.drawable.banana),
        Fruit("Orange", R.drawable.orange),
        Fruit("Watermelon", R.drawable.watermelon),
        Fruit("Pear", R.drawable.pear),
        Fruit("Grape", R.drawable.grape),
        Fruit("Pineapple", R.drawable.pineapple),
        Fruit("Strawberry", R.drawable.strawberry),
        Fruit("Cherry", R.drawable.cherry),
        Fruit("Mango", R.drawable.mango)
    )

    val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

//        加上这句toolbar才会显示出来,加载id
        setSupportActionBar(my_toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }

//        学习NavigationView,就是滑动菜单的内容
        val headerView = nav.getHeaderView(0)
        headerView.setOnClickListener {
            Toast.makeText(this, "这里是头部", Toast.LENGTH_SHORT).show()
        }
        nav.setCheckedItem(R.id.navCall)
        nav.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navFriends -> "navFriends".showToast()
                R.id.navMail -> "navMail".showToast()
                R.id.navTask -> "navTask".showToast()
            }
//            drawerLayout.closeDrawers()
            true
        }

        //学习悬浮按钮
        fab.setOnClickListener {
//            "fab".showToast()
            Snackbar.make(it, "Data deleted", Snackbar.LENGTH_SHORT)
                .setAction("Undo") {
                    //撤销所做的事情
                    "Data restored".showToast()
                }
                .show()
        }

//        初始化卡片布局用到的RV
        initFruits()
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapterCard(this, fruitList)
        recyclerView.adapter = adapter
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        swipeRefresh.setOnRefreshListener {
            refreshFruits(adapter)
        }


//        val keyvalue = intent.getStringExtra("KEY")
//        Log.e("gyy", "keyvalue is $keyvalue")

//        验证序列号跳转的
//        val fruit = intent.getSerializableExtra("serial_data") as Fruit
//        val fruit = intent.getParcelableExtra("serial_data") as Fruit
//        Log.e(TAG, "onCreate: fruit:${fruit.name}")

//        回传到上一个activity的数据setResult
//        button2.setOnClickListener {
//            val intent = Intent()
//            intent.putExtra("BACK_DATA", "IT IS BACK DATA")
//            setResult(Activity.RESULT_OK, intent)
//            finish()
//        }

    }

    private fun refreshFruits(adapter: FruitAdapterCard) {
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                initFruits()
                adapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun initFruits() {
        fruitList.clear()
        repeat(50) {
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
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
