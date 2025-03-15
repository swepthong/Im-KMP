plugins {
    alias(libs.plugins.sevenclouds.kotlinMultiplatform)
    alias(libs.plugins.sevenclouds.composeMultiplatform)
    alias(libs.plugins.kotlin.serialization)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.fsm.sevenclouds.android.resources"
    generateResClass = always
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            api(libs.kotlinx.datetime)
        }
        androidMain.dependencies {
            implementation(libs.system.ui.controller)
        }
    }
}
