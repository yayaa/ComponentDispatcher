package com.yayandroid.componentdispatcher.sample.base

import android.app.Application
import com.yayandroid.componentdispatcher.ComponentDispatcher

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ComponentDispatcher.initialize(this)
    }

}