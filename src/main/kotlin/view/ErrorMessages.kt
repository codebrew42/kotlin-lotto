package view

enum class ErrorMessages(val message: String) {
    INVALID_INPUT("[ERROR] The input is invalid!"),
    RETRY_INPUT("[ERROR] Enter the input again!"),
    INVALID_DIGITS("[ERROR] Please enter only numbers."),
    INVALID_BONUS_RANGE("[ERROR] The bonus number must be between 1 and 45."),
    DUPLICATE_NUMBER("[ERROR] Duplicate numbers are not allowed."),
    INVALID_TICKET_LENGTH("[ERROR] Please enter exactly 6 numbers."),
}
