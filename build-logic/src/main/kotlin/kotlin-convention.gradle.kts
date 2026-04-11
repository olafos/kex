@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
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

tasks {
    withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            apiVersion = KotlinVersion.KOTLIN_1_9
            languageVersion = KotlinVersion.KOTLIN_1_9
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