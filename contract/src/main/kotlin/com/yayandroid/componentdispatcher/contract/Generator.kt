package com.yayandroid.componentdispatcher.contract

import java.lang.reflect.ParameterizedType

abstract class Generator<out T : ApplicationComponent> {

    abstract val component: T

    @Suppress("HasPlatformType")
    fun componentClass() = (this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[0]

}

interface CoreGenerator