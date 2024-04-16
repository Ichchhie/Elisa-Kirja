//val logback_version: String by project
//val junit_version: String by project
//val hamcrest_version: String by project

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false

}

/*
repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/bootstrap")
    maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    //mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap/")
    maven("https://jitpack.io")
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-coroutines/maven")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://androidx.dev/storage/compose-compiler/repository")
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev/")
}

//val ktor_version: String by project

dependencies {
    implementation("io.ktor:ktor-client-core:2.3.1-wasm0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.1-wasm0")
    implementation("io.ktor:ktor-client-cio:2.3.1-wasm0")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.1-wasm0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0-RC-wasm0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.0-RC-wasm0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1-wasm0")
    //noinspection UseTomlInstead
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0-dev-6976")
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(20)
}

 */
