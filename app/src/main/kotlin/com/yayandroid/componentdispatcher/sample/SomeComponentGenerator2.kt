package com.yayandroid.componentdispatcher.sample

import com.yayandroid.componentdispatcher.CoreApplicationComponent
import com.yayandroid.componentdispatcher.FeatureApplicationComponent
import com.yayandroid.componentdispatcher.FeatureComponentGenerator

class SomeComponentGenerator2 : FeatureComponentGenerator<SomeComponent2>() {

    override fun generate(coreApplicationComponent: CoreApplicationComponent?): SomeComponent2 =
            SomeComponent2(coreApplicationComponent)

}

class SomeComponent2(private val coreApplicationComponent: CoreApplicationComponent?) : FeatureApplicationComponent {
    override fun toString(): String = super.toString() +
            " with $coreApplicationComponent as coreApplicationComponent"
}