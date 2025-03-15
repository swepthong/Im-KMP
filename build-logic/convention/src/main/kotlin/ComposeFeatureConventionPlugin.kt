import com.fsm.sevenclouds.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension


class ComposeFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain {
                    dependencies {
                        api(project(":core:common"))
                        implementation(project(":core:permissions"))
                        api(project(":core:domain"))
                        implementation(project(":core:designsystem"))

                        implementation(libs.findLibrary("koin.compose").get())
                        implementation(libs.findLibrary("koin.composeVM").get())
                        implementation(libs.findLibrary("compose.navigation").get())
                        implementation(libs.findLibrary("androidx.lifecycle.viewmodel").get())
                        implementation(libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                        implementation(libs.findLibrary("system.ui.controller").get())
                       // implementation(libs.findLibrary("filekit.core").get())
                        implementation(libs.findLibrary("coil.compose").get())
                    }
                }
            }
        }
    }
}