package com.dididi.viewdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dididi.viewdemo.fragment.*
import com.dididi.viewdemo.view.lifecycle.LifecycleView2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "Lifecycle"
    }

    private val fragmentList = listOf(
        "绘制基础" to BasicFragment(),
        "Paint详解" to PaintFragment(),
        "文字绘制" to TextFragment(),
        "画布详解" to CanvasFragment(),
        "属性动画" to AnimatorFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = FragmentAdapter(this)
        activityMainVp.adapter = adapter
        TabLayoutMediator(activityMainTab, activityMainVp) { tab, position ->
            tab.text = fragmentList[position].first
        }.attach()
        activityMainDialogBtn.setOnClickListener {
            AlertDialog.Builder(this)
                .setView(LifecycleView2(this))
                .create()
                .show()
        }
        activityMainActivityBtn.setOnClickListener {
            startActivity(Intent(this,Activity2::class.java))
        }
        Log.d(TAG, "activity onCreate: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "activity onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "activity onResume: ")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG, "activity onAttachedToWindow: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "activity onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "activity onStop: ")
    }


    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "activity onRestart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "activity onDestroy: ")
    }

    /**
     * 只有在销毁了activity时，才会解绑window
     */
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(TAG, "activity onDetachedFromWindow: ")
    }


    private inner class FragmentAdapter(activity: AppCompatActivity) :
        FragmentStateAdapter(activity) {
        override fun getItemCount() = fragmentList.size
        override fun createFragment(position: Int) = fragmentList[position].second
    }
}
