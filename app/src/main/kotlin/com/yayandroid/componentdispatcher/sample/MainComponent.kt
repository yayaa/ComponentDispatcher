package com.yayandroid.componentdispatcher.sample

import com.yayandroid.componentdispatcher.sample.base.di.ActivityScope
import com.yayandroid.componentdispatcher.sample.base.di.SampleCoreComponent
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(SampleCoreComponent::class))
abstract class MainComponent {

    companion object {
        fun create(coreComponent: SampleCoreComponent): MainComponent = DaggerMainComponent.builder()
                .sampleCoreComponent(coreComponent)
                .build()
    }

    abstract fun inject(activity: MainActivity)

}