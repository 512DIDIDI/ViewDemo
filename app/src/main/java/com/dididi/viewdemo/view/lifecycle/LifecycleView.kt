package com.dididi.viewdemo.view.lifecycle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View


/**
 * @author dididi(yechao)
 * @since 19/06/2020
 * @describe view的生命周期，各个方法的调用流程：
 * 与Activity生命周期关系
 *    Activity.onResume 后WindowManager才将DecorView与ViewRootImpl绑定，因此onResume之后，才执行View的三大绘制流程
 * 第一次创建：
 *    constructor() - onFinishInflate() - onAttachedToWindow() - onWindowVisibilityChanged()
 *    - onMeasure() - onSizeChanged() - onLayout() - onDraw()
 * 切换到别的页面：
 *    onWindowVisibilityChanged() - onDetachedFromWindow()
 * 重新切换到该页面：
 *    onAttachedToWindow() - onWindowVisibilityChanged() - onDraw()
 * 如果是属性动画：会一直调用 onDraw() 方法绘制
 */

class LifecycleView :View{

    companion object{
        private const val TAG = "Lifecycle"
    }

    constructor(context: Context) : this(context,null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        Log.d(TAG, "view1 constructor")
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        Log.d(TAG, "view1 onFinishInflate: ")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG, "view1 onAttachedToWindow: ")
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        Log.d(TAG, "view1 onWindowVisibilityChanged: ")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(TAG, "view1 onMeasure: ")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.d(TAG, "view1 onSizeChanged: ")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d(TAG, "view1 onLayout: ")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(TAG, "view1 onDraw: ")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(TAG, "view1 onDetachedFromWindow: ")
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d(TAG, "view1 onKeyDown: ")
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d(TAG, "view1 onKeyUp: ")
        return super.onKeyUp(keyCode, event)
    }

    override fun onTrackballEvent(event: MotionEvent?): Boolean {
        Log.d(TAG, "view1 onTrackballEvent: ")
        return super.onTrackballEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(TAG, "view1 onTouchEvent: ")
        return super.onTouchEvent(event)
    }

    override fun onFocusChanged(gainFocus: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect)
        Log.d(TAG, "view1 onFocusChanged: ")
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        Log.d(TAG, "view1 onWindowFocusChanged: ")
    }

}