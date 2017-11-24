package com.yayandroid.componentdispatcher

abstract class ComponentGenerator<out T: ApplicationComponent> {

    val component: T by lazy { generate() }

    abstract fun componentClass(): Class<*>

    abstract fun generate(): T

}