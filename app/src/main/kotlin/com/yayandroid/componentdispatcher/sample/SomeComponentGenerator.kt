package com.yayandroid.componentdispatcher.sample

import com.yayandroid.componentdispatcher.ComponentGenerator
import com.yayandroid.componentdispatcher.CoreApplicationComponent

class SomeComponentGenerator : ComponentGenerator<SomeComponent>() {

    override fun generate(): SomeComponent = object : SomeComponent() { }

}

abstract class SomeComponent : CoreApplicationComponent {

    override fun toString(): String = "Hello World!"

}