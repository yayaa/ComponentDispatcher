package com.yayandroid.componentdispatcher.sample.base

import android.app.Application
import com.yayandroid.componentdispatcher.CoreComponentGenerator
import com.yayandroid.componentdispatcher.annotation.ComponentGenerator
import com.yayandroid.componentdispatcher.sample.base.di.SampleCoreComponent

@ComponentGenerator
class SampleCoreComponentGenerator : CoreComponentGenerator<SampleCoreComponent>() {

    override fun generate(application: Application): SampleCoreComponent = SampleCoreComponent.create(application)

}