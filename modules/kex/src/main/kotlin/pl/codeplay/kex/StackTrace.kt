package pl.codeplay.kex

val Throwable.causalChain: Sequence<Throwable>
    get() =
        let { ex ->
            sequence {
                val emitted = mutableSetOf<Throwable>()
                var root: Throwable? = ex
                while (root != null && emitted.add(root)) {
                    yield(root)
                    root = root.cause
                }
            }
        }

@JvmName("isCausedByPredicate")
fun Throwable.isCausedBy(
    includeThis: Boolean = true,
    predicate: (Throwable) -> Boolean,
) = causalChain
    .run {
        when (includeThis) {
            true -> this
            false -> drop(1)
        }
    }.any(predicate)

@JvmName("isCausedByType")
inline fun <reified EX : Throwable> Throwable.isCausedBy(includeThis: Boolean = true) = isCausedBy(includeThis) { ex -> ex is EX }

@JvmName("isCausedByTypeAndPredicate")
inline fun <reified EX : Throwable> Throwable.isCausedBy(
    includeThis: Boolean = true,
    crossinline predicate: (Throwable) -> Boolean,
) = isCausedBy(includeThis) { ex -> ex is EX && predicate(ex) }
