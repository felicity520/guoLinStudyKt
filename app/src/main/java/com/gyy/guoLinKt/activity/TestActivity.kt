package com.gyy.guoLinKt.activity

import android.Manifest
import android.app.*
import android.content.*
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.gyy.guoLinKt.R
import com.gyy.guoLinKt.adapter.MsgAdapter
import com.gyy.guoLinKt.bean.*
import com.gyy.guoLinKt.kotlin.User
import com.gyy.guoLinKt.kotlin.Util
import com.gyy.guoLinKt.kotlin.later
import com.gyy.guoLinKt.kotlin.showToast
import com.gyy.guoLinKt.service.MyService
import com.gyy.guoLinKt.viewmodel.MainViewModel
import com.gyy.guoLinKt.viewmodel.MainViewModelFactory
import com.gyy.guoLinKt.viewmodel.MyObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.*
import javax.inject.Inject
import kotlin.concurrent.thread
import kotlin.coroutines.suspendCoroutine
import kotlin.properties.Delegates

/**
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃ 神兽保佑
 * 　　　　┃　　　┃ 代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * add by GYY
 */
//@AndroidEntryPoint
class TestActivity : BaseActivity(), View.OnClickListener {
    //正确的声明
    var int1 by Delegates.notNull<Int>()

    //报错
//    lateinit var int1: Int

    lateinit var test1: String

    //Hilt相关
//    @Inject
//    lateinit var truck: Truck

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var retrofit: Retrofit

//    @Inject
//    lateinit var viewModel: MyViewModel

    val viewModel: MyViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }

    //viewmodule相关
    lateinit var mainViewModule: MainViewModel

    lateinit var sp: SharedPreferences

    val mediaPlayer = MediaPlayer()

    //拍照相关的
    val iamgeCapture = 2
    val fromAlbum = 3
    lateinit var outImageFile: File
    lateinit var imageUri: Uri

    val p by later {
        Log.d("gyytestxxx", "懒加载初始化")
        "test code"
    }

    val contactsdata = ArrayList<String>()

    lateinit var contactsAdapter: ArrayAdapter<String>

//    val brocast = TimeBrocast()

    //也可以延迟初始化：TimeBrocast后面不用再加()了，注意啊啊啊啊
    lateinit var brocast: TimeBrocast

    companion object {
        const val TAG = "TestActivity"
        private const val appkey: String = "c1f9251fc22da079"
        private const val shouji: String = "13859110503"
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

    val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> {
                    text22.setText("在主线程")
                }
            }
        }
    }

//     val mHandler: Handler = object : Handler() {
//        override fun handleMessage(msg: Message) {
//            when (msg.what) {
//                1 -> {
//
//                }
//            }
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate: -----------------------")
        setContentView(R.layout.activity_test)

        "MY gyy 111".showToast()

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
        //studyLoadFile()
        studyDatabase()
        studyContentProvider()
        accessConPro()
        studyNotification()
        studyCamera()
        studyMusic()
        studyThread()
        studyService()
        studyViewModule()
        studyRoom()
        studyWorkManager()
        studySerialize()
        studyHandler()
