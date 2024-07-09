plugins {
    //id("com.android.library")
    //id("org.jetbrains.kotlin.android")
    id("rickmorty.android.library")
    id("rickmorty.android.library.compose")
}

android {
    namespace = "com.me.utils"
}

dependencies {
    api(libs.timber.library)
    implementation(libs.moshi)
    implementation(libs.gson)
    implementation(libs.androidx.databinding.common)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.transition.ktx)
}