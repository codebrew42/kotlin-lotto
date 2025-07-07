package view

import model.Lotto

class InputView {
    private fun readLineOrRetry(prompt: String): String {
        while (true) {
            print(prompt)
            val input = readLine()
            if (input != null) {
                return input
            } else {
                println(ErrorMessages.RETRY_INPUT.message)
            }
        }
    }

    private fun parsePurchaseAmount(input: String): Int? {
        return input.toIntOrNull()
    }

    fun getPurchaseAmount(): Int {
        while (true) {
            val input = readLineOrRetry(PromptMessages.GET_PURCHASE_AMOUNT.message)
            val amount = parsePurchaseAmount(input)
            if (amount != null) {
                return amount
            } else {
                println(ErrorMessages.INVALID_INPUT.message)
            }
        }
    }

    private fun convertWinningNumbers(input: String): List<Int> {
        return try {
            input.split(",")
                .map { it.trim().toInt() }
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(ErrorMessages.INVALID_DIGITS.message)
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
        require(isValidRange(winningNumbers)) { ErrorMessages.INVALID_BONUS_RANGE.message }
        require(hasNoDuplicates(winningNumbers)) { ErrorMessages.DUPLICATE_NUMBER.message }
        require(hasProperSize(winningNumbers)) { ErrorMessages.INVALID_TICKET_LENGTH.message }
    }

    fun getWinningNumbers(): List<Int> {
        while (true) {
            val input = readLineOrRetry(PromptMessages.GET_WINNING_NUMBERS.message)
            try {
                val winningNumbers = convertWinningNumbers(input)
                validateWinningNumbers(winningNumbers)
                return winningNumbers
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun validateBonusNumber(
        bonusNumber: Int,
        winningNumbers: List<Int>,
    ) {
        require(bonusNumber in Lotto.TICKET_NUMBER_MINIMUM..Lotto.TICKET_NUMBER_MAXIMUM) { ErrorMessages.INVALID_BONUS_RANGE.message }
        require(!winningNumbers.contains(bonusNumber)) { ErrorMessages.DUPLICATE_NUMBER.message }
    }

    fun getBonusNumber(winningNumbers: List<Int>): Int {
        while (true) {
            val input = readLineOrRetry(PromptMessages.GET_BONUS_NUMBER.message)
            try {
                val bonusNumber = input.toInt()
                validateBonusNumber(bonusNumber, winningNumbers)
                return bonusNumber
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}
