import java.util.Properties

plugins {
    alias(libs.plugins.sevenclouds.kotlinMultiplatform)
    alias(libs.plugins.kotlin.serialization)
    //alias(libs.plugins.buildConfig)
}

val secretKeyProperties by lazy {
    val secretKeyPropertiesFile = rootProject.file("secrets.properties")
    Properties().apply { secretKeyPropertiesFile.inputStream().use { secret -> load(secret) } }
}

kotlin {
    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }
        commonMain.dependencies {
            implementation(projects.core.model)
            implementation(projects.core.common)

            implementation(libs.kotlinx.serialization.json)
            api(libs.bundles.ktor)
        }
        desktopMain.dependencies {
            implementation(libs.ktor.client.java)
        }
        iosMain.dependencies {
            implementation(libs.ktor.darwin.ios)
        }
    }
}

/*
buildConfig {
    packageName = "com.fsm.sevenclouds"
    useKotlinOutput { internalVisibility = true }
    buildConfigField(
        "String",
        "RIJKSMUSEUM_API_KEY",
        "\"${secretKeyProperties["rijksmuseum.api.key"]}\""
    )
}*/
