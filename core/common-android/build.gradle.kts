plugins {
    alias(libs.plugins.jujodevs.android.library)
    alias(libs.plugins.jujodevs.android.hilt)
}

android {
    namespace = "com.jujodevs.habitsappcourse.core.common_android"
}

dependencies {
    implementation(projects.core.common)
}