package com.gyy.guoLinKt.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyy.guoLinKt.R
import com.gyy.guoLinKt.bean.Msg

class MsgAdapter(val msglist: List<Msg>) : RecyclerView.Adapter<MsgViewHolder>() {

    /***
     * getItemViewType这个方法，多View适配时用这个来判断要加载哪个view.否则默认返回0，所以造成只加载一个布局
     */
    override fun getItemViewType(position: Int): Int {
        val msg = msglist[position]
        return msg.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == Msg.TYPE_SEND) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item, parent, false)
            LeftViewHolder(view)

        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item, parent, false)
            RightViewHolder(view)
        }


//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgViewHolder {
//        Log.e("onCreateViewHolder", "viewType is: $viewType")
//        if (viewType == Msg.TYPE_SEND) {
//            val view =
//                LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item, parent, false)
//            return LeftViewHolder(view)
//
//        } else {
//            val view =
//                LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item, parent, false)
//            return RightViewHolder(view)
//        }
//    }


    override fun getItemCount() = msglist.size

    override fun onBindViewHolder(holder: MsgViewHolder, position: Int) {
        //when语言允许传入任意类型的参数
        when (holder) {
            is LeftViewHolder -> holder.lefttext.setText(msglist[position].content)
            is RightViewHolder -> holder.righttext.setText(msglist[position].content)
        }.exhaustive
    }

}