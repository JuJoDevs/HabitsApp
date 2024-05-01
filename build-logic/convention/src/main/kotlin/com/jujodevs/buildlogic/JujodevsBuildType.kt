package com.jujodevs.buildlogic

enum class JujodevsBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
}