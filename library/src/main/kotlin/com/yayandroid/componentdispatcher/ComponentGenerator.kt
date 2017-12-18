package com.yayandroid.componentdispatcher

import android.app.Application
import java.lang.reflect.ParameterizedType

sealed class ComponentGenerator<out T: ApplicationComponent> {

    internal lateinit var application: Application
    abstract val component: T

    @Suppress("HasPlatformType")
    fun componentClass() = (this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[0]

}

abstract class CoreComponentGenerator<out T: CoreApplicationComponent> : ComponentGenerator<T>() {
    override val component: T by lazy { generate(application) }
    abstract fun generate(application: Application): T
}

abstract class FeatureComponentGenerator<out T: FeatureApplicationComponent> : ComponentGenerator<T>() {
    internal var coreApplicationComponent: CoreApplicationComponent? = null
    override val component: T by lazy { generate(application, coreApplicationComponent) }
    abstract fun generate(application: Application, coreApplicationComponent: CoreApplicationComponent?): T
}