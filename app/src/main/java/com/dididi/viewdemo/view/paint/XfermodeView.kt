package com.dididi.viewdemo.view.paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.dididi.viewdemo.R


/**
 * @author dididi(yechao)
 * @since 11/06/2020
 * @describe 基于已有view，改变其方案
 * [Paint.setXfermode] : [PorterDuffXfermode]
 */

class XfermodeView :View{
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val paint = Paint()

    private val DstInMode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)

    private val batman = BitmapFactory.decodeResource(context.resources,R.mipmap.batman)
    private val batmanLogo = BitmapFactory.decodeResource(context.resources, R.mipmap.batman_logo)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.also {
            paint.apply {
                //这类写法会出现一个问题，在DST_IN的范围之外，SRC view会出现全黑现象
                //因此此时的DST会以整个View为目标，所以DST_IN显示的是整个view，而范围之外出现全黑，是因为view自身的底色并不是透明的
                //绘制DST view (这是目标view)
                it.drawBitmap(batman,100f,50f,this)
                //设置转换模式
                xfermode = DstInMode
                //绘制SRC view
                it.drawBitmap(batmanLogo,0f,0f,this)
                //用完后清除xfermode
                xfermode = null
                textSize = 50f
                it.drawText("直接使用xfermode转换",100f,600f,this)


                //解决方法(使用离屏缓冲 将绘制的内容先存放在缓存区)：
                //saveLayer做短时的离屏缓冲
                val saved = it.saveLayer(null,null)
                it.drawBitmap(batman,700f,50f,this)
                xfermode = DstInMode
                it.drawBitmap(batmanLogo,600f,50f,this)
                xfermode = null
                //绘制结束后，恢复缓存区
                it.restoreToCount(saved)
                it.drawText("使用离屏缓冲解决黑屏问题",650f,600f,this)

                //setLayerType 将整个view都绘制在离屏缓冲里(但是会影响所有的view绘制结果，这里为了不影响上面的实例，注释掉了)
//                setLayerType(LAYER_TYPE_SOFTWARE,null)
//                it.drawBitmap(batman,1300f,50f,paint)
//                xfermode = DstInMode
//                it.drawBitmap(batmanLogo,1200f,50f,paint)
//                xfermode = null

                //控制SRC 与 DST 的位置和大小 ，避免覆盖不全造成显示效果不佳的问题
                val saved2 = it.saveLayer(null,null)
                it.drawBitmap(batman,1300f,50f,this)
                xfermode = DstInMode
                it.drawBitmap(batmanLogo,1300f,50f,this)
                xfermode = null
                it.restoreToCount(saved2)
                it.drawText("使用合理的区域来解决覆盖不全问题",1250f,600f,this)
            }
        }
    }
}
