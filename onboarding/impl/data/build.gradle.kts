plugins {
    alias(libs.plugins.jujodevs.feature.data)
}

android {
    namespace = "com.jujodevs.habitsappcourse.onboarding.impl.data"
}

dependencies {
    implementation(projects.onboarding.impl.domain)
}