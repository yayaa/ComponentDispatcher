package com.yayandroid.componentdispatcher

import android.app.Application
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import java.lang.reflect.Type

object ComponentDispatcher {

    private val TAG = ComponentDispatcher::class.java.name
    @Suppress("MemberVisibilityCanPrivate")
    @PublishedApi internal var coreComponentGenerator: ComponentGenerator<*>? = null
    @PublishedApi internal val generatorMap = HashMap<Type, ComponentGenerator<*>>()

    fun initialize(application: Application) {
        val metaData = readMetaData(application)

        val generatorListResId = metaData.getInt(application.getString(R.string.component_generator_list))
        require(generatorListResId != 0, { throw NotRegisteredGeneratorListException() })

        readComponentGenerators(application, generatorListResId, metaData)
    }

    inline fun <reified T : ApplicationComponent> get(): T = generatorMap[T::class.java]
            ?.let {
                it.coreApplicationComponent = coreComponentGenerator?.component as CoreApplicationComponent?
                return@let it.component as T
            } ?: run { throw NotRegisteredGeneratorException(T::class.java.name) }

    private fun readMetaData(application: Application): Bundle {
        return application.packageManager
                .getApplicationInfo(application.packageName, PackageManager.GET_META_DATA)
                .metaData
    }

    private fun readComponentGenerators(application: Application, generatorListResId: Int, metaData: Bundle) {
        readPathKeys(application, generatorListResId).forEach { pathName ->
            metaData.getString(pathName)?.let {
                Log.i(TAG, "$pathName found as: $it")
                createGenerator(application, it)
            } ?: run {
                Log.i(TAG, "ComponentGenerator path is not registered in Manifest for: $pathName")
            }
        }
    }

    private fun readPathKeys(application: Application, generatorListResId: Int): Array<String> {
        try {
            return application.resources.getStringArray(generatorListResId)
        } catch (e: Resources.NotFoundException) {
            throw NotRegisteredGeneratorListException()
        }
    }

    private fun createGenerator(application: Application, generatorPath: String) {
        val fullPath = application.packageName + generatorPath
        Log.i(TAG, "Create ComponentGenerator Instance: $fullPath")
        try {
            val componentGenerator
                    = application.classLoader.loadClass(fullPath).getConstructor().newInstance() as ComponentGenerator<*>
            generatorMap.put(componentGenerator.componentClass(), componentGenerator)
            Log.i(TAG, "Mapped ComponentGenerator Instance: $componentGenerator")

            if (componentGenerator is CoreComponentGenerator) {
                require(coreComponentGenerator == null, { throw MultipleCoreComponentException() })
                coreComponentGenerator = componentGenerator
                Log.i(TAG, "CoreComponentGenerator is found as: $coreComponentGenerator")
            }
        } catch (e: ClassNotFoundException) {
            throw FailedToCreateGenerator(generatorPath)
        }
    }

}
