package com.gyy.guoLinKt.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.gyy.guoLinKt.R
import com.gyy.guoLinKt.bean.Fruit

class FruitAdapter(val activity: Activity, val resourceId: Int, val data: List<Fruit>) :
    ArrayAdapter<Fruit>(
        activity, resourceId,
        data
    ) {


    inner class ViewHolder(val iamgeView: ImageView, val textView: TextView)

    override fun getView(
        position: Int, convertView: View?,
        parent: ViewGroup
    ): View {
        val view: View
        val viewHolder: ViewHolder
        //convertView会将之前加载好的布局进行缓存
        if (convertView == null) {
            view = LayoutInflater.from(activity).inflate(resourceId, parent, false)
            val iamgeView: ImageView = view.findViewById(R.id.fruitImage)
            val textView: TextView = view.findViewById(R.id.fruitText)
            viewHolder = ViewHolder(iamgeView, textView)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val fruit = getItem(position)
        if (fruit != null) {
            viewHolder.iamgeView.setImageResource(fruit.fruitImage)
            viewHolder.textView.setText(fruit.name)
        }
        return view
    }

}


