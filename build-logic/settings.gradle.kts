@file:Suppress("UnstableApiUsage")

rootProject.name = "build-logic"

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("dev.panuszewski.typesafe-conventions") version "0.12.1"
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")