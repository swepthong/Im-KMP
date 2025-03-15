plugins {
    alias(libs.plugins.sevenclouds.kotlinMultiplatform)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core.common)
            implementation(projects.core.model)
            api(projects.core.imsdk)
            api(projects.core.network)
        }
    }
}
