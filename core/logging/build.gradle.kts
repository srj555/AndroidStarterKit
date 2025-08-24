plugins {
    id("convention.kmm.library")
    id("convention.quality")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies { implementation(libs.kermit) }
        }
    }
}

android { namespace = "com.acme.core.logging" }