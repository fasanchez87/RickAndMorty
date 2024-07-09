plugins {
    id("rickmorty.android.library")
    id("rickmorty.android.library.compose")
}

android {
    namespace = "com.me.ui"
//    defaultConfig {
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//    }
}

dependencies {

    //api(project("core.designsystem"))
    //**Core UI dependent of model and designsystem modules because it uses them to represent and draw the data
    api(project(":core:model"))
    api(project(":core:utils"))
    api(project(":core:designsystem"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.glide.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}