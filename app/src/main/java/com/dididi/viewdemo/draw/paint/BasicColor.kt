package com.dididi.viewdemo.draw.paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.dididi.viewdemo.R


/**
 * @author dididi(yechao)
 * @since 10/06/2020
 * @describe 基本颜色
 */

class BasicColor : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val paint = Paint()

    /**
     * 线性渐变
     * x0 y0 x1 y1 两个渐变色端点的起始位置
     * color0 color1 渐变色的颜色
     * tile 辐射范围之外的颜色 分为三种模式：
     * [Shader.TileMode.CLAMP]  普通的渐变 互相融合
     * [Shader.TileMode.MIRROR] 镜像 从端点[镜像]复制一个渐变色 直到充满图形
     * [Shader.TileMode.REPEAT] 重复 [重复]渐变色 直到充满图形
     */
    private val shader1 =
        LinearGradient(300f, 300f, 500f, 500f, Color.RED, Color.YELLOW, Shader.TileMode.MIRROR)

    /**
     * 辐射渐变
     * centerX centerY 辐射中心的坐标
     * radius 辐射范围
     * centerColor 辐射中心的颜色
     * edgeColor 辐射边缘的颜色
     * tile 辐射范围的颜色 [Shader.TileMode]
     */
    private val shader2 =
        RadialGradient(500f, 1000f, 150f, Color.BLUE, Color.RED, Shader.TileMode.CLAMP)

    /**
     * 扫描渐变(以圆扫描)
     * cx cy 扫描中心坐标
     * color0 扫描起始颜色
     * color1 扫描终点颜色
     */
    private val shader3 = SweepGradient(900f, 300f, Color.YELLOW, Color.GREEN)

    private val bitmap:Bitmap by lazy {
        BitmapFactory.decodeResource(context.resources,
            R.mipmap.ic_launcher
        )
    }

    /**
     * bitmap着色器
     * bitmap 需要着色的bitmap
     * tileX 横向的tileMode
     * tileY 纵向的tileMode
     */
    private val shader4 = BitmapShader(bitmap,Shader.TileMode.REPEAT,Shader.TileMode.MIRROR)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.also {
            paint.apply {
                reset()
                //设置颜色
                color = Color.WHITE
                it.drawRect(100f, 100f, 200f, 200f, this)
                //设置ARGB颜色 增加了透明度(透明度的范围从[0..255])
                setARGB(50, 255, 0, 0)
                it.drawRect(300f, 100f, 400f, 200f, this)
                //着色器虽然不与color共享RGB颜色，但alpha透明度会共享
                alpha = 100
                shader = shader1
                it.drawCircle(500f, 500f, 200f, this)
                shader = shader2
                it.drawCircle(500f, 1000f, 200f, this)
                shader = shader3
                it.drawCircle(900f, 300f, 200f, this)
                alpha = 255
                shader = shader4
                //BitmapShader配合drawXXX使用 可绘制特定图形的bitmap填充方案
                it.drawCircle(800f,700f,100f,this)
            }
        }
    }
}
