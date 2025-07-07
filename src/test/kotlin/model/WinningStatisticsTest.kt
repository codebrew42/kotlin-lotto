package model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WinningStatisticsTest {
    @Test
    fun `should calculate correct winning amount`() {
        val results = listOf(1, 1, 1, 1, 1)
        assertEquals(2031555000, Statistics.calculateWinningAmount(results))
    }

    @Test
    fun `should calculate correct winning statistic`() {
        val matchResult = listOf(0, 0, 0, 0, 1)
        assertEquals(1.0f, Statistics.calculateWinningStatistic(5000, matchResult))
    }
}
