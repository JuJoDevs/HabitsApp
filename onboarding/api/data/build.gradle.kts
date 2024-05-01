plugins {
    alias(libs.plugins.jujodevs.feature.data)
}

android {
    namespace = "com.jujodevs.habitsappcourse.onboarding.api.data"
}

dependencies {
    implementation(projects.onboarding.api.domain)
}