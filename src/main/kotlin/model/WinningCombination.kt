package model

import view.ErrorMessages

data class WinningCombination(
    val winningNumbers: Ticket,
    val bonusNumber: Int,
) {
    init {
        require(bonusNumber in Lotto.TICKET_NUMBER_MINIMUM..Lotto.TICKET_NUMBER_MAXIMUM) {
            ErrorMessages.BONUS_NUMBER_OUT_OF_RANGE.message
        }
        require(bonusNumber !in winningNumbers.numbers) { ErrorMessages.NUMBER_DUPLICATE.message }
    }
}
