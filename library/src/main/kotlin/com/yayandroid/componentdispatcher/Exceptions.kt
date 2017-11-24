package com.yayandroid.componentdispatcher

class NotRegisteredGeneratorException(key: String)
    : IllegalStateException("Required $key has no corresponding ComponentGenerator defined in manifest.")

class FailedToCreateGenerator(path: String)
    : IllegalStateException("Required $path has no corresponding ComponentGenerator. "
        + "Please make sure to define the path in manifest as full path to generator class without package name prefix "
        + "but starts with `.`")

class NotRegisteredGeneratorListException
    : IllegalStateException("R.string.component_generator_list must be defined in manifest as meta-data "
        + "with resource as string array.")