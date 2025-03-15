plugins {
    alias(libs.plugins.sevenclouds.kotlinMultiplatform)
    alias(libs.plugins.sevenclouds.composeMultiplatform)
    alias(libs.plugins.sevenclouds.feature)
}


kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.bundles.camera.group)
            //  implementation(libs.zxing.android.embedded)
            implementation(libs.barcode.scan)
            implementation(libs.vision.common)
            implementation(libs.play.services.mlkit.barcode.scanning)
        }
        commonMain.dependencies {

        }
    }
}