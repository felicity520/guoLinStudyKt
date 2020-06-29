package com.gyy.guoLinKt.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gyy.guoLinKt.R
import com.gyy.guoLinKt.activity.NewsContentActivity
import com.gyy.guoLinKt.adapter.MsgViewHolder
import com.gyy.guoLinKt.adapter.RvFruitAdapter
import com.gyy.guoLinKt.bean.Msg
import com.gyy.guoLinKt.bean.News
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.news_title_frag.*

class NewsTitleFragMent : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_title_frag, container, false)
    }

    private var isTwoPane = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isTwoPane = activity?.findViewById<View>(R.id.newsContentLayout) != null

        val layoutManger = LinearLayoutManager(activity)
        newsTitleRecyclerView.layoutManager = layoutManger
        val newsAdapter = NewsAdapter(getNews())
        newsTitleRecyclerView.adapter = newsAdapter
    }

    fun getNews(): List<News> {
        val list = ArrayList<News>()
        for (i in 0..50) {
            val news = News("This is news title $i", getRandomStr("This is news title $i."))
            list.add(news)
        }
        return list
    }

    fun getRandomStr(str: String): String {
        val n = (1..20).random()
        Log.e("gyytest", "n is $n")
        val stringbuilder = StringBuilder()
        repeat(n) {
            stringbuilder.append(str)
        }
        Log.e("gyytest", "stringbuilder.toString() is $stringbuilder.toString()")

        return stringbuilder.toString()
    }

    inner class NewsAdapter(val newslist: List<News>) :
        RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val newTitle: TextView = view.findViewById(R.id.newTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
            val viewholder = ViewHolder(view)
            viewholder.newTitle.setOnClickListener {
                if (isTwoPane) {
//双页模式
                    //将空间的id实例化成一个Fragment类
                    val contentfragment = newsContentFrag as NewsContentFragment
                    contentfragment.refresh(
                        newslist[viewholder.adapterPosition].title,
                        newslist[viewholder.adapterPosition].content
                    )
                } else {
//单页模式
                    NewsContentActivity.actionStart(
                        parent.context,
                        newslist[viewholder.adapterPosition].title,
                        newslist[viewholder.adapterPosition].content
                    )
                }
            }
            return viewholder
        }

        override fun getItemCount() = newslist.size


        /***
         * 在每个子项被滚动到屏幕上的时候调用
         */
        override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
            holder.newTitle.setText(newslist[position].title)
        }
    }


}