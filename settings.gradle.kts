pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "kmm-starter-multimodule"

include(
    
    ":core:common",
    ":core:utils",
    ":core:logging",
    ":core:config",
    ":core:bridge",
    ":core:networking",
    ":core:database",

    ":feature:example",

    ":app-android-legacy",
    ":app-kmm-android"
)