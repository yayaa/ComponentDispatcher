package com.yayandroid.componentdispatcher

import android.app.Application
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Bundle
import android.util.Log

object ComponentDispatcher {

    private val TAG = ComponentDispatcher::class.java.name
    @PublishedApi internal val generatorMap = HashMap<String, ComponentGenerator<*>>()

    fun initialize(application: Application) {
        val metaData = readMetaData(application)

        val generatorListResId = metaData.getInt(application.getString(R.string.component_generator_list))
        require(generatorListResId != 0, { throw NotRegisteredGeneratorListException() })

        readPathKeys(application, generatorListResId).forEach { pathName ->
            metaData.getString(pathName)?.let {
                Log.i(TAG, "$pathName found as: $it")
                createGenerator(application, it).also {
                    generatorMap.put(it.componentKey(), it)
                    Log.i(TAG, "Mapped ComponentGenerator Instance: $it")
                }
            } ?: run {
                Log.i(TAG, "ComponentGenerator path is not registered in Manifest for: $pathName")
            }
        }
    }

    inline fun <reified T : ApplicationComponent> get(key: String): T = generatorMap[key]
            ?.let { return@let it.component as T } ?: run { throw NotRegisteredGeneratorException(key) }

    private fun readPathKeys(application: Application, generatorListResId: Int): Array<String> {
        try {
            return application.resources.getStringArray(generatorListResId)
        } catch (e: Resources.NotFoundException) {
            throw NotRegisteredGeneratorListException()
        }
    }

    private fun readMetaData(application: Application): Bundle {
        return application.packageManager
                .getApplicationInfo(application.packageName, PackageManager.GET_META_DATA)
                .metaData
    }

    private fun createGenerator(application: Application, generatorPath: String): ComponentGenerator<*> {
        val fullPath = application.packageName + generatorPath
        Log.i(TAG, "Create ComponentGenerator Instance: $fullPath")
        try {
            return application.classLoader.loadClass(fullPath).getConstructor().newInstance() as ComponentGenerator<*>
        } catch (e: ClassNotFoundException) {
            throw FailedToCreateGenerator(generatorPath)
        }
    }

}
