apply(plugin = "org.jlleitschuh.gradle.ktlint")
apply(plugin = "org.jlleitschuh.gradle.ktlint-idea")

buildscript {

    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()

        maven {url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
        maven { url = uri("https://jitpack.io") }
    }

    dependencies {
        classpath(Dependencies.Classpath.androidGradlePlugin)
        classpath(Dependencies.Classpath.kotlinGradlePlugin)
        classpath(Dependencies.Classpath.googleServices)
        classpath(Dependencies.Classpath.firebaseCrashlytics)
        classpath(Dependencies.Classpath.ktlintPlugin)
        classpath(Dependencies.Classpath.hilt)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "org.jlleitschuh.gradle.ktlint-idea")
    // Optionally configure plugin
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()

        maven {url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
        maven { url = uri("https://jitpack.io") }
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
