package com.dididi.viewdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            /**
             * 避免viewStub重复加载
             */
            private var isInflated = false
            val viewStub: ViewStub = itemView.findViewById<ViewStub>(R.id.fragmentViewStub).apply {
                setOnInflateListener { _, _ ->
                    isInflated = true
                }
            }

            fun inflateView() {
                if (!isInflated) {
                    viewStub.inflate()
                }
            }
        }
    }

    private val list = listOf(
        "paint绘制" to R.layout.view_paint,
        "path绘制" to R.layout.view_path,
        "bitmap/text绘制" to R.layout.view_else,
        "直方图绘制" to R.layout.view_histogram,
        "饼状图" to R.layout.view_pied
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //viewPager2+tabLayout的使用
        activityMainVp.adapter = object : RecyclerView.Adapter<ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_view, parent, false)
            )


            override fun getItemCount() = list.size

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                holder.viewStub.layoutResource = list[position].second
                holder.inflateView()
            }
        }
        TabLayoutMediator(activityMainTab, activityMainVp) { tab, position ->
            tab.text = list[position].first
        }.attach()
    }

}
