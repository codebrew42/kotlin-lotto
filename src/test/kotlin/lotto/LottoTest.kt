package lotto

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow


class LottoTest {
    @Test
    fun `valid purchase amount unit`() {
        assertThrows<IllegalArgumentException> {
            Lotto(10)
        }
    }
}