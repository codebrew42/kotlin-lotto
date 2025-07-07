package model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `should return FIRST`() {
        val ticket = Ticket(listOf(1, 2, 3, 4, 5, 6))
        val winningCombination = WinningCombination(ticket, 7)
        val rank = Rank.valueOfEachTicket(ticket, winningCombination)
        assertEquals(Rank.FIRST, rank)
    }

    @Test
    fun `should return SECOND`() {
        val ticket = Ticket(listOf(1, 2, 3, 4, 5, 7))
        val winningTicket = Ticket(listOf(1, 2, 3, 4, 5, 6))
        val winningCombination = WinningCombination(winningTicket, 7)
        val rank = Rank.valueOfEachTicket(ticket, winningCombination)
        assertEquals(Rank.SECOND, rank)
    }

    @Test
    fun `should return THIRD`() {
        val ticket = Ticket(listOf(1, 2, 3, 4, 5, 9))
        val winningTicket = Ticket(listOf(1, 2, 3, 4, 5, 6))
        val winningCombination = WinningCombination(winningTicket, 7)
        val rank = Rank.valueOfEachTicket(ticket, winningCombination)
        assertEquals(Rank.THIRD, rank)
    }

    @Test
    fun `should return FOURTH`() {
        val ticket = Ticket(listOf(1, 2, 3, 4, 32, 21))
        val winningTicket = Ticket(listOf(1, 2, 3, 4, 5, 6))
        val winningCombination = WinningCombination(winningTicket, 7)
        val rank = Rank.valueOfEachTicket(ticket, winningCombination)
        assertEquals(Rank.FOURTH, rank)
    }

    @Test
    fun `should return FIFTH`() {
        val ticket = Ticket(listOf(1, 2, 3, 31, 32, 33))
        val winningTicket = Ticket(listOf(1, 2, 3, 4, 5, 6))
        val winningCombination = WinningCombination(winningTicket, 7)
        val rank = Rank.valueOfEachTicket(ticket, winningCombination)
        assertEquals(Rank.FIFTH, rank)
    }

    @Test
    fun `should return MISS`() {
        val ticket = Ticket(listOf(11, 21, 31, 24, 15, 17))
        val winningTicket = Ticket(listOf(1, 2, 3, 4, 5, 6))
        val winningCombination = WinningCombination(winningTicket, 7)
        val rank = Rank.valueOfEachTicket(ticket, winningCombination)
        assertEquals(Rank.MISS, rank)
    }
}
