package com.yayandroid.componentdispatcher.sample.base

import com.yayandroid.componentdispatcher.CoreComponentGenerator
import com.yayandroid.componentdispatcher.sample.base.di.SampleCoreComponent

class SampleCoreComponentGenerator : CoreComponentGenerator<SampleCoreComponent>() {

    override fun generate(): SampleCoreComponent = SampleCoreComponent.create()

}