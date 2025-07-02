package model

import kotlin.test.Test
import kotlin.test.assertEquals

class WinningStatistics {
    @Test
    fun `test winning amount`() {
        val results = listOf<Int>(1, 1, 1, 1, 1)
        assertEquals(2031555000, Statistics.calculateWinningAmount(results))
    }

    @Test
    fun `winning statistic calculation`() {
        val matchResult = listOf<Int>(0, 0, 0, 0, 1)
        assertEquals(1.0f, Statistics.calculateWinningStatistic(5000, matchResult))

    }
}