package com.dididi.viewdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View


/**
 * @author dididi(yechao)
 * @since 09/06/2020
 * @describe 饼状图
 */

class PiedChartView : View {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val explainPath = Path().apply {
        moveTo(840f,440f)
        rLineTo(-130f,-225f)
        rLineTo(-100f,0f)
        moveTo(750f,550f)
        rLineTo(190f,190f)
        rLineTo(100f,0f)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.GRAY)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3f
        paint.color = Color.WHITE
        canvas?.drawPath(explainPath,paint)
        paint.style = Paint.Style.FILL
        paint.color = Color.RED
        //注意坐标系朝向， top的值比bottom的值小
        canvas?.drawArc(590f, 290f, 1090f, 790f, -180f, 120f, true, paint)
        paint.color = Color.YELLOW
        canvas?.drawArc(600f, 300f, 1100f, 800f, -60f, 59f, true, paint)
        paint.color = Color.GREEN
        canvas?.drawArc(600f, 300f, 1100f, 800f, 0f, 19f, true, paint)
        paint.color = Color.BLUE
        canvas?.drawArc(600f, 300f, 1100f, 800f, 20f, 9f, true, paint)
        paint.color = Color.LTGRAY
        canvas?.drawArc(600f, 300f, 1100f, 800f, 30f, 39f, true, paint)
        paint.color = Color.CYAN
        canvas?.drawArc(600f, 300f, 1100f, 800f, 70f, 110f, true, paint)
    }
}

