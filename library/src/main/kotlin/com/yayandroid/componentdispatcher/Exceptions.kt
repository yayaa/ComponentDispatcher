package com.yayandroid.componentdispatcher

class NotRegisteredGenerator(requiredClass: String)
    : IllegalStateException("$requiredClass has no corresponding ComponentGenerator defined in manifest.")

class NotSealedGenerator(generatorClass: String)
    : IllegalStateException("$generatorClass is not one of the Sealed types. " +
        "Please extend either CoreComponentGenerator or FeatureComponentGenerator")