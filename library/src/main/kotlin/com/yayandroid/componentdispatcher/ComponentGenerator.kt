package com.yayandroid.componentdispatcher

import java.lang.reflect.ParameterizedType

abstract class ComponentGenerator<out T: ApplicationComponent> {

    var coreApplicationComponent: CoreApplicationComponent? = null
    val component: T by lazy { generate(coreApplicationComponent) }

    @Suppress("HasPlatformType")
    fun componentClass() = (this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[0]

    abstract fun generate(coreApplicationComponent: CoreApplicationComponent?): T

}

abstract class CoreComponentGenerator<out T: CoreApplicationComponent> : ComponentGenerator<T>()

abstract class FeatureComponentGenerator<out T: FeatureApplicationComponent> : ComponentGenerator<T>()