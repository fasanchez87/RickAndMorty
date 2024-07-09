plugins {
    id("rickmorty.android.library")
    id("rickmorty.android.hilt")
}

android {
    namespace = "com.me.data"
}

dependencies {
    api(project(":core:network"))
    api(project(":core:utils"))
}