@file:Suppress("UnstableApiUsage")

rootProject.name = "root"

includeBuild("build-logic")

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        mavenLocal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":modules:kex")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
