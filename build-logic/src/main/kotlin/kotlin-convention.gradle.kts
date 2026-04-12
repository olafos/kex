@file:Suppress("UnstableApiUsage")

import org.gradle.api.JavaVersion

plugins {
    id("project-metadata-convention")
    java
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.test.logger)
    alias(libs.plugins.versions)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

ktlint {
    version = libs.versions.ktlint.ruleset
}

dependencies {
    compileOnly(libs.kotlin.stdlib)
    testImplementation(libs.assertk)
    testImplementation(libs.mockk)
}

testing {
    suites {
        withType<JvmTestSuite>().configureEach {
            useJUnitJupiter(libs.versions.junit)
        }
    }
}