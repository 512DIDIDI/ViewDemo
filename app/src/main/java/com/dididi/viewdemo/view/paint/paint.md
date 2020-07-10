### Paint介绍

* [Paint属性介绍(1)](PaintView1.kt)

  1. `isAntiAlias`/`setStyle`/`setStrokeWidth`/`setStrokeCap`
  2. `setStrokeJoin`/`setStrokeMiter`/`isDither`/`isFilterBitmap`
* [Paint属性介绍(2)](PaintView2.kt)
  1. `PathEffect`(设置Path效果)的六大类介绍
  2. `MaskFilter`(设置Path滤波器)
  3. `getFillType`获取Path路径，并将结果存入另一个path
  4. `getTextPath`获取文字轮廓路径
  5. `setShadowLayer`设置阴影
* [基本颜色与着色器](ShaderView.kt)
  1. 设置基本颜色：`setColor`/`setARGB`
  2. 着色器：`Shader`的四大类介绍
* [Xfermode](XfermodeView.kt)
  1. `Xfermode`(基于已有view，改变其绘制方案)
  2. `PorterDuff.Mode`17种颜色模式
* [Filter](FilterView.kt)
  1. `setColorFilter`颜色过滤器(可做滤镜)的三大类介绍