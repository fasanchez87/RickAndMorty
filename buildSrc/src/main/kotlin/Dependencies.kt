
object Dependencies {

    object MainSettings {
        const val targetSdkVersion = 34
        const val compileSdk = 34
        const val minSdk = 26
        const val applicationId = "com.me.rickmorty"
        const val versionName = "1.0.1"
        const val versionCode = 2
    }

    private const val room_version = "2.5.0"
    private const val hilt = "2.44"

    val appDependencies = mutableListOf<String>().apply {

        add("androidx.compose.ui:ui")
        add("androidx.compose.ui:ui-graphics")
        add("androidx.compose.ui:ui-tooling-preview")
        add("androidx.compose.material3:material3")

        val vlifecycle = "2.5.1"
        add("androidx.lifecycle:lifecycle-reactivestreams-ktx:$vlifecycle")
        add("androidx.lifecycle:lifecycle-livedata-ktx:$vlifecycle")
        add("androidx.lifecycle:lifecycle-runtime-ktx:$vlifecycle")

        val vglide = "4.14.1"
        add("com.github.bumptech.glide:glide:$vglide")
        add("com.github.bumptech.glide:compose:1.0.0-beta01")

        val coroutines = "1.6.4"
        add("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")
        add("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")

        val gson = "2.9.1"
        add("com.google.code.gson:gson:$gson")

        val apacheArtifact = "3.8.6"
        add("org.apache.maven:maven-artifact:$apacheArtifact")

        val crypto = "1.1.0-alpha03"
        add("androidx.security:security-crypto-ktx:$crypto")

        val moshi = "1.14.0"
        add("com.squareup.moshi:moshi-kotlin:$moshi")

        val activity_result = "0.2.0"
        add("com.afollestad.inline-activity-result:core:${activity_result}")
        add("com.afollestad.inline-activity-result:coroutines:${activity_result}")

        val okhttp = "4.10.0"
        add("com.squareup.okhttp3:logging-interceptor:${okhttp}")

        val retrofit = "2.9.0"
        add("com.squareup.retrofit2:converter-gson:${retrofit}")
        add("com.squareup.retrofit2:retrofit:${retrofit}")
        add("com.squareup.retrofit2:converter-moshi:${retrofit}")

        val core_ktx = "1.9.0"
        add("androidx.core:core-ktx:${core_ktx}")

        val lifecycle_runtime = "2.6.2"
        add("androidx.lifecycle:lifecycle-runtime-ktx:${lifecycle_runtime}")

        val compose = "1.7.0"
        add("androidx.activity:activity-compose:${compose}")

        val material = "1.10.0"
        add("com.google.android.material:material:${material}")

        val constraintlayout = "2.1.4"
        add("androidx.constraintlayout:constraintlayout:${constraintlayout}")

        val appCompat_version = "1.6.1"
        add("androidx.appcompat:appcompat:${appCompat_version}")

        val coroutines_play_services = "1.6.4"
        add("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${coroutines_play_services}")

        val timber = "5.0.1"
        add("com.jakewharton.timber:timber:${timber}")

        val ktx_lifecycle = "2.5.1"
        add("androidx.lifecycle:lifecycle-viewmodel-ktx:${ktx_lifecycle}")

        val gms = "20.3.0"
        add("com.google.android.gms:play-services-auth:${gms}")

        val android_ktx = "1.6.0"
        add("androidx.activity:activity-ktx:${android_ktx}")

        add("com.google.dagger:hilt-android:${hilt}")

        val test_core = "1.4.0"
        add("androidx.test:core:${test_core}")

        val paging = "3.0.0"
        add("androidx.paging:paging-runtime-ktx:${paging}")

        val coil = "1.4.0"
        add("io.coil-kt:coil-compose:${coil}")

        add("androidx.compose.runtime:runtime-livedata:1.6.0")

        add("androidx.navigation:navigation-compose:2.7.7")
        add("androidx.hilt:hilt-navigation-compose:1.2.0")



    }

    val appAnnotationProcessor = mutableListOf<String>().apply {
        add("androidx.room:room-compiler:${room_version}")
    }

