plugins {
    alias(libs.plugins.sevenclouds.kotlinMultiplatform)
    alias(libs.plugins.sevenclouds.composeMultiplatform)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core.common)
            implementation(libs.coil.compose)
        }
    }
}