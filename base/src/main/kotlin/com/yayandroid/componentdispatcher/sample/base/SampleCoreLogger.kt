package com.yayandroid.componentdispatcher.sample.base

import android.util.Log
import com.yayandroid.componentdispatcher.contract.ApplicationComponent

class SampleCoreLogger {

    fun logSelf() = Log.e("SampleCoreLogger", "SampleCoreLogger instance: $this with hashcode: ${this.hashCode()}")

    fun string(component: ApplicationComponent) = "${component.javaClass.simpleName} ${component.hashCode()}"

}