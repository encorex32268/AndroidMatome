package com.lihan.androidmatome.mainactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lihan.androidmatome.R

class MainAdapter(var titles : ArrayList<String>) : RecyclerView.Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_item,parent,false))

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.itemView.tag = position
        holder.bindTo(titles[position])
    }

    override fun getItemCount() = titles.size
}