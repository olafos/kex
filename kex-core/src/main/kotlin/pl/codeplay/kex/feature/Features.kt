package pl.codeplay.kex.feature

object Features {

    val CANCELLATION_IS_FATAL = System.getProperty("pl.codeplay.kex.feature.CANCELLATION_IS_FATAL", "false").toBoolean()
}