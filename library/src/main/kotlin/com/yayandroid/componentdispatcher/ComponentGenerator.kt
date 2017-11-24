package com.yayandroid.componentdispatcher

import java.lang.reflect.ParameterizedType

abstract class ComponentGenerator<out T: ApplicationComponent> {

    abstract val component: T

    @Suppress("HasPlatformType")
    fun componentClass() = (this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[0]

}

abstract class CoreComponentGenerator<out T: CoreApplicationComponent> : ComponentGenerator<T>() {
    override val component: T by lazy { generate() }
    abstract fun generate(): T
}

abstract class FeatureComponentGenerator<out T: FeatureApplicationComponent> : ComponentGenerator<T>() {
    override val component: T by lazy { generate(ComponentDispatcher.getCoreApplicationComponent()) }
    abstract fun generate(coreApplicationComponent: CoreApplicationComponent?): T
}