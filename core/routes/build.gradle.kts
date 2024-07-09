plugins {
    id("rickmorty.android.library")
    id("rickmorty.android.library.compose")
}

android {
    namespace = "com.me.routes"
}

dependencies {
    implementation(project(":core:model"))
    //Serialization for secure routes by compose navigation
    implementation(libs.kotlinx.serialization.json)
}