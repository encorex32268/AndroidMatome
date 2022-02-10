package com.lihan.androidmatome.activity.customview.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.compose.ui.graphics.toArgb

class MyView  : View{
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint().apply {
            style = Paint.Style.STROKE
            color = Color.RED
            strokeWidth = 2f
            isAntiAlias = true
            textSize = 16f
        }
        val rect = RectF().apply {
           left = 0f
           right = 100f
           top = 0f
           bottom = 100f
        }
        canvas?.let{
            it.apply {
                drawCircle(300f,300f,200f,paint)
                x = 200f
                y = 200f
                drawRoundRect(rect,100f,100f,paint)

            }
        }
//        canvas?.drawColor(Color.Blue.toArgb())

    }



}