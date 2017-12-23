package com.yayandroid.componentdispatcher.sample.feature2

import android.app.Application
import com.yayandroid.componentdispatcher.FeatureComponentGenerator
import com.yayandroid.componentdispatcher.annotation.ComponentGenerator
import com.yayandroid.componentdispatcher.contract.CoreApplicationComponent
import com.yayandroid.componentdispatcher.contract.FeatureApplicationComponent
import com.yayandroid.componentdispatcher.sample.base.di.SampleCoreComponent
import dagger.Component

@ComponentGenerator
class Feature2ComponentGenerator : FeatureComponentGenerator<Feature2Component>() {

    override fun generate(application: Application,
                          coreApplicationComponent: CoreApplicationComponent?): Feature2Component =
            DaggerFeature2Component.builder()
                    .sampleCoreComponent(coreApplicationComponent as SampleCoreComponent)
                    .build()

}

@Feature2Scope
@Component(dependencies = arrayOf(SampleCoreComponent::class))
interface Feature2Component : FeatureApplicationComponent {

    fun inject(activity: Feature2Activity)

}