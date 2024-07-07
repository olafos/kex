plugins {
    alias(libs.plugins.kotlin.jvm) apply(false)
}

repositories {
    mavenCentral()
}

tasks.wrapper {
    gradleVersion = "8.8"
}