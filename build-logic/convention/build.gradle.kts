import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.jujodevs.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    //compileOnly(libs.firebase.crashlytics.gradlePlugin)
    //compileOnly(libs.firebase.performance.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("jujodevsLibrary") {
            id = "jujodevs.library"
            implementationClass = "JujodevsLibraryConventionPlugin"
        }
    }
    plugins {
        register("jujodevsAndroidLibrary") {
            id = "jujodevs.android.library"
            implementationClass = "JujodevsAndroidLibraryConventionPlugin"
        }
    }
    plugins {
        register("jujodevsAndroidLibraryCompose") {
            id = "jujodevs.android.library.compose"
            implementationClass = "JujodevsAndroidLibraryComposeConventionPlugin"
        }
    }
    plugins {
        register("jujodevsFeatureData") {
            id = "jujodevs.feature.data"
            implementationClass = "JujodevsFeatureData"
        }
    }
    plugins {
        register("jujodevsFeatureDomain") {
            id = "jujodevs.feature.domain"
            implementationClass = "JujodevsFeatureDomain"
        }
    }
    plugins {
        register("jujodevsFeaturePresentation") {
            id = "jujodevs.feature.presentation"
            implementationClass = "JujodevsFeaturePresentation"
        }
    }
    plugins {
        register("jujodevsApplicationFlavorsConventionPlugin") {
            id = "jujodevs.application.flavors"
            implementationClass = "JujodevsApplicationFlavorsConventionPlugin"
        }
    }
    plugins {
        register("jujodevsAndroidHiltConventionPlugin") {
            id = "jujodevs.android.hilt"
            implementationClass = "JujodevsAndroidHiltConventionPlugin"
        }
    }
    plugins {
        register("jujodevsandroidRoomConventionPlugin") {
            id = "jujodevs.android.room"
            implementationClass = "JujodevsAndroidRoomConventionPlugin"
        }
    }
    plugins {
        register("jujodevsAndroidMoshiConventionPlugin") {
            id = "jujodevs.android.moshi"
            implementationClass = "JujodevsAndroidMoshiConventionPlugin"
        }
    }
    plugins {
        register("jujodevsAndroidRetrofitConventionPlugin") {
            id = "jujodevs.android.retrofit"
            implementationClass = "JujodevsAndroidRetrofitConventionPlugin"
        }
    }
}
