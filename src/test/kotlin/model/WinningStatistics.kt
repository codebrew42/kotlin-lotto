package model
import controller.Controller
import kotlin.test.Test
import kotlin.test.assertEquals

class WinningStatistics {
    @Test
    fun `test winning amount`() {
        val controller = Controller()
        val results = listOf<Int>(1, 1, 1, 1, 1)
        assertEquals(2031555000, controller.getWinningAmount(results))
    }

    @Test
    fun `winning statistic calculation`() {
        val controller = Controller()
        val matchResult = listOf<Int>(0,0,0,0,1)
        assertEquals(1.0f, controller.calculateWinningStatistic(5000, matchResult))

    }
}