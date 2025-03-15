package com.fsm.sevenclouds

import com.fsm.sevenclouds.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
internal fun Project.configureKotlinMultiplatform(
    extension: KotlinMultiplatformExtension
) = extension.apply {

    jvmToolchain(17)
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    jvm("desktop")

    wasmJs { browser() }

    listOf(iosX64(), iosArm64(), iosSimulatorArm64())

    applyDefaultHierarchyTemplate()


    sourceSets.apply {

        commonMain.dependencies {
            implementation(libs.findLibrary("kotlinx.serialization.json").get())
            implementation(libs.findLibrary("kotlinx.datetime").get())
            implementation(libs.findLibrary("kotlinx.coroutines.core").get())
            implementation(libs.findLibrary("koin.core").get())
            implementation(libs.findLibrary("kermit").get())
        }

        androidMain.dependencies {
            implementation(libs.findLibrary("koin.android").get())
            implementation(libs.findLibrary("kotlinx.coroutines.android").get())

        }

        jvmMain.dependencies {
            implementation(libs.findLibrary("kotlinx.coroutines.swing").get())
        }
    }
}