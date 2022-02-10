package com.lihan.androidmatome.activity.customview.ui

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.util.AttributeSet
import android.util.Log
import androidx.core.content.ContextCompat
import com.lihan.androidmatome.R
import com.lihan.androidmatome.activity.customview.model.Item
import com.lihan.androidmatome.activity.retrofitapi.ui.UserViewModel

class PersonImageView : androidx.appcompat.widget.AppCompatImageView {

    constructor(context : Context) : super(context)
    constructor(context : Context,attributeSet: AttributeSet?) : super(context,attributeSet)

    var data = arrayListOf<Item>()
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (data.size != 0){
            useShader()
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
            val itemPaint = Paint().apply {
                style = Paint.Style.FILL
            }

        canvas?.let {
            it.apply {
                var startAngle = 0f
                //sorted
                val result = itemHashMap.toList().sortedBy { (_,value) -> value }.toMap()
                result.entries.forEach {
                    itemPaint.color = colors[it.key]!!
                    val percent =  ((it.value.toDouble() / total.toDouble()) * 100).toFloat()
                    var sweepAngle =  percent
                    drawArc(0f,0f,300f,300f,
                        startAngle,
                        360 * (sweepAngle/100),
                        true,itemPaint)
                    startAngle += 360 * (sweepAngle/100)

                }

                }
            }
        }
    }

    private fun useShader() {
        //        val bitmap = BitmapFactory.decodeResource(resources,R.drawable.shinodayuu)
        //        val shader = BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
        //        val paint = Paint().apply {
        //            style = Paint.Style.FILL_AND_STROKE
        //            color = Color.GREEN
        //            setShader(shader)
        //        }
    }
    private fun toDrawArc(){

//        val paint1 = Paint().apply {
//            style = Paint.Style.FILL
//            strokeWidth = 10f
//            color = Color.YELLOW
//        }
//        val paint2 = Paint().apply {
//            style = Paint.Style.FILL
//            strokeWidth = 10f
//            color = Color.RED
//        }
        //                drawArc(0f,0f,300f,300f,0f,90f,true,paint1)
//                drawArc(0f,0f,300f,300f,90f,90f,true,paint2)
//                paint1.color = Color.BLUE
//                paint2.color = Color.LTGRAY
//                drawArc(0f,0f,300f,300f,180f,90f,true,paint1)
//                drawArc(0f,0f,300f,300f,270f,90f,true,paint2)

//        val colors = arrayListOf<Int>(
//            Color.RED,Color.GREEN,Color.BLUE,Color.MAGENTA,Color.YELLOW
//        )
//                for (i in 1..5){
//                    paint1.color = colors.random()
//                    drawArc(0f,0f,300f,300f,0f,(i*(360/5)).toFloat(),true,paint1)
//                }
//                drawArc(0f,0f,300f,300f,90f,-180f,true,paint1)
//                drawArc(0f,0f,300f,300f,0f,90f,true,paint2)
//                paint1.color = Color.BLUE
//                paint2.color = Color.LTGRAY
//                drawArc(0f,0f,300f,300f,90f,180f,true,paint1)
//                drawArc(0f,0f,300f,300f,180f,90f,true,paint2)

    }


    fun Bitmap.getCircledBitmap(): Bitmap {
        val output = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val paint = Paint()
        val rect = Rect(0, 0, this.width, this.height)
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        canvas.drawCircle(this.width / 2f, this.height /2f, this.width / 2f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(this, rect, rect, paint)
        return output
    }



}