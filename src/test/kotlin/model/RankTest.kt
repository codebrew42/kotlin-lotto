package model

import kotlin.test.assertEquals
import kotlin.test.Test

class RankTest {

    @Test
    fun `should return FIRST` () {
        val ticket = listOf(1, 2, 3, 4, 5, 6)
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        val rank = Rank.valueOfEachTicket(ticket, winningNumbers, bonusNumber)
        assertEquals(Rank.FIRST, rank)
    }

    @Test
    fun `should return SECOND` () {
        val ticket = listOf(1, 2, 3, 4, 5, 7)
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        val rank = Rank.valueOfEachTicket(ticket, winningNumbers, bonusNumber)
        assertEquals(Rank.SECOND, rank)
    }

    @Test
    fun `should return THIRD` () {
        val ticket = listOf(1, 2, 3, 4, 5, 9)
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        val rank = Rank.valueOfEachTicket(ticket, winningNumbers, bonusNumber)
        assertEquals(Rank.THIRD, rank)
    }

    @Test
    fun `should return FOURTH` () {
        val ticket = listOf(1, 2, 3, 4, 32, 21)
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        val rank = Rank.valueOfEachTicket(ticket, winningNumbers, bonusNumber)
        assertEquals(Rank.FOURTH, rank)
    }

    @Test
    fun `should return FIFTH` () {
        val ticket = listOf(1, 2, 3, 31, 32, 33)
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        val rank = Rank.valueOfEachTicket(ticket, winningNumbers, bonusNumber)
        assertEquals(Rank.FIFTH, rank)
    }

    @Test
    fun `should return MISS` () {
        val ticket = listOf(11, 21, 31, 24, 15, 17)
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        val rank = Rank.valueOfEachTicket(ticket, winningNumbers, bonusNumber)
        assertEquals(Rank.MISS, rank)
    }

}