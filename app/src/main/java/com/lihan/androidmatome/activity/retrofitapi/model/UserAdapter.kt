package com.lihan.androidmatome.activity.retrofitapi.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lihan.androidmatome.R

class UserAdapter(var users : List<User>) : RecyclerView.Adapter<UserViewHolder>() {

    var retrofitItemClick : RetrofitItemClick?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
       return UserViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.retrofit_item,parent,false)
       )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemView.tag = position
        holder.bindTo(users[position])
        holder.itemView.setOnClickListener {
            retrofitItemClick?.itemClick(users[position])
        }
    }

    override fun getItemCount() = users.size
}