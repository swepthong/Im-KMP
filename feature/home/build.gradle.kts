plugins {
    alias(libs.plugins.sevenclouds.kotlinMultiplatform)
    alias(libs.plugins.sevenclouds.composeMultiplatform)
    alias(libs.plugins.sevenclouds.feature)
}


kotlin {
    sourceSets {
        commonMain.dependencies {
        }
    }
}