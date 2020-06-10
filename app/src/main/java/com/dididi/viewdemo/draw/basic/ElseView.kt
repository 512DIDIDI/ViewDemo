package com.dididi.viewdemo.draw.basic

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.dididi.viewdemo.R


/**
 * @author dididi(yechao)
 * @since 09/06/2020
 * @describe 绘制bitmap和文字
 */

class ElseView : View {

    private val bitmap:Bitmap by lazy {
        BitmapFactory.decodeResource(context.resources,
            R.mipmap.ic_launcher
        )
    }

    private val paint = Paint()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(bitmap,300f,300f,paint)
        paint.textSize = 100f
        //text是左下角为起始点
        canvas?.drawText("hello world",200f,200f,paint)
    }

}