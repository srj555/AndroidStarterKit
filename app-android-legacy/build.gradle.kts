plugins { id("convention.android.app") }

android {
    namespace = "com.acme.legacy"
    defaultConfig {
        applicationId = "com.acme.legacy"
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
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
}