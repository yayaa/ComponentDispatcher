package com.yayandroid.componentdispatcher

import android.app.Application
import com.yayandroid.componentdispatcher.contract.ApplicationComponent
import com.yayandroid.componentdispatcher.contract.Generator
import com.yayandroid.componentdispatcher.contract.GeneratorSource
import java.lang.reflect.Type

/**
 * @param application Android Application instance
 * @param source com.yayandroid.componentdispatcher.generated.ComponentGeneratorSource will be generator for you by
 * Annotation Processor. You only need to get the instance and pass it to the constructor.
 */
class ComponentDispatcher(private val application: Application, source: GeneratorSource) {

    private val coreComponentGenerator = source.getCoreGenerator() as CoreComponentGenerator<*>?
    @PublishedApi internal val generatorMap = source.getGeneratorMap().also { updateGenerators(it) }

    inline fun <reified T : ApplicationComponent> get(): T = generatorMap[T::class.java]
            ?.let { return it.component as T }
            ?: run { throw NotRegisteredGenerator(T::class.java.name) }

    private fun updateGenerators(map: HashMap<Type, Generator<*>>) {
        map.asIterable()
                .map { entry -> entry.value }
                .map {
                    if (it is SealedGenerator) {
                        it.application = application
                        if (it is FeatureComponentGenerator<*>)
                            it.coreApplicationComponent = coreComponentGenerator?.component
                    } else {
                        throw NotSealedGenerator(it::class.java.simpleName)
                    }
                }
    }

}