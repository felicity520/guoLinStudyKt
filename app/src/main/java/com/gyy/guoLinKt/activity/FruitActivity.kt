package com.gyy.guoLinKt.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.gyy.guoLinKt.R
import com.gyy.guoLinKt.activity.App.Companion.context
import com.gyy.guoLinKt.kotlin.showSnackerbarInt
import com.gyy.guoLinKt.kotlin.showToast
import kotlinx.android.synthetic.main.activity_fruit.*
import kotlinx.android.synthetic.main.activity_second.*

/**
 *将这个Activity作为水果详情页的Activity
 */
class FruitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)

        val fruitName = intent.getStringExtra(SecondActivity.FRUIT_NAME) ?: ""
        val fruitImage = intent.getIntExtra(SecondActivity.FRUIT_IMAGE_ID, 0)
        //        加上这句toolbar才会显示出来,加载id
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsingToolbar.title = fruitName
        Glide.with(context).load(fruitImage).into(fruitImageView)
        fruitContentText.text = createFruitContent(fruitName)

        commentFloatButton.setOnClickListener {
            it.showSnackerbarInt("我是小吃条的字符串", "action的字符串") {
                "my".showToast()
            }
        }
    }

    private fun createFruitContent(fruitName: String?) = fruitName?.repeat(500)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}
