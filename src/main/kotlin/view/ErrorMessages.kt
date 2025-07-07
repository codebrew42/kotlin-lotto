package view

enum class ErrorMessages(val message: String) {
    INPUT_EMPTY("[ERROR] The input is empty!"),
    INPUT_TOO_MANY_ATTEMPT("[ERROR] You reached maximum number of attempts."),
    INPUT_INVALID_DIGITS("[ERROR] Please enter valid numbers."),

    PURCHASE_AMOUNT_INVALID_UNIT("[ERROR] purchase amount must be divisible by 1000"),
    BONUS_NUMBER_OUT_OF_RANGE("[ERROR] The bonus number must be between 1 and 45."),
    NUMBER_DUPLICATE("[ERROR] Duplicate numbers are not allowed."),

    INVALID_TICKET_LENGTH("[ERROR] Please enter exactly 6 numbers."),
}
