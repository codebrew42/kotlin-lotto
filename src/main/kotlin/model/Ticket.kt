package model

import view.ErrorMessages

data class Ticket(val numbers: List<LottoNumber>) {
    companion object {
        fun fromInts(intNumbers: List<Int>): Ticket {
            return Ticket(intNumbers.map { LottoNumber.from(it) })
        }
    }

    init {
        require(numbers.size == Lotto.TICKET_LENGTH) { ErrorMessages.INVALID_TICKET_LENGTH.message }
        require(numbers.toSet().size == Lotto.TICKET_LENGTH) { ErrorMessages.NUMBER_DUPLICATE.message }
    }

    fun getIntNumbers(): List<Int> = numbers.map { it.getValue() }

    override fun toString(): String {
        return numbers.sortedBy { it.getValue() }.joinToString(prefix = "[", postfix = "]", separator = ", ")
    }
}
