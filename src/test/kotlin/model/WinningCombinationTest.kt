package model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class WinningCombinationTest {
    companion object {
        @JvmStatic
        fun invalidLists() =
            listOf(
                listOf(1, 2, 3, 4, 5, 5),
                listOf(1, 2, 3, 4),
                listOf(0, 1, 2, 3, 4, 5),
            )

        @JvmStatic
        fun invalidBonusNumbers() =
            listOf(
                6,
                -1,
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
        val winningTicket = Ticket(listOf(1, 2, 3, 4, 5, 6))
        assertThrows<IllegalArgumentException> {
            WinningCombination(winningTicket, bonusNumber)
        }
    }
}
