plugins {
    `kotlin-dsl`
}

group = "com.fsm.sevenclouds.buildlogic"

dependencies {
    compileOnly(libs.plugins.kotlin.serialization.toDep())
    compileOnly(libs.plugins.androidApplication.toDep())
    compileOnly(libs.plugins.androidLibrary.toDep())
    compileOnly(libs.plugins.composeMultiplatform.toDep())
    compileOnly(libs.plugins.kotlinMultiplatform.toDep())
    compileOnly(libs.plugins.compose.compiler.toDep())
}

fun Provider<PluginDependency>.toDep() = map {
    "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("kotlinMultiplatform") {
            id = "com.fsm.sevenclouds.kotlinMultiplatform"
            implementationClass = "KotlinMultiplatformConventionPlugin"
        }
        register("composeMultiplatform"){
            id = "com.fsm.sevenclouds.composeMultiplatform"
            implementationClass = "ComposeMultiplatformConventionPlugin"
        }
        register("androidApp") {
            id = "com.fsm.sevenclouds.androidApp"
            implementationClass = "AndroidAppConventionPlugin"
        }
        register("composeFeature") {
            id = "com.fsm.sevenclouds.feature"
            implementationClass = "ComposeFeatureConventionPlugin"
        }
    }
}