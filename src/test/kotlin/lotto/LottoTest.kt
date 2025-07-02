package lotto

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `valid purchase amount unit`() {
        assertThrows<IllegalArgumentException> {
            Lotto(10)
        }
    }
}