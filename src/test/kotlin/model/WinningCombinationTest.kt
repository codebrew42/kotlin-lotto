package model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class WinningCombinationTest {
    companion object {
        @JvmStatic
        fun invalidLists() =
            listOf(
                // Duplicate numbers
                listOf(1, 2, 3, 4, 5, 5),
                // Invalid size
                listOf(1, 2, 3, 4),
                // Number out of range
                listOf(0, 1, 2, 3, 4, 5),
            )

        @JvmStatic
        fun invalidBonusNumbers() =
            listOf(
                // Duplicate with winning numbers
                6,
                // Number out of range (violates the lower bound)
                -1,
                // Number out of range (violates the upper bound)
                46,
            )
    }

    @ParameterizedTest
    @MethodSource("invalidLists")
    fun `should throw exception - create Ticket with invalid numbers`(numbers: List<Int>) {
        assertThrows<IllegalArgumentException> {
            Ticket(numbers)
        }
    }

    @ParameterizedTest
    @MethodSource("invalidBonusNumbers")
    fun `should throw exception - create WinningCombination with invalid bonus number`(bonusNumber: Int) {
        assertThrows<IllegalArgumentException> {
            val winningTicket = Ticket(listOf(1, 2, 3, 4, 5, 6))
            WinningCombination(winningTicket, bonusNumber)
        }
    }
}
