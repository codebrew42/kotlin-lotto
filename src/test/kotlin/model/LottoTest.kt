package model

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `exception : invalid purchase amount - not divisible by unit`() {
        assertThrows<IllegalArgumentException> {
            Lotto(10)
        }
    }

    @Test
    fun `invalid purchase amount negative`() {
        assertThrows<IllegalArgumentException> {
            Lotto(-10)
        }
    }

    @Test
    fun `valid purchase amount which is unit`() {
        assertDoesNotThrow {
            val lotto = Lotto(1000)
            assertEquals(1, lotto.numberOfTickets)
        }
    }

    @Test
    fun `valid purchase amount above unit`() {
        assertDoesNotThrow {
            val lotto = Lotto(15000)
            assertEquals(15, lotto.numberOfTickets)
        }
    }

    @Test
    fun `generateTickets should create the correct number of tickets`() {
        val lotto = Lotto(5000)
        assertDoesNotThrow {
            lotto.generateTickets()
        }
        assertEquals(5, lotto.tickets.size)
    }
}
