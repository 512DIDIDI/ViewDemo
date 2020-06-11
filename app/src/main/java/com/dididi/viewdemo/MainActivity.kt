package com.dididi.viewdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dididi.viewdemo.fragment.BasicFragment
import com.dididi.viewdemo.fragment.PaintFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val fragmentList = listOf(
        "绘制基础" to BasicFragment(),
        "Paint详解" to PaintFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = FragmentAdapter(this)
        activityMainVp.adapter = adapter
        TabLayoutMediator(activityMainTab, activityMainVp) { tab, position ->
            tab.text = fragmentList[position].first
        }.attach()
    }

    private inner class FragmentAdapter(activity: AppCompatActivity) :
        FragmentStateAdapter(activity) {
        override fun getItemCount() = fragmentList.size
        override fun createFragment(position: Int) = fragmentList[position].second
    }

}
