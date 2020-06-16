package com.dididi.viewdemo.view.draw

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View


/**
 * @author dididi(yechao)
 * @since 16/06/2020
 * @describe 绘制顺序:
 * 1.背景（drawBackground 不能重写）
 * 2.主体（onDraw）
 * 3.子View（dispatchDraw）
 * 4.滑动边缘渐变和滑动条 >（onDrawForeground）
 * 5.前景（api>23）      >（onDrawForeground）
 *
 */

class DrawOrderView :View{
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    /**
     * 总的调度方法，定义了绘制顺序
     * [drawBackground]
     * [onDraw]
     * [dispatchDraw]
     * [onDrawForeground]
     */
    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
    }

    /**
     * 如果效果同写在onDraw方法内一样，优先写在onDraw方法内，因为android对onDraw方法优化
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    /**
     * 主体   - onDraw
     * 子View - dispatchDraw
     * 绘制子view的方法
     * 如果需要绘制内容覆盖在 [ViewGroup] 的子view上，调用此方法，绘制内容写在super.dispatchDraw后
     */
    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
    }

    /**
     * 绘制滑动边缘渐变、滑动条和前景
     */
    override fun onDrawForeground(canvas: Canvas?) {
        super.onDrawForeground(canvas)
    }
}

