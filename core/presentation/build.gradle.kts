plugins {
    alias(libs.plugins.jujodevs.android.library)
    alias(libs.plugins.jujodevs.android.library.compose)
}

android {
    namespace = "com.jujodevs.habitsappcourse.core.presentation"
}

dependencies {
    implementation(libs.material)
}