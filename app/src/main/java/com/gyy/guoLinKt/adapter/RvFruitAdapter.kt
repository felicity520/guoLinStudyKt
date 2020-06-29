package com.gyy.guoLinKt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.gyy.guoLinKt.R
import com.gyy.guoLinKt.bean.Fruit

class RvFruitAdapter(val fruitlist: List<Fruit>) :
    RecyclerView.Adapter<RvFruitAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitText)
    }

    /***
     *将布局加载进来。返回类型是RecyclerView.ViewHolder,这里用parent.context代替当前的上下文
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false)
        val viewholder = ViewHolder(view)
        viewholder.itemView.setOnClickListener {
            Toast.makeText(
                parent.context,
                "you click fruit name ${fruitlist[viewholder.adapterPosition].name}",
                Toast.LENGTH_SHORT
            ).show()
        }

        viewholder.fruitImage.setOnClickListener {
            Toast.makeText(
                parent.context,
                "you click fruit image ${fruitlist[viewholder.adapterPosition].name}",
                Toast.LENGTH_SHORT
            ).show()
        }

        return viewholder

    }

    override fun getItemCount() = fruitlist.size

    /***
     * 在每个子项被滚动到屏幕上的时候调用
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fruitImage.setImageResource(fruitlist[position].fruitImage)
        holder.fruitName.setText(fruitlist[position].name)
    }


}