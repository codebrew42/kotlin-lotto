package view

class InputView {
    private fun readLineOrRetry(prompt: String): String {
        while (true) {
            print(prompt)
            val input = readLine()
            if (input != null) {
                return input
            } else {
                println(RETRY_INPUT_MESSAGE)
            }
        }
    }

    private fun parsePurchaseAmount(input: String): Int? {
        return input.toIntOrNull()
    }

    fun getPurchaseAmount(): Int {
        while (true) {
            val input = readLineOrRetry(GET_PURCHASE_AMOUNT)
            val amount = parsePurchaseAmount(input)
            if (amount != null) {
                return amount
            } else {
                println(INVALID_INPUT_MESSAGE)
            }
        }
    }

    private fun convertWinningNumbers(input: String): List<Int> {
        return try {
            input.split(",")
                .map { it.trim().toInt() }
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(ERROR_INVALID_DIGITS)
        }
    }

    fun isValidRange(numbers: List<Int>): Boolean {
        return numbers.all { it in 1..45 }
    }


    fun hasNoDuplicates(numbers: List<Int>): Boolean {
        return numbers.toSet().size == numbers.size
    }

    fun hasProperSize(numbers: List<Int>): Boolean {
        return numbers.size == 6
    }

    fun validateWinningNumbers(winningNumbers: List<Int>) {
        require(isValidRange(winningNumbers)) { ERROR_BONUS_RANGE }
        require(hasNoDuplicates(winningNumbers)) { ERROR_DUPLICATE }
        require(hasProperSize(winningNumbers)) { ERROR_NUMBERSET_SIZE }
    }

    fun getWinningNumbers(): List<Int> {
        while (true) {
            val input = readLineOrRetry(GET_WINNING_NUMBERS)
            try {
                val winningNumbers = convertWinningNumbers(input)
                validateWinningNumbers(winningNumbers)
                return winningNumbers
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun validateBonusNumber(bonusNumber: Int, winningNumbers: List<Int>) {
        require(bonusNumber in 1..45) { ERROR_BONUS_RANGE }
        require(!winningNumbers.contains(bonusNumber)) { ERROR_DUPLICATE }
    }

    fun getBonusNumber(winningNumbers: List<Int>): Int {
        while (true) {
            val input = readLineOrRetry(GET_BONUS_NUMBERS)
            try {
                val bonusNumber = input.toInt()
                validateBonusNumber(bonusNumber, winningNumbers)
                return bonusNumber
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    companion object {
        private const val GET_PURCHASE_AMOUNT = "Please enter the purchase amount.\n"
        private const val GET_WINNING_NUMBERS = "Please enter last week's winning numbers.\n"
        private const val GET_BONUS_NUMBERS = "Please enter last week's bonus number.\n"
        private const val INVALID_INPUT_MESSAGE = "The input is invalid!"
        private const val RETRY_INPUT_MESSAGE = "Enter the input again!"
        private const val ERROR_INVALID_DIGITS = "[ERROR] Winning numbers must be valid digits."
        private const val ERROR_BONUS_RANGE = "[ERROR] Number must be between 1 and 45."
        private const val ERROR_DUPLICATE = "[ERROR] Numbers can't be duplicates."
        private const val ERROR_NUMBERSET_SIZE = "[ERROR] There must be 6 numbers."
    }
}