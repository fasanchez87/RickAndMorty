plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    //id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
}

android {

    compileSdk = Dependencies.MainSettings.compileSdk

    defaultConfig {
        versionName = Dependencies.MainSettings.versionName
        versionCode = Dependencies.MainSettings.versionCode
        applicationId = Dependencies.MainSettings.applicationId
        namespace = Dependencies.MainSettings.applicationId
        minSdk = Dependencies.MainSettings.minSdk
        targetSdk = Dependencies.MainSettings.targetSdkVersion
        setProperty("archivesBaseName", Dependencies.getVersionName())
        vectorDrawables.useSupportLibrary = true
        //testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.me.runner.HiltTestRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["dagger.hilt.disableModulesHaveInstallInCheck"] = "true"
            }
        }

        // Allow references to generated code
        kapt {
            correctErrorTypes = true
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lint {
        disable.add("NullSafeMutableLiveData")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_PATH", "\"https://rickandmortyapi.com/\"")
        }
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            buildConfigField("String", "BASE_PATH", "\"https://rickandmortyapi.com/\"")
        }
    }

    testOptions.unitTests {
        isIncludeAndroidResources = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

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
        annotationProcessor(it)
    }

    Dependencies.appKapt.forEach {
        kapt(it)
    }

    Dependencies.appKaptAndroidTest.forEach {
        kaptAndroidTest(it)
    }

    Dependencies.appKaptTest.forEach {
        kaptTest(it)
    }

    Dependencies.appDesugaring.forEach {
        coreLibraryDesugaring(it)
    }
}