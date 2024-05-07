plugins {
    alias(libs.plugins.jujodevs.feature.data)
    alias(libs.plugins.jujodevs.android.room)
    alias(libs.plugins.jujodevs.android.moshi)
    alias(libs.plugins.jujodevs.android.retrofit)
}

android {
    namespace = "com.jujodevs.habitsappcourse.home.api.data"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.home.impl.domain)
    // WorkManager
    implementation(libs.work.runtime.ktx)
    implementation(libs.hilt.work)
}