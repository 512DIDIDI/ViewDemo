package com.dididi.viewdemo.view.paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.dididi.viewdemo.R


/**
 * @author dididi(yechao)
 * @since 11/06/2020
 * @describe Paint属性介绍(1)
 * [Paint.isAntiAlias] [Paint.setStyle] [Paint.setStrokeWidth] [Paint.setStrokeCap]
 * [Paint.setStrokeJoin] [Paint.setStrokeMiter] [Paint.isDither] [Paint.isFilterBitmap]
 */

class PaintView1 :View{
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

    private val sevenPathUseStrokeMiter = Path().apply {
        moveTo(100f,400f)
        rLineTo(100f,0f)
        rLineTo(-50f,100f)
    }

    private val ditherBitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.dither)
    //bitmap改变属性，需要使用copy 并且设置其为isMutable 不能直接更改其Config
    private val lowerBitmap = ditherBitmap.copy(Bitmap.Config.ARGB_4444,true)

    private val filterBitmap = BitmapFactory.decodeResource(context.resources,R.mipmap.filter)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.also {
            paint.apply {
                //抗锯齿 默认不开启
                isAntiAlias = true
                //设置图形风格 默认FILL(填充) [Paint.Style]
                style = Paint.Style.STROKE
                //设置绘画线的宽度（设置成0也还是能画出来，只是后面计算的时候无法被放大缩小等操作）
                strokeWidth = 0f
                strokeWidth = 20f
                //设置线头形状 默认BUTT [Paint.Cap]
                strokeCap = Paint.Cap.ROUND
                it.drawLine(100f,100f,200f,100f,this)
                strokeCap = Paint.Cap.BUTT
                //设置拐角形状 默认为MITER [Paint.Join]
                strokeJoin = Paint.Join.MITER
                it.drawPath(sevenPath,this)
                reset()
                strokeWidth = 20f
                style = Paint.Style.STROKE
                //设置[strokeMiter] 更改拐角补偿限制 默认为4 即29° 小于29°用 [BEVEL] 大于29°延长线成尖角
                //注意，只有在 [strokeJoin] 设置为 [MITER] 时才生效
                strokeMiter = 1.3f
                it.drawPath(sevenPathUseStrokeMiter,this)
                //设置图像抖动 避免出现大片的色带或色块，
                //适用于图像降低色彩深度后，由于android默认绘制是ARGB_8888 因此看不出效果
                isDither = true
                //设置是否使用双线性过滤来绘制bitmap，优化bitmap放大绘制的效果
                isFilterBitmap = true
                it.drawBitmap(ditherBitmap,500f,100f,this)
                it.drawBitmap(filterBitmap,500f,600f,this)
                style = Paint.Style.FILL
                strokeWidth = 1f
                textSize = 30f
                it.drawText("isDither设置图像是否抖动",750f,80f,this)
                it.drawText("isFilterBitmap设置图像双线性过滤",700f,580f,this)
                it.drawText("默认miter limit，拐角补偿为尖角",0f,350f,this)
                it.drawText("更改miter limit，拐角变成BEVEL",0f,550f,this)
            }
        }
    }
}

