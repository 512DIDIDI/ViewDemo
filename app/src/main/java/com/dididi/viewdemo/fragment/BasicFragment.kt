package com.dididi.viewdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.dididi.viewdemo.R
import com.dididi.viewdemo.ViewHolder
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_view.*


/**
 * @author dididi(yechao)
 * @since 10/06/2020
 * @describe 绘制基础activity
 */

class BasicFragment :Fragment(){

    private val list = listOf(
        "paint绘制" to R.layout.view_paint,
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
        //viewPager2+tabLayout的使用
        fragmentBasicVp.adapter = object : RecyclerView.Adapter<ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                ViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(
                            R.layout.item_view,
                            parent,
                            false
                        )
                )

            override fun getItemCount() = list.size

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                holder.viewStub.layoutResource = list[position].second
                holder.inflateView()
            }

            override fun getItemViewType(position: Int) = list[position].second
        }
        TabLayoutMediator(fragmentBasicTab, fragmentBasicVp) { tab, position ->
            tab.text = list[position].first
        }.attach()
    }
}
