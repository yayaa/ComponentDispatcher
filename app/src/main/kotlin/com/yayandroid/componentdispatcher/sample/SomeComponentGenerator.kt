package com.yayandroid.componentdispatcher.sample

import com.yayandroid.componentdispatcher.CoreApplicationComponent
import com.yayandroid.componentdispatcher.CoreComponentGenerator

class SomeComponentGenerator : CoreComponentGenerator<SomeComponent>() {

    override fun generate(): SomeComponent = SomeComponent()

}

class SomeComponent : CoreApplicationComponent