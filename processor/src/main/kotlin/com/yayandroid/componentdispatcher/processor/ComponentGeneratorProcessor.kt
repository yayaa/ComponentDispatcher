package com.yayandroid.componentdispatcher.processor

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import com.yayandroid.componentdispatcher.annotation.ComponentGenerator
import com.yayandroid.componentdispatcher.contract.CoreGenerator
import com.yayandroid.componentdispatcher.contract.Generator
import com.yayandroid.componentdispatcher.contract.GeneratorSource
import java.io.File
import java.lang.reflect.Type
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedOptions
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions(ComponentGeneratorProcessor.KAPT_KOTLIN_GENERATED_OPTION_NAME)
@SupportedAnnotationTypes(ComponentGeneratorProcessor.COMPONENT_GENERATOR_ANNOTATION_PATH)
class ComponentGeneratorProcessor : AbstractProcessor() {

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
        const val COMPONENT_GENERATOR_ANNOTATION_PATH = "com.yayandroid.componentdispatcher.annotation.ComponentGenerator"
        const val COMPONENT_GENERATOR_SOURCE_PACKAGE = "com.yayandroid.componentdispatcher.generated"
        const val COMPONENT_GENERATOR_SOURCE_CLASS = "ComponentGeneratorSource"
    }

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment): Boolean {
        println("ComponentGeneratorProcessor::: in process")

        val annotatedElements = roundEnv.getElementsAnnotatedWith(ComponentGenerator::class.java)
        if (annotatedElements.isEmpty()) return false

        println("ComponentGeneratorProcessor::: there are annotations")

        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: run {
            processingEnv.messager.printMessage(Diagnostic.Kind.ERROR,
                    "Can't find the target directory for generated Kotlin files.")
            return false
        }

        val generatorMap = HashMap<Type, Generator<*>>()
        var coreGenerator: CoreGenerator? = null

        annotatedElements.forEach {
            val packageName = processingEnv.elementUtils.getPackageOf(it).toString()
            val className = it.simpleName.toString()
            println("ComponentGeneratorProcessor::: Processing: $packageName.$className")

            /** TODO: Fix generatorInstance method
            val generator = generatorInstance(packageName, className)
            if (generator is CoreGenerator) {
                if (coreGenerator == null) {
                    coreGenerator = generator
                } else {
                    throw IllegalStateException("Cannot have more than one CoreGenerator!")
                }
            }

            generatorMap.put(generator.componentClass(), generator)
            */
        }

        println("ComponentGeneratorProcessor::: Generating the file...")

        val hashMapType = HashMap::class.asTypeName() // TODO: Find the way to define as HashMap<Type, Generator<*>>
        val coreGeneratorType = CoreGenerator::class.asTypeName().asNullable()
        val fileSpec = FileSpec.builder(COMPONENT_GENERATOR_SOURCE_PACKAGE, COMPONENT_GENERATOR_SOURCE_CLASS)
                .addType(TypeSpec.classBuilder(COMPONENT_GENERATOR_SOURCE_CLASS)
                        .addSuperinterface(GeneratorSource::class)
                        .addProperty(PropertySpec.builder("core", coreGeneratorType, KModifier.PRIVATE)
                                .mutable(true)
                                .initializer("$coreGenerator") // TODO: Find a way to set it
                                .build())
                        .addProperty(PropertySpec.builder("map", hashMapType, KModifier.PRIVATE)
                                .mutable(false)
                                .initializer("$generatorMap") // TODO: Find a way to set it
                                .build())
                        .addFunction(FunSpec.builder("getGeneratorMap")
                                .addModifiers(KModifier.OVERRIDE)
                                .returns(hashMapType)
                                .addStatement("return map")
                                .build())
                        .addFunction(FunSpec.builder("getCoreGenerator")
                                .addModifiers(KModifier.OVERRIDE)
                                .returns(coreGeneratorType)
                                .addStatement("return core")
                                .build())
                        .build())
                .build()
        fileSpec.writeTo(File(kaptKotlinGeneratedDir, "$COMPONENT_GENERATOR_SOURCE_CLASS.kt"))

        return true
    }

    // TODO: Always throws ClassNotFoundException
    private fun generatorInstance(packageName: String,
                                  className: String): Generator<*> {
        return Class.forName("$packageName.$className")
                .getConstructor()
                .newInstance() as Generator<*>
    }

}