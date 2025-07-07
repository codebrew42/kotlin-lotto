package model

import view.ErrorMessages

data class Ticket(val numbers: List<Int>) {
    init {
        require(numbers.size == Lotto.TICKET_LENGTH) { ErrorMessages.INVALID_TICKET_LENGTH.message }
        require(numbers.toSet().size == Lotto.TICKET_LENGTH) { ErrorMessages.NUMBER_DUPLICATE.message }
        require(numbers.all { it in Lotto.TICKET_NUMBER_MINIMUM..Lotto.TICKET_NUMBER_MAXIMUM }) {
            ErrorMessages.BONUS_NUMBER_OUT_OF_RANGE.message
        }
    }

    override fun toString(): String {
        return numbers.sorted().joinToString(prefix = "[", postfix = "]", separator = ", ")
    }
}
