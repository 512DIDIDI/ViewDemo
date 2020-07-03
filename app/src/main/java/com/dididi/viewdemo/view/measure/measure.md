## view的测量流程

1. 主要思路：**递归** （递的是`MeasureSpec` 归的是`measureWidth/Height`）

2. 主体函数：`[View.measure]` `[View.onMeasure]` `[View.setMeasuredDimension]`

3. `ViewGroup.LayoutParams`

   * 作用：用来告诉parent view布局的样式

   * `public static final int MATCH_PARENT = -1;`等于父布局大小-padding

   * `public static final int WRAP_CONTENT = -2;`要求视图足够大以适应内容大小	

4. `MeasureSpec`

   * 作用：父布局对子布局的布局约束
   * `UNSPECIFIED`
   * `EXACTLY`
   * `AT_MOST`

5. `measure`源码

6. `onMeasure`源码

7. `setMeasureDimension`源码

8. 递归简要流程：(虚线是递流程 实线是归流程)

   A.measure ---> A.onMeasure --> A.measureChild ——————> A.setMeasureDimension
               _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ↓  ↑_____________________________________________________________________________________________________________
             ↓                                                                                                                         ↑
   B.measure --> B.onMeasure --> B.measureChild ——————> B.setMeasureDimension
              _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ↓  ↑_____________________________________________________________________________________________________________
            ↓                                                                                                                         ↑
   C.measure --> C.onMeasure ———————————————> C.setMeasureDimension

9. 

   

10. 

11. 