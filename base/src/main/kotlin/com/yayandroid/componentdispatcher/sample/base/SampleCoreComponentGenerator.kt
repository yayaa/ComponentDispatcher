package com.yayandroid.componentdispatcher.sample.base

import com.yayandroid.componentdispatcher.CoreApplicationComponent
import com.yayandroid.componentdispatcher.CoreComponentGenerator

class SampleCoreComponentGenerator : CoreComponentGenerator<SampleCoreComponent>() {

    override fun generate(): SampleCoreComponent = SampleCoreComponent()

}

class SampleCoreComponent : CoreApplicationComponent