plugins {
    id("kotlin-convention")
}

group = "pl.codeplay.kex"
version = providers.gradleProperty("version").map { it.removePrefix("v") }.getOrElse("0.1.0-SNAPSHOT")