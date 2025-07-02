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
            val input = readLineOrRetry("Enter the purchase amount: ")
            val amount = parsePurchaseAmount(input)
            if (amount != null) {
                return amount
            } else {
                println("The input is invalid!")
            }
        }
    }
}