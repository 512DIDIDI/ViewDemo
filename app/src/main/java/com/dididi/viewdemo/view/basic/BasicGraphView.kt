package com.dididi.viewdemo.view.basic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.dididi.uiextlib.ext.getScreeSize
import com.dididi.uiextlib.ext.log


/**
 * @author dididi(yechao)
 * @since 27/05/2020
 * @describe paint绘制官方提供图形
 * 基本图形绘制： [Canvas.drawCircle] [Canvas.drawArc] [Canvas.drawRect] [Canvas.drawPoint] ...
 */

class BasicGraphView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    /**
     * style三种模式 [Paint.Style.FILL]（填充）[Paint.Style.STROKE]（画线）[Paint.Style.FILL_AND_STROKE]（填充并画线）
     */
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    /**
     * onDraw在每次viewPager切换时都会调用
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        val point = context.getScreeSize()
        val x = point.x.toFloat() / 2
        val y = point.y.toFloat() / 2
        //这个坐标不是屏幕坐标 应该是View内的坐标
        //园
        canvas?.drawCircle(x, y, 200f, paint)
        //矩形
        canvas?.drawRect(x - 200f, y - 200f, x + 200f, y + 200f, paint)
        paint.strokeCap = Paint.Cap.SQUARE
        paint.strokeWidth = 50f
        //点
        canvas?.drawPoint(x, y, paint)
        val points = floatArrayOf(
            0f,
            0f,
            x - 300f,
            y,
            x,
            y - 300f,
            x + 300f,
            y,
            x,
            y + 300f,
            150f,
            50f,
            150f,
            100f
        )
        canvas?.drawPoints(points, 2/*跳过前两个数*/, 8/*共绘制四个点*/, paint)
        paint.strokeWidth = 25f
        val lines = floatArrayOf(
            x, 0f, x, 2 * y,
            0f, y, 2 * x, y
        )
        canvas?.apply {
            //椭圆
            drawOval(x - 300f, y - 300f, x + 300f, y + 300f, paint)
            //线
            drawLines(lines, paint)
            //圆角矩形
            drawRoundRect(x - 300f, y - 300f, x + 300f, y + 300f, 50f, 50f, paint)
            paint.style = Paint.Style.FILL
            //绘制弧形或扇形
            //注意屏幕xy坐标系，是朝右下角的，所以正右是0度位置，顺时针为正，逆时针为负
            //useCenter是否需要连接到圆心(false为弧形 true为扇形)
            drawArc(x - 200f, y - 200f, x + 200f, y + 200f, -90f, 90f, true, paint)
            drawArc(x - 200f, y - 200f, x + 200f, y + 200f, 90f, 90f, true, paint)
        }
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        when(event?.action){
            KeyEvent.KEYCODE_BACK ->{
                Toast.makeText(context, "拦截返回", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }
}