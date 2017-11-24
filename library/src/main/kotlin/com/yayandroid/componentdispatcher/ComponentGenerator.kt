package com.yayandroid.componentdispatcher

import java.lang.reflect.ParameterizedType

abstract class ComponentGenerator<out T: ApplicationComponent> {

    val component: T by lazy { generate() }

    @Suppress("HasPlatformType")
    fun componentClass() = (this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[0]

    abstract fun generate(): T

}