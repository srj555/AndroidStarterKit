plugins {
    id("convention.kmm.library")
    id("convention.quality")
    alias(libs.plugins.sqldelight)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.sqldelight.runtime)
                implementation(libs.sqldelight.coroutines)
            }
        }
        val androidMain by getting {
            dependencies { implementation(libs.sqldelight.driver.android) }
        }
        val iosMain by getting {
            dependencies { implementation(libs.sqldelight.driver.native) }
        }
    }
}

android { namespace = "com.acme.core.database" }

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.acme.core.database")
            schemaOutputDirectory.set(file("src/commonMain/sqldelight/databases"))
        }
    }
}