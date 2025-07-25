package model

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `should throw exception - invalid purchase amount not divisible by unit`() {
        assertThrows<IllegalArgumentException> {
            Lotto(10, 0)
        }
    }

    @Test
    fun `should throw exception - invalid purchase amount negative`() {
        assertThrows<IllegalArgumentException> {
            Lotto(-10, 0)
        }
    }

    @Test
    fun `should not throw exception - valid purchase amount equal to unit`() {
        assertDoesNotThrow {
            val lotto = Lotto(1000, 0)
            assertEquals(1, lotto.numberOfTotalTickets)
        }
    }

    @Test
    fun `should not throw exception - valid purchase amount above unit`() {
        assertDoesNotThrow {
            val lotto = Lotto(15000, 0)
            assertEquals(15, lotto.numberOfTotalTickets)
        }
    }

    @Test
    fun `generateAutomaticTickets - should create correct number of tickets`() {
        val lotto = Lotto(5000, 0)
        assertDoesNotThrow {
            lotto.generateAutomaticTickets()
        }
        assertEquals(5, lotto.tickets.size())
    }

    @Test
    fun `number should be properly saved - check manual creation of lotto tickets`() {
        val lotto = Lotto(2000, 2)
        val ticket1 = Ticket.fromInts(listOf(1, 2, 3, 4, 5, 6))
        val ticket2 = Ticket.fromInts(listOf(7, 8, 9, 10, 11, 12))
        lotto.tickets.add(ticket1)
        lotto.tickets.add(ticket2)
        assertEquals(2, lotto.tickets.size())
        assertEquals(ticket1, lotto.tickets[0])
        assertEquals(ticket2, lotto.tickets[1])
    }

    @Test
    fun `manually added tickets are used for game logic`() {
        val lotto = Lotto(1000, 1)
        val myTicket = Ticket.fromInts(listOf(1, 2, 3, 10, 20, 30))
        lotto.tickets.add(myTicket)
        assertEquals(1, lotto.tickets.size())
        assertEquals(myTicket, lotto.tickets[0])
    }

    @Test
    fun `should throw exception - a ticket with incorrect length`() {
        assertThrows<IllegalArgumentException> {
            Ticket.fromInts(listOf(1, 2, 3, 4, 5))
        }
    }

    @Test
    fun `should throw exception - a ticket with duplicates`() {
        assertThrows<IllegalArgumentException> {
            Ticket.fromInts(listOf(1, 1, 2, 3, 4, 5))
        }
    }

    @Test
    fun `should throw exception - a ticket with an out-of-range number violating maximum limit`() {
        assertThrows<IllegalArgumentException> {
            Ticket.fromInts(listOf(1, 2, 3, 4, 5, 46))
        }
    }

    @Test
    fun `should throw exception - a ticket with an out-of-range number violating minimum limit`() {
        assertThrows<IllegalArgumentException> {
            Ticket.fromInts(listOf(1, 2, 3, 4, 5, -1))
        }
    }
}
