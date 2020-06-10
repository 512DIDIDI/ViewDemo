package com.dididi.viewdemo.draw.basic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View


/**
 * @author dididi(yechao)
 * @since 09/06/2020
 * @describe 直方图
 */

class HistogramView : View {

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    /**
     * 直方图path
     */
    private val histogramPath = Path().apply {
        moveTo(550f, 500f)
        addRect(550f, 300f, 600f, 500f, Path.Direction.CW)
        moveTo(650f, 500f)
        addRect(650f, 400f, 700f, 500f, Path.Direction.CW)
        moveTo(750f, 500f)
        addRect(750f, 350f, 800f, 500f, Path.Direction.CCW)
    }

    /**
     * 坐标系路径
     */
    private val coordinatesPath = Path().apply {
        moveTo(500f, 500f)
        rLineTo(0f, -300f)
        rLineTo(-20f, 20f)
        moveTo(500f, 200f)
        rLineTo(20f, 20f)
        moveTo(500f, 500f)
        rLineTo(400f, 0f)
        rLineTo(-20f, -20f)
        moveTo(900f, 500f)
        rLineTo(-20f, 20f)
    }

    /**
     * onDraw在每次viewpager切换时会调用
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.GRAY)
        //paint设置放到onDraw中
        paint.style = Paint.Style.FILL
        paint.color = Color.RED
        canvas?.drawPath(histogramPath, paint)
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        canvas?.drawPath(coordinatesPath, paint)
        paint.strokeWidth = 1f
        paint.style = Paint.Style.FILL
        paint.color = Color.BLACK
        paint.textSize = 20f
        canvas?.drawText("测试1", 550f, 530f, paint)
        canvas?.drawText("测试2", 650f, 530f, paint)
        canvas?.drawText("测试3", 750f, 530f, paint)
    }
}
