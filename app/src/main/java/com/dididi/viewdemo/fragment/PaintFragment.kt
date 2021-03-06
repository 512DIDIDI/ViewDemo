package com.dididi.viewdemo.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dididi.viewdemo.R
import kotlinx.android.synthetic.main.fragment_view.*


/**
 * @author dididi(yechao)
 * @since 10/06/2020
 * @describe
 */

class PaintFragment :Fragment() {

    private val list = listOf(
        "基本颜色与着色器" to R.layout.view_shader,
        "颜色过滤器" to R.layout.view_filter,
        "XferMode" to R.layout.view_xfermode,
        "Paint属性(1)" to R.layout.view_paint1,
        "Paint属性(2)" to R.layout.view_paint2
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        bindViewpager2WithTabLayout(fragmentBasicVp,fragmentBasicTab,list)
    }
}