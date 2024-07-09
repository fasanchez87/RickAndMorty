
import com.me.rickmorty.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            pluginManager.apply {
                apply("rickmorty.android.library")
                apply("rickmorty.android.hilt")
            }

            dependencies {
                // Core dependencies that are shared across all *Features
                add("api", project(":core:ui"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:routes"))
                //add("implementation", project(":domain"))

                add("testImplementation", kotlin("test"))
                add("androidTestImplementation", kotlin("test"))

                add("implementation", libs.findLibrary("androidx-core").get())
                add("implementation", libs.findLibrary("activityCompose").get())
//                add("implementation", libs.findLibrary("coil.kt").get())
                add("implementation", libs.findLibrary("coil-kt-compose").get())
                add("implementation", libs.findLibrary("compose-navigation").get())
                add("implementation", libs.findLibrary("androidx-hilt-navigation-compose").get())
                add("implementation", libs.findLibrary("kotlinx-coroutines-android").get())
            }
        }
    }
}
