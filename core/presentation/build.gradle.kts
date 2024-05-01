plugins {
    alias(libs.plugins.jujodevs.android.library)
    alias(libs.plugins.jujodevs.android.library.compose)
}

android {
    namespace = "com.jujodevs.habitsappcourse.core.presentation"
}

dependencies {
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.tooling.preview)
    api(libs.androidx.material3)
}