package com.yayandroid.componentdispatcher.sample.base.di

import android.app.Application
import com.yayandroid.componentdispatcher.contract.CoreApplicationComponent
import com.yayandroid.componentdispatcher.sample.base.SampleCoreLogger
import dagger.BindsInstance
import dagger.Component

@CoreScope
@Component(modules = arrayOf(SampleCoreModule::class))
interface SampleCoreComponent : CoreApplicationComponent {

    companion object {
        fun create(application: Application): SampleCoreComponent =
                DaggerSampleCoreComponent.builder()
                        .application(application)
                        .build()
    }

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder

        fun build(): SampleCoreComponent
    }

    // Expose Logger so depending components can use the same instance
    fun getSampleCoreLogger(): SampleCoreLogger

}
