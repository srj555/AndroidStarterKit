plugins {
    id("convention.kmm.library")
    id("convention.quality")
    alias(libs.plugins.sqldelight)
    `maven-publish`
}

android {
    namespace = "com.acme.feature.example"
    publishing {
        singleVariant("release") { withSourcesJar() }
    }
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:common"))
                implementation(project(":core:utils"))
                implementation(project(":core:logging"))
                implementation(project(":core:config"))
                implementation(project(":core:bridge"))
                implementation(project(":core:networking"))
                implementation(project(":core:database"))
                implementation(libs.koin.core)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.coroutines.core)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.android)
            }
        }
        val iosMain by getting {}
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.acme.core.database")
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("exampleAndroid") {
            groupId = providers.gradleProperty("group").get()
            artifactId = "feature-example-android"
            version = providers.gradleProperty("version").get()
            from(components["androidRelease"])
        }
    }
}