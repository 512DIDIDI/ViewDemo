package com.dididi.viewdemo.view.paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.dididi.viewdemo.R


/**
 * @author dididi(yechao)
 * @since 12/06/2020
 * @describe Paint属性介绍(2)
 * [Paint.setPathEffect] :
 * [CornerPathEffect] [DiscretePathEffect] [DashPathEffect]
 * [PathDashPathEffect] [SumPathEffect] [ComposePathEffect]
 * [Paint.setMaskFilter] : [BlurMaskFilter]
 * [Paint.getFillPath] [Paint.getTextPath]
 * [Paint.setShadowLayer]
 * [Canvas.translate] [Canvas.save] [Canvas.restore]
 */

class PaintView2 : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val paint = Paint()
    private val pathPaint = Paint().apply {
        strokeWidth = 0f
        style = Paint.Style.STROKE
    }

    private val sourcePath = Path().apply {
        moveTo(50f, 50f)
        rLineTo(100f, 100f)
        rLineTo(50f, -50f)
        rLineTo(100f, 150f)
        rLineTo(50f, -200f)
        rLineTo(50f, 100f)
    }

    /**
     * [CornerPathEffect] 将图形中的拐角变为圆角
     * @param radius 拐角圆角的半径
     */
    private val cornerPathEffect = CornerPathEffect(50f)
    private val cornerPath = Path().apply {
        moveTo(50f, 350f)
        rLineTo(100f, 100f)
        rLineTo(50f, -50f)
        rLineTo(100f, 150f)
        rLineTo(50f, -200f)
        rLineTo(50f, 100f)
    }

    /**
     * [DiscretePathEffect] 将path变成离散的线拼接，
     * @param segmentLength 每条离散线的长度
     * @param deviation     每条离散线的偏移量
     */
    private val discretePathEffect = DiscretePathEffect(20f, 5f)
    private val discretePath = Path().apply {
        moveTo(50f, 650f)
        rLineTo(100f, 100f)
        rLineTo(50f, -50f)
        rLineTo(100f, 150f)
        rLineTo(50f, -200f)
        rLineTo(50f, 100f)
    }

    /**
     * [DashPathEffect] 将path变成虚线
     * @param intervals 传入一个数组，数组长度必须是偶数，按照 [画线长度, 空白长度, 画线长度, 空白长度...]
     * @param phase     虚线偏移量
     */
    private val dashPathEffect = DashPathEffect(floatArrayOf(10f, 5f, 20f, 10f), 0f)
    private val dashPath = Path().apply {
        moveTo(450f, 50f)
        rLineTo(100f, 100f)
        rLineTo(50f, -50f)
        rLineTo(100f, 150f)
        rLineTo(50f, -200f)
        rLineTo(50f, 100f)
    }

    private val shape = Path().apply {
        paint.style = Paint.Style.STROKE
        addRect(0f, 0f, 20f, 20f, Path.Direction.CW)
    }

    /**
     * [PathDashPathEffect] 使用shape来绘制path
     * @param shape   绘制path的shape 注意[Paint.setStyle]需要设置成[Paint.Style.STROKE]或[Paint.Style.FILL_AND_STROKE]
     * @param advance 每个shape的间距
     * @param phase   shape的偏移量
     * @param style   shape的风格
     * [PathDashPathEffect.Style.TRANSLATE] 平移 [PathDashPathEffect.Style.MORPH] 变体
     * [PathDashPathEffect.Style.ROTATE] 旋转
     */
    private val pathDashPathEffect =
        PathDashPathEffect(shape, 30f, 0f, PathDashPathEffect.Style.TRANSLATE)
    private val pathDashPath = Path().apply {
        moveTo(450f, 350f)
        rLineTo(100f, 100f)
        rLineTo(50f, -50f)
        rLineTo(100f, 150f)
        rLineTo(50f, -200f)
        rLineTo(50f, 100f)
    }

    /**
     * [SumPathEffect] 两种效果相加，两种效果都绘制一遍
     */
    private val sumPathEffect = SumPathEffect(discretePathEffect, dashPathEffect)
    private val sumPath = Path().apply {
        moveTo(450f, 650f)
        rLineTo(100f, 100f)
        rLineTo(50f, -50f)
        rLineTo(100f, 150f)
        rLineTo(50f, -200f)
        rLineTo(50f, 100f)
    }

    /**
     * [ComposePathEffect] 组合效果，有先后顺序，先绘制第一个效果，然后第二个效果再第一个效果的基础上绘制
     */
    private val composePathEffect = ComposePathEffect(dashPathEffect, cornerPathEffect)
    private val composePath = Path().apply {
        moveTo(850f, 50f)
        rLineTo(100f, 100f)
        rLineTo(50f, -50f)
        rLineTo(100f, 150f)
        rLineTo(50f, -200f)
        rLineTo(50f, 100f)
    }

    private val bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.batman)

    /**
     * [BlurMaskFilter] 模糊滤波器
     * @param radius    模糊半径
     * @param style     模糊样式 [BlurMaskFilter.Blur]
     * [BlurMaskFilter.Blur.NORMAL] 内外模糊
     * [BlurMaskFilter.Blur.OUTER]  内部正常绘制 外部模糊
     * [BlurMaskFilter.Blur.INNER]  内部模糊 外部不绘制
     * [BlurMaskFilter.Blur.SOLID]  内部不绘制 外部模糊
     */
    private val blurMaskFilter = BlurMaskFilter(100f, BlurMaskFilter.Blur.NORMAL)

    private val srcPath = Path().apply {
        moveTo(50f, 950f)
        rLineTo(100f, 100f)
        rLineTo(50f, -100f)
        rLineTo(50f, 50f)
    }
    private val dstPath = Path()

    private val textPath = Path()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // [PathDashPathEffect] 对硬件加速支持有问题 最好关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        canvas?.also {
            paint.apply {
                //清除阴影
                clearShadowLayer()
                maskFilter = null
                strokeWidth = 2f
                style = Paint.Style.STROKE
                it.drawPath(sourcePath, this)
                //设置path效果
                pathEffect = cornerPathEffect
                it.drawPath(cornerPath, this)
                pathEffect = discretePathEffect
                it.drawPath(discretePath, this)
                pathEffect = dashPathEffect
                it.drawPath(dashPath, this)
                pathEffect = pathDashPathEffect
                it.drawPath(pathDashPath, this)
                pathEffect = sumPathEffect
                it.drawPath(sumPath, this)
                pathEffect = composePathEffect
                it.drawPath(composePath, this)
                //清除path效果
                pathEffect = null
                maskFilter = blurMaskFilter
                it.drawBitmap(bitmap, 900f, 450f, this)
                //清除滤波器
                maskFilter = null
                strokeWidth = 20f
                it.drawPath(srcPath, this)
                //获取srcPath轮廓，并将结果存进dstPath
                getFillPath(srcPath, dstPath)
                //保存当前canvas状态
                it.save()
                //平移画布
                it.translate(250f, 0f)
                it.drawPath(dstPath, pathPaint)
                //恢复canvas状态
                it.restore()
                strokeWidth =20f
                //获取文字轮廓路径
                getTextPath("PATH",0,4,600f,950f,textPath)
                it.drawPath(textPath,pathPaint)
                textSize = 30f
                strokeWidth = 2f
                style = Paint.Style.FILL
                it.drawText("原图形", 200f, 300f, this)
                it.drawText("增加CornerPathEffect图形", 50f, 600f, this)
                it.drawText("增加DiscretePathEffect图形", 50f, 900f, this)
                it.drawText("增加DashPathEffect图形", 450f, 300f, this)
                it.drawText("增加PathDashPathEffect图形", 450f, 600f, this)
                it.drawText("增加SumPathEffect图形", 450f, 900f, this)
                it.drawText("增加ComposePathEffect图形", 850f, 300f, this)
                it.drawText("原文字", 850f, 350f, this)
                //设置阴影 如果不开启硬件加速 只支持文字
                //color如果没有设置alpha 则跟随paint的alpha
                setShadowLayer(20f, 0f, 0f, Color.RED)
                it.drawText("增加shadowLayer的文字", 850f, 400f, this)
            }
        }
    }
}

