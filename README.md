# KMM Starter Multimodule

This repository is a production-grade Kotlin Multiplatform (KMM) starter with strict multi-module architecture. It supports reuse in legacy Android apps and serves as a modern KMM starter for new apps.

See `/docs` for ADRs and architecture diagrams. Build and run instructions are below.

## Build

- Install Java 17
- Android Studio Ladybug+ recommended
- Build: `./gradlew assemble`

## Apps

- Legacy Android app: `./gradlew :app-android-legacy:installDebug`
- KMM Android app: `./gradlew :app-kmm-android:installDebug`

## Publish Feature to mavenLocal

```kotlin
repositories { mavenLocal() }
dependencies { implementation("com.acme.feature:example-android:0.1.0-SNAPSHOT") }
```

## Quality

- Run: `./gradlew qualityCheck`

## Modules

- Core KMM modules: `core/common`, `core/utils`, `core/logging`, `core/config`, `core/bridge`, `core/networking`, `core/database`
- Feature KMM module: `feature/example` (domain + data only)
- Apps: `app-android-legacy`, `app-kmm-android`