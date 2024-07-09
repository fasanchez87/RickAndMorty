plugins {
    id("rickmorty.android.library")
}

android {
    namespace = "com.me.model"
}

dependencies {
    api(project(":core:utils"))
    implementation(libs.androidx.annotation.jvm)
    implementation(libs.moshi)
    implementation(libs.parcelize)
}