plugins {
    alias(libs.plugins.jujodevs.feature.presentation)
}

android {
    namespace = "com.jujodevs.habitsappcourse.home.api.presentation"
}

dependencies {
    implementation(projects.home.api.domain)
    implementation(projects.core.domain)
    // Permissions
    implementation(libs.accompanist.permissions)
    // Time picker
    implementation(libs.sheets.compose.dialogs.core)
    implementation(libs.sheets.compose.dialogs.clock)
}