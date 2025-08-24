plugins {
    id("convention.kmm.library")
    id("convention.quality")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.bundles.ktor.client)
                implementation(libs.koin.core)
            }
        }
        val androidMain by getting {
            dependencies { implementation(libs.ktor.client.android) }
        }
        val iosMain by getting {
            dependencies { implementation(libs.ktor.client.darwin) }
        }
    }
}

android { namespace = "com.acme.core.networking" }