//        studyHilt()
        studyXiechen()
        studyRetrofit();

    }

    private fun studyRetrofit() {
        btn_tel.setOnClickListener {
            val telService = ServiceCreator.create(TelService::class.java)
            telService.queryPlace(Companion.appkey, Companion.shouji)
                .enqueue(object : Callback<Tel> {
                    override fun onFailure(call: Call<Tel>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                    override fun onResponse(call: Call<Tel>, response: Response<Tel>) {
                        val tel = response.body()
                        Log.e(TAG, "onResponse city: ${tel?.result?.city.toString()}")
                    }
                })
        }
    }

    private fun studyXiechen() {
        val job = Job()
        val scope = CoroutineScope(job)
        scope.launch {
            //具体的逻辑
            val deferred1 = async {
                5 + 5
            }
            val deferred2 = async {
                5 + 6
            }
            Log.e(TAG, "${deferred1.await() + deferred2.await()}")


            val result = withContext(Dispatchers.Default) {
                5 + 1
            }
            Log.e(TAG, "$result")

            suspendCoroutine {

            }
        }
        job.cancel()
    }

//    private fun studyHilt() {
//        truck.deliver()
//    }

    private fun studyHandler() {
        thread {
            Thread.sleep(2000)
            Log.e(TAG, "studyHandler: 更新UI------------------")
            text_handler.text = Thread.currentThread().toString()
        }
    }

    private fun studySerialize() {
//        val fruit = Fruit("ping_gyy", 25)
//        val intent = Intent(this, SecondActivity::class.java)
//        intent.putExtra("serial_data", fruit)
//        startActivity(intent)
    }

    private fun studyWorkManager() {
        "MY gyy".showToast()
        R.string.app_name.showToast()
        btn_do_work.setOnClickListener {
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()
            WorkManager.getInstance(this).enqueue(request)
        }
    }

    private fun studyRoom() {
        val userDao = AppDatabase.getInstance(this).UserDao()
        val user1 = User("firstName1", "lastName1", 40)
        val user2 = User("firstName2", "lastName2", 20)
        room_adddata.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }
        room_upgradedata.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }
        room_deletedata.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("lastName1")
            }
        }
        room_querydata.setOnClickListener {
            thread {
                for (user in userDao.loadAllUser()) {
                    Log.e(TAG, "user ${user.firstName} ${user.lastName} ${user.age}")
                }
            }
        }
    }

    private fun studyViewModule() {
        lifecycle.addObserver(MyObserver(this.lifecycle))

        sp = getPreferences(Context.MODE_PRIVATE)
        val counterReserved = sp.getInt("counterReserved", 0)
        //2.2.0的最新代码
//        mainViewModule = ViewModelProvider(this)[MainViewModel::class.java]

        //2.2.0带有参数的初始化
        mainViewModule = ViewModelProvider(
            this,
            MainViewModelFactory(counterReserved)
        )[MainViewModel::class.java]

        //2.1.0中的代码，在2.2.0已经被废弃
        // mainViewModule = ViewModelProvider.of(this).get(MainViewModel::class.java)
        btn_plus.setOnClickListener {
            mainViewModule.plusOne()
        }
        btn_clear.setOnClickListener {
            mainViewModule.clear()
        }
        mainViewModule.counter.observe(this, Observer { counter ->
            Log.e(TAG, "studyViewModule: " + mainViewModule.a)
            textCounter.text = counter.toString()
        })

        btn_user.setOnClickListener {
            val userId = (1..1000).random().toString()
            mainViewModule.getUserId(userId)
        }
        mainViewModule.user.observe(this, Observer { user ->
            Log.e(TAG, "user: " + user.firstName)
            textCounter.text = user.firstName
        })

        mainViewModule.user.observeForever { user ->
            Log.e(TAG, "user-------: " + user.firstName)
            textCounter.text = user.firstName
        }
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume:-------------------:" + this.lifecycle.currentState)

    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("counterReserved", mainViewModule.counter.value ?: 0)
        }
    }

    private fun reFreshCounter() {
        textCounter.text = mainViewModule.counter.toString()
    }

    private fun studyService() {
        btn_startservice.setOnClickListener {
            //启动服务
//            val intent1 = Intent(this,MyService::class.java)
//            startService(intent1)

//            Log.e(
//                "MyIntentService",
//                "按钮Thread.currentThread().name = ${Thread.currentThread().name}"
//            )
//            val intent1 = Intent(this, MyIntentService()::class.java)
//            startService(intent1)


            startActivity<MainActivity>(this) {
                //传参
                putExtra("param1", "data")
            }


        }
        btn_stopservice.setOnClickListener {
            val intent1 = Intent(this, MyService::class.java)
            stopService(intent1)
        }
    }

    inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit) {
        val intent = Intent(this, T::class.java)
        intent.block()
        startActivity(intent)
    }

    inline fun <reified T> getType() = T::class.java

    private fun studyThread() {
        btn_thread.setOnClickListener {
            val msg = Message()
            msg.what = 1
            mHandler.sendMessage(msg)
        }
    }

    private fun studyMusic() {
        initMediaPlayer()
        btn_play.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        }
        btn_pause.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            }
        }
        btn_stop.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                //reset()是暂停的意思
                mediaPlayer.reset()
                initMediaPlayer()
            }
        }

        //播放视频
        //Uri.parse("android.resource://org.crazyit.ui/"+R.raw.msg)
        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        videoView.setVideoURI(uri)
        play.setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start()
            }
        }
        pause.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
            }
        }
        replay.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.resume()
            }
        }


    }

    private fun initMediaPlayer() {
        val assets = assets
        val fd = assets.openFd("music.mp3")
        mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        mediaPlayer.prepare()
    }

    private fun studyCamera() {
        //调用系统的拍照功能
        btn_picture.setOnClickListener {
            //externalCacheDir是应用关联缓存目录/sdcard/Android/data/<包名>/cache
            Log.e(TAG, "externalCacheDir:${externalCacheDir} ")
            outImageFile = File(externalCacheDir, "outImageFile.jpg")
            if (outImageFile.exists()) {
                outImageFile.delete()
            }
            outImageFile.createNewFile()
            imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                FileProvider.getUriForFile(this, "com.gyy.guoLinKt.provider", outImageFile)
            } else {
                Uri.fromFile(outImageFile);
            }
            //调用系统的拍照功能，action是固定的
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //设置图片输出的路径
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, iamgeCapture)
        }
        //调起系统的相册
        btn_album.setOnClickListener {
            val intent1 = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent1.addCategory(Intent.CATEGORY_OPENABLE)
            intent1.type = "image/*"
            startActivityForResult(intent1, fromAlbum)
        }
    }

    private fun studyNotification() {
        val notificationManger =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //通知渠道一旦创建就不能更改。更改无效
            val channel =
                NotificationChannel("normal", "用户看到的Normal", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManger.createNotificationChannel(channel)
            val channel2 =
                NotificationChannel("important", "用户看到的Normal", NotificationManager.IMPORTANCE_HIGH)
            notificationManger.createNotificationChannel(channel)
            notificationManger.createNotificationChannel(channel2)
        }
        //以上创建完就可以在设置中看到了
        btn_sendNotice.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, 0)
            val notification = NotificationCompat.Builder(this, "important")
