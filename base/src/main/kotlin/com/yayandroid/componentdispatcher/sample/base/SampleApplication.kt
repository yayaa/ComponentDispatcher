package com.yayandroid.componentdispatcher.sample.base

import android.app.Activity
import android.app.Application
import com.yayandroid.componentdispatcher.ApplicationComponent
import com.yayandroid.componentdispatcher.ComponentDispatcher

class SampleApplication : Application() {

    lateinit var componentDispatcher: ComponentDispatcher

    override fun onCreate() {
        super.onCreate()
        componentDispatcher = ComponentDispatcher(this)
    }

}

inline fun <reified T: ApplicationComponent> Activity.getComponent()
        = (applicationContext as SampleApplication).componentDispatcher.get<T>()