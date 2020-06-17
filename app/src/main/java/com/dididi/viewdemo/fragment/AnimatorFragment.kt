package com.dididi.viewdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dididi.viewdemo.R
import kotlinx.android.synthetic.main.fragment_view.*
import kotlinx.android.synthetic.main.view_animator.*


/**
 * @author dididi(yechao)
 * @since 17/06/2020
 * @describe
 */

class AnimatorFragment : Fragment(){
    private val list = listOf(
        "属性动画" to R.layout.view_animator
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