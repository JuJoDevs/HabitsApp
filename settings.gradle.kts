@file:Suppress("UnstableApiUsage")

include(":core:common-android")


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
include(":core:common")
include(":core:domain")
include(":core:data")
include(":core:presentation")
include(":home:api:data")
include(":home:api:domain")
include(":home:api:presentation")
include(":authentication:api:data")
include(":authentication:api:domain")
include(":authentication:api:presentation")
include(":onboarding:api:data")
include(":onboarding:api:domain")
include(":onboarding:api:presentation")
include(":settings:api:presentation")
