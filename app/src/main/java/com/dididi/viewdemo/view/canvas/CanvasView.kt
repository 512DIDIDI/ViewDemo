package com.dididi.viewdemo.view.canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.dididi.viewdemo.R
import org.w3c.dom.Node


/**
 * @author dididi(yechao)
 * @since 15/06/2020
 * @describe 画布Canvas
 */

class CanvasView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val paint = Paint()
    private val bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.batman)
    private val path = Path().apply {
        addCircle(300f,300f,100f,Path.Direction.CW)
    }

    private val mMatrix = Matrix()
    private val pointSrc = floatArrayOf(
        50f,50f,1500f,50f,50f,1500f,1500f,1500f
    )
    private val pointDst = floatArrayOf(
        40f,100f,1560f,0f,40f,1600f,1300f,1400f
    )

    private val camera = Camera()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.also {
            paint.apply {
                //使用 save-restore来恢复画布范围
                it.save()
                //裁剪画布，裁剪后，所有的绘制代码都会限制在裁剪画布的范围内
                it.clipRect(50f,50f,200f,200f)
                it.drawBitmap(bitmap,0f,0f,this)
                it.restore()
                it.save()
                //裁剪画布范围为path路径内
                it.clipPath(path)
                it.drawBitmap(bitmap,200f,200f,this)
                it.restore()
                it.save()
                //平移画布
                it.translate(200f,0f)
                it.drawBitmap(bitmap,200f,0f,this)
                it.restore()
                it.save()
                //旋转画布
                it.rotate(45f,200f,200f)
                it.drawBitmap(bitmap,0f,300f,this)
                it.restore()
                it.save()
                //放缩画布
                it.scale(1.3f,1.3f)
                it.drawBitmap(bitmap,200f,400f,this)
                it.restore()
                it.save()
                //错切画布
                it.skew(-0.5f,0f)
                it.drawBitmap(bitmap,1000f,0f,this)
                it.restore()
                mMatrix.reset()
                mMatrix.postTranslate(50f,100f)
                mMatrix.postRotate(45f)
                it.save()
                //将canvas的变换矩阵与mMatrix矩阵相乘，叠加变换
                //或者通过setMatrix()替换canvas的矩阵，但行为可能不一致，推荐使用concat()
                it.concat(mMatrix)
                it.drawBitmap(bitmap,100f,200f,this)
                it.restore()
                mMatrix.reset()
                //设置点对点映射 从而产生任意形变
                mMatrix.setPolyToPoly(pointSrc,0,pointDst,0,4)
                it.save()
                it.concat(mMatrix)
                it.drawBitmap(bitmap,1000f,500f,this)
                it.restore()
                it.save()
                camera.save()
                //旋转相机
                camera.rotateX(30f)
                //移动画布 旋转之后把投影找回来，注意：canvas几何变换顺序是相反的，也就是从下往上执行
                it.translate(1500f,500f)
                //将相机旋转应用到canvas上
                camera.applyToCanvas(it)
                //旋转之前把绘制内容移动到轴心
                it.translate(-1500f,-500f)
                camera.restore()
                it.drawBitmap(bitmap,1500f,0f,this)
                it.restore()
                it.save()
                camera.save()
                //设置相机位置，注意 x y z的单位是英寸(inch) 一英寸=72像素
                camera.setLocation(0f,0f,10f)
                camera.restore()
                it.restore()
            }
        }
    }

}