package com.dididi.viewdemo.view.animation

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator


/**
 * @author dididi(yechao)
 * @since 17/06/2020
 * @describe 加载进度view
 * 创建属性动画流程：
 * 1. 创建属性的getXXX/setXXX方法
 * 2. 创建ObjectAnimator实例
 * 3. start()开启动画
 */

class ProgressView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val pathPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 20f
        setARGB(100, 255, 0, 0)
    }
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        strokeWidth = 2f
        textSize = 35f
        color = Color.BLACK
    }

    private var progress = 0f

    //创建ObjectAnimator对象
    private val animator = ObjectAnimator.ofInt(this, "progress", 0, 95).apply {
        //设置动画时长(单位ms)
        duration = 2000L
        //设置动画速率控制器 [Interceptor]
        interpolator = LinearInterpolator()
        repeatMode = ValueAnimator.REVERSE
        repeatCount = ValueAnimator.INFINITE
    }

    //自定义控件需要添加 setXXX/getXXX 方法
    private fun setProgress(progress: Int) {
        this.progress = progress.toFloat()
        invalidate()
    }

    private fun getProgress() = progress

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.end()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val centerX = width / 2.toFloat()
        val centerY = width / 2.toFloat()
        val text = "$progress%"
        val textWidth = textPaint.measureText(text)
        val textHeight = textPaint.fontSpacing
        canvas?.also {
            pathPaint.apply {
                it.drawArc(
                    this.strokeWidth,
                    this.strokeWidth,
                    width.toFloat() - this.strokeWidth,
                    height.toFloat() - this.strokeWidth,
                    0f,
                    progress * 3.6f,
                    false,
                    this
                )
                it.drawText(
                    text,
                    0,
                    text.length,
                    centerX - textWidth / 2,
                    centerY + textHeight / 4,
                    textPaint
                )
            }
        }
    }
}

