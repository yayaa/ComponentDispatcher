package com.yayandroid.componentdispatcher.sample.feature1

import com.yayandroid.componentdispatcher.CoreApplicationComponent
import com.yayandroid.componentdispatcher.FeatureApplicationComponent
import com.yayandroid.componentdispatcher.FeatureComponentGenerator
import com.yayandroid.componentdispatcher.sample.base.di.SampleCoreComponent
import dagger.Component

class Feature1ComponentGenerator : FeatureComponentGenerator<Feature1Component>() {

    override fun generate(coreApplicationComponent: CoreApplicationComponent?): Feature1Component =
            DaggerFeature1Component.builder()
                    .sampleCoreComponent(coreApplicationComponent as SampleCoreComponent)
                    .build()

}

@Feature1Scope
@Component(dependencies = arrayOf(SampleCoreComponent::class))
interface Feature1Component : FeatureApplicationComponent {

    fun inject(activity: Feature1Activity)

}