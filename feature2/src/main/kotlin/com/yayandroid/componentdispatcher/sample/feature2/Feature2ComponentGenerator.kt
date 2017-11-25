package com.yayandroid.componentdispatcher.sample.feature2

import android.util.Log
import com.yayandroid.componentdispatcher.CoreApplicationComponent
import com.yayandroid.componentdispatcher.FeatureApplicationComponent
import com.yayandroid.componentdispatcher.FeatureComponentGenerator

class Feature2ComponentGenerator : FeatureComponentGenerator<Feature2Component>() {

    override fun generate(coreApplicationComponent: CoreApplicationComponent?): Feature2Component =
            Feature2Component(coreApplicationComponent)

}

class Feature2Component(private val coreApplicationComponent: CoreApplicationComponent?) : FeatureApplicationComponent {

    init {
        Log.i("Feature2Component", "Feature2Component is now generated.")
    }

    override fun toString(): String = super.toString() +
            " with $coreApplicationComponent as coreApplicationComponent"
}