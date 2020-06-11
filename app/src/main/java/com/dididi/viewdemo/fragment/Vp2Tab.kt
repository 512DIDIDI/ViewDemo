package com.dididi.viewdemo.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dididi.viewdemo.R
import com.dididi.viewdemo.ViewHolder
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


/**
 * @author dididi(yechao)
 * @since 11/06/2020
 * @describe viewPager2与tabLayout绑定
 */

/**
 * 绑定viewPager2 TabLayout与数据源
 */
fun bindViewpager2WithTabLayout(
    viewPager2: ViewPager2,
    tabLayout: TabLayout,
    data: List<Pair<String, Int>>
) {
    viewPager2.adapter = object : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(
                        R.layout.item_view,
                        parent,
                        false
                    )
            )

        override fun getItemCount() = data.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.viewStub.layoutResource = data[position].second
            holder.inflateView()
        }

        override fun getItemViewType(position: Int) = data[position].second
    }
    TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
        tab.text = data[position].first
    }.attach()
}

