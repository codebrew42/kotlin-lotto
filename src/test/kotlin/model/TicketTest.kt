package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TicketTest {
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
