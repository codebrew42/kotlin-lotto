package view
import model.Lotto

enum class ErrorMessages(val message: String) {
    PROGRAM_RUNNING_ERROR("[ERROR] An error occurred. The program will terminate."),
    INPUT_EMPTY("[ERROR] The input is empty!"),
    INPUT_TOO_MANY_ATTEMPT("[ERROR] You reached maximum number of attempts."),
    INPUT_INVALID_DIGITS("[ERROR] Please enter valid numbers."),
    PURCHASE_AMOUNT_INVALID_UNIT(
        "[ERROR] The purchase amount must be positive and in units of ${Lotto.PURCHASE_AMOUNT_UNIT}.",
    ),
    BONUS_NUMBER_OUT_OF_RANGE(
        "[ERROR] The bonus number must be between ${Lotto.TICKET_NUMBER_MIN} and ${Lotto.TICKET_NUMBER_MAX}.",
    ),
    NUMBER_DUPLICATE("[ERROR] Duplicate numbers are not allowed."),
    INVALID_TICKET_LENGTH("[ERROR] Please enter exactly ${Lotto.TICKET_LENGTH} numbers."),
}
