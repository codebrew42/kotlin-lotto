package view

import model.Lotto
import model.Ticket

class InputView {
    private fun getUserInputAsString(
        prompt: String,
        numberOfAttempts: Int = 3,
    ): String {
        repeat(numberOfAttempts) {
            if (prompt.isNotBlank()) {
                println(prompt)
            }
            val input = readlnOrNull()?.trim()
            if (!input.isNullOrBlank()) {
                return input
            }
            println(ErrorMessages.INPUT_EMPTY.message)
        }
        throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
    }

    private fun getUserInputAsInt(
        prompt: String,
        numberOfAttempts: Int = 3,
    ): Int {
        repeat(numberOfAttempts) {
            val inputAsString = getUserInputAsString(prompt)
            val input = inputAsString.toIntOrNull()
            if (input != null) {
                return input
            }
            println(ErrorMessages.INPUT_INVALID_DIGITS.message)
        }
        throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
    }

    fun getUserInputAsListOfInt(
        prompt: String,
        numberOfAttempts: Int = 3,
        delimiter: String = ",",
    ): List<Int> {
        repeat(numberOfAttempts) {
            val inputAsString = getUserInputAsString(prompt)
            val result =
                inputAsString.split(delimiter)
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }
                    .mapNotNull { it.toIntOrNull() }
            if (result.isNotEmpty()) {
                return result
            }
            println(ErrorMessages.INPUT_INVALID_DIGITS.message)
        }
        throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
    }

    fun getPurchaseAmount(): Int {
        return getUserInputAsInt(PromptMessages.GET_PURCHASE_AMOUNT.message)
    }

    fun getNumberOfManualTickets(): Int {
        return getUserInputAsInt(PromptMessages.GET_NUMBER_OF_MANUAL_TICKETS.message)
    }

    fun getManualTicketNumbers(): List<Int> {
        repeat(3) {
            try {
                val numbers = getUserInputAsListOfInt("", 3)
                require(numbers.size == Lotto.TICKET_LENGTH) { 
                    ErrorMessages.INVALID_TICKET_LENGTH.message
                }
                require(numbers.all { it in Lotto.TICKET_NUMBER_MIN..Lotto.TICKET_NUMBER_MAX }) {
                    ErrorMessages.BONUS_NUMBER_OUT_OF_RANGE.message
                }
                require(numbers.toSet().size == numbers.size) {
                    ErrorMessages.NUMBER_DUPLICATE.message
                }
                return numbers.sorted()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
        throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
    }

    private fun convertWinningNumbers(input: String): List<Int> {
        return try {
            input.split(",")
                .map(String::trim)
                .map(String::toInt)
        } catch (_: NumberFormatException) {
            throw IllegalArgumentException(ErrorMessages.INPUT_INVALID_DIGITS.message)
        }
    }

    private fun getTicket(
        prompt: String,
        numberOfAttempts: Int = 3,
    ): Ticket {
        repeat(numberOfAttempts) {
            try {
                val input = getUserInputAsString(prompt)
                val numbers = convertWinningNumbers(input)
                require(numbers.all { it in 1..45 }) { ErrorMessages.BONUS_NUMBER_OUT_OF_RANGE.message }
                require(numbers.toSet().size == numbers.size) { ErrorMessages.NUMBER_DUPLICATE.message }
                require(numbers.size == Lotto.TICKET_LENGTH) { ErrorMessages.INVALID_TICKET_LENGTH.message }
                return Ticket(numbers)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
        throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
    }

    fun getWinningTicket(numberOfAttempts: Int = 3): Ticket {
        return getTicket(PromptMessages.GET_WINNING_NUMBERS.message, numberOfAttempts)
    }

    fun getBonusNumber(
        winningTicket: Ticket,
        numberOfAttempts: Int = 3,
    ): Int {
        repeat(numberOfAttempts) {
            try {
                val bonusNumber = getUserInputAsInt(PromptMessages.GET_BONUS_NUMBER.message)
                require(bonusNumber in Lotto.TICKET_NUMBER_MIN..Lotto.TICKET_NUMBER_MAX) {
                    ErrorMessages.BONUS_NUMBER_OUT_OF_RANGE.message
                }
                require(bonusNumber !in winningTicket.numbers) { ErrorMessages.NUMBER_DUPLICATE.message }
                return bonusNumber
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
        throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
    }
}
