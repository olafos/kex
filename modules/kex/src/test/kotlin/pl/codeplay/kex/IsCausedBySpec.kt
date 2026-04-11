package pl.codeplay.kex

import assertk.all
import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test

class IsCausedBySpec {
    @Test
    fun `should return true when predicate matches this throwable and includeThis is true`() {
        // given
        val error = RuntimeException("test")
        // when
        val result = error.isCausedBy { cause -> cause.message == "test" }
        // then
        assertThat(result).isTrue()
    }

    @Test
    fun `should return false when predicate does not match and includeThis is true`() {
        // given
        val error = RuntimeException("test")
        // when
        val result = error.isCausedBy { cause -> cause.message == "other" }
        // then
        assertThat(result).isFalse()
    }

    @Test
    fun `should return true when predicate matches cause and includeThis is false`() {
        // given
        val cause = IllegalArgumentException("cause")
        val error = RuntimeException("root", cause)
        // when
        val result = error.isCausedBy(includeThis = false) { cause -> cause.message == "cause" }
        // then
        assertThat(result).isTrue()
    }

    @Test
    fun `should return false when predicate matches only this and includeThis is false`() {
        // given
        val error = RuntimeException("root")
        // when
        val result = error.isCausedBy(includeThis = false) { cause -> cause.message == "root" }
        // then
        assertThat(result).isFalse()
    }

    @Test
    fun `should return true when caused by specific type and includeThis is true`() {
        // given
        val error = RuntimeException("test")
        // when
        val result = error.isCausedBy<RuntimeException>()
        // then
        assertThat(result).isTrue()
    }

    @Test
    fun `should return true when caused by specific type in chain and includeThis is true`() {
        // given
        val cause = IllegalArgumentException("cause")
        val error = RuntimeException("root", cause)
        // when
        val result = error.isCausedBy<IllegalArgumentException>()
        // then
        assertThat(result).isTrue()
    }

    @Test
    fun `should return false when not caused by specific type`() {
        // given
        val error = RuntimeException("test")
        // when
        val result = error.isCausedBy<IllegalArgumentException>()
        // then
        assertThat(result).isFalse()
    }

    @Test
    fun `should return true when caused by specific type and additional predicate matches`() {
        // given
        val cause = IllegalArgumentException("cause")
        val error = RuntimeException("root", cause)
        // when
        val result = error.isCausedBy<IllegalArgumentException> { cause -> cause.message == "cause" }
        // then
        assertThat(result).isTrue()
    }

    @Test
    fun `should return false when caused by specific type but additional predicate does not match`() {
        // given
        val cause = IllegalArgumentException("cause")
        val error = RuntimeException("root", cause)
        // when
        val result = error.isCausedBy<IllegalArgumentException> { cause -> cause.message == "other" }
        // then
        assertThat(result).isFalse()
    }
}
