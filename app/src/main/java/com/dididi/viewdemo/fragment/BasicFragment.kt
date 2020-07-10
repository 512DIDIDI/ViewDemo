package com.dididi.viewdemo.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
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

    companion object{
        private const val TAG = "Lifecycle"
    }

    private val list = listOf(
        "view的生命周期" to R.layout.view_lifecycle,
        "paint绘制" to R.layout.view_basic,
        "path绘制" to R.layout.view_path,
        "bitmap/text绘制" to R.layout.view_else,
        "直方图绘制" to R.layout.view_histogram,
        "饼状图" to R.layout.view_pied
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "fragment onAttach: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "fragment onCreate: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "fragment onCreateView: ")
        return inflater.inflate(R.layout.fragment_view,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "fragment onActivityCreated: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "fragment onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "fragment onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "fragment onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "fragment onStop: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "fragment onDestroyView: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "fragment onDestroy: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "fragment onDetach: ")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        //绑定viewPager2+tabLayout与数据源
        bindViewpager2WithTabLayout(fragmentBasicVp,fragmentBasicTab,list)
    }
}
