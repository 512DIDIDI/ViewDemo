package com.dididi.viewdemo.view.animation

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import com.dididi.uiextlib.ext.log


/**
 * @author dididi(yechao)
 * @since 19/06/2020
 * @describe 两点之间移动 主要认识 [ObjectAnimator.ofObject] 和自定义 [TypeEvaluator] 实现自定义动画
 * 流程：
 * 1. 实现 [TypeEvaluator] 接口
 * 2. [ObjectAnimator.ofObject]传入自定义接口
 */

class PointMoveView : View {

    constructor(context: Context) : this(context,null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val paint = Paint().apply {
        color = Color.RED
    }

    private var position:PointF = PointF(0f,0f)

    private val animator =
        ObjectAnimator.ofObject(
            this,
            "position",
            PointFEvaluator(),
            PointF(0f, 0f),
            PointF(this.width.toFloat(), this.height.toFloat())
        ).apply {
            duration = 1000
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
        }

    fun setPosition(position:PointF){
        this.position = position
        invalidate()
    }

    fun getPosition() = position

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.also {
            it.drawCircle(position.x,position.y,20f,paint)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.end()
    }

    private class PointFEvaluator : TypeEvaluator<PointF> {
        /**
         * 计算公式 result = startValue + fraction * (endValue - startValue)
         */
        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
            val x = startValue.x + fraction * (endValue.x - startValue.x)
            val y = startValue.y + fraction * (endValue.y - startValue.y)
            return PointF(x, y)
        }
    }
}

