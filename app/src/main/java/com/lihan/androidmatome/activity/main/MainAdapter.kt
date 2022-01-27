package com.lihan.androidmatome.activity.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lihan.androidmatome.R
import com.lihan.androidmatome.model.Function

class MainAdapter(var titles : ArrayList<Function>) : RecyclerView.Adapter<MainViewHolder>() {

    interface MainItemClickListener {
        fun itemClick(function: Function)
    }

    var mainItemClickListener : MainItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_item,parent,false))

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val functionClassName = titles[position].toFunctionClass.simpleName
        holder.itemView.tag = position
        holder.bindTo(functionClassName)
        holder.itemView.setOnClickListener {
            if (mainItemClickListener!=null){
                mainItemClickListener!!.itemClick(titles[position])
            }
        }
    }

    override fun getItemCount() = titles.size
}