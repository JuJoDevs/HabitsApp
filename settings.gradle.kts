@file:Suppress("UnstableApiUsage")


gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "HabitsAppCourse"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":navigation")
include(":core:common")
include(":core:common-android")
include(":core:domain")
include(":core:data")
include(":core:presentation")
include(":home:impl:data")
include(":home:impl:domain")
include(":home:impl:presentation")
include(":authentication:impl:data")
include(":authentication:impl:domain")
include(":authentication:impl:presentation")
include(":onboarding:impl:data")
include(":onboarding:impl:domain")
include(":onboarding:impl:presentation")
include(":settings:impl:presentation")
