plugins {
    `kotlin-dsl`
}

group = "com.acme.buildlogic"
version = "0.1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

// Provide access to the root version catalog inside precompiled scripts
dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation("com.android.tools.build:gradle:${libs.versions.agp.get()}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")
    implementation("org.jetbrains.kotlin:compose-compiler-gradle-plugin:${libs.versions["versions.compose"].get("compiler")}")
    implementation("app.cash.sqldelight:gradle-plugin:${libs.versions.sqldelight.get()}")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${libs.versions.detekt.get()}")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:${libs.versions.ktlint.get()}")
}