package com.yayandroid.componentdispatcher.sample.base.di

import com.yayandroid.componentdispatcher.CoreApplicationComponent
import com.yayandroid.componentdispatcher.sample.base.SampleCoreLogger
import dagger.Component

@CoreScope
@Component(modules = arrayOf(SampleCoreModule::class))
interface SampleCoreComponent : CoreApplicationComponent {

    companion object {
        fun create(): SampleCoreComponent = DaggerSampleCoreComponent.builder().build()
    }

    // Expose Logger so depending components can use the same instance
    fun getSampleCoreLogger(): SampleCoreLogger

}
