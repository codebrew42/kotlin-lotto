package view

class InputView {
    // TODO: decide whether we want to re-prompt always or never
    fun readLineOrRetry(prompt: String): String {
        while (true) {
            print(prompt)
            val input = readLine()
            if (input != null) {
                return input
            } else {
                println("Enter the input again!")
            }
        }
    }

    fun parsePurchaseAmount(input: String): Int? {
        return input.toIntOrNull()
    }

    fun getPurchaseAmount(): Int {
        while (true) {
            val input = readLineOrRetry(GET_PURCHASE_AMOUNT)
            val amount = parsePurchaseAmount(input)
            if (amount != null) {
                return amount
            } else {
                println("The input is invalid!")
            }
        }
    }

    fun convertWinningNumbers(input: String): List<Int> {
        return try {
            input.split(",")
                .map { it.trim().toInt() }
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("[ERROR] winning numbers must be valid digits.")
        }
    }

    fun isValidRange(numbers: List<Int>): Boolean{
        return numbers.all { it in 1..45 }
    }


    fun hasNoDuplicates(numbers: List<Int>): Boolean{
        return numbers.toSet().size == numbers.size
    }

    fun hasProperSize(numbers: List<Int>): Boolean{
        return numbers.size == 6
    }

    fun validateWinningNumbers(winningNumbers: List<Int>) {
        require(isValidRange(winningNumbers)) { "[ERROR] Numbers must be between 1 and 45. "}
        require(hasNoDuplicates(winningNumbers)) { "[ERROR] Numbers must be unique." }
        require(hasProperSize(winningNumbers)) { "[ERROR] There must be 6 numbers." }
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
        require ( bonusNumber in 1..45) { "[ERROR] Number must be between 1 and 45. " }
        require (!winningNumbers.contains(bonusNumber)) { "[ERROR] Bonus number can't be contained in winning numbers. " }
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
        const val GET_PURCHASE_AMOUNT = "Please enter the purchase amount."
        const val GET_WINNING_NUMBERS = "Please enter last week’s winning numbers."
        const val GET_BONUS_NUMBERS = "Please enter last week’s bonus number."

    }
}