package com.yayandroid.componentdispatcher.sample.feature2

import com.yayandroid.componentdispatcher.CoreApplicationComponent
import com.yayandroid.componentdispatcher.FeatureApplicationComponent
import com.yayandroid.componentdispatcher.FeatureComponentGenerator
import com.yayandroid.componentdispatcher.sample.base.di.SampleCoreComponent
import dagger.Component

class Feature2ComponentGenerator : FeatureComponentGenerator<Feature2Component>() {

    override fun generate(coreApplicationComponent: CoreApplicationComponent?): Feature2Component =
            DaggerFeature2Component.builder()
                    .sampleCoreComponent(coreApplicationComponent as SampleCoreComponent)
                    .build()

}

@Feature2Scope
@Component(dependencies = arrayOf(SampleCoreComponent::class))
interface Feature2Component : FeatureApplicationComponent {

    fun inject(activity: Feature2Activity)

}