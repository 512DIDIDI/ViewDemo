package com.dididi.viewdemo.view.paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.dididi.viewdemo.R


/**
 * @author dididi(yechao)
 * @since 11/06/2020
 * @describe 颜色过滤器 [Paint.setColorFilter]
 * [LightingColorFilter] [PorterDuffColorFilter] [ColorMatrixColorFilter]
 */

open class FilterView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val batman =
        BitmapFactory.decodeResource(context.resources, R.mipmap.batman)

    private val batmanShader = BitmapShader(batman, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    /**
     * RGB(光的三原色)颜色过滤器
     * 计算公式如下：
     * R' = R * mul.R / 0xff + add.R
     * G' = G * mul.G / 0xff + add.G
     * B' = B * mul.B / 0xff + add.B
     * 例如此例中：
     * R' = R * 0x00 / 0xff + 0x00 = 0
     * G' = G * 0xff / 0xff + 0x00 = G
     * B' = B * 0xff / 0xff + 0x00 = B
     * R为0，因此就相当于移除了红色
     */
    private val redColorFilter = LightingColorFilter(0x00ffff, 0x000000)

    /**
     * 此例中：
     * R' = R * 0xff / 0xff + 0x00 = R
     * G' = G * 0xff / 0xff + 0x30 = G + 0x30
     * B' = B * 0xff / 0xff + 0x00 = B
     */
    private val moreWarmFilter = LightingColorFilter(0xffffff,0x003000)

    /**
     * PorterDuff颜色过滤器
     * 等同于使用 [ComposeShader] 只是是以颜色代替 Source
     */
    private val multiplyRedFilter = PorterDuffColorFilter(Color.RED,PorterDuff.Mode.SRC_OVER)

    /**
     * 矩阵颜色过滤器
     * 内部维持一个4*5的矩阵
     * [ a , b , c , d , e ,
     *   f , g , h , i , j ,
     *   k , l , m , n , o ,
     *   p , q , r , s , t ]
     * 计算方式如下：
     * R' = a * R + b * G + c * B + d * A + e
     * G' = f * R + g * G + h * B + i * A + j
     * B' = k * R + l * G + m * B + n * A + o
     * A' = p * R + q * G + r * B + s * A + t
     */
    private val greyMatrix = floatArrayOf(
        0.33f, 0.59f, 0.11f, 0f, 0f,
        0.33f, 0.59f, 0.11f, 0f, 0f,
        0.33f, 0.59f, 0.11f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f
    )
    private val matrixColorFilter = ColorMatrixColorFilter(greyMatrix)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setLayerType(View.LAYER_TYPE_SOFTWARE,null)
        canvas?.also {
            paint.apply {
                shader = batmanShader
                //设置颜色过滤器
                colorFilter = redColorFilter
                it.drawCircle(250f, 250f, 200f, this)
                colorFilter = moreWarmFilter
                it.drawCircle(750f,250f,200f,paint)
                colorFilter = multiplyRedFilter
                it.drawCircle(1250f,250f,200f,this)
                colorFilter = matrixColorFilter
                it.drawCircle(250f,750f,200f,this)
            }
        }
    }
}

