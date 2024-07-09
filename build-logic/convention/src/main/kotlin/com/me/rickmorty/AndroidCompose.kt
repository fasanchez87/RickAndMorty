package com.me.rickmorty

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Configure Compose-specific options for an Android project.
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {

        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("androidxComposeCompiler").get().toString()
        }


        dependencies {
            val bom = libs.findLibrary("compose-bom").get()
            add("implementation", platform(bom))
            add("implementation", libs.findLibrary("activityCompose").get())
            add("implementation", libs.findLibrary("composeUi").get())
            add("implementation", libs.findLibrary("composeUiGraphics").get())
            add("implementation", libs.findLibrary("composeUiToolingPreview").get())
            add("implementation", libs.findLibrary("composeMaterial3").get())
            add("implementation", libs.findLibrary("lifecycleViewmodelCompose").get())
            add("implementation", libs.findLibrary("androidx-runtime-android").get())
            add("implementation", libs.findLibrary("androidx-compose-runtime").get())
            add("implementation", libs.findLibrary("androidx-compose-runtime-livedata").get())
            add("implementation", libs.findLibrary("androidx-compose-ui-tooling").get())
            add("implementation", libs.findLibrary("androidx-hilt-navigation-compose").get())
            add("implementation", libs.findLibrary("coil-kt-compose").get())
            add("implementation", libs.findLibrary("compose-navigation").get())
            add("androidTestImplementation", platform(bom))

            //add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
            //add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
        }

//        dependencies {
//            val bom = libs.findLibrary("compose-bom").get()
//            add("implementation", platform(bom))
//            add("androidTestImplementation", platform(bom))
//            //add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
//            //add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
//        }

//        testOptions {
//            unitTests {
//                // For Robolectric
//                isIncludeAndroidResources = true
//            }
//        }
    }

//    tasks.withType<KotlinCompile>().configureEach {
//        kotlinOptions {
//            freeCompilerArgs += buildComposeMetricsParameters()
//            freeCompilerArgs += stabilityConfiguration()
//        }
//    }
}
//
//private fun Project.buildComposeMetricsParameters(): List<String> {
//    val metricParameters = mutableListOf<String>()
//    val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
//    val relativePath = projectDir.relativeTo(rootDir)
//    val buildDir = layout.buildDirectory.get().asFile
//    val enableMetrics = (enableMetricsProvider.orNull == "true")
//    if (enableMetrics) {
//        val metricsFolder = buildDir.resolve("compose-metrics").resolve(relativePath)
//        metricParameters.add("-P")
//        metricParameters.add(
//            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + metricsFolder.absolutePath,
//        )
//    }
//
//    val enableReportsProvider = project.providers.gradleProperty("enableComposeCompilerReports")
//    val enableReports = (enableReportsProvider.orNull == "true")
//    if (enableReports) {
//        val reportsFolder = buildDir.resolve("compose-reports").resolve(relativePath)
//        metricParameters.add("-P")
//        metricParameters.add(
//            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + reportsFolder.absolutePath
//        )
//    }
//    return metricParameters.toList()
//}
//
//private fun Project.stabilityConfiguration() = listOf(
//    "-P",
//    "plugin:androidx.compose.compiler.plugins.kotlin:stabilityConfigurationPath=${project.rootDir.absolutePath}/compose_compiler_config.conf",
//)
