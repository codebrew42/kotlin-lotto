package model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import view.InputView

class WinningNumbersTest {
    companion object {
        @JvmStatic
        fun invalidLists() = listOf(
            listOf(1, 2, 3, 4, 5, 5),
            listOf(1, 2, 3, 4),
            listOf(0, 1, 2, 3, 4, 5)
        )

    }

    @ParameterizedTest
    @MethodSource("invalidLists")
    fun `invalid sets` (numbers: List<Int>) {
        assertThrows<IllegalArgumentException> {
            val inputView = InputView()
            inputView.validateWinningNumbers(numbers)
        }
    }
}