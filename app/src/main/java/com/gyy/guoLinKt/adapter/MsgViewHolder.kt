package com.gyy.guoLinKt.adapter

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gyy.guoLinKt.R

sealed class MsgViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class LeftViewHolder(itemView: View) : MsgViewHolder(itemView) {
    val lefttext: TextView = itemView.findViewById(R.id.left_msg_text)
}

class RightViewHolder(itemView: View) : MsgViewHolder(itemView) {
    val righttext: TextView = itemView.findViewById(R.id.right_msg_text)
}

val <T> T.exhaustive: T
    get() = this