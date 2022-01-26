package com.lihan.androidmatome.mainactivity

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lihan.androidmatome.databinding.MainItemBinding

class MainViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private var binding = MainItemBinding.bind(itemView)
    fun bindTo(title : String){
        binding.mainText.text = title
    }

}