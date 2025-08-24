plugins {
    // Root build uses version catalogs and delegates logic to convention plugins
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.ktlint) apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

// Aggregate quality task that individual modules may wire to via the quality convention plugin
tasks.register("qualityCheck") {
    group = "verification"
    description = "Runs static analysis (detekt, ktlint) across the project"
    dependsOn(gradle.includedBuild("build-logic").task(":noop"))
    // Module-specific detekt/ktlint tasks are added by the quality convention
    // This task will be realized when modules are configured
}