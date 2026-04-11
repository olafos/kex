# kex

Kotlin exception utilities - a lightweight library providing extension functions for better exception handling in Kotlin.

## Installation

### Gradle

Add the following to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("pl.codeplay.kex:kex:1.0-SNAPSHOT")
}
```

### Maven

Add the following to your `pom.xml`:

```xml
<dependency>
    <groupId>pl.codeplay.kex</groupId>
    <artifactId>kex</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## API Documentation

### Fatal Exceptions

#### `Throwable.isFatal: Boolean`

Checks if the exception is considered fatal. Fatal exceptions are those that indicate serious problems and typically should not be caught and handled normally.

**Fatal exceptions include:**
- `VirtualMachineError`
- `ThreadDeath`
- `InterruptedException`
- `LinkageError`

```kotlin
val error = OutOfMemoryError("heap space")
println(error.isFatal) // true

val normalError = IllegalArgumentException("invalid argument")
println(normalError.isFatal) // false
```

#### `Throwable.asNonFatal(): T`

Throws the exception if it is fatal, otherwise returns it. Useful for ensuring that fatal exceptions are not caught by generic catch blocks.

```kotlin
try {
    riskyOperation()
} catch (e: Exception) {
    e.asNonFatal() // Re-throws if fatal, otherwise continues
    // Handle non-fatal exception
}
```

### Causal Chain

#### `Throwable.causalChain: Sequence<Throwable>`

Returns a sequence containing this exception and all its causes, avoiding cycles.

```kotlin
val cause = IllegalArgumentException("cause")
val root = RuntimeException("root", cause)

root.causalChain.forEach { println(it.message) }
// Output:
// root
// cause
```

### Checking Causes

#### `Throwable.isCausedBy(predicate: (Throwable) -> Boolean): Boolean`

Checks if this exception or any exception in its causal chain matches the given predicate.

```kotlin
val cause = IllegalArgumentException("invalid input")
val error = RuntimeException("processing failed", cause)

val isCausedByInvalidInput = error.isCausedBy { it.message?.contains("invalid") == true }
println(isCausedByInvalidInput) // true
```

#### `Throwable.isCausedBy(includeThis: Boolean, predicate: (Throwable) -> Boolean): Boolean`

Same as above, but allows excluding the current exception from the check.

```kotlin
val error = RuntimeException("wrapper")

val includesThis = error.isCausedBy { it is RuntimeException } // true
val excludesThis = error.isCausedBy(includeThis = false) { it is RuntimeException } // false (no causes)
```

#### `Throwable.isCausedBy<T : Throwable>(): Boolean`

Checks if this exception or any in its causal chain is an instance of the specified type.

```kotlin
val cause = IllegalArgumentException("cause")
val error = RuntimeException("root", cause)

println(error.isCausedBy<IllegalArgumentException>()) // true
println(error.isCausedBy<IOException>()) // false
```

#### `Throwable.isCausedBy<T : Throwable>(includeThis: Boolean): Boolean`

Same as above, with option to exclude the current exception.

```kotlin
val error = IllegalArgumentException("direct")

println(error.isCausedBy<IllegalArgumentException>()) // true
println(error.isCausedBy<IllegalArgumentException>(includeThis = false)) // false
```

#### `Throwable.isCausedBy<T : Throwable>(predicate: (Throwable) -> Boolean): Boolean`

Checks if any exception in the chain is of the specified type and matches the additional predicate.

```kotlin
val cause = IllegalArgumentException("invalid value")
val error = RuntimeException("processing error", cause)

val isCausedByInvalidArg = error.isCausedBy<IllegalArgumentException> { it.message?.contains("invalid") == true }
println(isCausedByInvalidArg) // true
```

#### `Throwable.isCausedBy<T : Throwable>(includeThis: Boolean, predicate: (Throwable) -> Boolean): Boolean`

Full version with both parameters.

```kotlin
val cause = IllegalArgumentException("cause")
val wrapper = RuntimeException("wrapper", cause)

val matchesCauseOnly = wrapper.isCausedBy<IllegalArgumentException>(includeThis = false) { it.message == "cause" }
println(matchesCauseOnly) // true
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
