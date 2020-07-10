package com.dididi.viewdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dididi.viewdemo.view.lifecycle.LifecycleView2


/**
 * @author dididi(yechao)
 * @since 08/07/2020
 * @describe
 */

class Activity2 :AppCompatActivity(){

    companion object{
        private const val TAG = "Lifecycle"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LifecycleView2(this))
        Log.d(TAG, "activity2 onCreate: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "activity2 onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "activity2 onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "activity2 onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "activity2 onStop: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "activity2 onRestart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "activity2 onDestroy: ")
    }
}