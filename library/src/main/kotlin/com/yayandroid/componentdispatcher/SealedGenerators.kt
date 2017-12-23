package com.yayandroid.componentdispatcher

import android.app.Application
import com.yayandroid.componentdispatcher.contract.ApplicationComponent
import com.yayandroid.componentdispatcher.contract.CoreApplicationComponent
import com.yayandroid.componentdispatcher.contract.CoreGenerator
import com.yayandroid.componentdispatcher.contract.FeatureApplicationComponent
import com.yayandroid.componentdispatcher.contract.Generator

sealed class SealedGenerator<out T : ApplicationComponent> : Generator<T>() {
    internal lateinit var application: Application
}

abstract class CoreComponentGenerator<out T : CoreApplicationComponent> : SealedGenerator<T>(), CoreGenerator {
    override val component: T by lazy { generate(application) }
    abstract fun generate(application: Application): T
}

abstract class FeatureComponentGenerator<out T : FeatureApplicationComponent> : SealedGenerator<T>() {
    internal var coreApplicationComponent: CoreApplicationComponent? = null
    override val component: T by lazy { generate(application, coreApplicationComponent) }
    abstract fun generate(application: Application, coreApplicationComponent: CoreApplicationComponent?): T
}