plugins {
//    id("com.android.application")
//    id("org.jetbrains.kotlin.android")
//    id("kotlin-kapt")
//    id("kotlin-parcelize")
//    //id("com.google.gms.google-services")
//    //id("com.google.firebase.crashlytics")
//    id("com.google.dagger.hilt.android")
//    id("dagger.hilt.android.plugin")
//    //id("rickmorty.android.application.compose")
    id("rickmorty.android.application.compose")
    id("rickmorty.android.application")
    //id("rickmorty.android.library.compose")
    //id("rickmorty.android.library")
    id("rickmorty.android.hilt")
}

android {

    //compileSdk = Dependencies.MainSettings.compileSdk

    defaultConfig {
        applicationId = "com.me.rickmorty"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level
        //versionCode = libs.versions.versionCode.get().toInt()
        //versionName = libs.versions.versionName.get()
        namespace = "com.me.rickmorty"
        vectorDrawables.useSupportLibrary = true

//        javaCompileOptions {
//            annotationProcessorOptions {
//                arguments["dagger.hilt.disableModulesHaveInstallInCheck"] = "true"
//            }
//        }

        // Allow references to generated code
        kapt {
            correctErrorTypes = true
        }

    }

//    defaultConfig {
//       // versionName = Dependencies.MainSettings.versionName
//       // versionCode = Dependencies.MainSettings.versionCode
//       // applicationId = Dependencies.MainSettings.applicationId
//        //namespace = Dependencies.MainSettings.applicationId
//        //minSdk = Dependencies.MainSettings.minSdk
//        //targetSdk = Dependencies.MainSettings.targetSdkVersion
//        //setProperty("archivesBaseName", Dependencies.getVersionName())
//        //vectorDrawables.useSupportLibrary = true
//        //testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        //testInstrumentationRunner = "com.me.runner.HiltTestRunner" ****THIS GO TO MODULE CORE
////        javaCompileOptions {
////            annotationProcessorOptions {
////                arguments["dagger.hilt.disableModulesHaveInstallInCheck"] = "true"
////            }
////        }
//
//        // Allow references to generated code
////        kapt {
////            correctErrorTypes = true
////        }
//    }
//
//    compileOptions {
//        isCoreLibraryDesugaringEnabled = true
//        sourceCompatibility = JavaVersion.VERSION_17
//        targetCompatibility = JavaVersion.VERSION_17
//    }

    lint {
        disable.add("NullSafeMutableLiveData")
    }

    buildTypes {
        release {
            isMinifyEnabled = true //Enable R8 code shrinking to remove unused code as well as obfuscate the code
            isShrinkResources = true //Enable resource shrinking (images, resources, etc.)
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            //buildConfigField("String", "BASE_PATH", "\"https://rickandmortyapi.com/\"")
        }
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            //buildConfigField("String", "BASE_PATH", "\"https://rickandmortyapi.com/\"")
        }
    }

    testOptions.unitTests {
        isIncludeAndroidResources = true
    }

//    kotlinOptions {
//        jvmTarget = "17"
//    }

    buildFeatures {
    //    viewBinding = true
     //   dataBinding = true
        compose = true
    //    buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

//kapt {
//    correctErrorTypes = true
//}

dependencies {

    implementation(project(":core:utils"))
    implementation(project(":core:ui"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:data"))
    implementation(project(":core:routes"))
    implementation(project(":core:model"))

    implementation(project(":feature:characters"))
    implementation(project(":feature:splash"))


   // implementation(libs.core.ktx)

//    implementation(platform(libs.compose.bom))
//    implementation(libs.activityCompose)
//    implementation(libs.composeUi)
//    implementation(libs.composeUiGraphics)
//    implementation(libs.composeUiToolingPreview)
//    implementation(libs.composeMaterial3)
//    implementation(libs.lifecycleViewmodelCompose)
//    implementation(libs.navigationCompose)
//    implementation(libs.androidx.runtime.android)


//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.google.android.material:material:1.10.0")
//    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
//    implementation("androidx.activity:activity-compose:1.8.2")
//    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
//    implementation("androidx.compose.ui:ui")
//    implementation("androidx.compose.ui:ui-graphics")
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    implementation("androidx.compose.material3:material3")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
//    implementation("androidx.navigation:navigation-compose:2.7.7")
//    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
//    debugImplementation("androidx.compose.ui:ui-tooling")
//    debugImplementation("androidx.compose.ui:ui-test-manifest")

    Dependencies.testImplementation.forEach {
        testImplementation(it)
    }

    Dependencies.androidTestImplementation.forEach {
        androidTestImplementation(it)
    }

    Dependencies.debugImplementation.forEach {
        debugImplementation(it)
    }

    Dependencies.platform.forEach {
        api(platform(it))
    }

    Dependencies.appDependencies.forEach {
        implementation(it)
    }

    Dependencies.appAnnotationProcessor.forEach {
        kapt(it)
    }

//    Dependencies.appKapt.forEach {
//        kapt(it)
//    }

//    Dependencies.appKaptAndroidTest.forEach {
//        kaptAndroidTest(it)
//    }

//    Dependencies.appKaptTest.forEach {
//        kaptTest(it)
//    }

    Dependencies.appDesugaring.forEach {
        coreLibraryDesugaring(it)
    }
}