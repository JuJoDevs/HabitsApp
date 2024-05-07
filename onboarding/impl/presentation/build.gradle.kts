plugins {
    alias(libs.plugins.jujodevs.feature.presentation)
}

android {
    namespace = "com.jujodevs.habitsappcourse.onboarding.impl.presentation"
}

dependencies {
    implementation(projects.onboarding.impl.domain)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
}