@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.gradle.api.JavaVersion

plugins {
    java
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.test.logger)
    alias(libs.plugins.versions)
}

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
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

tasks {
    withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_25)
            freeCompilerArgs.add("-Xcontext-parameters")
            freeCompilerArgs.add("-Xcontext-sensitive-resolution")
            freeCompilerArgs.add("-Xnested-type-aliases")
        }
    }
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter(libs.versions.junit)
        }
    }
}