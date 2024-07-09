plugins {
    id("rickmorty.android.library")
    id("rickmorty.android.hilt")
    kotlin("kapt")
}

android {
    namespace = "com.me.network"

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            buildConfigField("String", "BASE_PATH", "\"https://rickandmortyapi.com/\"")
        }
        debug {
            buildConfigField("String", "BASE_PATH", "\"https://rickandmortyapi.com/\"")
        }
    }
}

dependencies {

    kapt(libs.hilt.compiler)
    kapt(libs.moshi.codegen)

    api(project(":core:model"))
    api(project(":core:utils"))
    api(libs.retrofit)

    implementation(libs.moshi)
    implementation(libs.hilt.android)
    implementation(libs.retrofit.moshi)
    implementation(libs.retrofit.gson)
    implementation(libs.gson)
    implementation(libs.okhttp)
}