package com.dididi.viewdemo.view.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View


/**
 * @author dididi(yechao)
 * @since 11/06/2020
 * @describe Paint属性介绍
 */

class PaintView :View{
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val paint = Paint()

    private val sevenPath = Path().apply {
        moveTo(100f,200f)
        rLineTo(100f,0f)
        rLineTo(-50f,100f)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.also {
            paint.apply {
                //抗锯齿 默认不开启
                isAntiAlias = true
                //设置图形风格 默认FILL [Paint.Style]
                style = Paint.Style.STROKE
                //设置绘画线的宽度（设置成0也还是能画出来，只是后面计算的时候无法被放大缩小等操作）
                strokeWidth = 0f
                strokeWidth = 20f
                //设置线头形状 默认BUTT [Paint.Cap]
                strokeCap = Paint.Cap.ROUND
                it.drawLine(100f,100f,200f,100f,this)
                strokeCap = Paint.Cap.BUTT
                //设置拐角形状 默认为MITER [Paint.Join]
                strokeJoin = Paint.Join.ROUND
                it.drawPath(sevenPath,this)
            }
        }
    }
}

