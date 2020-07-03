package com.dididi.viewdemo.view.canvas

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import com.dididi.viewdemo.R


/**
 * @author dididi(yechao)
 * @since 16/06/2020
 * @describe 翻页效果
 */

class FlipView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val paint = Paint()
    private val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.maps)
    private val camera = Camera()
    private var degree: Float = 0f
    private val animator = ObjectAnimator.ofInt(this, "degree", 0, 180).apply {
        duration = 2500
        interpolator = LinearInterpolator()
        repeatCount = ValueAnimator.INFINITE
        repeatMode = ValueAnimator.REVERSE
    }

    private val bitmapHeight = bitmap.height
    private val bitmapWidth = bitmap.width


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.end()
    }

    fun setDegree(degree: Int) {
        this.degree = degree.toFloat()
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val centerX = width / 2
        val centerY = height / 2
        val x = centerX - bitmapWidth / 2
        val y = centerY - bitmapHeight / 2
        canvas?.also {
            paint.apply {
                //绘制上半部分，不动的bitmap
                it.save()
                it.clipRect(x,y,x+bitmapWidth,y+bitmapHeight/2)
                it.drawBitmap(bitmap,x.toFloat(),y.toFloat(),this)
                it.restore()

                //绘制下半部分，旋转的bitmap
                it.save()
                camera.save()
                camera.rotateX(degree)
                it.translate(centerX.toFloat(),centerY.toFloat())
                camera.applyToCanvas(it)
                it.translate(-centerX.toFloat(),-centerY.toFloat())
                camera.restore()
                it.clipRect(x,y+bitmapHeight/2,x+bitmapWidth,y+bitmapHeight)
                it.drawBitmap(bitmap,x.toFloat(),y.toFloat(),this)
                it.restore()
            }
        }
    }
}

