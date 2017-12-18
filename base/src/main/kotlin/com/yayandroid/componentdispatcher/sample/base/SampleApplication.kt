package com.yayandroid.componentdispatcher.sample.base

import android.app.Activity
import android.app.Application
import com.yayandroid.componentdispatcher.ComponentDispatcher

class SampleApplication : Application() {

    private lateinit var componentDispatcher: ComponentDispatcher

    override fun onCreate() {
        super.onCreate()
        componentDispatcher = ComponentDispatcher(this)
    }

    fun getComponentDispatcher() = componentDispatcher

}

val Activity.componentDispatcher: ComponentDispatcher
    get() = (this.application as SampleApplication).getComponentDispatcher()