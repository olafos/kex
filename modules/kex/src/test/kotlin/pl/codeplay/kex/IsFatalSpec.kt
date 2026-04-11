package pl.codeplay.kex

import assertk.all
import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test

class IsFatalSpec {
    @Test
    fun `should return true for VirtualMachineError`() {
        // given
        val error = OutOfMemoryError()
        // when // then
        assertThat(error.isFatal).isTrue()
    }

    @Test
    fun `should return true for ThreadDeath`() {
        // given
        val error = ThreadDeath()
        // when // then
        assertThat(error.isFatal).isTrue()
    }

    @Test
    fun `should return true for InterruptedException`() {
        // given
        val error = InterruptedException()
        // when // then
        assertThat(error.isFatal).isTrue()
    }

    @Test
    fun `should return true for LinkageError`() {
        // given
        val error = NoClassDefFoundError()
        // when // then
        assertThat(error.isFatal).isTrue()
    }

    @Test
    fun `should return false for RuntimeException`() {
        // given
        val error = RuntimeException("test")
        // when // then
        assertThat(error.isFatal).isFalse()
    }
}
