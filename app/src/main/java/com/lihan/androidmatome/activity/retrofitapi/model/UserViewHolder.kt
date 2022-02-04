package com.lihan.androidmatome.activity.retrofitapi.model

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lihan.androidmatome.databinding.RetrofitItemBinding

class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val binding = RetrofitItemBinding.bind(itemView)
    fun bindTo(user : User){
        binding.apply {
            retrofitUsername.text = user.name
            retrofitEmail.text = user.email
        }
    }


}