package com.dididi.viewdemo.view.text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.StaticLayout
import android.util.AttributeSet
import android.view.View


/**
 * @author dididi(yechao)
 * @since 15/06/2020
 * @describe 测量文字尺寸
 */

class MeasureTextView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val textPaint = Paint().apply {
        style = Paint.Style.FILL
        strokeWidth = 20f
        textSize = 40f
    }

    private val pathPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 0f
    }

    private val text = "DIDIDI STUDIO"
    private var textBounds:Rect = Rect(0,0,0,0)

    /**
     * measureText 获取字符串宽度
     */
    private val textWidth = textPaint.measureText(text)
    private val charWidth = FloatArray(text.length)
    private val breakWidth = floatArrayOf()
    private val breakCount = textPaint.breakText(text,0,text.length,true,100f,breakWidth)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.also {
            textPaint.apply {
                it.drawText(text, 50f, 50f, this)
                //fontSpacing 获取间距(baseline之间的距离)
                it.drawText(text, 50f, 50f + this.fontSpacing, this)
                //fontMetrics 获取文字的各种线尺寸
                //top/bottom     图形的顶部和底部
                //ascent/descent 一般文字的顶部和底部
                //baseline       基准线
                //leading        top-bottom 行的额外间距
                it.drawText(
                    "top:${fontMetrics.top} / ascent:${fontMetrics.ascent} / descent:${fontMetrics.descent} / bottom:${fontMetrics.bottom} / leading:${fontMetrics.leading} / fontSpacing:${fontSpacing}",
                    50f,
                    50f + this.fontSpacing * 2,
                    this
                )
                it.drawText(text,50f,50f+this.fontSpacing*3,this)
                //getTextBounds 获取文字显示范围，将其写入到rect中
                getTextBounds(text,0,text.length,textBounds)
                textBounds.left += 50
                textBounds.right += 50
                textBounds.top += (50 + this.fontSpacing.toInt()*3)
                textBounds.bottom += (50 + this.fontSpacing.toInt()*3)
                it.drawRect(textBounds,pathPaint)
                it.drawText(text,50f,50f+this.fontSpacing*4,this)
                it.drawLine(50f,50f+this.fontSpacing*4,50f+textWidth,50f+this.fontSpacing*4,pathPaint)
                //获取字符串内每个字符串的宽度
                getTextWidths(text,charWidth)
                it.drawText(text,0,breakCount,50f,50f+this.fontSpacing*5,this)
            }
        }
    }

}