package com.dididi.viewdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View


/**
 * @author dididi(yechao)
 * @since 03/06/2020
 * @describe 路径绘制自定义图形
 */

class PathView : View {

    private val paint = Paint()
    private val path = Path().apply {
        addArc(200f, 200f, 400f, 400f, -225f, 225f)
        arcTo(400f, 200f, 600f, 400f, -180f, 225f, false)
        lineTo(400f, 542f)
    }

    /**
     * 1.addXXX()-------添加子图形
     * 最后一个参数dir ，有两个方向
     * [Path.Direction.CW] (顺时针) [Path.Direction.CCW] (逆时针)
     * 如果重叠部分方向相反，则消除，方向相同，则叠加
     */
    private val path1 = Path().apply {
        addCircle(700f, 300f, 100f, Path.Direction.CCW)
        addCircle(800f, 300f, 100f, Path.Direction.CW)
    }

    /**
     * 2.xxxTo()--------画线(直线或曲线)
     * xxxTo()参数使用的是绝对坐标(使用屏幕坐标)
     * rXXXTo()参数使用的是相对坐标(相对当前位置的坐标)
     */
    private val path2 = Path().apply {
        lineTo(100f, 100f)
        rLineTo(100f, 0f)
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(path, paint)
        canvas?.drawPath(path1, paint)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        canvas?.drawPath(path2,paint)
    }

}

