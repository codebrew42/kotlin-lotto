package view

import model.Lotto
import model.Ticket
import model.WinningCombination

class InputView {
    fun getUserInputAsString(
        prompt: String,
        numberOfAttempts: Int = 3,
    ): String {
        if (numberOfAttempts <= 0) {
            throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message) // check
        }
        println(prompt)
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
        println(prompt)
        val input = readLine()?.trim()?.toIntOrNull()
        return if (input != null) {
            input
        } else {
            println(ErrorMessages.INPUT_INVALID_DIGITS.message)
            return getUserInputAsInt(prompt, numberOfAttempts - 1)
        }
    }

    fun getPurchaseAmount(): Int {
        return getUserInputAsInt(PromptMessages.GET_PURCHASE_AMOUNT.message)
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

    fun getWinningCombination(): WinningCombination {
        val winningTicket = getWinningTicketWithRetry()
        val bonusNumber = getBonusNumberWithRetry(winningTicket)
        return WinningCombination(winningTicket, bonusNumber)
    }

    private fun getWinningTicketWithRetry(numberOfAttempts: Int = 3): Ticket {
        if (numberOfAttempts <= 0) {
            throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
        }
        return try {
            val input = getUserInputAsString(PromptMessages.GET_WINNING_NUMBERS.message)
            val numbers = convertWinningNumbers(input)
            require(numbers.all { it in 1..45 }) { ErrorMessages.BONUS_NUMBER_OUT_OF_RANGE.message }
            require(numbers.toSet().size == numbers.size) { ErrorMessages.NUMBER_DUPLICATE.message }
            require(numbers.size == Lotto.TICKET_LENGTH) { ErrorMessages.INVALID_TICKET_LENGTH.message }
            Ticket(numbers)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getWinningTicketWithRetry(numberOfAttempts - 1)
        }
    }

    private fun getBonusNumberWithRetry(
        winningTicket: Ticket,
        numberOfAttempts: Int = 3,
    ): Int {
        if (numberOfAttempts <= 0) {
            throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
        }
        return try {
            val bonusNumber = getUserInputAsInt(PromptMessages.GET_BONUS_NUMBER.message)
            require(bonusNumber in Lotto.TICKET_NUMBER_MINIMUM..Lotto.TICKET_NUMBER_MAXIMUM) {
                ErrorMessages.BONUS_NUMBER_OUT_OF_RANGE.message
            }
            require(bonusNumber !in winningTicket.numbers) { ErrorMessages.NUMBER_DUPLICATE.message }
            bonusNumber
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getBonusNumberWithRetry(winningTicket, numberOfAttempts - 1)
        }
    }
}
