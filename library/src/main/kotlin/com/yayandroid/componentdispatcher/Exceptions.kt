package com.yayandroid.componentdispatcher

class NotCoreComponentGenerator(path: String)
    : IllegalStateException("R.string.component_generator_core should target a CoreComponentGenerator instance. "
        + "$path is not an instance of CoreComponentGenerator.")

class NotRegisteredGenerator(requiredClass: String)
    : IllegalStateException("$requiredClass has no corresponding ComponentGenerator defined in manifest.")

class FailedToCreateGenerator(path: String)
    : IllegalStateException("A ComponentGenerator with path = $path is required, but not found. "
        + "Please make sure to define the path in manifest as full path to the generator class.")

class NotRegisteredGeneratorList
    : IllegalStateException("R.string.component_generator_list must be defined in manifest as meta-data "
        + "with resource as string array.")

class NoEmptyConstructor(path: String)
    : IllegalStateException("$path doesn't have an empty constructor.")