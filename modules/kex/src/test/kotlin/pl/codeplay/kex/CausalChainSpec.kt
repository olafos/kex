package pl.codeplay.kex

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.hasSize
import org.junit.jupiter.api.Test

class CausalChainSpec {
    @Test
    fun `should return sequence with single throwable when no cause`() {
        // given
        val error = RuntimeException("root")
        // when
        val chain = error.causalChain.toList()
        // then
        assertThat(chain).all {
            hasSize(1)
            containsExactly(error)
        }
    }

    @Test
    fun `should return sequence with causal chain`() {
        // given
        val cause = IllegalArgumentException("cause")
        val error = RuntimeException("root", cause)
        // when
        val chain = error.causalChain.toList()
        // then
        assertThat(chain).all {
            hasSize(2)
            containsExactly(error, cause)
        }
    }

    @Test
    fun `should avoid cycles in causal chain`() {
        // given
        val error = RuntimeException("root")
        val cause = RuntimeException("cause", error)
        error.initCause(cause) // Create a cycle
        // when
        val chain = error.causalChain.toList()
        // then
        assertThat(chain).all {
            hasSize(2)
            containsExactly(error, cause)
        }
    }
}
