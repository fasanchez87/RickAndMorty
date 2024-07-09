plugins {
    id("rickmorty.android.feature")
    id("rickmorty.android.library")
    id("rickmorty.android.hilt")
    id("rickmorty.android.library.compose")
}

android {
    namespace = "com.me.characters"
}

dependencies {
    implementation(project(":core:data"))
}