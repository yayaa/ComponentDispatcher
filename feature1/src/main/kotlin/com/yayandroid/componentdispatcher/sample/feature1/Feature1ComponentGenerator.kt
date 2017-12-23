package com.yayandroid.componentdispatcher.sample.feature1

import android.app.Application
import com.yayandroid.componentdispatcher.FeatureComponentGenerator
import com.yayandroid.componentdispatcher.annotation.ComponentGenerator
import com.yayandroid.componentdispatcher.contract.CoreApplicationComponent
import com.yayandroid.componentdispatcher.contract.FeatureApplicationComponent
import com.yayandroid.componentdispatcher.sample.base.di.SampleCoreComponent
import dagger.Component

@ComponentGenerator
class Feature1ComponentGenerator : FeatureComponentGenerator<Feature1Component>() {

    override fun generate(application: Application,
                          coreApplicationComponent: CoreApplicationComponent?): Feature1Component =
            DaggerFeature1Component.builder()
                    .sampleCoreComponent(coreApplicationComponent as SampleCoreComponent)
                    .build()

}

@Feature1Scope
@Component(dependencies = arrayOf(SampleCoreComponent::class))
interface Feature1Component : FeatureApplicationComponent {

    fun inject(activity: Feature1Activity)

}