package com.dididi.viewdemo.view.text

import android.content.Context
import android.graphics.*
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import java.util.*


/**
 * @author dididi(yechao)
 * @since 15/06/2020
 * @describe
 */

class DrawTextView : View {
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
        textSize = 50f
    }
    private val text = "DIDIDI STUDIO"
    private val textPath = Path().apply {
        moveTo(50f, 100f)
        quadTo(200f, 200f, 400f, 100f)
    }

    private val pathPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 0f
    }

    private val longText =
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry."

    /**
     * 用来绘制多行文字的layout，它会自动换行 [StaticLayout]
     * @param source       文字
     * @param paint        需要转为 [TextPaint]
     * @param width        单行文字的最大宽度 也就是什么时候要换行
     * @param align        文字对齐方式
     * @param spacingmult  行间距倍数
     * @param spacingadd   行间距添加值
     * @param includepad   是否在文字上下添加额外控件
     */
    private val staticLayout1 =
        StaticLayout(
            longText,
            TextPaint(textPaint),
            600,
            Layout.Alignment.ALIGN_NORMAL,
            1f,
            0f,
            true
        )

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.also {
            textPaint.apply {
                it.drawPath(textPath, pathPaint)
                //x是最左侧文字的位置+文字间隙 y是基准线(baseline)的位置
                it.drawText(text, 50f, 50f, this)
                //绘制文字在路径上 （最好使用曲线路径 直线文字效果不好）
                it.drawTextOnPath(text, textPath, 0f, 0f, this)
                it.save()
                it.translate(1000f,50f)
                staticLayout1.draw(it)
                it.restore()
                //设置字体 或者使用 Typeface.createFromAsset() 来使用自定义字体库
                typeface = Typeface.SERIF
                it.drawText(text,50f,200f,this)
                typeface = Typeface.DEFAULT
                //设置是否伪粗体，不是通过weight改变，而是秒粗，也就是字体长度没有改变
                isFakeBoldText = true
                it.drawText(text,50f,250f,this)
                isFakeBoldText = false
                //是否增加删除线
                isStrikeThruText = true
                it.drawText(text,50f,300f,this)
                isStrikeThruText = false
                //是否增加下划线
                isUnderlineText = true
                it.drawText(text,50f,350f,this)
                isUnderlineText = false
                //设置字体倾斜度
                textSkewX = -0.5f
                it.drawText(text,50f,400f,this)
                textSkewX = 0f
                //设置文字横向放缩
                textScaleX = 0.5f
                it.drawText(text,50f,450f,this)
                textScaleX = 1f
                //设置文字间隙，默认是0
                letterSpacing = 0.1f
                it.drawText(text,50f,500f,this)
                letterSpacing = 0f
                //文字对齐方式
                textAlign = Paint.Align.RIGHT
                it.drawText(text,50f,550f,this)
                textAlign = Paint.Align.LEFT
                //设置文字地域 (默认使用系统地域)
                textLocale = Locale.ITALY
                it.drawText(text,50f,600f,this)
                textLocale = Locale.CHINA
            }
        }
    }
}