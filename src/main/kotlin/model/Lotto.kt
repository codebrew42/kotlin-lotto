package model
import view.ErrorMessages

class Lotto(val purchaseAmount: Int) {
    val numberOfTotalTickets: Int
    var numberOfManualTickets: Int = 0
    var numberOfAutomaticTickets: Int = 0
    val tickets = mutableListOf<Ticket>()

    init {
        require(purchaseAmount != 0 && purchaseAmount % PURCHASE_AMOUNT_UNIT == 0) {
            ErrorMessages.PURCHASE_AMOUNT_INVALID_UNIT.message
        }
        numberOfTotalTickets = purchaseAmount / PURCHASE_AMOUNT_UNIT
    }

    fun generateAutomaticTickets() {
        numberOfAutomaticTickets = numberOfTotalTickets - numberOfManualTickets
        for (i in 0 until numberOfAutomaticTickets) {
            tickets.add(createTicket())
        }
    }

    fun createTicketForController(): Ticket {
        return createTicket()
    }

    private fun createTicket(): Ticket {
        val numbers = (TICKET_NUMBER_MIN..TICKET_NUMBER_MAX).shuffled().take(TICKET_LENGTH).sorted()
        return Ticket(numbers)
    }

    companion object {
        const val PURCHASE_AMOUNT_UNIT = 1000
        const val TICKET_LENGTH = 6
        const val TICKET_NUMBER_MIN = 1
        const val TICKET_NUMBER_MAX = 45
    }
}
