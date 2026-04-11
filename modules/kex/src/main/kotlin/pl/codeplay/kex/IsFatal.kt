package pl.codeplay.kex

val Throwable.isFatal: Boolean
    get() =
        when (this) {
            is VirtualMachineError,
            is ThreadDeath,
            is InterruptedException,
            is LinkageError,
            -> true

            else -> false
        }

fun <EX : Throwable> EX.asNonFatal(): EX =
    when {
        isFatal -> throw this
        else -> this
    }
