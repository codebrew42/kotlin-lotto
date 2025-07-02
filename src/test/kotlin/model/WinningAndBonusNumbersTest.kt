package model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import view.InputView

class WinningAndBonusNumbersTest {
    companion object {
        @JvmStatic
        fun invalidLists() = listOf(
            listOf(1, 2, 3, 4, 5, 5),
            listOf(1, 2, 3, 4),
            listOf(0, 1, 2, 3, 4, 5)
        )
        @JvmStatic
        fun invalidBonusNumbers() = listOf(6, -1, 46)
    }

    @ParameterizedTest
    @MethodSource("invalidLists")
    fun `invalid winning numbers` (numbers: List<Int>) {
        assertThrows<IllegalArgumentException> {
            val inputView = InputView()
            inputView.validateWinningNumbers(numbers)
        }
    }

    @ParameterizedTest
    @MethodSource("invalidBonusNumbers")
    fun `invalid bonus number` (invalidBonusNumbers: Int) {
        assertThrows<IllegalArgumentException> {
            val validList = listOf(1, 2, 3, 4, 5, 6)
            val inputView = InputView()
            inputView.validateBonusNumber(invalidBonusNumbers, validList)
        }

    }

}