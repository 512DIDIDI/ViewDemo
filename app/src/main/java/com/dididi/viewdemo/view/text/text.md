### 文字绘制

* [文字绘制](DrawTextView.kt)
  1. `drawText`
  2. `drawTextOnPath`在路径上绘制文字
  3. `StaticLayout`绘制长文字
  4. `typeface`/`isFakeBoldText`/`isStrikeThruText`/`isUnderLineText`/`textSkewX`/`textScaleX`/`letterSpacing`/`textAlign`/`textLocale`设置字体样式
* [文字测量](MeasureTextView.kt)
  1. `Paint.measureText()`获取字符串宽度
  2. `fontMetrics`获取文字的各种线尺寸
     * top/bottom         图形的顶部和底部
     * ascent/descent   一般文字的顶部和底部
     * baseline               基准线
     * leading                 top-bottom的额外间距
  3. `getTextBounds`获取文字显示范围，将其写入rect种
  4. `getTextWidths`获取字符串内每个字符的宽度
  5. `Paint.breakText`截取到maxWidth长度的文字，返回字符个数