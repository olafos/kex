package pl.codeplay.kex

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import org.junit.jupiter.api.Test

class AsNonFatalSpec {
    @Test
    fun `should throw fatal exception`() {
        // given
        val error = OutOfMemoryError()
        // then
        assertFailure {
            // when
            error.asNonFatal()
        }.isInstanceOf<OutOfMemoryError>()
    }

    @Test
    fun `should return non-fatal exception`() {
        // given
        val error = RuntimeException("test")
        // when
        val result = error.asNonFatal()
        // then
        assertThat(result).isEqualTo(error)
    }
}
