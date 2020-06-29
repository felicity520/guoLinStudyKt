package com.gyy.guoLinKt.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gyy.guoLinKt.R
import com.gyy.guoLinKt.adapter.MsgAdapter
import com.gyy.guoLinKt.bean.Fruit
import com.gyy.guoLinKt.bean.Msg
import com.gyy.guoLinKt.kotlin.Util
import kotlinx.android.synthetic.main.activity_test.*


class TestActivity : BaseActivity(), View.OnClickListener {

//    val brocast = TimeBrocast()

    //也可以延迟初始化：TimeBrocast后面不用再加()了，注意啊啊啊啊
    lateinit var brocast: TimeBrocast

    companion object {
        const val TAG = "TestActivity"
    }

//        listview简单示例的数据源
//    private val listViewData = listOf(
//        "Apple",
//        "Banana",
//        "Orange",
//        "Apple",
//        "Banana",
//        "Orange",
//        "Apple",
//        "Banana",
//        "Orange"
//    )

    private val listViewData = ArrayList<Fruit>()

    val msglist = ArrayList<Msg>()
    private lateinit var msgAdapter: MsgAdapter
//    lateinit 延迟初始化
//    private var msgAdapter: MsgAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        supportActionBar?.hide()  //隐藏自带的标题栏
        Util.doAction3()

//        在savedInstanceState中读取临时保存的数据.taskId表示返回栈的ID
        val somedatastr = savedInstanceState?.getString("somedata")
        Log.e("gyy", "gyy somedatastr is $somedatastr $taskId")

        getScreenData()
        initListViewData()
        initMsgData()
        initView()
        initEventnt()
//        studyFragment()
        startBrocast()
        sendMyBrocast()
    }

    private fun sendMyBrocast() {
//        val btn_send: Button = findViewById(R.id.btn_send) //不用这行也可以
        btn_send.setOnClickListener {
            //发送标准广播和有序广播示例代码
//            val intent = Intent("android.intent.action.MY_CUSTOME")
//            intent.setPackage(packageName)
//            sendBroadcast(intent)
//            sendOrderedBroadcast(intent,null)

            //实现强制下线功能
            val intent = Intent("android.intent.action.ForceofflineReceiver")
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }
    }

    private fun startBrocast() {
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        brocast = TimeBrocast()
        registerReceiver(brocast, intentFilter)
    }

    inner class TimeBrocast : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "时间改变了", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getScreenData() {
        val resources: Resources = this.resources
        val dm: DisplayMetrics = resources.getDisplayMetrics()
        val density1 = dm.density
        val width3 = dm.widthPixels
        val height3 = dm.heightPixels
        //density1 is 2.625,width3 is 1080,height3 is 2118
        Log.e(TAG, "density1 is $density1,width3 is $width3,height3 is $height3")


        val d =
            getResources().displayMetrics //可以获得scaledDensity，densityDpi，heightPixels，widthPixels等信息。
        val configuration: Configuration = getResources().configuration //获取设备的配置信息
        val width: Int = configuration.screenHeightDp  //当前屏幕可用空间的高度，单位是dp
        val height: Int = configuration.screenWidthDp   //当前屏幕可用空间的宽度，单位是dp
        val densityDpi: Int = configuration.densityDpi //当前设备的dpi信息
        //width is 782,height is 411
        Log.e(TAG, "width is $width,height is $height")
    }


//    private fun studyFragment() {
//        leftbtn.setOnClickListener {
//            replaceFragment(AnotherRightFragment())
//        }
//        replaceFragment(RightFragment())
//    }

