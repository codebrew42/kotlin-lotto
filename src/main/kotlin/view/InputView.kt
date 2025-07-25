package view

import model.Lotto
import model.Ticket

class InputView {
    private fun getUserInputAsString(
        prompt: String,
        numberOfAttempts: Int = 3,
    ): String {
        repeat(numberOfAttempts) {
            printPromptIfNotBlank(prompt)
            val input = readlnOrNull()?.trim()
            if (!input.isNullOrBlank()) return input
            println(ErrorMessages.INPUT_EMPTY.message)
        }
        throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
    }

    private fun printPromptIfNotBlank(prompt: String) {
        if (prompt.isNotBlank()) println(prompt)
    }

    private fun getUserInputAsInt(
        prompt: String,
        numberOfAttempts: Int = 3,
    ): Int {
        repeat(numberOfAttempts) {
            val inputAsString = getUserInputAsString(prompt)
            val input = inputAsString.toIntOrNull()
            if (input != null) return input
            println(ErrorMessages.INPUT_INVALID_DIGITS.message)
        }
        throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
    }

    private fun getUserInputAsListOfInt(
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
            val validatedNumbers = tryGetValidManualTicket()
            if (validatedNumbers != null) return validatedNumbers
        }
        throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
    }

    private fun tryGetValidManualTicket(): List<Int>? {
        return try {
            val numbers = getUserInputAsListOfInt("", 3)
            validateManualTicketNumbers(numbers)
            numbers.sorted()
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }

    private fun validateManualTicketNumbers(numbers: List<Int>) {
        require(numbers.size == Lotto.TICKET_LENGTH) {
            ErrorMessages.INVALID_TICKET_LENGTH.message
        }
        require(numbers.all { it in Lotto.TICKET_NUMBER_MIN..Lotto.TICKET_NUMBER_MAX }) {
            ErrorMessages.BONUS_NUMBER_OUT_OF_RANGE.message
        }
        require(numbers.toSet().size == numbers.size) {
            ErrorMessages.NUMBER_DUPLICATE.message
        }
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
            val validTicket = tryCreateValidTicket(prompt)
            if (validTicket != null) return validTicket
        }
        throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
    }

    private fun tryCreateValidTicket(prompt: String): Ticket? {
        return try {
            val input = getUserInputAsString(prompt)
            val numbers = convertWinningNumbers(input)
            validateTicketNumbers(numbers)
            Ticket.fromInts(numbers)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }

    private fun validateTicketNumbers(numbers: List<Int>) {
        require(numbers.all { it in 1..45 }) { ErrorMessages.BONUS_NUMBER_OUT_OF_RANGE.message }
        require(numbers.toSet().size == numbers.size) { ErrorMessages.NUMBER_DUPLICATE.message }
        require(numbers.size == Lotto.TICKET_LENGTH) { ErrorMessages.INVALID_TICKET_LENGTH.message }
    }

    fun getWinningTicket(numberOfAttempts: Int = 3): Ticket {
        return getTicket(PromptMessages.GET_WINNING_NUMBERS.message, numberOfAttempts)
    }

    fun getBonusNumber(
        winningTicket: Ticket,
        numberOfAttempts: Int = 3,
    ): Int {
        repeat(numberOfAttempts) {
            val validBonusNumber = tryGetValidBonusNumber(winningTicket)
            if (validBonusNumber != null) return validBonusNumber
        }
        throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
    }

    private fun tryGetValidBonusNumber(winningTicket: Ticket): Int? {
        return try {
            val bonusNumber = getUserInputAsInt(PromptMessages.GET_BONUS_NUMBER.message)
            validateBonusNumber(bonusNumber, winningTicket)
            bonusNumber
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }

    private fun validateBonusNumber(
        bonusNumber: Int,
        winningTicket: Ticket,
    ) {
        require(bonusNumber in Lotto.TICKET_NUMBER_MIN..Lotto.TICKET_NUMBER_MAX) {
            ErrorMessages.BONUS_NUMBER_OUT_OF_RANGE.message
        }
        require(bonusNumber !in winningTicket.getIntNumbers()) {
            ErrorMessages.NUMBER_DUPLICATE.message
        }
    }
}
