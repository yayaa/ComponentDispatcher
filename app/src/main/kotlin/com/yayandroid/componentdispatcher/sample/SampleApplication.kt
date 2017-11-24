package com.yayandroid.componentdispatcher.sample

import android.app.Application
import com.yayandroid.componentdispatcher.ComponentDispatcher

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ComponentDispatcher.initialize(this)
    }

}