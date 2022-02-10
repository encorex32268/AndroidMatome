package com.lihan.androidmatome.activity.customview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lihan.androidmatome.R
import com.lihan.androidmatome.activity.customview.model.Item
import com.lihan.androidmatome.databinding.ActivityCustomViewBinding

class CustomViewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCustomViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            personImageView.data = dumpData()
            pointImageView.data =dumpData()
        }
    }

    private fun dumpData(): ArrayList<Item> {
        return arrayListOf(
            Item("吃飯",100, Color.RED),
            Item("生活用品",500,Color.GREEN),
            Item("吃飯",250,Color.RED),
            Item("同事聚會",2000,Color.BLUE),
            Item("同事聚會",1000,Color.BLUE),
            Item("衣服",2000,Color.parseColor("#cc33ff")),
            Item("生活用品",500,Color.GREEN),
            Item("衣服",5000,Color.parseColor("#cc33ff")),
            Item("捷運車錢",30,Color.YELLOW),
            Item("捷運車錢",25,Color.YELLOW),
            Item("捷運車錢",45,Color.YELLOW),
            Item("捷運車錢",60,Color.YELLOW),
            Item("手機費",499,Color.BLACK),
            Item("房租",15000,Color.parseColor("#FFA500")),
            Item("油錢",200,Color.MAGENTA),
            Item("捷運車錢",50,Color.YELLOW),
            Item("捷運車錢",40,Color.YELLOW),
            Item("捷運車錢",50,Color.YELLOW),
        )
    }
}