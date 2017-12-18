package com.yayandroid.componentdispatcher

import android.app.Application
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import java.lang.reflect.Type

class ComponentDispatcher(application: Application) {

    private val TAG = ComponentDispatcher::class.java.name
    private val CORE_RES_ID = R.string.component_generator_core
    private val FEATURES_RES_ID = R.string.component_generator_features

    @Suppress("MemberVisibilityCanPrivate")
    @PublishedApi internal val generatorMap = HashMap<Type, ComponentGenerator<*>>()

    init {
        val metaData = readMetaData(application)

        val generatorListResId = metaData.getInt(application.getString(FEATURES_RES_ID))
        require(generatorListResId != 0, { throw NotRegisteredGeneratorList() })

        readFeatureComponentGenerators(application, metaData, generatorListResId,
                readCoreComponentGenerator(application, metaData))
    }

    inline fun <reified T : ApplicationComponent> get(): T = generatorMap[T::class.java]
            ?.let { return it.component as T }
            ?: run { throw NotRegisteredGenerator(T::class.java.name) }

    private fun readMetaData(application: Application): Bundle {
        return application.packageManager
                .getApplicationInfo(application.packageName, PackageManager.GET_META_DATA)
                .metaData
    }

    private fun readCoreComponentGenerator(application: Application, metaData: Bundle): CoreComponentGenerator<*>? {
        return metaData.getString(application.getString(CORE_RES_ID))?.let {
            val componentGenerator = createGenerator(application, it)
            if (componentGenerator is CoreComponentGenerator) {
                return componentGenerator
            } else {
                throw NotCoreComponentGenerator(it)
            }
        }
    }

    private fun readFeatureComponentGenerators(application: Application, metaData: Bundle, generatorListResId: Int,
                                               coreComponentGenerator: CoreComponentGenerator<*>?) {
        readPathKeys(application, generatorListResId).forEach { pathName ->
            metaData.getString(pathName)?.let {
                Log.i(TAG, "$pathName found as: $it")
                createGenerator(application, it, coreComponentGenerator)
            } ?: run {
                Log.i(TAG, "ComponentGenerator path is not registered in Manifest for: $pathName")
            }
        }
    }

    private fun readPathKeys(application: Application, generatorListResId: Int): Array<String> {
        try {
            return application.resources.getStringArray(generatorListResId)
        } catch (e: Resources.NotFoundException) {
            throw NotRegisteredGeneratorList()
        }
    }

    private fun createGenerator(application: Application, generatorPath: String,
                                coreComponentGenerator: CoreComponentGenerator<*>? = null): ComponentGenerator<*> {
        Log.i(TAG, "Create ComponentGenerator Instance: $generatorPath")

        try {
            val componentGenerator = application.classLoader.loadClass(generatorPath)
                    .getConstructor().newInstance() as ComponentGenerator<*>

            componentGenerator.application = application
            if (componentGenerator is FeatureComponentGenerator) {
                componentGenerator.coreApplicationComponent = coreComponentGenerator?.component
            }

            generatorMap.put(componentGenerator.componentClass(), componentGenerator)
            Log.i(TAG, "Mapped ComponentGenerator Instance: $componentGenerator")
            return componentGenerator
        } catch (e: ClassNotFoundException) {
            throw FailedToCreateGenerator(generatorPath)
        } catch (e: NoSuchMethodException) {
            throw NoEmptyConstructor(generatorPath)
        }
    }

}