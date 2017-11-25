package com.yayandroid.componentdispatcher.sample.feature1

import android.util.Log
import com.yayandroid.componentdispatcher.CoreApplicationComponent
import com.yayandroid.componentdispatcher.FeatureApplicationComponent
import com.yayandroid.componentdispatcher.FeatureComponentGenerator

class Feature1ComponentGenerator : FeatureComponentGenerator<Feature1Component>() {

    override fun generate(coreApplicationComponent: CoreApplicationComponent?): Feature1Component =
            Feature1Component(coreApplicationComponent)

}

class Feature1Component(private val coreApplicationComponent: CoreApplicationComponent?) : FeatureApplicationComponent {

    init {
        Log.i("Feature1Component", "Feature1Component is now generated.")
    }

    override fun toString(): String = super.toString() +
            " with $coreApplicationComponent as coreApplicationComponent"
}