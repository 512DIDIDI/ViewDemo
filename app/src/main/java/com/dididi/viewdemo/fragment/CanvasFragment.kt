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
 * @since 15/06/2020
 * @describe 画布
 */

class CanvasFragment : Fragment(){
    private val list = listOf(
        "范围裁剪" to R.layout.view_canvas,
        "翻页效果" to R.layout.view_flip
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