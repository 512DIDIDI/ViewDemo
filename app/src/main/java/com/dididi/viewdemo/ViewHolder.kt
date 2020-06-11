package com.dididi.viewdemo

import android.view.View
import android.view.ViewStub
import androidx.recyclerview.widget.RecyclerView


/**
 * @author dididi(yechao)
 * @since 10/06/2020
 * @describe
 */

class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
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