//    private fun replaceFragment(fragment: Fragment) {
//        val fragmentManager = supportFragmentManager
//        val transaction = fragmentManager.beginTransaction()
//        transaction.replace(R.id.rightFragment, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }

    private fun initMsgData() {
        msglist.add(Msg("你是谁？", Msg.TYPE_SEND))
        msglist.add(Msg("我是GYTY", Msg.TYPE_RECEIVED))
        msglist.add(Msg("那我们做个朋友吧 ", Msg.TYPE_SEND))
    }

    private fun initListViewData() {
        repeat(3) {
            listViewData.add(Fruit(getRandomLengthString("applexxx"), R.drawable.apple))
            listViewData.add(Fruit(getRandomLengthString("banana"), R.drawable.banana))
            listViewData.add(Fruit(getRandomLengthString("cherry"), R.drawable.cherry))
            listViewData.add(Fruit(getRandomLengthString("orange"), R.drawable.orange))
            listViewData.add(Fruit(getRandomLengthString("mango"), R.drawable.mango))
        }
    }

    fun getRandomLengthString(str: String): String {
        val stringBuilder = StringBuilder()
        //产生一个随机随机数
        val n = (0..10).random()
        repeat(n) {
            stringBuilder.append(str)
        }
        return stringBuilder.toString()
    }


    private fun initView() {
//        listview的简单示例
//        val adapter =
//            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listViewData)
//        listView.adapter = adapter

//重写listview的FruitAdapter
//        val fruitAdapter = FruitAdapter(this, R.layout.fruit_item, listViewData)
//        listView.adapter = fruitAdapter

        //java单抽象方法:看onItemClick中有那些参数，就在lambda中传什么参数
//        listView.setOnItemClickListener { parent, view, position, id ->
//            val fruit = listViewData[position]
//            Toast.makeText(this, fruit.name, Toast.LENGTH_SHORT).show()
//        }
        //kt中没有使用到的参数可以用下划线 _ 代替
//        listView.setOnItemClickListener { _, _, position, _ ->
//            val fruit = listViewData[position]
//            Toast.makeText(this, fruit.name, Toast.LENGTH_SHORT).show()
//        }

//RecyclerView加载水果图片及名称的示例
//        线性布局
//        val linearLayoutmanager = LinearLayoutManager(this)
//        linearLayoutmanager.orientation = LinearLayoutManager.HORIZONTAL;

//        瀑布流布局
//        val staggeredGridLayoutManager =
//            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
//
//        recyclerView.setLayoutManager(staggeredGridLayoutManager)
//        val rvFruitAdapter = RvFruitAdapter(listViewData)
//        recyclerView.adapter = rvFruitAdapter

        send.setOnClickListener(this)

        val msglinearLayoutmanager = LinearLayoutManager(this)
        recyclerView.setLayoutManager(msglinearLayoutmanager)
        //若未在使用前完成初始化，会报错
//Caused by: kotlin.UninitializedPropertyAccessException: lateinit property msgAdapter has not been initialized
        //判断变量是否有初始化  ::msgAdapter.isInitialized  ::表示调用变量msgAdapter
        if (!::msgAdapter.isInitialized) {
            msgAdapter = MsgAdapter(msglist)
        }
        recyclerView.adapter = msgAdapter
    }

    /**
     * 在onSaveInstanceState中保存临时数据
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("gyy", "gyy somedatastr is onSaveInstanceState")
        val somedata = "It is somedata"
        outState.putString("somedata", somedata)
    }

    /**
     *onCreateOptionsMenu和onOptionsItemSelected都是用KT语法糖，item?.itemId相当于item.getItemId()
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.Add -> Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show()
            R.id.Remove -> Toast.makeText(this, "Remove", Toast.LENGTH_SHORT).show()
        }
        return true
    }


    private fun initEventnt() {
        imageView.setImageResource(R.drawable.ic_launcher_background)//
        button1.setOnClickListener(this)
//        button1.setOnClickListener {
//            //            在KT中SecondActivity::class.java = SecondActivity.class
//            Toast.makeText(this, "跳转到SecondActivity", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, SecondActivity::class.java)
////            intent.putExtra("KEY", "hello")
//            startActivityForResult(intent, 1)

//            intentWeb.data再次使用了KT语法糖
//            Toast.makeText(this, "跳转到baidu", Toast.LENGTH_SHORT).show()
//            val intentWeb = Intent(Intent.ACTION_VIEW)
//            intentWeb.data = Uri.parse("https://www.baidu.com")
//            startActivity(intentWeb)
//        }

        //无需通过findViewById来加载控件，也无需声明，可以直接使用。因为这个插件kotlin-android-extensions
//        val button1: Button = findViewById(R.id.button1)
//        button1.setOnClickListener {
//        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            1 -> if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, data?.getStringExtra("BACK_DATA"), Toast.LENGTH_SHORT).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    override fun onDestroy() {
        super.onDestroy()
//        ActivityCollector.finishAll()
        unregisterReceiver(brocast)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button1 -> {
//                Toast.makeText(this, editText.hint.toString(), Toast.LENGTH_SHORT).show()
//                progressbar.progress = progressbar.progress + 10
//                if (progressbar.visibility == View.VISIBLE) {
//                    progressbar.visibility = View.GONE
//                } else {
//                    progressbar.visibility = View.VISIBLE
//                }

                val dialog = AlertDialog.Builder(this).apply {
                    setTitle("Title")
                    setMessage("Message")
                    setPositiveButton("OK") { dialog, which ->
                        Log.e("gyy", "点击了OK")
                    }
                    show()
                }
            }

            R.id.send -> {
                val content = msgedittext.text.toString()
                if (!content.isEmpty()) {
                    msglist.add(Msg(content, Msg.TYPE_RECEIVED))
                    //刷新整个页面
//                    msgAdapter?.notifyDataSetChanged()
                    //延迟初始化后这里msgAdapter就不用判空处理了
                    msgAdapter.notifyDataSetChanged()
                    //刷新刚插入的一行
                    msgAdapter.notifyItemInserted(msglist.size - 1)
                    //将光标定位到最后一行
                    recyclerView.scrollToPosition(msglist.size - 1)
                }
            }


        }
    }
}
