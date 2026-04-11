plugins {
    id("kotlin-convention")
}

group = "pl.codeplay.kex"
version = providers.gradleProperty("version").getOrElse("0.1.0-SNAPSHOT")
