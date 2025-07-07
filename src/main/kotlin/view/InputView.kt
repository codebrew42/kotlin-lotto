package view

import model.Lotto
import model.Ticket

class InputView {
    fun getUserInputAsString(
        prompt: String,
        numberOfAttempts: Int = 3,
    ): String {
        if (numberOfAttempts <= 0) {
            throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message) // check
        }
        if (prompt.isNotBlank()) {
            println(prompt)
        }
        val input = readLine()?.trim()
        return if (!input.isNullOrBlank()) { // need Blank? i have .trim()
            input
        } else {
            println(ErrorMessages.INPUT_EMPTY.message)
            return getUserInputAsString(prompt, numberOfAttempts - 1)
        }
    }

    fun getUserInputAsInt(
        prompt: String,
        numberOfAttempts: Int = 3,
    ): Int {
        if (numberOfAttempts <= 0) {
            throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message) // automatically return
        }
        // ver1: new
        val inputAsString = getUserInputAsString(prompt)
        val input = inputAsString.toIntOrNull()
        // ver2: original
        // val input = readLine()?.trim()?.toIntOrNull()
        return if (input != null) {
            input
        } else {
            println(ErrorMessages.INPUT_INVALID_DIGITS.message)
            return getUserInputAsInt(prompt, numberOfAttempts - 1)
        }
    }

    fun getUserInputAsListOfInt(
        prompt: String,
        numberOfAttempts: Int = 3,
        delimiter: String = ",",
    ): List<Int> {
        if (numberOfAttempts <= 0) {
            throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
        }
        val inputAsString = getUserInputAsString(prompt)
        val inputList =
            inputAsString.split(delimiter)
                .map { it.trim() }
                .mapNotNull { it.toIntOrNull() }
        return if (inputList.isNotEmpty() && inputList.all { it.toString() in inputAsString.split(delimiter).map { s -> s.trim() } }) {
            inputList
        } else {
            println(ErrorMessages.INPUT_INVALID_DIGITS.message)
            return getUserInputAsListOfInt(prompt, numberOfAttempts - 1, delimiter)
        }
    }

    fun getPurchaseAmount(): Int {
        return getUserInputAsInt(PromptMessages.GET_PURCHASE_AMOUNT.message)
    }

    fun getNumberOfManualTickets(): Int {
        return getUserInputAsInt(PromptMessages.GET_NUMBER_OF_MANUAL_TICKETS.message)
    }

    private fun convertWinningNumbers(input: String): List<Int> {
        return try {
            input.split(",")
                .map(String::trim)
                .map(String::toInt)
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(ErrorMessages.INPUT_INVALID_DIGITS.message)
        }
    }

    private fun getTicket(
        prompt: String,
        numberOfAttempts: Int = 3,
    ): Ticket {
        if (numberOfAttempts <= 0) {
            throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
        }
        return try {
            val input = getUserInputAsString(prompt)
            val numbers = convertWinningNumbers(input)
            require(numbers.all { it in 1..45 }) { ErrorMessages.BONUS_NUMBER_OUT_OF_RANGE.message }
            require(numbers.toSet().size == numbers.size) { ErrorMessages.NUMBER_DUPLICATE.message }
            require(numbers.size == Lotto.TICKET_LENGTH) { ErrorMessages.INVALID_TICKET_LENGTH.message }
            Ticket(numbers)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getWinningTicket(numberOfAttempts - 1)
        }
    }

    fun getWinningTicket(numberOfAttempts: Int = 3): Ticket {
        return getTicket(PromptMessages.GET_WINNING_NUMBERS.message, numberOfAttempts)
    }

    fun getManualTicket(numberOfAttempts: Int = 3): Ticket {
        return getTicket(PromptMessages.GET_MANUAL_TICKETS_NUMBERS.message, numberOfAttempts)
    }

    fun getBonusNumber(
        winningTicket: Ticket,
        numberOfAttempts: Int = 3,
    ): Int {
        if (numberOfAttempts <= 0) {
            throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
        }
        return try {
            val bonusNumber = getUserInputAsInt(PromptMessages.GET_BONUS_NUMBER.message)
            require(bonusNumber in Lotto.TICKET_NUMBER_MIN..Lotto.TICKET_NUMBER_MAX) {
                ErrorMessages.BONUS_NUMBER_OUT_OF_RANGE.message
            }
            require(bonusNumber !in winningTicket.numbers) { ErrorMessages.NUMBER_DUPLICATE.message }
            bonusNumber
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getBonusNumber(winningTicket, numberOfAttempts - 1)
        }
    }
}
