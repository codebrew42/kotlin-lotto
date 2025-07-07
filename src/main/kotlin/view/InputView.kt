package view

import model.Lotto

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
            throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message) // throw err & automatically return
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
                .map { it.trim().toInt() }
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(ErrorMessages.INPUT_INVALID_DIGITS.message)
        }
    }

    fun isValidRange(numbers: List<Int>): Boolean {
        return numbers.all { it in 1..45 }
    }

    fun hasNoDuplicates(numbers: List<Int>): Boolean {
        return numbers.toSet().size == numbers.size
    }

    fun hasProperSize(numbers: List<Int>): Boolean {
        return numbers.size == Lotto.TICKET_LENGTH
    }

    fun validateWinningNumbers(winningNumbers: List<Int>) {
        require(isValidRange(winningNumbers)) { ErrorMessages.BONUS_NUMBER_OUT_OF_RANGE.message }
        require(hasNoDuplicates(winningNumbers)) { ErrorMessages.NUMBER_DUPLICATE.message }
        require(hasProperSize(winningNumbers)) { ErrorMessages.INVALID_TICKET_LENGTH.message }
    }

    fun getWinningNumbers(numberOfAttempts: Int = 3): List<Int> {
        if (numberOfAttempts <= 0) {
            throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
        }
        val input = getUserInputAsString(PromptMessages.GET_WINNING_NUMBERS.message)
        return try {
            val winningNumbers = convertWinningNumbers(input)
            validateWinningNumbers(winningNumbers)
            winningNumbers
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getWinningNumbers(numberOfAttempts - 1)
        }
    }

    fun validateBonusNumber(
        bonusNumber: Int,
        winningNumbers: List<Int>,
    ) {
        require(bonusNumber in Lotto.TICKET_NUMBER_MINIMUM..Lotto.TICKET_NUMBER_MAXIMUM) {
            ErrorMessages.BONUS_NUMBER_OUT_OF_RANGE.message
        }
        require(!winningNumbers.contains(bonusNumber)) { ErrorMessages.NUMBER_DUPLICATE.message }
    }

    fun getBonusNumber(
        winningNumbers: List<Int>,
        numberOfAttempts: Int = 3,
    ): Int {
        if (numberOfAttempts == 0) {
            throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
        }
        return try {
            val bonusNumber = getUserInputAsInt(PromptMessages.GET_BONUS_NUMBER.message)
            validateBonusNumber(bonusNumber, winningNumbers)
            bonusNumber
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getBonusNumber(winningNumbers, numberOfAttempts - 1)
        }
    }
}