    val appKapt = mutableListOf<String>().apply {
        val moshi = "1.14.0"
        add("androidx.room:room-compiler:${room_version}")
        add("com.squareup.moshi:moshi-kotlin-codegen:${moshi}")

        add("com.google.dagger:hilt-android-compiler:${hilt}")

//        val metadata_jvm = "0.5.0"
//        add("org.jetbrains.kotlinx:kotlinx-metadata-jvm:${metadata_jvm}")

       // add("androidx.hilt:hilt-compiler:1.0.0")

    }

    val appKaptAndroidTest = mutableListOf<String>().apply {
        add("com.google.dagger:hilt-android-compiler:${hilt}")
    }

    val appKaptTest = mutableListOf<String>().apply {
        add("com.google.dagger:hilt-android-compiler:${hilt}")
    }

    val appDesugaring = mutableListOf<String>().apply {
        val desugar_jdk_libs = "1.2.2"
        add("com.android.tools:desugar_jdk_libs:${desugar_jdk_libs}")
    }

    val platform = mutableListOf<String>().apply {
        val firebase = "32.0.0"
        add("com.google.firebase:firebase-bom:$firebase")

        val compose_bom = "2023.03.00"
        add("androidx.compose:compose-bom:$compose_bom")
    }

    val testImplementation = mutableListOf<String>().apply {
        val junit = "4.13.2"
        add("junit:junit:$junit")

        val hamcrest = "1.3"
        add("org.hamcrest:hamcrest-all:$hamcrest")

        add("org.mockito:mockito-core:4.2.0")
        add("org.mockito:mockito-inline:3.6.28")
        add("androidx.arch.core:core-testing:2.1.0")
        add("androidx.test.ext:junit-ktx:1.1.3")
        add("androidx.test:core-ktx:1.4.0")
        add("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
        add("io.mockk:mockk:1.12.0")
        add("org.robolectric:robolectric:4.9")
        add("com.google.truth:truth:1.1.3")
        add("com.google.dagger:hilt-android-testing:$hilt")
    }

    val androidTestImplementation = mutableListOf<String>().apply {
        val junit = "1.1.5"
        add("androidx.test.ext:junit:$junit")

        val junit_test = "4.13.2"
        add("junit:junit:$junit_test")

        val espresso_core = "3.5.1"
        add("androidx.test.espresso:espresso-core:$espresso_core")

        val ui_test_junit4 = "1.0.0-alpha08"
        add("androidx.compose.ui:ui-test-junit4:$ui_test_junit4")

        add("com.google.dagger:hilt-android-testing:$hilt")

        val coroutines_test = "1.6.4"
        add("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_test")

        val test_runner = "1.4.0"
        add("androidx.test:runner:$test_runner")

        val test_core = "1.4.0"
        add("androidx.test:core:$test_core")

        add("org.mockito:mockito-core:4.2.0")
        add("androidx.test.espresso:espresso-contrib:3.4.0")
        add("com.linkedin.dexmaker:dexmaker-mockito-inline-extended:2.28.1")
        add("androidx.test:core-ktx:1.4.0")
        add("androidx.test.ext:junit-ktx:1.1.3")
        add("androidx.test.espresso:espresso-intents:3.4.0")
        add("io.mockk:mockk:1.12.0")
    }

    val debugImplementation = mutableListOf<String>().apply {
        add("androidx.compose.ui:ui-tooling")
        add("androidx.compose.ui:ui-test-manifest")

        val fragment_testing = "1.5.3"
        add("androidx.fragment:fragment-testing:$fragment_testing")
    }

    object Classpath {
        const val androidGradlePlugin = "com.android.tools.build:gradle:8.0.1"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10"
        const val googleServices = "com.google.gms:google-services:4.3.15"
        const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-gradle:2.9.5"
        const val ktlintPlugin = "org.jlleitschuh.gradle:ktlint-gradle:11.3.2"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:2.44"
    }

    fun getVersionName(): String = "VersionName_" + MainSettings.versionName + "_" + MainSettings.versionCode
}