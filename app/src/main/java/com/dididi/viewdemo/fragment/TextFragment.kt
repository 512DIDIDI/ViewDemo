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
 * @since 12/06/2020
 * @describe 文字绘制
 */

class TextFragment :Fragment(){
    private val list = listOf(
        "文字绘制" to R.layout.view_text,
        "测量文字尺寸" to R.layout.view_measure_text
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
