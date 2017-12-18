# ComponentDispatcher

It is important to have your Dagger implementation to be optimised in order to create a clear separation between features.
And every application, library or feature mostly wants to keep instances for as long as Application lifecycle. To achieve that, we create **one big** component to keep every instance in it and store that in Application class. But this already blocks having separated features!

// TODO: More explanation!

## Usage

**1.** Add library dependency to your build.gradle file:

```gradle
dependencies {    
     api 'com.yayandroid:componentdispatcher:x.y.z'
}
````

Suggested; Add it into your core module and compile it as `api` so that every other feature modules depending on core can use it.

**2.** Create `ComponentGenerator` instances. `CoreComponentGenerator` is not mandatory but if you want to share some instances from core module to your feature modules you need to define it as well.

```kotlin
class SampleCoreComponentGenerator : CoreComponentGenerator<YourCoreComponent>() {
    override fun generate(): YourCoreComponent = // Create your component here.
}

class Feature1ComponentGenerator : FeatureComponentGenerator<YourFeatureComponent>() {
    override fun generate(coreComponent: YourCoreComponent?): YourFeatureComponent = // Create your component here.
    // Please note, if you don't create an implementation of `CoreComponentGenerator` coreComponent will be null here.
}
```

**3.** Define the CoreComponentGenerator path and create a string-array that contains keys for feature generators and define it in core module `AndroidManifest`.
```xml
<application>
    <meta-data
         android:name="@string/component_generator_features"
         android:resource="@array/component_generators" />
         
    <meta-data
        android:name="@string/component_generator_core"
        android:value="com.yayandroid.componentdispatcher.sample.base.SampleCoreComponentGenerator" />
</application
```

`component_generator_features` and `component_generator_core` is required by the library. But for the rest of the features, library will use the given array as keys to the `ComponentGenerator` implementations.

**4.** Initialize

```kotlin

class SampleApplication : Application() {

  private lateinit var componentDispatcher: ComponentDispatcher

  override fun onCreate() {
      super.onCreate()
      componentDispatcher = ComponentDispatcher(this)
  }

  fun getComponentDispatcher() = componentDispatcher

}
```

**5.** Get your component right away!
```kotlin
val componentDispatcher = (application as SampleApplication).getComponentDispatcher()
val feature1Component = componentDispatcher.get<Feature1Component>()
```

You can create an extension property for componentDispatcher [like it is done in sample application.](https://github.com/yayaa/ComponentDispatcher/blob/master/base/src/main/kotlin/com/yayandroid/componentdispatcher/sample/base/SampleApplication.kt#L20)
