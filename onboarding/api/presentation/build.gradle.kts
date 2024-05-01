plugins {
    alias(libs.plugins.jujodevs.feature.presentation)
}

android {
    namespace = "com.jujodevs.habitsappcourse.onboarding.api.presentation"
}

dependencies {
    implementation(projects.onboarding.api.domain)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
}