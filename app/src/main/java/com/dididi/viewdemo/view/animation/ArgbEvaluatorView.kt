package com.dididi.viewdemo.view.animation

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min


/**
 * @author dididi(yechao)
 * @since 18/06/2020
 * @describe
 */

class ArgbEvaluatorView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = 0xffff00
    }

    private var color = 0

    private val animator = ObjectAnimator.ofInt(this, "color", Color.parseColor("#ffff0000"), Color.parseColor("#ff00ff00")).apply {
        setEvaluator(ArgbEvaluator())
        duration = 1000
        repeatCount = ValueAnimator.INFINITE
        repeatMode = ValueAnimator.REVERSE
    }

    fun setColor(color: Int) {
        this.color = color
        invalidate()
    }

    fun getColor() = this.color

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
        paint.color = this.color
        canvas?.drawCircle(
            width / 2.toFloat(),
            height / 2.toFloat(),
            min(width / 2, height / 2).toFloat(),
            paint
        )
    }
}