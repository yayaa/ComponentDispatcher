package com.yayandroid.componentdispatcher.contract

import java.lang.reflect.Type

interface GeneratorSource {
    fun getGeneratorMap(): HashMap<Type, Generator<*>>
    fun getCoreGenerator(): CoreGenerator?
}