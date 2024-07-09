pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()//TODO: Review if this is needed due to styles.xml fail any resources.
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RickMorty"

include(":app")
include(":feature")
include(":feature:characters")
include(":core")
include(":core:data")
include(":core:model")
include(":core:utils")
include(":core:ui")
include(":core:designsystem")
include(":core:domain")
include(":core:network")
include(":feature:splash")
include(":core:routes")
