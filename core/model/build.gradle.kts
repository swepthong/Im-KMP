plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlin.serialization)
}

kotlin{
    jvm()
    listOf(iosX64(), iosArm64(), iosSimulatorArm64())
    wasmJs { browser() }

}