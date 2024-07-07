package pl.codeplay.kex

import pl.codeplay.kex.feature.Features
import java.util.concurrent.CancellationException

val Throwable.isFatal: Boolean
    get() =
        when (this) {
            is VirtualMachineError,
            is ThreadDeath,
            is InterruptedException,
            is LinkageError -> true

            is CancellationException -> Features.CANCELLATION_IS_FATAL

            else -> false
        }

fun <EX: Throwable> EX.asNonFatal(): EX = when {
    isFatal -> throw this
    else -> this
}
