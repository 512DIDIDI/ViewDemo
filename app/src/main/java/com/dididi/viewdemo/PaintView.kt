package com.dididi.viewdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.jar.Attributes


/**
 * @author dididi(yechao)
 * @since 27/05/2020
 * @describe
 */

class PaintView :View{
    constructor(context:Context):super(context)
    constructor(context: Context,attrs:AttributeSet):super(context,attrs)
    constructor(context: Context,attrs:AttributeSet,defStyleAttr: Int):super(context,attrs,defStyleAttr)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        //这个坐标不是屏幕坐标 应该是View内的坐标
        canvas?.drawCircle(1100f,650f,200f,paint)
        canvas?.drawRect(900f,450f,1300f,850f,paint)
        paint.strokeCap = Paint.Cap.SQUARE
        paint.strokeWidth = 50f
        canvas?.drawPoint(1100f,650f,paint)
        val points = floatArrayOf(0f, 0f, 800f, 650f, 1100f, 350f, 1400f, 650f, 1100f, 950f, 150f, 50f, 150f, 100f)
        canvas?.drawPoints(points,2/*跳过前两个数*/,8/*共绘制四个点*/,paint)
    }
}