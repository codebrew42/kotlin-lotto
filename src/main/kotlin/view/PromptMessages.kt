package view

enum class PromptMessages(val message: String) {
    GET_PURCHASE_AMOUNT("Please enter the purchase amount."),
    GET_WINNING_NUMBERS("Please enter last week's winning numbers."),
    GET_BONUS_NUMBER("Please enter last week's bonus number."),
    GET_NUMBER_OF_MANUAL_TICKETS("Enter the number of manual tickets to purchase."),
    GET_MANUAL_TICKETS_NUMBERS("Enter the numbers for manual tickets.")
}
