package com.yayandroid.componentdispatcher.sample

import com.yayandroid.componentdispatcher.ComponentGenerator
import com.yayandroid.componentdispatcher.CoreApplicationComponent

class SomeComponentGenerator : ComponentGenerator<SomeComponent>() {

    companion object {
        const val KEY = "SomeComponent"
    }

    override fun componentKey(): String = KEY

    override fun generate(): SomeComponent = object : SomeComponent() { }

}

abstract class SomeComponent : CoreApplicationComponent {

    override fun toString(): String = "Hello World!"

}