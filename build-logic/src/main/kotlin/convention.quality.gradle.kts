plugins {
    id("io.gitlab.arturbosch.detekt")
    id("org.jlleitschuh.gradle.ktlint")
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom(files(rootProject.file("config/quality/detekt.yml")))
    autoCorrect = false
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "17"
}

ktlint {
    verbose.set(true)
    android.set(true)
    ignoreFailures.set(false)
    additionalEditorconfig.set(mapOf(
        "indent_size" to "4",
        "max_line_length" to "120"
    ))
}

tasks.register("noop") { }