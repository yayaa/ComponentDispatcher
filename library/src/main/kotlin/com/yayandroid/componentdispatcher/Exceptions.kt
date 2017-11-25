package com.yayandroid.componentdispatcher

class NotRegisteredGeneratorException(requiredClass: String)
    : IllegalStateException("$requiredClass has no corresponding ComponentGenerator defined in manifest.")

class FailedToCreateGenerator(path: String)
    : IllegalStateException("A ComponentGenerator with path = $path is required, but not found. "
        + "Please make sure to define the path in manifest as full path to the generator class.")

class NotRegisteredGeneratorListException
    : IllegalStateException("R.string.component_generator_list must be defined in manifest as meta-data "
        + "with resource as string array.")

class MultipleCoreComponentException
    : IllegalStateException("Only one CoreApplicationComponent is allowed.")