//                .setContentText("这是长text这是长text这是长text这是长text这是长text这是长text这是长text这是长text这是长text这是长text这是长text这是长text这是长text这是长text")
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText("这是长text这是长text这是长text这是长text这是长text这是长text这是长text这是长text这是长text这是长text这是长text这是长text这是长text这是长text")
                )
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.big_image))
                )
                .setContentTitle("这是title")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.small_icon)
                .setContentIntent(pi)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
                .build()
            notificationManger.notify(1, notification)
        }
    }

    fun click(view: View) {
        startActivity(Intent(this, TestActivity::class.java))
        Log.i("TAG", "是否在前台: ${App.isForeground}")
    }

    /***
     * 这是另外一个工程的代码，主工程有一个可以共享的数据库，然后通过DatabaseProvider供其他app进行访问
     * 这个方法就是其他app通过CP来访问的代码。为了防止找不到，所以copy到了这里
     */
    private fun accessConPro() {
//var bookid: String? = null
//        btn_adddata.setOnClickListener {
//            val uri = Uri.parse("content://com.gyy.guoLinKt/book")
//            val values = contentValuesOf(
//                "names" to "names 225252",
//                "pages" to 525,
//                "price" to 0,
//                "author" to "gyy123"
//            )
//            val uri1 = contentResolver.insert(uri, values)
//            //注意：uri没有非空时可以这样写 uri.pathSegments[1]
//            //有非空判断时，要这样写，uri?.pathSegments?.get(1)
//            bookid = uri1?.pathSegments?.get(1)
//            Log.e("test1111", "btn_adddata bookid = $bookid")
//        }
//
//        btn_upgradedata.setOnClickListener {
//            val values1 = contentValuesOf("names" to "zhouzhnegliang")
//            val uri1 = Uri.parse("content://com.gyy.guoLinKt/book/$bookid")
//            contentResolver.update(uri1, values1, "id = ?", arrayOf(bookid))
//            Log.e("test1111", "btn_upgradedata uri1 = $uri1")
//        }
//        btn_deletedata.setOnClickListener {
//            val uri1 = Uri.parse("content://com.gyy.guoLinKt/book/$bookid")
//            contentResolver.delete(uri1, "id = ?", arrayOf(bookid))
//            Log.e("test1111", "btn_deletedata uri1 = $uri1")
//        }
//        btn_querydata.setOnClickListener {
//            val uri = Uri.parse("content://com.gyy.guoLinKt/book")
//            Log.e("test1111", "btn_querydata uri = $uri")
//            contentResolver.query(uri, null, null, null, null)?.apply {
//                while (moveToNext()) {
//                    val name = getString(getColumnIndex("names"))
//                    val pages = getInt(getColumnIndex("pages"))
//                    val price = getDouble(getColumnIndex("price"))
//                    val author = getString(getColumnIndex("author"))
//                    Log.e("test1111", "name = $name,pages = $pages,price = $price,author = $author")
//                }
//                close()
//            }
//
//        }

        btn_lazy.setOnClickListener {
            p
        }
    }

    private fun studyContentProvider() {
        btn_makecall.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf("android.permission.CALL_PHONE"),
                    0
                )
            } else {
                callPhone()
            }
        }

        //加载手机联系人的电话和姓名
        //请注意样式不要写错了
        contactsAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactsdata)
        listView_contacts.adapter = contactsAdapter
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf("android.permission.READ_CONTACTS"), 1)
        } else {
            readContacts()
        }
    }

    private fun readContacts() {
        //注意注意：小写的contentResolver相当于getContentResolver()
        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )?.apply {
            while (moveToNext()) {
                //获取联系人的名字
                val name =
                    getString(this.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                //获取联系人的电话
                val number =
                    getString(this.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactsdata.add("$name\n$number")
            }
            contactsAdapter.notifyDataSetChanged()
            close()
        }
    }

    private fun callPhone() {
        val intent = Intent(Intent.ACTION_CALL)
        intent.setData(Uri.parse("tel:10086"))
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            0 -> {
                if (!grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone()
                } else {
                    Toast.makeText(this, "用户拒绝了权限", Toast.LENGTH_SHORT).show()
                }
            }
            1 -> {
                if (!grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts()
                } else {
                    Toast.makeText(this, "用户拒绝了权限嘤嘤嘤", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun studyLoadFile() {
        val inputText1: String = loadContent()
        if (!inputText1.isEmpty()) {
            editText1.setText(inputText1)
            editText1.setSelection(inputText1.length)
            Toast.makeText(this, "文件读取成功$inputText1", Toast.LENGTH_SHORT).show()
        }
    }


    fun cvOf(vararg pairs: Pair<String, Any?>): ContentValues {
        val cv = ContentValues()
        for (pair in pairs) {
            //固定写法，first拿到的就是key
            val key = pair.first
            val value = pair.second
            when (value) {
                is Int -> cv.put(key, value)
                is Long -> cv.put(key, value)
                is Short -> cv.put(key, value)
                is Float -> cv.put(key, value)
                is Double -> cv.put(key, value)
                is Boolean -> cv.put(key, value)
                is String -> cv.put(key, value)
                is Byte -> cv.put(key, value)
                is ByteArray -> cv.put(key, value)
                null -> cv.putNull(null)
            }
        }
        return cv
    }

    fun cvOf1(vararg pairs: Pair<String, Any?>) = ContentValues().apply {
        for (pair in pairs) {
            //固定写法，first拿到的就是key
            val key = pair.first
            val value = pair.second
            when (value) {
                is Int -> put(key, value)
                is Long -> put(key, value)
                is Short -> put(key, value)
                is Float -> put(key, value)
                is Double -> put(key, value)
                is Boolean -> put(key, value)
                is String -> put(key, value)
                is Byte -> put(key, value)
                is ByteArray -> put(key, value)
                null -> putNull(null)
            }
        }
    }


    private fun studyDatabase() {
        val mydatabase = MyDataBaseHelper(this, "BookStore.db", 7)
        btn_creatDatabase.setOnClickListener {
            //第一次创建会自动调用onCreate
            mydatabase.writableDatabase
        }
        //"author text," +
        //"price real," +
        //"pages integer," +
        //"names text)"
        btn_adddata.setOnClickListener {
            val db = mydatabase.writableDatabase
            val value1 = ContentValues().apply {
                put("author", "gyy1")
                put("price", 100.01)
                put("pages", 51)
                put("names", "names 111")
            }

            val value3 = cvOf("names" to "names 225252", "pages" to 525)
            db.insert("Book", null, value3)

            val value4 = cvOf1("names" to "names 6666", "pages" to 555)
            db.insert("Book", null, value4)

            db.insert("Book", null, value1)
            val value2 = ContentValues().apply {
                put("author", "gyy2")
                put("price", 100.02)
                put("pages", 52)
                put("names", "names 222")
            }
            db.insert("Book", null, value2)
        }
        btn_upgradedata.setOnClickListener {
            val db = mydatabase.writableDatabase
            val value3 = ContentValues()
            value3.put("price", 50.01)
            //注意where筛选的时候要写 = ? SQLite语言写起来好累，得记语法
            db.update("Book", value3, "author = ?", arrayOf("gyy1"))
        }
        btn_deletedata.setOnClickListener {
            val db = mydatabase.writableDatabase
//            db.delete("Book", "pages = ?", arrayOf("51"))
            db.delete("Book", null, null)
        }
        btn_querydata.setOnClickListener {
            val db = mydatabase.writableDatabase
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    val index = cursor.getColumnIndex("author")
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val names = cursor.getString(cursor.getColumnIndex("names"))
                    val price = cursor.getFloat(cursor.getColumnIndex("price"))
                    Log.e(
                        TAG,
                        "index is $index,author is $author,pages is $pages,names is $names,price is $price"
                    )

                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        btn_replacedata.setOnClickListener {
            val db = mydatabase.writableDatabase
            db.beginTransaction()
            try {
                db.delete("Book", null, null)
//                if (true) {
//                    throw NullPointerException()
//                }
                val value2 = ContentValues().apply {
                    put("author", "gyy333")
                    put("price", 100.03)
                    put("pages", 53)
                    put("names", "names 333")
                }
                db.insert("Book", null, value2)
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction()
            }
        }
    }

    private fun loadContent(): String {
        val str = StringBuilder()
        val in1 = openFileInput("gyyfile")
        val reader = BufferedReader(InputStreamReader(in1))
        reader.use {
            //forEachLine是kt的内置函数，会将读取到的内容回调到lambda表达式中，注意it是读取的String
            reader.forEachLine {
                str.append(it)
            }
        }
        return str.toString()
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

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
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
            iamgeCapture -> if (resultCode == Activity.RESULT_OK) {
                val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
                imageView1.setImageBitmap(bitmap)
            }
            fromAlbum -> if (resultCode == Activity.RESULT_OK && data != null) {
                data.data?.let { uri ->
                    val bitmap = getBitmapFromUri(uri)
                    imageView1.setImageBitmap(bitmap)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun getBitmapFromUri(uri: Uri) = contentResolver.openFileDescriptor(uri, "r")?.use {
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
    }


    override fun onDestroy() {
        super.onDestroy()
//        ActivityCollector.finishAll()
        unregisterReceiver(brocast)

        //在程序销毁的时候获取编辑的内容，并且保存
        val inputText = editText1.text.toString()
        saveEditContent(inputText)

        //释放视频资源
        videoView.suspend()
    }

    private fun saveEditContent(inputText: String) {
        val out = openFileOutput("gyyfile", Context.MODE_PRIVATE)
        val writer = BufferedWriter(OutputStreamWriter(out))
        //use会自动将外层的流关闭
        writer.use {
            it.write(inputText)
        }
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
