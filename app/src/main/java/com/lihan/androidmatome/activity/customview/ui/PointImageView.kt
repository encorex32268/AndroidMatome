package com.lihan.androidmatome.activity.customview.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import com.lihan.androidmatome.activity.customview.model.Item

class PointImageView : androidx.appcompat.widget.AppCompatImageView{

    constructor(context : Context) : super(context)
    constructor(context : Context,attributeSet: AttributeSet) : super(context,attributeSet)

    var data = arrayListOf<Item>()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (data.size != 0){
            val paint = Paint().apply {
                style = Paint.Style.STROKE
                strokeCap = Paint.Cap.ROUND
                strokeWidth = 20f
            }
            val textPaint = Paint().apply {
                textSize = 20f
            }

            canvas?.let {
                it.apply {

                    val itemHashMap = HashMap<String,Int>()
                    val colors = HashMap<String,Int>()
                    data.forEach {
                        if (itemHashMap.containsKey(it.name)){
                            var itemValue = itemHashMap[it.name]!!
                            itemValue += it.price
                            itemHashMap[it.name] = itemValue
                        }else{
                            itemHashMap[it.name] = it.price
                            colors[it.name] = it.color
                        }
                    }
                    var total  = 0
                    itemHashMap.entries.forEach {
                        total+=it.value
                    }
                    var  x = 50f
                    var  y = 50f
                    itemHashMap.entries.forEach {
                        paint.color = colors[it.key]!!
                        drawPoint(x,y,paint)
                        drawText("${it.key}  $ ${it.value}",x+20f,y+5f,textPaint)
                        y+=50f
                    }

                }
            }
        }



    }

}