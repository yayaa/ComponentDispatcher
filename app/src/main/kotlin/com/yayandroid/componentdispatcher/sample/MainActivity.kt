package com.yayandroid.componentdispatcher.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yayandroid.componentdispatcher.ComponentDispatcher

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val component = ComponentDispatcher.get<SomeComponent2>()
        Log.e("MainActivity", "$component")
    }
}
