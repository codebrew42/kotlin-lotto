package model
import view.ErrorMessages

class Lotto(val purchaseAmount: Int) {
    val numberOfTickets: Int
    val tickets = mutableListOf<Ticket>()

    init {
        require(purchaseAmount % PURCHASE_AMOUNT_UNIT == 0) { ErrorMessages.PURCHASE_AMOUNT_INVALID_UNIT }
        numberOfTickets = purchaseAmount / PURCHASE_AMOUNT_UNIT
    }

    fun generateTickets() {
        for (i in 0 until numberOfTickets) {
            tickets.add(createTicket())
        }
    }

    private fun createTicket(): Ticket {
        val numbers = (TICKET_NUMBER_MINIMUM..TICKET_NUMBER_MAXIMUM).shuffled().take(TICKET_LENGTH).sorted()
        return Ticket(numbers)
    }

    companion object {
        const val PURCHASE_AMOUNT_UNIT = 1000
        const val TICKET_LENGTH = 6
        const val TICKET_NUMBER_MINIMUM = 1
        const val TICKET_NUMBER_MAXIMUM = 45
    }
}
