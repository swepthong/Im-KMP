plugins {
    alias(libs.plugins.sevenclouds.kotlinMultiplatform)
    alias(libs.plugins.sevenclouds.composeMultiplatform)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.com.github.wukongim.wukongimandroidsdk10)
            implementation(libs.android.database.sqlcipher)
            implementation (libs.androidx.sqlite.ktx)
            implementation (libs.curve25519.android)
            implementation (libs.signal.protocol.android)
        }
        commonMain.dependencies {
            implementation(projects.core.model)
            implementation(projects.core.common)

            implementation(libs.kotlinx.serialization.json)
        }
        desktopMain.dependencies {
        }
        iosMain.dependencies {
        }
    }
}