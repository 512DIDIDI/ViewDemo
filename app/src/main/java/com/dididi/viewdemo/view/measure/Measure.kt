package com.dididi.viewdemo.view.measure

import android.view.View


/**
 * @author dididi(yechao)
 * @since 03/07/2020
 * @describe
 * view的测量流程
 * 主要思路：递归 （递的是MeasureSpec 归的是measureWidth/Height）
 * 主体函数：[View.measure] [View.onMeasure] [View.setMeasuredDimension]
 * 简要流程：(虚线是递流程 实线是归流程)
 * A.measure --> A.onMeasure --> A.measureChild ————> A.setMeasureDimension
 *      _ _ _ _ _ _ _ _ _ _ _ _ _ _ ↓  ↑_______________________
 *     ↓                                                       ↑
 * B.measure --> B.onMeasure --> B.measureChild ————> B.setMeasureDimension
 *      _ _ _ _ _ _ _ _ _ _ _ _ _ _ ↓  ↑_______________________
 *     ↓                                                       ↑
 * C.measure --> C.onMeasure————————————————————————> C.setMeasureDimension
 */

class Measure {

}