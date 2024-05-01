plugins {
    alias(libs.plugins.jujodevs.android.library)
    alias(libs.plugins.jujodevs.android.hilt)
}

android {
    namespace = "com.jujodevs.habitsappcourse.core.data"
}

dependencies {
    implementation(libs.work.runtime.ktx)
}