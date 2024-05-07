plugins {
    alias(libs.plugins.jujodevs.feature.data)
}

android {
    namespace = "com.jujodevs.habitsappcourse.authentication.api.data"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.authentication.impl.domain)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(libs.play.services.auth)
}