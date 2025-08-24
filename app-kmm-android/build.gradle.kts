plugins { id("convention.android.app") }

android {
    namespace = "com.acme.kmmapp"
    defaultConfig {
        applicationId = "com.acme.kmmapp"
    }
    productFlavors {
        named("dev") {
            buildConfigField("String", "BASE_URL", "\"https://fakestoreapi.com\"")
            buildConfigField("boolean", "ENABLE_LOGGING", "true")
        }
        named("stage") {
            buildConfigField("String", "BASE_URL", "\"https://fakestoreapi.com\"")
            buildConfigField("boolean", "ENABLE_LOGGING", "true")
        }
        named("prod") {
            buildConfigField("String", "BASE_URL", "\"https://fakestoreapi.com\"")
            buildConfigField("boolean", "ENABLE_LOGGING", "false")
        }
    }
}

dependencies {
    implementation(project(":feature:example"))
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
}