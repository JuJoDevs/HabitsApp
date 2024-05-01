plugins {
    alias(libs.plugins.jujodevs.feature.presentation)
}

android {
    namespace = "com.jujodevs.habitsappcourse.authentication.api.presentation"
}

dependencies {
    implementation(projects.authentication.api.domain)
}