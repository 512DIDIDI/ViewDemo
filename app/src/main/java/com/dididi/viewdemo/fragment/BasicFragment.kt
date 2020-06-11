package com.dididi.viewdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dididi.viewdemo.R
import kotlinx.android.synthetic.main.fragment_view.*


/**
 * @author dididi(yechao)
 * @since 10/06/2020
 * @describe 绘制基础activity
 */

class BasicFragment :Fragment(){

    private val list = listOf(
        "paint绘制" to R.layout.view_basic,
        "path绘制" to R.layout.view_path,
        "bitmap/text绘制" to R.layout.view_else,
        "直方图绘制" to R.layout.view_histogram,
        "饼状图" to R.layout.view_pied
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        //绑定viewPager2+tabLayout与数据源
        bindViewpager2WithTabLayout(fragmentBasicVp,fragmentBasicTab,list)
    }
}
