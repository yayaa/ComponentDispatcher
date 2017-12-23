package com.yayandroid.componentdispatcher.sample.base

import android.app.Activity
import android.app.Application
import com.yayandroid.componentdispatcher.ComponentDispatcher
import com.yayandroid.componentdispatcher.contract.CoreGenerator
import com.yayandroid.componentdispatcher.contract.Generator
import com.yayandroid.componentdispatcher.contract.GeneratorSource
import java.lang.reflect.Type

class SampleApplication : Application() {

    private lateinit var componentDispatcher: ComponentDispatcher

    override fun onCreate() {
        super.onCreate()

        // TODO: Replace this source with ComponentGeneratorSource()
        val source = object : GeneratorSource {
            override fun getGeneratorMap(): HashMap<Type, Generator<*>> = HashMap()

            override fun getCoreGenerator(): CoreGenerator? = null
        }

        componentDispatcher = ComponentDispatcher(this, source)
    }

    fun getComponentDispatcher() = componentDispatcher

}

val Activity.componentDispatcher: ComponentDispatcher
    get() = (this.application as SampleApplication).getComponentDispatcher()